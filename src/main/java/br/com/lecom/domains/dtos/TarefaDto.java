package br.com.lecom.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDto {

    private String nome;
    private Double valor;
    private Double valorDesconto;
    private Double valorEspecial;
}
