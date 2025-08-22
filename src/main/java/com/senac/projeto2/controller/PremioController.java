package com.senac.projeto2.controller;

import com.senac.projeto2.entity.Premio;
import com.senac.projeto2.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/premio")
@Tag(name="Premio", description="API para gerenciamento dos premios do sistema")
public class PremioController {
    private final PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios do sistema")
    public ResponseEntity<List<Premio>> listar(){
        return ResponseEntity.ok(premioService.listarPremios());
    }

    @GetMapping("/listarPorIdpremio/{idPremio}")
    @Operation(summary = "Listar premios do sistema pelo id do premio")
    public ResponseEntity<Premio> listarPorIdpremio(@PathVariable("idPremio") Integer idPremio){
        Premio premio = premioService.listarPremioPorId(idPremio);
        if (premio == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(premio);
        }
    }

}
