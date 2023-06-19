package com.gamesdear.gamesdear.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamesdear.gamesdear.domain.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer>{

    @Query("SELECT j FROM Jogo j JOIN FETCH j.categoria WHERE j.id = :id")
    Optional<Jogo> findAllByJogo(@Param("id") Integer id);
    
    
}

