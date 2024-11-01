package br.csi.barbeariabarbadus.controller;

import br.csi.barbeariabarbadus.model.Produto;
import br.csi.barbeariabarbadus.service.ProdutoService;
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
@RequestMapping("/produto")
@Tag(name = "Produtos", description = "Path relacionado a operações de produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    /* http://localhost:8080/barbearia-barbadus/produto/listar */
    @GetMapping("/listar")
    public List<Produto> listar() {
        return this.service.listar();
    }

    /* http://localhost:8080/barbearia-barbadus/produto/1 */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    public Produto produto(@Parameter(description = "ID do produto a ser buscado") @PathVariable Integer id) {
        return this.service.getProduto(id);
    }

    /* http://localhost:8080/barbearia-barbadus/produto/print-json */
    @PostMapping("/print-json")
    public void printJson(@RequestBody String json) {
        System.out.println(json);
    }

    /* http://localhost:8080/barbearia-barbadus/produto */
    @PostMapping()
    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto e o adiciona ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @Transactional
    public ResponseEntity<Produto> salvar(@RequestBody @Valid Produto produto, UriComponentsBuilder uriBuilder) {
        this.service.salvar(produto);
        URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getIdproduto()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    /* http://localhost:8080/barbearia-barbadus/produto */
    @PutMapping
    public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
        this.service.atualizar(produto);
        return ResponseEntity.ok(produto);
    }

    /* http://localhost:8080/barbearia-barbadus/produto/1 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
