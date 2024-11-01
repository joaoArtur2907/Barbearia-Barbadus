package br.csi.barbeariabarbadus.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um produto no sistema")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproduto;

    @NonNull
    @Schema(description = "Nome do produto", example = "Shampoo Dove")
    private String nome_produto;

    @NonNull
    @Schema(description = "Descrição do produto")
    private String descricao;

    @NonNull
    @Schema(description = "Preço do produto")
    private BigDecimal preco;

    @NonNull
    @Schema(description = "Link para a imagem do produto")
    private String img;
}
