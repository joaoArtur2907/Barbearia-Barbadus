package br.csi.barbeariabarbadus;

import br.csi.barbeariabarbadus.model.Permissao;
import br.csi.barbeariabarbadus.model.PermissaoRepository;
import br.csi.barbeariabarbadus.model.Usuario;
import br.csi.barbeariabarbadus.model.UsuarioRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@OpenAPIDefinition(
        info = @Info(
                title = "API Barbearia",
                version = "1.0",
                description = "Documentação da API Barbearia",
                contact = @Contact(name = "Suporte", email = "joao.olszeweski@gmail.com")

        )
)


@SpringBootApplication
public class BarbeariaBarbadusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarbeariaBarbadusApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner demo(UsuarioRepository usuarioRepositoryrepository, PermissaoRepository permissaoRepository){
//        return (args) ->{
//
//
//        Permissao pe = new Permissao("admin");
//        permissaoRepository.save(pe);
//
//        usuarioRepositoryrepository.save(new Usuario("marcos", "marcos@email", "12345", true, pe));
//
//        };
//    }

}
