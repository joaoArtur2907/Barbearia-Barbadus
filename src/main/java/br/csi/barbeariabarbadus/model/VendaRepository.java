package br.csi.barbeariabarbadus.model;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findByVendedorIdusuario(Integer vendedorIdusuario);
    List<Venda> findByProdutoIdproduto(Integer idproduto);
}
