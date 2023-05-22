package com.controle_estoqueapi.Controle.de.Estoque.Controller;


import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.MovementDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemAlreadyRegisteredException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.MovementAlreadyRegisteredException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages movements content")
public interface MovementControllerDocs {
    @ApiOperation(value = "Movement creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success movement creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    MovementDTO createMovement(MovementDTO movementDTO) throws MovementAlreadyRegisteredException;

    @ApiOperation(value = "Returns movement found by a given code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success movement found in the system"),
            @ApiResponse(code = 404, message = "Movement with given name not found.")
    })
    MovementDTO findByName(@PathVariable String name) throws ItemNotFoundException;

    @ApiOperation(value = "Returns a list of all movements registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all movements registered in the system"),
    })
    List<MovementDTO> listMovements();

    @ApiOperation(value = "Delete a movement found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success movement deleted in the system"),
            @ApiResponse(code = 404, message = "Movement with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws ItemNotFoundException;
}
