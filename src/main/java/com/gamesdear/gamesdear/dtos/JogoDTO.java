package com.gamesdear.gamesdear.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JogoDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String descricao;
    private Integer idcategoria;
}
