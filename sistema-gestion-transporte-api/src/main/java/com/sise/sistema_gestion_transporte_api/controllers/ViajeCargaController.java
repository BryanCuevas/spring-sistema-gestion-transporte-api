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

import com.sise.sistema_gestion_transporte_api.entities.ViajeCarga;
import com.sise.sistema_gestion_transporte_api.mappers.ViajeCargaMapper;
import com.sise.sistema_gestion_transporte_api.payload.requests.ViajeCargaRequest;
import com.sise.sistema_gestion_transporte_api.services.IViajeCargaService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;
import com.sise.sistema_gestion_transporte_api.shared.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "ViajesCargas", description = "Operaciones relacionadas con la gestión de la tabla ViajesCargas")
@RestController
@RequestMapping("/api/viajesCargas")
public class ViajeCargaController {
    @Autowired
    private IViajeCargaService viajeCargaService;

    private ViajeCargaMapper viajeCargaMapper;

    public ViajeCargaController() {
        viajeCargaMapper = new ViajeCargaMapper();
    }

    @Operation(summary = "Listar cargas de los viajes", description = "Este endpoint permite listar las cargas de los viajes con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarViajesCargas(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idViajeCarga") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<ViajeCarga> viajesCargas = viajeCargaService.listarViajesCargas(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(viajesCargas), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar carga de un viaje", description = "Este endpoint permite listar una carga de un viaje solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idViajeCarga}")
    public ResponseEntity<BaseResponse> obtenerViajeCarga(@PathVariable Integer idViajeCarga) {
        try {
            ViajeCarga viajeCarga = viajeCargaService.obtenerViajeCarga(idViajeCarga);

            if (viajeCarga == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(viajeCarga), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar carga de un viaje", description = "Este endpoint permite insertar una carga de un viaje")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarViajeCarga(@Valid @RequestBody ViajeCargaRequest viajeCargaRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            ViajeCarga viajeCarga = viajeCargaService.insertarViajeCarga(viajeCargaMapper.requestToEntity(viajeCargaRequest));
            return new ResponseEntity<>(BaseResponse.success(viajeCarga), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar carga de un viaje", description = "Este endpoint permite actualizar una carga de un viaje")
    @PutMapping("/{idCargaViaje}")
    public ResponseEntity<BaseResponse> actualizarViajeCarga(@PathVariable Integer idViajeCarga,@Valid @RequestBody ViajeCargaRequest viajeCargaRequest, Errors errors) {
        try {
            if (errors.hasErrors()){
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            if (viajeCargaService.obtenerViajeCarga(idViajeCarga) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            ViajeCarga viajeCargaActualizar = viajeCargaMapper.requestToEntity(viajeCargaRequest);
            viajeCargaActualizar.setIdViajeCarga(idViajeCarga);

            ViajeCarga viajeCarga = viajeCargaService.actualizarViajeCarga(viajeCargaActualizar);
            return new ResponseEntity<>(BaseResponse.success(viajeCarga), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar carga de un viaje", description = "Este endpoint permite eliminar lógicamente una carga de un viaje, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idViajeCarga}")
    public ResponseEntity<BaseResponse> darBajaViajeCarga(@PathVariable Integer idViajeCarga) {
        try {
            if (viajeCargaService.obtenerViajeCarga(idViajeCarga) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            viajeCargaService.darBajaViajeCarga(idViajeCarga);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
