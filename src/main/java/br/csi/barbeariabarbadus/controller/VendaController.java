package br.csi.barbeariabarbadus.controller;


import br.csi.barbeariabarbadus.model.Venda;
import br.csi.barbeariabarbadus.service.VendaService;
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
@RequestMapping("/venda")
@Tag(name = "Venda", description = "Path relacionado a venda de produtos")
public class VendaController {

    private VendaService service;

    public VendaController(VendaService service){
        this.service = service;
    }

    /* http://localhost:8080/barbearia-barbadus/venda/listar */
    @GetMapping("/listar")
    public List<Venda> listar(){
        return this.service.listar();
    }

    /* http://localhost:8080/barbearia-barbadus/venda/1 */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar venda por ID", description = "Retorna uma venda correspondente ao ID escolhido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Venda encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Venda.class))),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada", content = @Content)
    })
    public Venda venda(@Parameter(description = "ID da venda a ser buscado") @PathVariable Integer id){
        return this.service.getVenda(id);
    }


    /* http://localhost:8080/barbearia-barbadus/venda/print-json */
    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }



    /* http://localhost:8080/barbearia-barbadus/venda */
    @PostMapping()
    @Operation(summary = "Criar uma nova venda", description = "Cria uma nova venda e adiciona a lista")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201", description = "venda criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Venda.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos fornecidos", content = @Content)

    })
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Venda venda, UriComponentsBuilder uriBuilder) {
        this.service.salvar(venda);
        URI uri = uriBuilder.path("/venda/{id}").buildAndExpand(venda.getIdvenda()).toUri();
        return ResponseEntity.created(uri).body(venda);
    }


    /* http://localhost:8080/barbearia-barbadus/venda */
    @PutMapping
    public ResponseEntity atualizar(@RequestBody Venda venda){
        this.service.atualizar(venda);
        return ResponseEntity.ok(venda);
    }


    /*  http://localhost:8080/barbearia-barbadus/venda/1 */
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
