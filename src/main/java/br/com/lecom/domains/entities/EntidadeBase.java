package br.com.lecom.domains.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
abstract class EntidadeBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "criado", nullable = false)
    private LocalDateTime criado;

    @Column(name = "alterado", nullable = false)
    private LocalDateTime alterado;

    @PrePersist
    protected void onCreate() {
        this.alterado = this.criado = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.alterado = LocalDateTime.now();
    }

}
