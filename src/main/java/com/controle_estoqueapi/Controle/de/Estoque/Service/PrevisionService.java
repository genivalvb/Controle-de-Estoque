package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Person;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Prevision;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ActionDeniedException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.ItemMapper;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.ItemRepository;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.PrevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrevisionService {
    @Autowired
    private PrevisionRepository previsionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;
    @Autowired
    private StockService stockService;
    @Autowired
    private LogFutureService logFutureService;

    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Autowired
    private PersonService personService;

    // Listar todas as previsões cadastradas
    public Iterable<Prevision> listPrevisions() {
        return previsionRepository.findAll();
    }

    // Filtrar previsões por id do item
    public List<Prevision> consultarByIdItem(Long idItem) throws ItemNotFoundException {
        //Lança exceção se o item nao existir.
        Item i = itemMapper.toModel(itemService.findById(idItem));

        return previsionRepository.QueryByIdItem(idItem);
    }


    /** Metodo para cadastrar novas previsoes. **/
    @Transactional
    public Prevision registerPrevisions(Prevision pr) throws ItemNotFoundException {
        //Validacoes gerais
        pr = this.validatePrevision(pr);

        //Validacão especifica
        if(pr.getDateExpected().isBefore(LocalDate.now())) {
            throw new ActionDeniedException("Informe uma data maior que a atual");
        }

        //  Verifica se existe a uma previsao de status finalizado = false para a ordem
        //    alterando a quantidade em caso positivo.
        long idItem = pr.getItem().getIdItem();
        List<Prevision> list = previsionRepository.findByOrdem(pr.getOrdem())
                .stream()
                .filter(prevision -> prevision.getItem().getIdItem() == idItem)
                .collect(Collectors.toList());
        if(list.size() > 0){
            Prevision p = list.get(0);
            if(!p.getFinished() && p.getItem().getIdItem() == pr.getItem().getIdItem()){
                p.setQuantityExpected(p.getQuantityExpected() + pr.getQuantityExpected());
                return this.alterPrevision(p.getIdPrevision(),p);
            }
        }
        //Atualiza estoque futuro
        Prevision prevision1 = previsionRepository.save(pr);
        stockService.updateFutureStock(idItem ,logFutureService.queryLogIdItem(idItem));
        return prevision1;
    }

    //Filtrar previsão por idPrevisao
    public Prevision filterId(long idPrevision){
        Optional<Prevision> obj = previsionRepository.findById(idPrevision);
        Prevision prev = null;
        try{
            prev = obj.get();
        } catch (NoSuchElementException exception) {
            throw new EntityNotFoundException("Previsão não localizada");
        }
        return prev;
    }

    /** Metodo para alteracao de uma previsao a partir de um id. * */
    public Prevision alterPrevision(Long idPrevision, Prevision prevision) throws ItemNotFoundException {
        Prevision prev = this.filterId(idPrevision);

        // Não deixa alterar se previsao finalizada
        if (prev.getFinished()) {
            throw new ActionDeniedException("Previsão já finalizada");
        }

        // Não deixa alterar se nova data é anterior a atual da previsao e anterior a now()
        if(prevision.getDateExpected().isBefore(prev.getDateExpected())
                && prevision.getDateExpected().isBefore(LocalDate.now())){
            throw new ActionDeniedException("Informe uma data maior que a atual");
        }

        //Lancará excecões caso haja problemas
        prevision = this.validatePrevision(prevision);
        prev.setItem(prevision.getItem());
        prev.setPerson(prevision.getPerson());
        prev.setFinished(prevision.getFinished());
        prev.setOrdem(prevision.getOrdem());
        prev.setQuantityExpected(prevision.getQuantityExpected());
        prev.setDateExpected(prevision.getDateExpected());
        //Atualiza estoque futuro
        Prevision previsao1 = previsionRepository.save(prev);
        stockService.updateFutureStock(prev.getItem().getIdItem(), logFutureService.queryLogIdItem(prev.getItem().getIdItem()));
        return previsao1;
    }

    //Excluir previsão (só pode ser excluído quando finalizada = false)
    @Transactional
    public void deletePrevision(long idPrevision){
        Prevision prevision = this.filterId(idPrevision);
        if(prevision.getFinished() == false){
            previsionRepository.delete(prevision);
        }else{
            throw new ActionDeniedException("Previsao já finalizada");
        }
    }

    public List<Prevision> queryPendingByIdItem(Long idItem) {
        return previsionRepository.QueryPendingByIdItem(idItem);
    }


    /** Metodo para validacao de uma previsao. * */
    public Prevision validatePrevision(Prevision prevision) throws ItemNotFoundException {
        if (prevision.getQuantityExpected() <= 0) {
            throw new ActionDeniedException("Quantidade inválida");
        }
        if (prevision.getOrdem() == null || prevision.getOrdem().isEmpty()) {
            throw new ActionDeniedException("Ordem nao informada");
        }
        if (prevision.getItem() == null) {
            throw new ActionDeniedException("Item nao informado");
        }
        if (prevision.getDateExpected() == null) {
            throw new ActionDeniedException("Informe uma data válida");
        }
        //Vai lancar excecao se o item for invalido
        Item i = itemMapper.toModel(itemService.findById(prevision.getItem().getIdItem()));
        Person u = personService.queryPersonById(prevision.getPerson().getIdPerson());

        prevision.setItem(i);
        prevision.setPerson(u);
        return prevision;
    }

    /** Metodo para criar uma copia de objeto em memoria para evitar conflitos. **/
    public Prevision clone(Prevision prevision){
        Prevision nPrev = new Prevision();

        nPrev.setItem(prevision.getItem());
        nPrev.setPerson(prevision.getPerson());
        nPrev.setIdPrevision(prevision.getIdPrevision());
        nPrev.setFinished(prevision.getFinished());
        nPrev.setOrdem(prevision.getOrdem());
        nPrev.setQuantityExpected(prevision.getQuantityExpected());
        nPrev.setDateExpected(prevision.getDateExpected());
        return nPrev;
    }
}
