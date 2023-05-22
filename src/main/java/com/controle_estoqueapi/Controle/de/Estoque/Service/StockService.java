package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.StockDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.LogFuture;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Stock;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ActionDeniedException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.MovementInvalidException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.StockMapper;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.ItemMapper;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemService itemService;
    private final StockMapper stockMapper = StockMapper.INSTANCE;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    //public List<Stock> listStock(){ return stockRepository.findAll(); }

    public List<StockDTO> listStock(){
        return stockRepository.findAll()
                .stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Stock findStockIdItem(long idItem){
        Stock stock = stockRepository.findByIdItem(idItem).orElseThrow(()-> new EntityNotFoundException("Item nao encontrado no estoque."));
        return stock;
    }


    public Stock addStock(long idItem, float qtd) {
        Stock stock = this.findStockIdItem(idItem);
        if(qtd <= 0){
            throw new MovementInvalidException("Quantidade Invalida");
        }
        stock.setRealStock(stock.getRealStock() + qtd);
        return stockRepository.save(stock);
    }


    public Stock subtractStock(long idItem, float qtd) {
        Stock stock = this.findStockIdItem(idItem);
        if(qtd <= 0){
            throw new MovementInvalidException("Quantidade Invalida");
        }else if(qtd > stock.getRealStock()){
            throw new MovementInvalidException("Quantidade Indisponível");
        }
        stock.setRealStock(stock.getRealStock() - qtd);
        return stockRepository.save(stock);
    }



    public Stock findStockById(long idStock) {
        Stock stock = stockRepository.findById(idStock).orElseThrow(() -> new EntityNotFoundException("Estoque nao encontrado"));
        return stock;
    }

    public Stock salve(Stock stock) throws ItemNotFoundException {
        return this.validateStock(stock);
    }
    /*ja estava*/
    /*public Estoque alterarEstoque(long idEstoque, Estoque estoque){
        Estoque estoqueAlterado = this.buscarEstoqueById(idEstoque);

        if(estoque.getEstoqueReal() > 0){
            estoqueAlterado.setEstoqueReal(estoque.getEstoqueReal());
        }
        if(!estoque.getLocalizacao().isBlank() && !estoque.getLocalizacao().isEmpty()){
            estoqueAlterado.setLocalizacao(estoque.getLocalizacao());
        }
        if(estoque.getItem().getIdItem() > 0){
            estoqueAlterado.setItem(itemService.consultarItemById(estoque.getItem().getIdItem()));
        }
        return this.salvar(estoqueAlterado);
    }*/

    public Stock validateStock(Stock e) throws ItemNotFoundException {
        Item item = itemMapper.toModel(itemService.findById(e.getItem().getIdItem()));
        e.setItem(item);
        if(e.getRealStock() <= 0){
            throw new ActionDeniedException("Estoque menor ou igual a zero");
        }else if(e.getLocalization().isEmpty() || e.getLocalization().isBlank()){
            throw new ActionDeniedException("Localizacao Invalida");
        }
        return e;
    }

    public List<Stock> consultStockBelowLimit (){
        List<Stock> list = stockRepository.findItemsBelowStock();
        if(list.size() == 0){
            throw new EntityNotFoundException("Lista nao encontrada");
        }
        return list;
    }


    public Stock updateFutureStock(long idItem, List<LogFuture> log){
        Float quantity;
        LocalDate data;
        Stock e = this.findStockIdItem(idItem);

        if(log.size() > 0) {
            quantity = log.get(log.size() - 1).getStockMoment();
            data = log.get(log.size() - 1).getData();
            e.setFutureDate(data);
            e.setFutureStock(quantity);
        }else{
            e.setFutureDate(LocalDate.now());
            e.setFutureStock(e.getRealStock());
        }

        return stockRepository.save(e);
    }
}
