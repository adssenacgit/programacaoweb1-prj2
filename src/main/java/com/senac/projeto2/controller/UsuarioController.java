package com.senac.projeto2.controller;

import com.senac.projeto2.dto.request.LoginUserDto;
import com.senac.projeto2.dto.request.UsuarioDtoRequest;
import com.senac.projeto2.dto.response.RecoveryJwtTokenDto;
import com.senac.projeto2.dto.response.UsuarioDtoResponse;
import com.senac.projeto2.entity.Usuario;
import com.senac.projeto2.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
@Tag(name="Usuario", description="API para gerenciamento dos usuarios do sistema")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios do sistema")
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/listarPorIdUsuario/{idUsuario}")
    @Operation(summary = "Listar usuarios do sistema pelo id do usuário")
    public ResponseEntity<Usuario> listarPorIdUsuario(@PathVariable("idUsuario") Integer idUsuario){
        Usuario usuario = usuarioService.listarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(usuario);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = usuarioService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar um novo usuario")
    public ResponseEntity<UsuarioDtoResponse> criar(@Valid @RequestBody UsuarioDtoRequest usuarioDtoRequest){
        return ResponseEntity.ok(usuarioService.salvar(usuarioDtoRequest));
    }

    @PutMapping("/atualizar/{idUsuario}")
    @Operation(summary = "Atualizar todos os dados um usuario")
    public ResponseEntity<UsuarioDtoResponse> atualizar(
            @Valid @PathVariable("idUsuario") Integer idUsuario,
            @RequestBody UsuarioDtoRequest usuarioDtoRequest){
        return ResponseEntity.ok(usuarioService.atualizar(idUsuario, usuarioDtoRequest));
    }

    @DeleteMapping("/apagar/{idUsuario}")
    @Operation(summary = "Apagar usuario pelo idUsuario")
    public ResponseEntity<UsuarioDtoResponse> apagar(@PathVariable("idUsuario") Integer idUsuario){
        usuarioService.apagar(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
