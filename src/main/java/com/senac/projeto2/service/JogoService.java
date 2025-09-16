package com.senac.projeto2.service;

import com.senac.projeto2.dto.request.JogoDTORequest;
import com.senac.projeto2.dto.request.UsuarioDtoRequest;
import com.senac.projeto2.dto.response.JogoDTOResponse;
import com.senac.projeto2.dto.response.UsuarioDtoResponse;
import com.senac.projeto2.entity.Jogo;
import com.senac.projeto2.entity.Usuario;
import com.senac.projeto2.repository.CategoriaRepository;
import com.senac.projeto2.repository.JogoRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {
    @Autowired
    private ModelMapper modelMapper;
    private final JogoRepository jogoRepository;
    private final CategoriaRepository categoriaRepository;

    public JogoService(JogoRepository jogoRepository,
                       CategoriaRepository categoriaRepository) {
        this.jogoRepository = jogoRepository;
        this.categoriaRepository = categoriaRepository;
    }
    public List<Jogo> listarJogos(){
        return this.jogoRepository.listarJogos();
    }
    public Jogo listarJogoPorId(int idJogo){
        return this.jogoRepository.obterJogoPorId(idJogo);
    }
    public JogoDTOResponse salvar(JogoDTORequest jogoDTORequest) {
        Jogo jogo = new Jogo();
        jogo.setNome(jogoDTORequest.getNome());
        jogo.setStatus(1);
        jogo.setCategoria(categoriaRepository.buscarRegistroPorId(jogoDTORequest.getCategoriaId()));
        Jogo jogoSave = this.jogoRepository.save(jogo);
        return modelMapper.map(jogoSave, JogoDTOResponse.class);
    }

    public JogoDTOResponse atualizar(@Valid Integer idJogo, JogoDTORequest jogoDTORequest) {
        Jogo jogo = this.listarJogoPorId(idJogo);
        if(jogo!= null){
            jogo.setNome(jogoDTORequest.getNome());
            jogo.setStatus(jogoDTORequest.getStatus());
            jogo.setCategoria(categoriaRepository.buscarRegistroPorId(jogoDTORequest.getCategoriaId()));
            Jogo jogoTemp = this.jogoRepository.save(jogo);
            return modelMapper.map(jogoTemp, JogoDTOResponse.class);
        }else{
            return null;
        }
    }

    public void apagar(Integer idJogo) {
        this.jogoRepository.apagadoLogico(idJogo);
    }
}
