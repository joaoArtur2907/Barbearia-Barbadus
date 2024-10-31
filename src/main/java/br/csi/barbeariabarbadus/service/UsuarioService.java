package br.csi.barbeariabarbadus.service;

import br.csi.barbeariabarbadus.model.Usuario;
import br.csi.barbeariabarbadus.model.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository){this.repository = repository;}
    public void salvar(Usuario usuario){this.repository.save(usuario);}
    public List<Usuario> listar(){return this.repository.findAll();}
    public Usuario findById(Integer id){
        return this.repository.findById(id).get();
    }

    public void atualizar(Usuario usuario){
        Usuario u = this.repository.getReferenceById(usuario.getIdusuario());
        u.setNome(usuario.getNome());
        u.setEmail(usuario.getEmail());
        u.setSenha(usuario.getSenha());
        u.setAtivo(usuario.isAtivo());
        this.repository.save(u);
    }

    public void excluir(Integer id){this.repository.deleteById(id);}



}
