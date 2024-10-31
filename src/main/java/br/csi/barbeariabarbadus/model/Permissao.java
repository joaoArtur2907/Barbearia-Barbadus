package br.csi.barbeariabarbadus.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpermissao;

    @NonNull
    private String nome_permissao;

}
