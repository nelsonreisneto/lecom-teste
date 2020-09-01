package br.com.lecom.services;

import br.com.lecom.domains.dtos.ClienteDto;
import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.enums.TipoDocumentos;
import br.com.lecom.domains.repositories.ClienteRepository;
import br.com.lecom.resources.exceptions.ClienteNaoEncontradoException;
import br.com.lecom.resources.exceptions.DocumentoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServiceTest {

    private ClienteDto clienteDto;
    private Cliente cliente;

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Test
    void contextLoads(){
        assertThat(clienteRepository).isNotNull();
        assertThat(clienteService).isNotNull();
    }

    @BeforeEach
    void initEach() {
        clienteDto = ClienteDto.builder()
                .nome("Nelson")
                .clienteOuro(true)
                .clientePrata(false)
                .documento("00000000000")
                .tipoDocumentos(TipoDocumentos.CPF)
                .build();

        cliente = Cliente.builder()
                .nome("Nelson")
                .clienteOuro(true)
                .clientePrata(false)
                .documento("00000000000")
                .tipoDocumentos(TipoDocumentos.CPF)
                .build();
    }

    @Test
    void clientePodeSerAdicionadoCpf() {
        when(clienteRepository.saveAndFlush(any())).thenReturn(Cliente.builder().build());

        assertDoesNotThrow(() -> clienteService.adicionar(clienteDto));
    }

    @Test
    void clientePodeSerAdicionadoCnpj() {
        clienteDto.setTipoDocumentos(TipoDocumentos.CNPJ);
        clienteDto.setDocumento("00000000000000");
        when(clienteRepository.saveAndFlush(any())).thenReturn(Cliente.builder().build());

        assertDoesNotThrow(() -> clienteService.adicionar(clienteDto));
    }

    @Test
    void clientePodeSerEncontrado() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        assertDoesNotThrow(() -> clienteService.buscar(1L));
    }

    @Test
    void clientePodeSerAlterado() {
        when(clienteRepository.saveAndFlush(any())).thenReturn(Cliente.builder().build());

        assertDoesNotThrow(() -> clienteService.alterar(clienteDto));
    }

    @Test
    void clientePodeSerApagado() {
        doNothing().when(clienteRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> clienteService.apagar(1L));
    }

    @Test
    void capturaExceptionCliente() {
        clienteDto = null;

        assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.adicionar(clienteDto));
    }


    @Test
    void capturaExceptionDocumento() {
        clienteDto.setDocumento("00000");

        assertThrows(DocumentoNaoEncontradoException.class, () -> clienteService.adicionar(clienteDto));
    }

}