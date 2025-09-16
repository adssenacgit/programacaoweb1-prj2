package com.senac.projeto2.service;

import com.senac.projeto2.dto.request.CategoriaDTORequest;
import com.senac.projeto2.dto.request.CategoriaDTORequestUpdate;
import com.senac.projeto2.dto.response.CategoriaDTOResponse;
import com.senac.projeto2.entity.Categoria;
import com.senac.projeto2.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    public List<Categoria> listarCategorias(){
        return this.categoriaRepository.buscarRegistros();
    }

    public Categoria listarCategoriaPorId(int idCategoria){
        return this.categoriaRepository.buscarRegistroPorId(idCategoria);
    }

    public CategoriaDTOResponse salvar(CategoriaDTORequest categoriaDTORequest) {
        Categoria categoria = modelMapper.map(categoriaDTORequest, Categoria.class);
        categoria.setStatus(1);
        Categoria save = this.categoriaRepository.save(categoria);
        return modelMapper.map(save, CategoriaDTOResponse.class);
    }

    public CategoriaDTOResponse editar(Integer id, CategoriaDTORequestUpdate categoriaDTORequestUpdate){
        Categoria categoria = this.listarCategoriaPorId(id);
        if(categoria!=null){
            modelMapper.map(categoriaDTORequestUpdate, categoria);
            Categoria update = this.categoriaRepository.save(categoria);
            return modelMapper.map(update, CategoriaDTOResponse.class);
        }else{
            return null;
        }
    }
    public void apagar(Integer id){
        this.categoriaRepository.apagadoLogico(id);
    }
}
