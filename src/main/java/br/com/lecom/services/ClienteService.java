package br.com.lecom.services;

import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.repositories.ClienteRepository;
import br.com.lecom.resources.exceptions.ClienteNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void adicionar(Cliente cliente) {
        log.info("Gravando um cliente na base de dados.");
        if (Objects.isNull(cliente.getNome())) {
            log.error("O serviço não pode ser nulo");
            throw new ClienteNaoEncontradoException("erro!");
        }
        clienteRepository.saveAndFlush(cliente);
    }

    public void apagar(Long id) {
        log.info("Apagando um cliente na base de dados.");
        clienteRepository.deleteById(id);
    }

    public void alterar(Cliente cliente) {
        log.info("Alterando o cliente {} da base", cliente.getNome());
        if (Objects.isNull(cliente.getNome())) {
            log.error("O serviço não pode ser nulo");
            throw new ClienteNaoEncontradoException("erro!");
        }
        clienteRepository.saveAndFlush(cliente);
    }

    public Cliente buscar(Long id) {
        log.info("Buscando cliente pelo ID {}", id);
        return clienteRepository.findById(id).orElse(Cliente.builder().build());
    }

    public List<Cliente> buscarTodos() {
        log.info("Buscando todos os registros.");
        return clienteRepository.findAll();
    }
}
