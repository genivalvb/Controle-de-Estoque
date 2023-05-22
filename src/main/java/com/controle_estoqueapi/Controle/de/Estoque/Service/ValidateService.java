package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.*;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.MovementInvalidException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.ItemMapper;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle_estoqueapi.Controle.de.Estoque.Enums.Origin;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ValidateService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private StockService stockService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ReserveService reserveService;
    @Autowired
    private PrevisionService previsionService;

    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    public boolean personExiste(Long idPerson){
        return personRepository.existsById(idPerson);
    }

    public Movement validateMovement(Movement m) throws ItemNotFoundException {
        Person person = personService.queryPersonById(m.getPerson().getIdPerson());
        Stock stock = stockService.findStockIdItem(m.getItem().getIdItem());
        Item item = itemMapper.toModel(itemService.findById(m.getItem().getIdItem()));

        if(m.getQuantity() <= 0){
            throw new MovementInvalidException("Quantidade Inválida!");
        }

        m.setPerson(person);
        m.setStock(stock);
        m.setItem(item);
        /*
         * Validacoes de usuário, quantidade, item ou outras
         * */
        return m;
    }

    public Prevision consultPrevisionsByMovement(Movement m){

        List<Prevision> list = previsionService.queryPendingByIdItem(m.getItem().getIdItem());
        Stream<Prevision> stream =  list.stream().filter(previsoes -> {
            boolean ret = previsoes.getOrdem().equalsIgnoreCase(m.getOriginDestination())
                    && previsoes.getQuantityExpected() == m.getQuantity();
            return ret;
        });

        if (m.getOriginDestination().equalsIgnoreCase(Origin.AVULSO.toString())
                || m.getOriginDestination().equalsIgnoreCase(Origin.DEVOLUCAO.toString())){
            Prevision prev = new Prevision();
            prev.setIdPrevision(0);
            return prev;
        }
        return stream.findFirst().orElseThrow(()-> new MovementInvalidException("Previsão não encontrada"));


    }

    public Reserve consultaReservesByMovement(Movement m){

        List<Reserve> list = reserveService.queryPendingByIdItem(m.getItem().getIdItem());
        Stream<Reserve> stream =  list.stream().filter(reserves -> {
            boolean ret = reserves.getOrdem().equalsIgnoreCase(m.getOriginDestination())
                    && reserves.getQuantityReserve() == m.getQuantity();
            return ret;
        });

        if (m.getOriginDestination().equalsIgnoreCase(Origin.AVULSO.toString())){
            Reserve r = new Reserve();
            r.setIdReserve(0);
            return r;
        }
        return stream.findFirst().orElseThrow(()-> new MovementInvalidException("Reserva não encontrada"));
    }
}
