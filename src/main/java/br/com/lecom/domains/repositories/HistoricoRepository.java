package br.com.lecom.domains.repositories;

import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.entities.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    List<Historico> findAllByCliente(Cliente cliente);
}
