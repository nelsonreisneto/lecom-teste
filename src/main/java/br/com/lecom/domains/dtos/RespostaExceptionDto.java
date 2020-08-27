package br.com.lecom.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor

@NoArgsConstructor
public class RespostaExceptionDto {

    private String mensagem;
    private String data;
    private String caminho;

}
