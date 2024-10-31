package br.csi.barbeariabarbadus.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Schema(description = "Entidade que representa um usuário do sistema, tanto admin, funcionario ou cliente")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @UuidGenerator
    private UUID uuid;

    @NonNull
    @Schema(description = "nome do usuário", example = "Carlos")
    private String nome;

    @NonNull
    @Email(message = "Email invalido")
    private String email;

    @NonNull
    private String senha;

    @NonNull
    @Schema(description = "Se o usuário está ativo no sistema atualmente")
    private boolean ativo;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "idpermissao", nullable = false)
    private Permissao permissao;
}
