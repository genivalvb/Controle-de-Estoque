package com.controle_estoqueapi.Controle.de.Estoque.Repository;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    @Query("select r from Reserve r where item.idItem = ?1")
    List<Reserve> QueryByIdItem(Long idItem);

    @Query("select r from Reserve r where item.idItem = ?1 and r.finished=false")
    List<Reserve> queryPendingByIdItem(Long idItem);

    @Query("select r from Reserve r order by r.dateExpected desc")
    List<Reserve> findAllOrderByDesc();

    List<Reserve> findByOrdem(String ordem);
}
