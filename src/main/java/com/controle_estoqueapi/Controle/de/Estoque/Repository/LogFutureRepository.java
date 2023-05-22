package com.controle_estoqueapi.Controle.de.Estoque.Repository;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.LogFuture;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LogFutureRepository extends JpaRepository<LogFuture, Long> {
    @Query("SELECT l FROM LogFuture l WHERE l.idItem = ?1 ORDER BY l.data")
    List<LogFuture> findLogByIdItem(long idItem);
}
