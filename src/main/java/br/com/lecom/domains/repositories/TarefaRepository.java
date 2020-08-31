package br.com.lecom.domains.repositories;

import br.com.lecom.domains.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Tarefa findFirstByNome(String nome);
}
