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

import com.sise.sistema_gestion_transporte_api.entities.Incidencia;
import com.sise.sistema_gestion_transporte_api.mappers.IncidenciaMapper;
import com.sise.sistema_gestion_transporte_api.payload.requests.IncidenciaRequest;
import com.sise.sistema_gestion_transporte_api.services.IIncidenciaService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;
import com.sise.sistema_gestion_transporte_api.shared.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Incidencias", description = "Operaciones relacionadas con la gestión de incidencias")
@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {
    @Autowired
    private IIncidenciaService incidenciaService;

    private IncidenciaMapper incidenciaMapper;

    public IncidenciaController() {
        incidenciaMapper = new IncidenciaMapper();
    }

    @Operation(summary = "Listar incidencias", description = "Este endpoint permite listar las incidencias con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarIncidencias(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idIncidencia") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Incidencia> incidencias = incidenciaService.listarIncidencias(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(incidencias), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar incidencias", description = "Este endpoint permite listar una incidencia solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idIncidencia}")
    public ResponseEntity<BaseResponse> obtenerIncidencia(@PathVariable Integer idIncidencia) {
        try {
            Incidencia incidencia = incidenciaService.obtenerIncidencia(idIncidencia);

            if (incidencia == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(incidencia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar incidencias", description = "Este endpoint permite insertar una incidencia")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarIncidencia(@Valid @RequestBody IncidenciaRequest incidenciaRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            Incidencia incidencia = incidenciaService.insertarIncidencia(incidenciaMapper.requestToEntity(incidenciaRequest));
            return new ResponseEntity<>(BaseResponse.success(incidencia), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar incidencias", description = "Este endpoint permite actualizar una incidencia")
    @PutMapping("/{idIncidencia}")
    public ResponseEntity<BaseResponse> actualizarIncidencia(@PathVariable Integer idIncidencia,@Valid @RequestBody IncidenciaRequest incidenciaRequest, Errors errors) {
        try {
            if (errors.hasErrors()){
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            if (incidenciaService.obtenerIncidencia(idIncidencia) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            Incidencia incidenciaActualizar = incidenciaMapper.requestToEntity(incidenciaRequest);
            incidenciaActualizar.setIdIncidencia(idIncidencia);

            Incidencia incidencia = incidenciaService.actualizarIncidencia(incidenciaActualizar);
            return new ResponseEntity<>(BaseResponse.success(incidencia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar incidencias", description = "Este endpoint permite eliminar lógicamente una incidencia, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idIncidencia}")
    public ResponseEntity<BaseResponse> darBajaIncidencia(@PathVariable Integer idIncidencia) {
        try {
            if (incidenciaService.obtenerIncidencia(idIncidencia) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            incidenciaService.darBajaIncidencia(idIncidencia);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
