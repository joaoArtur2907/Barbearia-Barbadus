package br.csi.barbeariabarbadus.controller;

import br.csi.barbeariabarbadus.model.Permissao;
import br.csi.barbeariabarbadus.service.PermissaoService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/permissao")
@Tag(name = "Permissões", description = "Path relacionado a operações de permissões")
public class PermissaoController {

    private final PermissaoService service;

    public PermissaoController(PermissaoService service) {
        this.service = service;
    }

    /* http://localhost:8080/barbearia-barbadus/permissao/listar */
    @GetMapping("/listar")
    public List<Permissao> listar() {
        return this.service.listar();
    }

    /* http://localhost:8080/barbearia-barbadus/permissao/1 */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar permissão por ID", description = "Retorna uma permissão correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissão encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permissao.class))),
            @ApiResponse(responseCode = "404", description = "Permissão não encontrada", content = @Content)
    })
    public Permissao permissao(@Parameter(description = "ID da permissão a ser buscada") @PathVariable Integer id) {
        return this.service.getPermissao(id);
    }

    /* http://localhost:8080/barbearia-barbadus/permissao */
    @PostMapping()
    @Operation(summary = "Criar uma nova permissão", description = "Cria uma nova permissão e a adiciona à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permissão criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permissao.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @Transactional
    public ResponseEntity<Permissao> salvar(@RequestBody @Valid Permissao permissao, UriComponentsBuilder uriBuilder) {
        this.service.salvar(permissao);
        URI uri = uriBuilder.path("/permissao/{id}").buildAndExpand(permissao.getIdpermissao()).toUri();
        return ResponseEntity.created(uri).body(permissao);
    }

    /* http://localhost:8080/barbearia-barbadus/permissao */
    @PutMapping
    public ResponseEntity<Permissao> atualizar(@RequestBody Permissao permissao) {
        this.service.atualizar(permissao);
        return ResponseEntity.ok(permissao);
    }

    /* http://localhost:8080/barbearia-barbadus/permissao/1 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
