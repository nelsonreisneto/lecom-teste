package br.com.lecom.services;

import br.com.lecom.domains.dtos.TarefaDto;
import br.com.lecom.domains.entities.Tarefa;
import br.com.lecom.domains.repositories.TarefaRepository;
import br.com.lecom.resources.exceptions.TarefaNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public void adicionar(TarefaDto tarefaDto) {
        log.info("Gravando um serviço na base de dados.");
        if (Objects.isNull(tarefaDto.getNome())) {
            log.error("O serviço não pode ser nulo");
            throw new TarefaNaoEncontradoException("erro!");
        }
        Tarefa tarefa = tarefaDto.toEntity(tarefaDto);
        tarefaRepository.saveAndFlush(tarefa);
    }

    public void apagar(Long id) {
        log.info("Apagando um serviço na base de dados.");
        tarefaRepository.deleteById(id);
    }

    public void alterar(TarefaDto tarefaDto) {
        log.info("Alterando o serviço {} da base", tarefaDto.getNome());
        if (Objects.isNull(tarefaDto.getNome())) {
            log.error("O serviço não pode ser nulo");
            throw new TarefaNaoEncontradoException("erro!");
        }
        Tarefa tarefa = tarefaDto.toEntity(tarefaDto);
        tarefaRepository.saveAndFlush(tarefa);
    }

    public Tarefa buscar(Long id) {
        log.info("Buscando serviço pelo ID {}", id);
        return tarefaRepository.findById(id).orElse(Tarefa.builder().build());
    }

    public List<Tarefa> buscarTodos() {
        log.info("Buscando todos os registros.");
        return tarefaRepository.findAll();
    }

}
