package com.controle_estoqueapi.Controle.de.Estoque.Controller;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemAlreadyRegisteredException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/items")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController implements ItemControllerDocs{
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestBody @Valid ItemDTO itemDTO) throws ItemAlreadyRegisteredException {
        return itemService.createItem(itemDTO);
    }
    @GetMapping("/consultar/{id}")
    public ItemDTO consultarItemById(@PathVariable Long id) throws ItemNotFoundException{
        return itemService.findById(id);
    }

    @GetMapping("/code/{code}")
    public ItemDTO findItemByCode(@PathVariable long code) throws ItemNotFoundException{
        return itemService.findByCode(code);
    }

    @GetMapping("/name/{name}")
    public ItemDTO findByName(@PathVariable String name) throws ItemNotFoundException {
        return itemService.findByName(name);
    }

    @GetMapping("/{id}")
    public ItemDTO findById(@PathVariable Long id) throws ItemNotFoundException {
        return itemService.findById(id);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<ItemDTO> listItems() {
        return itemService.listAll();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ItemNotFoundException {
        itemService.deleteById(id);
    }
}
