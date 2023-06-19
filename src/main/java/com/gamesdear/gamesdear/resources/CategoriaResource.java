package com.gamesdear.gamesdear.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamesdear.gamesdear.domain.Categoria;
import com.gamesdear.gamesdear.service.CategoriaService;

@RequestMapping(value = "/categorias")
@RestController
@CrossOrigin("*")
public class CategoriaResource {

    @Autowired
    CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> obj = service.findAll();  
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById( @PathVariable Integer id){
        Categoria obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Categoria obj, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }

        obj = service.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> update(@Valid @PathVariable Integer id, @RequestBody Categoria obj){
        Categoria novaCategoria = service.update(id, obj);
        return ResponseEntity.ok().body(novaCategoria);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    
    }
    
}
