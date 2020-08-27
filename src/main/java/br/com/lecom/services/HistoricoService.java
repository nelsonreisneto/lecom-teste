package br.com.lecom.services;

import br.com.lecom.domains.dtos.TarefaDto;
import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.entities.Historico;
import br.com.lecom.domains.entities.Tarefa;
import br.com.lecom.domains.repositories.HistoricoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HistoricoService {

    private final HistoricoRepository historicoRepository;

    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    public void criarOrdemServico(Cliente cliente, Tarefa tarefa, LocalDateTime dtInicio, LocalDateTime dtFim) {
        Historico historico = Historico.builder()
                .cliente(cliente)
                .tarefa(tarefa)
                .nomeCliente(cliente.getNome())
                .nomeServico(tarefa.getNome())
                .dataFim(dtFim)
                .dataInicio(dtInicio)
                .build();

        historicoRepository.saveAndFlush(historico);
    }

    public List<TarefaDto> exibirHistorico(Cliente cliente) {
        log.info("Valores dos serviços do cliente {}", cliente.getNome());
        List<Historico> historicoPorCliente = historicoRepository.findAllByCliente(cliente);
        List<TarefaDto> tarefas = new ArrayList<>();

        historicoPorCliente.forEach(historico -> {
            Double valor = historico.getTarefa().getValor();
            tarefas.add(TarefaDto.builder()
                    .nome(historico.getTarefa().getNome())
                    .valor(valor)
                    .valorEspecial(calculaClienteEspecial(valor, cliente))
                    .valorDesconto(calculaDescontoData(valor, historico.getDataFim()))
                    .build());
        });
        return tarefas;
    }

    private Double calculaClienteEspecial(Double valor, Cliente cliente) {
        if (Boolean.TRUE.equals(cliente.getClienteOuro())) {
            return valor * 0.10;
        } else if (Boolean.TRUE.equals(cliente.getClientePrata())) {
            return valor * 0.05;
        }
        return valor;
    }

    private Double calculaDescontoData(Double valor, LocalDateTime data) {
        if (data.minusDays(10L).isBefore(LocalDateTime.now())) {
            return valor * 0.05;
        }
        return valor;
    }
}
