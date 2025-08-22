package com.senac.projeto2.service;

import com.senac.projeto2.entity.Categoria;
import com.senac.projeto2.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias(){
        return this.categoriaRepository.findAll();
    }

    public Categoria listarCategoriaPorId(int idCategoria){
        return this.categoriaRepository.findById(idCategoria).orElse(null);
    }
}
