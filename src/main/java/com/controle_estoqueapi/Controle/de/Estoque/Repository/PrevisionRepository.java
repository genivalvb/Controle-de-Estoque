package com.controle_estoqueapi.Controle.de.Estoque.Repository;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Prevision;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrevisionRepository extends CrudRepository<Prevision, Long> {
    // Retorna todas as previsões selecionadas por um item
    @Query(value = "SELECT * from prevision LEFT JOIN item ON prevision.item_id_Item = item.id_Item WHERE item.id_Item = :idItem", nativeQuery = true)
    List<Prevision> QueryByIdItem(Long idItem);

    @Query(value = "SELECT * from prevision LEFT JOIN item ON prevision.item_id_Item = item.id_Item WHERE item.id_Item = :idItem AND finalizada = 0", nativeQuery = true)
    List<Prevision> QueryPendingByIdItem(Long idItem);

    List<Prevision> findByOrdem(String ordem);
}
