package com.controle_estoqueapi.Controle.de.Estoque.Controller;


import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemAlreadyRegisteredException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;



@Api("Manages items content")
public interface ItemControllerDocs {
    @ApiOperation(value = "Item creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success item creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    ItemDTO createItem(ItemDTO itemDTO) throws ItemAlreadyRegisteredException;

    @ApiOperation(value = "Returns item found by a given code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success item found in the system"),
            @ApiResponse(code = 404, message = "Item with given name not found.")
    })
    ItemDTO findByName(@PathVariable String name) throws ItemNotFoundException;

    @ApiOperation(value = "Returns a list of all items registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all items registered in the system"),
    })
    List<ItemDTO> listItems();

    @ApiOperation(value = "Delete a item found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success item deleted in the system"),
            @ApiResponse(code = 404, message = "Item with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws ItemNotFoundException;
}
