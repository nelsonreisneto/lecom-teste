package br.com.lecom.domains.dtos;

import br.com.lecom.domains.entities.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDto {
    private static final long serialVersionUID = 1L;

    private String nome;
    private Double valor;
    private Double valorDesconto;
    private Double valorEspecial;

    public Tarefa toEntity(TarefaDto tarefaDto) {
        return Tarefa.builder()
                .nome(tarefaDto.getNome())
                .valor(tarefaDto.getValor())
                .build();
    }
}
