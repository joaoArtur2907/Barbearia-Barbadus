package br.csi.barbeariabarbadus.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @UuidGenerator
    private UUID uuid;

    @NonNull
    private String nome;

    @NonNull
    private String email;

    @NonNull
    private String senha;

    @NonNull
    private boolean ativo;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "idpermissao", nullable = false)
    private Permissao permissao;
}
