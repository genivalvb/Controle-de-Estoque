package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Person;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Reserve;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ActionDeniedException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.ItemMapper;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PersonService personService;
    @Autowired
    private StockService stockService;
    @Autowired
    private LogFutureService logFutureService;

    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    public List<Reserve> query(){
        return reserveRepository.findAllOrderByDesc();
    }

    public Reserve queryById(Long id){
        Optional<Reserve> obj = reserveRepository.findById(id);
        Reserve res = null;// obj.orElseThrow(()-> new EntityNotFoundException("Reserva não encontrada"));
        try{
            res = obj.get();
        }
        catch (NoSuchElementException e){
            throw new EntityNotFoundException("Reserva não encontrada");
        }
        return res;
    }

    public List<Reserve> queryByIdItem(Long idItem) throws ItemNotFoundException {
        //Lança exceção se o item nao existir.
        Item i = itemMapper.toModel(itemService.findById(idItem));
        return reserveRepository.QueryByIdItem(idItem);
    }

    /** Metodo para cadastrar novas reserva. **/
    @Transactional
    public Reserve salve(Reserve reserve) throws ItemNotFoundException {
        //Validacoes gerais
        reserve = this.validateReserve(reserve);

        //Validacão especifica
        if(reserve.getDateExpected().isBefore(LocalDate.now())) {
            throw new ActionDeniedException("Informe uma data maior que a atual");
        }

        //  Verifica se existe a uma reserva de status finalizado = false para a ordem
        //    alterando a quantidade em caso positivo.
        long idItem = reserve.getItem().getIdItem();
        List<Reserve> list = reserveRepository.findByOrdem(reserve.getOrdem())
                .stream()
                .filter(reserve1 -> reserve1.getItem().getIdItem() == idItem)
                .collect(Collectors.toList());
        if(list.size() > 0){
            Reserve r = list.get(0);
            if(!r.isFinished() && r.getItem().getIdItem() == reserve.getItem().getIdItem()){
                r.setQuantityReserve(r.getQuantityReserve() + reserve.getQuantityReserve());
                return this.alter(r.getIdReserve(),r);
            }
        }
        Reserve reserve1 = reserveRepository.save(reserve);
        //Atualiza o estoque futuro do item
        stockService.updateFutureStock(idItem, logFutureService.queryLogIdItem(idItem));
        return reserve1;
    }

    /** Metodo para alteracao de uma reserva a partir de um id. **/
    public Reserve alter(Long idreserve, Reserve reserve) throws ItemNotFoundException {
        Reserve res = this.queryById(idreserve);

        // Não deixa alterar se reserva finalizada
        if (res.isFinished()) {
            throw new ActionDeniedException("Reserva já finalizada");
        }
        // Não deixa alterar se nova data é anterior a atual da reserva e anterior a now()
        if(reserve.getDateExpected().isBefore(res.getDateExpected())
                && reserve.getDateExpected().isBefore(LocalDate.now())){
            throw new ActionDeniedException("Informe uma data maior que a atual");
        }
        //Lancará excecões caso haja problemas
        reserve = this.validateReserve(reserve);
        res.setFinished(reserve.isFinished());
        res.setQuantityReserve(reserve.getQuantityReserve());
        res.setDateExpected(reserve.getDateExpected());
        res.setOrdem(reserve.getOrdem());
        res.setPerson(reserve.getPerson());
        res.setItem(reserve.getItem());
        Reserve reserve1 = reserveRepository.save(res);

        //Atualiza o estoque futuro do item
        stockService.updateFutureStock(res.getItem().getIdItem(), logFutureService.queryLogIdItem(res.getItem().getIdItem()));
        return reserve1;
    }

    @Transactional
    public void delete(Long idreserve){
        Reserve reserves = this.queryById(idreserve);
        reserveRepository.delete(reserves);
    }

    public List<Reserve> queryPendingByIdItem(Long idItem){
        return reserveRepository.queryPendingByIdItem(idItem);
    }

    /** Metodo para validacao de uma reserva. **/
    public Reserve validateReserve(Reserve reserve) throws ItemNotFoundException {
        if (reserve.getQuantityReserve() <= 0) {
            throw new ActionDeniedException("Quantidade inválida");
        }
        if (reserve.getOrdem() == null || reserve.getOrdem().isEmpty()) {
            throw new ActionDeniedException("Ordem nao informada");
        }
        if (reserve.getItem() == null) {
            throw new ActionDeniedException("Item nao informado");
        }
        if (reserve.getDateExpected() == null) {
            throw new ActionDeniedException("Informe uma data válida");
        }
        //Vai lancar excecao se o item for invalido
        Item i = itemMapper.toModel(itemService.findById(reserve.getItem().getIdItem()));
        Person u = personService.queryPersonById(reserve.getPerson().getIdPerson());
        reserve.setItem(i);
        reserve.setPerson(u);
        return reserve;
    }

    /** Metodo para criar uma copia de objeto em memoria para evitar conflitos. **/
    public Reserve clone(Reserve reserve){
        Reserve nRes = new Reserve();
        nRes.setItem(reserve.getItem());
        nRes.setPerson(reserve.getPerson());
        nRes.setIdReserve(reserve.getIdReserve());
        nRes.setFinished(reserve.isFinished());
        nRes.setOrdem(reserve.getOrdem());
        nRes.setQuantityReserve(reserve.getQuantityReserve());
        nRes.setDateExpected(reserve.getDateExpected());
        return nRes;
    }
}
