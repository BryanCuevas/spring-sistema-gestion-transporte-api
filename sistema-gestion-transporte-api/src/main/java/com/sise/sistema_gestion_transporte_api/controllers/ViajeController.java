package com.sise.sistema_gestion_transporte_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sise.sistema_gestion_transporte_api.entities.Viaje;
import com.sise.sistema_gestion_transporte_api.mappers.ViajeMapper;
import com.sise.sistema_gestion_transporte_api.payload.requests.ViajeRequest;
import com.sise.sistema_gestion_transporte_api.services.IViajeService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;
import com.sise.sistema_gestion_transporte_api.shared.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Viajes", description = "Operaciones relacionadas con la gestión de viajes")
@RestController
@RequestMapping("/api/viajes")
public class ViajeController {
    @Autowired
    private IViajeService viajeService;

    private ViajeMapper viajeMapper;

    public ViajeController() {
        viajeMapper = new ViajeMapper();
    }

    @Operation(summary = "Listar viajes", description = "Este endpoint permite listar los viajes con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarViajes(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idViaje") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Viaje> viajes = viajeService.listarViajes(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(viajes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar viajes", description = "Este endpoint permite listar un viaje solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idViaje}")
    public ResponseEntity<BaseResponse> obtenerViaje(@PathVariable Integer idViaje) {
        try {
            Viaje viaje = viajeService.obtenerViaje(idViaje);

            if (viaje == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(viaje), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar viajes", description = "Este endpoint permite insertar un viaje")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarViaje(@Valid @RequestBody ViajeRequest viajeRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            Viaje viaje = viajeService.insertarViaje(viajeMapper.requestToEntity(viajeRequest));
            return new ResponseEntity<>(BaseResponse.success(viaje), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar viajes", description = "Este endpoint permite actualizar un viaje")
    @PutMapping("/{idViaje}")
    public ResponseEntity<BaseResponse> actualizarViaje(@PathVariable Integer idViaje, @Valid @RequestBody ViajeRequest viajeRequest, Errors errors) {
        try {
            if (errors.hasErrors()){
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            if (viajeService.obtenerViaje(idViaje) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            Viaje viajeActualizar = viajeMapper.requestToEntity(viajeRequest);
            viajeActualizar.setIdViaje(idViaje);

            Viaje viaje = viajeService.actualizarViaje(viajeActualizar);
            return new ResponseEntity<>(BaseResponse.success(viaje), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar viajes", description = "Este endpoint permite eliminar lógicamente un viaje, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idViaje}")
    public ResponseEntity<BaseResponse> darBajaViaje(@PathVariable Integer idViaje) {
        try {
            if (viajeService.obtenerViaje(idViaje) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            viajeService.darBajaViaje(idViaje);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
