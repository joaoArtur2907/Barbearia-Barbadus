package br.csi.barbeariabarbadus.service;

import br.csi.barbeariabarbadus.model.Permissao;
import br.csi.barbeariabarbadus.model.PermissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    private final PermissaoRepository repository;

    public PermissaoService(PermissaoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Permissao permissao) {
        this.repository.save(permissao);
    }

    public List<Permissao> listar() {
        return this.repository.findAll();
    }

    public Permissao getPermissao(Integer id) {
        return this.repository.findById(id).orElse(null); // Retorna null se não encontrado
    }

    public void excluir(Integer id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Permissao permissao) {
        Permissao p = this.repository.getReferenceById(permissao.getIdpermissao());
        p.setNome_permissao(permissao.getNome_permissao());
        this.repository.save(p);
    }
}
