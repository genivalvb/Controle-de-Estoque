package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.MovementDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.*;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.MovementNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.ItemMapper;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.MovementMapper;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.MovementRepository;
import net.bytebuddy.asm.Advice;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {


    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ValidateService validateService;

    @Autowired
    private StockService stockService;

    @Autowired
    private PrevisionService previsionService;

    @Autowired
    private ReserveService reserveService;
    @Autowired
    private LogFutureService logFutureService;

    @Autowired
    private  ItemService itemService;

    private final MovementMapper movementMapper = MovementMapper.INSTANCE;

    private final ItemMapper itemMapper = ItemMapper.INSTANCE;


    public Movement salve(Movement movement) {
        return movementRepository.save(movement);
    }

    /** Metodo para consultar todas as movimentacoes * */
    public List<Movement> consult(){
        return movementRepository.findAllOrderByDesc();
    }

    public List<MovementDTO> listAll() {
        return movementRepository.findAll()
                .stream()
                .map(movementMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<Movement> queryByIdItem(Long idItem) throws ItemNotFoundException {
        //Lança exceção se o item nao existir.
        Item i = itemMapper.toModel(itemService.findById(idItem));

        return movementRepository.findAllByIdItem(idItem);
    }


    /** Metodo para efetuar uma movimentacao de entrada. * */
    public Movement inputItem(Movement mov) throws ItemNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        mov.setDateMovement(localDateTime);
        mov.setType("IN");
        Movement m = validateService.validateMovement(mov);
        Prevision p = validateService.consultPrevisionsByMovement(mov);

        if (p.getIdPrevision() > 0){
            Prevision p2 = previsionService.clone(p);
            p2.setFinished(true);
            previsionService.alterPrevision(p.getIdPrevision(), p2);
        }
        stockService.addStock(mov.getItem().getIdItem(), mov.getQuantity());
        //Atualiza estoque futuro
        updateStockFuture(m);
        return this.salve(m);
    }

    /** Metodo para efetuar uma movimentacao de saida. * */
    public Movement exitItem(Movement mov) throws ItemNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        mov.setDateMovement(localDateTime);
        mov.setType("OUT");
        Movement m = validateService.validateMovement(mov);
        Reserve r = validateService.consultaReservesByMovement(mov);

        if (r.getIdReserve() > 0){
            Reserve r2 = reserveService.clone(r);
            r2.setFinished(true);
            reserveService.alter(r.getIdReserve(), r2);
        }
        stockService.subtractStock(mov.getItem().getIdItem(), mov.getQuantity());

        //Atualiza estoque futuro
        updateStockFuture(m);
        return this.salve(m);
    }

    private void updateStockFuture(Movement m){
        stockService.updateFutureStock(m.getItem().getIdItem(), logFutureService.queryLogIdItem(m.getItem().getIdItem()));
    }
}
