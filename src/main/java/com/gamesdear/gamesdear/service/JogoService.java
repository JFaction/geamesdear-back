package com.gamesdear.gamesdear.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamesdear.gamesdear.domain.Categoria;
import com.gamesdear.gamesdear.domain.Jogo;
import com.gamesdear.gamesdear.repositories.JogoRepository;
import com.gamesdear.gamesdear.service.exceptions.DataIntegrityViolationException;
import com.gamesdear.gamesdear.service.exceptions.ObjectNotFoundException;

@Service
public class JogoService {
    
    @Autowired
    private JogoRepository repository;

    @Autowired
    private CategoriaService categoriaService;

    public List<Jogo> findAll() {
        return repository.findAll();
    }

public Jogo findAllByJogo(Integer id) {
    Optional<Jogo> jogoOptional = repository.findAllByJogo(id);
    Jogo jogo = jogoOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id:" + id + ", Tipo: " + Jogo.class.getName()));
    return jogo;
}

    public Jogo findById(Integer id){

        Optional<Jogo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! id:" + id + ", Tipo: " + Jogo.class.getName()));
            
    }

    public Jogo create(Integer id_cat, Jogo obj){
        obj.setId(null);
        Categoria categoria = categoriaService.findById(id_cat);
        obj.setCategoria(categoria);
        return repository.save(obj);
    }

    public Jogo update(Integer id, Jogo obj){

        Jogo verificarJogo = findById(id);

        verificarJogo.setNome(obj.getNome());
        verificarJogo.setDescricao(obj.getDescricao());

        return repository.save(verificarJogo);
    }

    public void delete(Integer id) {
        findById(id);
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("A Categoria possui vinculos e por isso não pode ser deletada.");
        }
    }

}