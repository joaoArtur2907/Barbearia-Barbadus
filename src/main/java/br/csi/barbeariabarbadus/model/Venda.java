package br.csi.barbeariabarbadus.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa uma venda no sistema")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idvenda;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "vendedor", nullable = false)
    @Schema(description = "Usuário que realizou a venda")
    private Usuario vendedor;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "comprador", nullable = false)
    @Schema(description = "Usuário que comprou o produto")
    private Usuario comprador;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "idproduto", nullable = false)
    @Schema(description = "Produto vendido")
    private Produto produto;

    @NonNull
    @Schema(description = "Valor da venda")
    private Float valor;

    @NonNull
    @Schema(description = "Quantidade de itens vendidos")
    private Integer quantia;
}
