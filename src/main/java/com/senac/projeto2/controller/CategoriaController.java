package com.senac.projeto2.controller;

import com.senac.projeto2.dto.request.CategoriaDTORequest;
import com.senac.projeto2.dto.request.CategoriaDTORequestUpdate;
import com.senac.projeto2.dto.response.CategoriaDTOResponse;
import com.senac.projeto2.entity.Categoria;
import com.senac.projeto2.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
@Tag(name="Categoria", description="API para gerenciamento das categorias do sistema")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar categorias do sistema")
    public ResponseEntity<List<Categoria>> listar(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/listarPorIdCategoria/{idCategoria}")
    @Operation(summary = "Listar categorias do sistema pelo id da categoria")
    public ResponseEntity<Categoria> listarPorIdCategoria(@PathVariable("idCategoria") Integer idCategoria){
        Categoria categoria = categoriaService.listarCategoriaPorId(idCategoria);
        if (categoria == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(categoria);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar uma categoria")
    public ResponseEntity<CategoriaDTOResponse> salvarCategoria(@RequestBody CategoriaDTORequest categoriaDTORequest){
        return ResponseEntity.ok(categoriaService.salvar(categoriaDTORequest));
    }

    @PutMapping("/atualizar/{idCategoria}")
    @Operation(summary = "Atualizar uma categoria")
    public ResponseEntity<CategoriaDTOResponse> atualizarCategoria(@PathVariable("idCategoria") Integer idCategoria, @RequestBody CategoriaDTORequestUpdate categoriaDTORequestUpdate){
        return ResponseEntity.ok(categoriaService.editar(idCategoria, categoriaDTORequestUpdate));
    }

    @DeleteMapping("/apagar/{idCategoria}")
    @Operation(summary = "Apagar uma categoria")
    public ResponseEntity<CategoriaDTOResponse> apagarCategoria(@PathVariable("idCategoria") Integer idCategoria){
        categoriaService.apagar(idCategoria);
        return ResponseEntity.noContent().build();
    }
}
