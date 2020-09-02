package br.com.lecom.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientexServicoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeCliente;
    private String nomeTarefa;
    private LocalDateTime dtInicio;
    private LocalDateTime dtFim;
}
