package br.com.lecom.services;

import br.com.lecom.domains.dtos.ClienteDto;
import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.enums.TipoDocumentos;
import br.com.lecom.domains.repositories.ClienteRepository;
import br.com.lecom.resources.exceptions.ClienteNaoEncontradoException;
import br.com.lecom.resources.exceptions.DocumentoNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private static boolean cpf(String cpf) {
        char dig10;
        char dig11;
        int sm;
        int i;
        int r;
        int num;
        int peso;

        try {
            if (cpf.length() != 11) {
                return false;
            }
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private static boolean cnpj(String cnpj) {
        char dig13;
        char dig14;
        int sm;
        int i;
        int r;
        int num;
        int peso;

        try {
            if (cnpj.length() != 14) {
                return false;
            }
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public void adicionar(ClienteDto clienteDto) {
        log.info("Gravando um cliente na base de dados.");
        validaDocumento(clienteDto);
    }

    public void apagar(Long id) {
        log.info("Apagando um cliente na base de dados.");
        clienteRepository.deleteById(id);
    }

    public void alterar(ClienteDto clienteDto) {
        log.info("Alterando o cliente {} da base", clienteDto.getNome());
        validaDocumento(clienteDto);
    }

    public Cliente buscar(Long id) {
        log.info("Buscando cliente pelo ID {}", id);
        return clienteRepository.findById(id).orElse(Cliente.builder().build());
    }

    public List<Cliente> buscarTodos() {
        log.info("Buscando todos os registros.");
        return clienteRepository.findAll();
    }

    private void validaDocumento(ClienteDto clienteDto) {
        if (Objects.isNull(clienteDto)) {
            log.error("O cliente não pode ser nulo");
            throw new ClienteNaoEncontradoException("Erro! Cliente não encontrado para adicionar no banco de dados");
        }
        Cliente cliente = clienteDto.toEntity(clienteDto);

        if (cliente.getTipoDocumentos().getTipo().equals(TipoDocumentos.CPF.getTipo()) && !cpf(cliente.getDocumento())) {
            log.error("Documento CPF não foi encontrado ou não existe. Nome do cliente : {}", clienteDto.getNome());
            throw new DocumentoNaoEncontradoException("Documento CPF não foi encontrado ou não existe.");
        } else if (cliente.getTipoDocumentos().getTipo().equals(TipoDocumentos.CNPJ.getTipo()) && !cnpj(cliente.getDocumento())) {
            log.error("Documento CNPJ não foi encontrado ou não existe. Nome do cliente : {}", clienteDto.getNome());
            throw new DocumentoNaoEncontradoException("Documento CNPJ não foi encontrado ou não existe.");
        }

        clienteRepository.saveAndFlush(cliente);
    }
}
