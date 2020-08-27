package br.com.lecom.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum TipoDocumentos {

    CPF(1),
    CNPJ(2);

    @Getter
    private Integer tipo;

}
