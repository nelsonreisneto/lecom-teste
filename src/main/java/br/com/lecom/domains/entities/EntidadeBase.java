package br.com.lecom.domains.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
abstract class EntidadeBase {

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
