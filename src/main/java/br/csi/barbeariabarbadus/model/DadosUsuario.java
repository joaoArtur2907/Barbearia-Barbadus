package br.csi.barbeariabarbadus.model;

public record DadosUsuario(int id, String email,
                           Permissao permissao) {
    public DadosUsuario(Usuario usuario){
        this(usuario.getIdusuario(), usuario.getEmail(),
                usuario.getPermissao());
    }
}
