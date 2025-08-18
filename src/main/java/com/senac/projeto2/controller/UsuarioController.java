package com.senac.projeto2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
@Tag(name="Usuario", description="API para gerenciamento dos usuarios do sistema")
public class UsuarioController {

    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios do sistema")
    public String listar(){
        return "Listando com sucesso";
    }

    @GetMapping("/listarPorIdUsuario/{idUsuario}")
    @Operation(summary = "Listar usuarios do sistema pelo id do usuário")
    public String listarPorIdUsuario(@PathVariable("idUsuario") Integer idUsuario){
        return "Listando um usuario por id "+ idUsuario +" com sucesso";
    }

    @PostMapping("/criar")
    public String criar(){
        return "Usuario Criado com sucesso!";
    }

    @PutMapping("/atualizar")
    public String atualizar(){
        return  "Usuario atualizado com sucesso!";
    }

    @DeleteMapping("/apagar")
    public String apagar(){
        return "Usuario apagado com sucesso!";
    }
}
