package com.gamesdear.gamesdear.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamesdear.gamesdear.domain.Categoria;
import com.gamesdear.gamesdear.repositories.CategoriaRepository;
import com.gamesdear.gamesdear.service.exceptions.DataIntegrityViolationException;
import com.gamesdear.gamesdear.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    
    public Categoria create(Categoria obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public void delete(Integer id){
        findById(id);
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("A categoria possui vinculos! Não pode ser deletada");
        }
    }

    public Categoria update(Integer id, Categoria obj){

        Categoria verificarCategoria = findById(id);

        verificarCategoria.setNome(obj.getNome());

        return repository.save(verificarCategoria);
    }

    public Categoria findById(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: "+ id + ", Tipo: " + Categoria.class.getName()));
            
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    

}
