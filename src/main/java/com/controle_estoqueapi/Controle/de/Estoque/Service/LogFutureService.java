package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.LogFuture;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.LogFutureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogFutureService {

    @Autowired
    private LogFutureRepository repository;
    @Autowired
    private StockService stockService;


    public List<LogFuture> queryLogIdItem(long idItem) {
        List<LogFuture> log = repository.findLogByIdItem(idItem);
        Float stockNow = stockService.findStockIdItem(idItem).getRealStock();

        if(log.size() > 0){
            Float stockFutureItem = log.get(0).getQuantity() + stockNow;
            log.get(0).setStockMoment(stockFutureItem);

            for (int i = 1; i != log.size(); i++){
                stockFutureItem = log.get(i).getQuantity() + log.get(i-1).getStockMoment();
                log.get(i).setStockMoment(stockFutureItem);
            }
        }

        return log;
    }
}
