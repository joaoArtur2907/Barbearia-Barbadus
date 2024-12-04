package br.csi.barbeariabarbadus.service;

import br.csi.barbeariabarbadus.model.DadosUsuario;
import br.csi.barbeariabarbadus.model.Usuario;
import br.csi.barbeariabarbadus.model.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository){this.repository = repository;}
    public void salvar(Usuario usuario){
        // criptografa senha
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        this.repository.save(usuario);}
    public List<Usuario> listar(){return this.repository.findAll();}

    public Usuario getUsuario(Integer id){return this.repository.findById(id).get();}

    public void excluir(Integer id){this.repository.deleteById(id);}


    public void atualizar(Usuario usuario){
        Usuario u = this.repository.getReferenceById(usuario.getIdusuario());
        u.setNome(usuario.getNome());
        u.setEmail(usuario.getEmail());
        u.setSenha(usuario.getSenha());
        u.setAtivo(usuario.isAtivo());
        u.setPermissao(usuario.getPermissao());
        this.repository.save(u);
    }

    public void atualizarUUID(Usuario usuario){
        Usuario a = this.repository.findUsuarioByUuid(usuario.getUuid());
        a.setNome(usuario.getNome());
        a.setEmail(usuario.getEmail());
        a.setSenha(usuario.getSenha());
        a.setAtivo(usuario.isAtivo());
        a.setPermissao(usuario.getPermissao());
        this.repository.save(a);
    }

    public Usuario getUsuarioUUID(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findUsuarioByUuid(uuidformatado);
    }

    public void deletarUUID(String uuid){
        this.repository.deleteUsuarioByUuid(UUID.fromString(uuid));
    }


    public DadosUsuario findUsuario(int id){
        Usuario usuario = this.repository.getReferenceById(id);
        return new DadosUsuario(usuario);
    }

    public List<DadosUsuario> findAllUsuarios(){
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }


}
