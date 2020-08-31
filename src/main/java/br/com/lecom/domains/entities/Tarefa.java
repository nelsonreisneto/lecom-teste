package br.com.lecom.domains.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
