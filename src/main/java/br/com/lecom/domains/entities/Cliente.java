package br.com.lecom.domains.entities;

import br.com.lecom.domains.enums.TipoDocumentos;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cliente", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cliente extends EntidadeBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "nome")
    private String nome;

    @Column(name = "documento")
    private String documento;

    @Column(name = "tipo_documento")
    private TipoDocumentos tipoDocumentos;

    @Column(name = "cliente_ouro")
    private Boolean clienteOuro;

    @Column(name = "cliente_prata")
    private Boolean clientePrata;

}
