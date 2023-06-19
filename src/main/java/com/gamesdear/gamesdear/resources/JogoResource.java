package com.gamesdear.gamesdear.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamesdear.gamesdear.domain.Jogo;
import com.gamesdear.gamesdear.dtos.JogoDTO;
import com.gamesdear.gamesdear.service.JogoService;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/jogos")
public class JogoResource {
    @Autowired
    private JogoService service;

    @PostMapping
    public ResponseEntity<Jogo> create(@RequestParam(value = "categoria", defaultValue = "0")
     Integer id_cat, @Valid @RequestBody Jogo obj){

        service.create(id_cat, obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Jogo> update(@Valid @PathVariable Integer id, @RequestBody Jogo obj) {
        Jogo novoJogo = service.update(id, obj);
        return ResponseEntity.ok().body(novoJogo);
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> findAll(){
        List<Jogo> lista = service.findAll();

        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/all/{id}")
    public ResponseEntity<JogoDTO> findAllByJogo(@PathVariable Integer id){
        Jogo obj = service.findAllByJogo(id);
        JogoDTO dto = new JogoDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setDescricao(obj.getDescricao());
        dto.setIdcategoria(obj.getCategoria().getId());
        
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<JogoDTO> findById(@PathVariable Integer id){
        Jogo obj = service.findById(id);

        JogoDTO dto = new JogoDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setDescricao(obj.getDescricao());
        dto.setIdcategoria(obj.getCategoria().getId());

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}