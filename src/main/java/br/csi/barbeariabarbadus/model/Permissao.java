package br.csi.barbeariabarbadus.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa o nível de permissões que o usuário possui no sistema")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpermissao;

    @NonNull
    @Schema(description = "Nome da permissao", example = "Funcionario")
    private String nome_permissao;

}
