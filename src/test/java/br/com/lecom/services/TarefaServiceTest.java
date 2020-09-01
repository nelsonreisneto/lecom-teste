package br.com.lecom.services;

import br.com.lecom.domains.dtos.TarefaDto;
import br.com.lecom.domains.entities.Tarefa;
import br.com.lecom.domains.repositories.TarefaRepository;
import br.com.lecom.resources.exceptions.ClienteNaoEncontradoException;
import br.com.lecom.resources.exceptions.TarefaNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class TarefaServiceTest {

    private TarefaDto tarefaDto;
    private Tarefa tarefa;

    @MockBean
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaService tarefaService;

    @Test
    void contextLoads() {
        assertThat(tarefaRepository).isNotNull();
        assertThat(tarefaService).isNotNull();
    }

    @BeforeEach
    void initEach() {
        tarefaDto = TarefaDto.builder()
                .nome("Nelson")
                .valor(123.00)
                .valorDesconto(124.00)
                .valorEspecial(125.00)
                .build();

        tarefa = Tarefa.builder()
                .nome("Reis")
                .valor(1234.00)
                .build();
    }

    @Test
    void clientePodeSerAdicionado() {
        when(tarefaRepository.saveAndFlush(any())).thenReturn(Tarefa.builder().build());

        assertDoesNotThrow(() -> tarefaService.adicionar(tarefaDto));
    }

    @Test
    void clientePodeSerEncontrado() {
        when(tarefaRepository.findById(anyLong())).thenReturn(Optional.of(tarefa));

        assertDoesNotThrow(() -> tarefaService.buscar(1L));
    }

    @Test
    void clientePodeSerAlterado() {
        when(tarefaRepository.saveAndFlush(any())).thenReturn(Tarefa.builder().build());

        assertDoesNotThrow(() -> tarefaService.alterar(tarefaDto));
    }

    @Test
    void clientePodeSerApagado() {
        doNothing().when(tarefaRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> tarefaService.apagar(1L));
    }

    @Test
    void capturaExceptionTarefa() {
        tarefaDto = null;

        assertThrows(TarefaNaoEncontradoException.class, () -> tarefaService.adicionar(tarefaDto));
    }

}