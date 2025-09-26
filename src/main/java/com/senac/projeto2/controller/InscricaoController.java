package com.senac.projeto2.controller;

import com.senac.projeto2.dto.request.InscricaoDTORequest;
import com.senac.projeto2.dto.request.InscricaoDTORequestUpdate;
import com.senac.projeto2.dto.response.InscricaoDTOResponse;
import com.senac.projeto2.entity.Inscricao;
import com.senac.projeto2.service.InscricaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inscricao")
@Tag(name="Inscricao", description="API para gerenciamento das inscrições no sistema")
public class InscricaoController {

    private final InscricaoService inscricaoService;
    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Inscricao>> listar(){
        return ResponseEntity.ok(inscricaoService.listarInscricoes());
    }
    @GetMapping("/listarPorIdInscricao/{idInscricao}")
    public ResponseEntity<Inscricao> listarPorIdInscricao(@PathVariable ("idInscricao") Integer idInscricao){
        Inscricao inscricao = inscricaoService.listaInscricaoPorId(idInscricao);
        if(inscricao==null){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(inscricao);
        }
    }
    @PostMapping("/criar")
    public ResponseEntity<InscricaoDTOResponse> criar(@Valid @RequestBody  InscricaoDTORequest inscricaoDTORequest){
        return ResponseEntity.ok(inscricaoService.salvar(inscricaoDTORequest));
    }
    @PutMapping("/atualizar/{idInscricao}")
    public ResponseEntity<InscricaoDTOResponse> atualizar(
            @Valid @PathVariable("idInscriçao") Integer idInscricao, @RequestBody InscricaoDTORequestUpdate inscricaoDTORequestUpdate){
        return ResponseEntity.ok(inscricaoService.atualizar(idInscricao, inscricaoDTORequestUpdate));
    }
    @DeleteMapping("/apagar/{idInscricao}")
    public ResponseEntity<InscricaoDTOResponse> apagar(@PathVariable("idInscricao") Integer idInscricao){
        inscricaoService.apagar(idInscricao);
        return ResponseEntity.noContent().build();
    }
}
