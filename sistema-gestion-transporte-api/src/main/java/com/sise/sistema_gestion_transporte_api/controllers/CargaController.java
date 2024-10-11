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

import com.sise.sistema_gestion_transporte_api.entities.Carga;
import com.sise.sistema_gestion_transporte_api.mappers.CargaMapper;
import com.sise.sistema_gestion_transporte_api.payload.requests.CargaRequest;
import com.sise.sistema_gestion_transporte_api.services.ICargaService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;
import com.sise.sistema_gestion_transporte_api.shared.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cargas", description = "Operaciones relacionadas con la gestión de cargas")
@RestController
@RequestMapping("/api/cargas")
public class CargaController {
    @Autowired
    private ICargaService cargaService;

    private CargaMapper cargaMapper;

    public CargaController() {
        cargaMapper = new CargaMapper();
    }

    @Operation(summary = "Listar cargas", description = "Este endpoint permite listar las cargas con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarCargas(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idCarga") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Carga> cargas = cargaService.listarCargas(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(cargas), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar cargas", description = "Este endpoint permite listar una carga solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idCarga}")
    public ResponseEntity<BaseResponse> obtenerCarga(@PathVariable Integer idCarga) {
        try {
            Carga carga = cargaService.obtenerCarga(idCarga);

            if (carga == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(carga), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar cargas", description = "Este endpoint permite insertar una carga")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarCarga(@Valid @RequestBody CargaRequest cargaRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            Carga carga = cargaService.insertarCarga(cargaMapper.requestToEntity(cargaRequest));
            return new ResponseEntity<>(BaseResponse.success(carga), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar cargas", description = "Este endpoint permite actualizar una carga")
    @PutMapping("/{idCarga}")
    public ResponseEntity<BaseResponse> actualizarCarga(@PathVariable Integer idCarga,@Valid @RequestBody CargaRequest cargaRequest, Errors errors) {
        try {
            if (errors.hasErrors()){
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            if (cargaService.obtenerCarga(idCarga) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            Carga cargaActualizar = cargaMapper.requestToEntity(cargaRequest);
            cargaActualizar.setIdCarga(idCarga);

            Carga carga = cargaService.actualizarCarga(cargaActualizar);
            return new ResponseEntity<>(BaseResponse.success(carga), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar cargas", description = "Este endpoint permite eliminar lógicamente una carga, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idCarga}")
    public ResponseEntity<BaseResponse> darBajaCarga(@PathVariable Integer idCarga) {
        try {
            if (cargaService.obtenerCarga(idCarga) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            cargaService.darBajaCarga(idCarga);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
