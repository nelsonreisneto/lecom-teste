package br.com.lecom.services;

import br.com.lecom.domains.dtos.TarefaDto;
import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.entities.Historico;
import br.com.lecom.domains.entities.Tarefa;
import br.com.lecom.domains.repositories.ClienteRepository;
import br.com.lecom.domains.repositories.HistoricoRepository;
import br.com.lecom.domains.repositories.TarefaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final ClienteRepository clienteRepository;
    private final TarefaRepository tarefaRepository;

    public HistoricoService(HistoricoRepository historicoRepository,
                            ClienteRepository clienteRepository,
                            TarefaRepository tarefaRepository) {
        this.historicoRepository = historicoRepository;
        this.clienteRepository = clienteRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public void criarOrdemServico(String nomeCliente, String nomeTarefa, LocalDateTime dtInicio, LocalDateTime dtFim) {
        Cliente cliente = clienteRepository.findFirstByNome(nomeCliente);
        Tarefa tarefa = tarefaRepository.findFirstByNome(nomeTarefa);

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

    public List<TarefaDto> exibirHistorico(String nomeCliente) {
        log.info("Valores dos serviços do cliente {}", nomeCliente);
        Cliente cliente = clienteRepository.findFirstByNome(nomeCliente);
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
            return valor + (valor * 0.10);
        } else if (Boolean.TRUE.equals(cliente.getClientePrata())) {
            return valor + (valor * 0.05);
        }
        return valor;
    }

    private Double calculaDescontoData(Double valor, LocalDateTime dtFim) {
        if (LocalDateTime.now().isBefore(dtFim.minusDays(10L))) {
            return valor + (valor * 0.05);
        }
        return valor;
    }
}
