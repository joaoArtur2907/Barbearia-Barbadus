package br.csi.barbeariabarbadus.service;

import br.csi.barbeariabarbadus.model.Produto;
import br.csi.barbeariabarbadus.model.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Produto produto) {
        this.repository.save(produto);
    }

    public List<Produto> listar() {
        return this.repository.findAll();
    }

    public Produto getProduto(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    public void excluir(Integer id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Produto produto) {
        Produto p = this.repository.getReferenceById(produto.getIdproduto());
        p.setNome_produto(produto.getNome_produto());
        p.setDescricao(produto.getDescricao());
        p.setPreco(produto.getPreco());
        p.setImg(produto.getImg());
        this.repository.save(p);
    }
}
