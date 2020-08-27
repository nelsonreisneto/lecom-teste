package br.com.lecom.domains.repositories;

import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByCliente(Cliente cliente);
}
