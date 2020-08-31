package br.com.lecom.services;

import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.entities.Historico;
import br.com.lecom.domains.entities.Tarefa;
import br.com.lecom.domains.enums.TipoDocumentos;
import br.com.lecom.domains.repositories.ClienteRepository;
import br.com.lecom.domains.repositories.HistoricoRepository;
import br.com.lecom.domains.repositories.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class HistoricoServiceTest {

    private Cliente cliente;
    private Tarefa tarefa;
    private Historico historico;

    @MockBean
    private HistoricoRepository historicoRepository;

    @MockBean
    private ClienteRepository clientereRepository;

    @MockBean
    private TarefaRepository tarefaRepository;

    @Autowired
    private HistoricoService historicoService;

    @BeforeEach
    void initEach() {
        tarefa = Tarefa.builder()
                .nome("Reis")
                .valor(1234.00)
                .build();

        cliente = Cliente.builder()
                .nome("Nelson")
                .clienteOuro(true)
                .clientePrata(false)
                .documento("00000000000")
                .tipoDocumentos(TipoDocumentos.CPF)
                .build();

        historico = Historico.builder()
                .cliente(cliente)
                .dataFim(LocalDateTime.now())
                .dataInicio(LocalDateTime.now())
                .nomeCliente("")
                .nomeServico("")
                .tarefa(tarefa)
                .build();
    }

    @Test
    void contextLoads() {
        assertThat(historicoRepository).isNotNull();
        assertThat(clientereRepository).isNotNull();
        assertThat(tarefaRepository).isNotNull();
        assertThat(historicoService).isNotNull();
    }

    @Test
    void consigoCriarOrdemDeSevico() {
        when(clientereRepository.findFirstByNome(anyString())).thenReturn(cliente);
        when(tarefaRepository.findFirstByNome(anyString())).thenReturn(tarefa);

        assertDoesNotThrow(() -> historicoService.criarOrdemServico("", "", LocalDateTime.now(), LocalDateTime.now()));
    }

    @Test
    void consigoExibirHistorico() {
        when(clientereRepository.findFirstByNome(anyString())).thenReturn(cliente);
        when(historicoRepository.findAllByCliente(any())).thenReturn(Collections.singletonList(historico));

        assertDoesNotThrow(() -> historicoService.exibirHistorico(""));
    }

}