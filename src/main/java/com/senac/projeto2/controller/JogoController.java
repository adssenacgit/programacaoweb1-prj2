package com.senac.projeto2.controller;

import com.senac.projeto2.dto.request.JogoDTORequest;
import com.senac.projeto2.dto.request.UsuarioDtoRequest;
import com.senac.projeto2.dto.response.JogoDTOResponse;
import com.senac.projeto2.dto.response.UsuarioDtoResponse;
import com.senac.projeto2.entity.Jogo;
import com.senac.projeto2.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jogo")
@Tag(name="Jogo", description="API para gerenciamento dos jogos do sistema")
public class JogoController {
    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar jogod do sistema")
    public ResponseEntity<List<Jogo>> listar(){
        return ResponseEntity.ok(jogoService.listarJogos());
    }

    @GetMapping("/listarPorIdJogo/{idJogo}")
    @Operation(summary = "Listar categorias do sistema pelo id do jogo")
    public ResponseEntity<Jogo> listarPorIdJogo(@PathVariable("idJogo") Integer idJogo){
        Jogo jogo = jogoService.listarJogoPorId(idJogo);
        if (jogo == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(jogo);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar um novo usuario")
    public ResponseEntity<JogoDTOResponse> criar(@Valid @RequestBody JogoDTORequest jogoDTORequest){
        return ResponseEntity.ok(jogoService.salvar(jogoDTORequest));
    }

    @PutMapping("/atualizar/{idJogo}")
    @Operation(summary = "Atualizar todos os dados um jogo")
    public ResponseEntity<JogoDTOResponse> atualizar(
            @Valid @PathVariable("idJogo") Integer idJogo,
            @RequestBody JogoDTORequest jogoDTORequest){
        return ResponseEntity.ok(jogoService.atualizar(idJogo, jogoDTORequest));
    }

    @DeleteMapping("/apagar/{idJogo}")
    @Operation(summary = "Apagar jogo pelo idJogo")
    public ResponseEntity<JogoDTOResponse> apagar(@PathVariable("idJogo") Integer idJogo){
        jogoService.apagar(idJogo);
        return ResponseEntity.noContent().build();
    }

}
