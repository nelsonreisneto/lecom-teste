package br.com.lecom.domains.repositories;

import br.com.lecom.domains.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
