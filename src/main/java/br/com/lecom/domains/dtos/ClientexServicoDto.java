package br.com.lecom.domains.dtos;

import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.entities.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientexServicoDto {

    private Cliente cliente;
    private List<Tarefa> tarefa;
    private Double valorFinal;
}
