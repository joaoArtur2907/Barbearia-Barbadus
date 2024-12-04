package br.csi.barbeariabarbadus.controller;

import br.csi.barbeariabarbadus.model.DadosUsuario;
import br.csi.barbeariabarbadus.model.Usuario;
import br.csi.barbeariabarbadus.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Path relacionado a operações de usuarios")
public class UsuarioController {

    private UsuarioService service;
    public UsuarioController(UsuarioService service){
        this.service = service;
    }
    /* http://localhost:8080/barbearia-barbadus/usuario/listar */

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return this.service.listar();
    }

    /* http://localhost:8080/barbearia-barbadus/usuario/1 */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário correspondente ao ID escolhido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public Usuario usuario(@Parameter(description = "ID do usuário a ser buscado") @PathVariable Integer id){
        return this.service.getUsuario(id);
    }


    /* http://localhost:8080/barbearia-barbadus/usuario/print-json */
    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }


    /* http://localhost:8080/barbearia-barbadus/usuario */
    @PostMapping()
    @Operation(summary = "Criar um novo usuario", description = "Cria um novo usuario e o adiciona a lista")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos fornecidos", content = @Content)

    })
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {
        this.service.salvar(usuario);
        URI uri = uriBuilder.path("/usuario/uuid/{uuid}").buildAndExpand(usuario.getUuid()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }


    /* http://localhost:8080/barbearia-barbadus/usuario */
    @PutMapping
    public ResponseEntity atualizar(@RequestBody Usuario usuario){
        this.service.atualizar(usuario);
        return ResponseEntity.ok(usuario);
    }


    /*  http://localhost:8080/barbearia-barbadus/usuario/1 */
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }


   /* http://localhost:8080/barbearia-barbadus/usuario/uuid/edddd513-9ecd-45ac-8387-16256eae723a */
    @GetMapping("/uuid/{uuid}")
    public Usuario usuario(@PathVariable String uuid){
        return this.service.getUsuarioUUID(uuid);
    }

    /*
   http://localhost:8080/barbearia-barbadus/usuario/uuid/edddd513-9ecd-45ac-8387-16256eae723a
   */
    @PutMapping("/uuid")
    public void atualizarUUID(@RequestBody Usuario usuario){
        this.service.atualizarUUID(usuario);
    }




}
