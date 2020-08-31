package br.com.lecom.domains.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tarefa", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Tarefa extends EntidadeBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private Double valor;

}
