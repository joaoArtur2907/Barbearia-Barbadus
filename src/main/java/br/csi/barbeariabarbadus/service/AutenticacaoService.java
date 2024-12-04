package br.csi.barbeariabarbadus.service;

import br.csi.barbeariabarbadus.model.Usuario;
import br.csi.barbeariabarbadus.model.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository repository;

    public AutenticacaoService(UsuarioRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = this.repository.findByEmail(email);
        if(usuario == null){
            throw new UsernameNotFoundException("Email ou senha incorretos");
        }else {
            UserDetails user = User.withUsername(usuario.getEmail()).password(usuario.getSenha())
                    .authorities(usuario.getPermissao().getNome_permissao()).build();
            return user;
        }
    }

}
