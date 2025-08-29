package com.senac.projeto2.service;

import com.senac.projeto2.dto.request.UsuarioDtoRequest;
import com.senac.projeto2.dto.response.UsuarioDtoResponse;
import com.senac.projeto2.entity.Usuario;
import com.senac.projeto2.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private ModelMapper modelMapper;

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios(){
        return this.usuarioRepository.findAll();
    }

    public Usuario listarUsuarioPorId(int idUsuario){
        return this.usuarioRepository.findById(idUsuario).orElse(null);
    }

    public UsuarioDtoResponse salvar(UsuarioDtoRequest usuarioDtoRequest) {
        //preciso passar os valores do objeto usuarioDto para usuario
        Usuario usuario = modelMapper.map(usuarioDtoRequest, Usuario.class);
        usuario.setStatus(1);

        Usuario usuarioSave = this.usuarioRepository.save(usuario);

        return modelMapper.map(usuarioSave, UsuarioDtoResponse.class);
    }
}
