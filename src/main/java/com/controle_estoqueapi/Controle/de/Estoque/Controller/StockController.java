package com.controle_estoqueapi.Controle.de.Estoque.Controller;


//@RestController
//@RequestMapping("estoque")

import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.StockDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Stock;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.StockRepository;
import com.controle_estoqueapi.Controle.de.Estoque.Service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/stock")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockController {
    //@Autowired
    private final StockService stockService;

    //@Autowired
    private final StockRepository stockRepository;

    /*@GetMapping("/list")
    public ResponseEntity<List<Stock>> findStock(){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.listStock());
    }*/

    @GetMapping("/list")
    public List<StockDTO> listItems() {
        return stockService.listStock();
    }


    @GetMapping("/baixo")
    public ResponseEntity<List<Stock>> consultStockLow(){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.consultStockBelowLimit());
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<Stock> consultStockIdItem(@PathVariable("idItem") long idItem){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.findStockIdItem(idItem));
    }
}
