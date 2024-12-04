package br.csi.barbeariabarbadus.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findUsuarioByUuid(UUID uuid);
    public void deleteUsuarioByUuid(UUID uuid);

    public Usuario findByEmail(String email);

}
