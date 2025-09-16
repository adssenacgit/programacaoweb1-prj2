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
        return this.usuarioRepository.listarUsuariosAtivos();
    }

    public Usuario listarUsuarioPorId(int idUsuario){
        return this.usuarioRepository.obterUsuarioAtivoPorId(idUsuario);
    }

    public UsuarioDtoResponse salvar(UsuarioDtoRequest usuarioDtoRequest) {
        Usuario usuario = new Usuario();
        modelMapper.map(usuarioDtoRequest, usuario);
        usuario.setStatus(1);

        Usuario usuarioSave = this.usuarioRepository.save(usuario);

        return modelMapper.map(usuarioSave, UsuarioDtoResponse.class);
    }

    public UsuarioDtoResponse atualizar(@Valid Integer idUsuario, UsuarioDtoRequest usuarioDtoRequest) {
        Usuario usuario = this.listarUsuarioPorId(idUsuario);
        if(usuario!= null){
            modelMapper.map(usuarioDtoRequest, usuario);
            Usuario usuarioTemp = this.usuarioRepository.save(usuario);
            return modelMapper.map(usuarioTemp, UsuarioDtoResponse.class);
        }else{
            return null;
        }
    }

    public void apagar(Integer idUsuario) {
        this.usuarioRepository.apagadoLogico(idUsuario);
    }
}
