package com.controle_estoqueapi.Controle.de.Estoque.Repository;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
    Optional<Item> findByName(String name);
    Optional<Item> findByCode(Long code);
}

