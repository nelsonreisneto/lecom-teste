package br.com.lecom.domains.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(exclude = {"tarefa", "cliente"}, callSuper = false)
@Table(name = "historico", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Historico extends EntidadeBase {

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "nome_servico")
    private String nomeServico;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @OneToOne
    private Cliente cliente;

    @OneToOne
    private Tarefa tarefa;

}
