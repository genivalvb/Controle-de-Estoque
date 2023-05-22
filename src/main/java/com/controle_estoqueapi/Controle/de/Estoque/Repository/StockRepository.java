package com.controle_estoqueapi.Controle.de.Estoque.Repository;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select e from Stock e where e.item.idItem = ?1")
    Optional<Stock> findByIdItem(Long idItem);

    @Query(value = "select * from Stock as e inner join item as i " +
            "on e.item_id_item = i.id_item " +
            "where e.real_stock <= i.stock_safe" ,nativeQuery = true)
    List<Stock> findItemsBelowStock();
}
