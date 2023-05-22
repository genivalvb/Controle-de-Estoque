package com.controle_estoqueapi.Controle.de.Estoque.Repository;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    @Query("Select m from Movement m where item.idItem = ?1 order by m.idMovement desc")
    List<Movement> findAllByIdItem(Long idItem);
    @Query("Select m from Movement m order by m.idMovement desc")
    List<Movement> findAllOrderByDesc();
}
