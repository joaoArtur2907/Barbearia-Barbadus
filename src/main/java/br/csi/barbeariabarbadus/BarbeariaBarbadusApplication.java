package br.csi.barbeariabarbadus;

import br.csi.barbeariabarbadus.model.Permissao;
import br.csi.barbeariabarbadus.model.PermissaoRepository;
import br.csi.barbeariabarbadus.model.Usuario;
import br.csi.barbeariabarbadus.model.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BarbeariaBarbadusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarbeariaBarbadusApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(UsuarioRepository usuarioRepositoryrepository, PermissaoRepository permissaoRepository){
        return (args) ->{


        Permissao pe = new Permissao("admin");
        permissaoRepository.save(pe);

        usuarioRepositoryrepository.save(new Usuario("marcos", "marcos@email", "12345", true, pe));

        };
    }

}
