package br.csi.barbeariabarbadus.service;

import br.csi.barbeariabarbadus.model.Produto;
import br.csi.barbeariabarbadus.model.ProdutoRepository;
import br.csi.barbeariabarbadus.model.Venda;
import br.csi.barbeariabarbadus.model.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Venda venda) {
        this.repository.save(venda);
    }

    public List<Venda> listar() {
        return this.repository.findAll();
    }

    public Venda getVenda(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    public void excluir(Integer id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Venda venda) {
        Venda v = this.repository.getReferenceById(venda.getIdvenda());
        v.setProduto(venda.getProduto());
        v.setComprador(venda.getComprador());
        v.setVendedor(venda.getVendedor());
        v.setQuantia(venda.getQuantia());
        v.setValor(venda.getValor());
        this.repository.save(v);
    }

}
