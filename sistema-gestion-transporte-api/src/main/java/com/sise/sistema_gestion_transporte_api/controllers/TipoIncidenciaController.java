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

import com.sise.sistema_gestion_transporte_api.entities.TipoIncidencia;
import com.sise.sistema_gestion_transporte_api.mappers.TipoIncidenciaMapper;
import com.sise.sistema_gestion_transporte_api.payload.requests.TipoIncidenciaRequest;
import com.sise.sistema_gestion_transporte_api.services.ITipoIncidenciaService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;
import com.sise.sistema_gestion_transporte_api.shared.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "TiposIncidencia", description = "Operaciones relacionadas con los tipos de incidencia")
@RestController
@RequestMapping("/api/tiposIncidencia")
public class TipoIncidenciaController {
    @Autowired
    private ITipoIncidenciaService tipoIncidenciaService;

    private TipoIncidenciaMapper tipoIncidenciaMapper;

    public TipoIncidenciaController() {
        tipoIncidenciaMapper = new TipoIncidenciaMapper();
    }

    @Operation(summary = "Listar tipos de incidencia", description = "Este endpoint permite listar los tipos de incidencia con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarTiposIncidencia(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idTipoIncidencia") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<TipoIncidencia> tiposIncidencia = tipoIncidenciaService.listarTiposIncidencia(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(tiposIncidencia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar tipos de incidencia", description = "Este endpoint permite listar un tipo de incidencia solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idTipoIncidencia}")
    public ResponseEntity<BaseResponse> obtenerTipoIncidencia(@PathVariable Integer idTipoIncidencia) {
        try {
            TipoIncidencia tipoIncidencia = tipoIncidenciaService.obtenerTipoIncidencia(idTipoIncidencia);

            if (tipoIncidencia == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(tipoIncidencia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar tipos de incidencia", description = "Este endpoint permite insertar un tipo de incidencia")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarTipoIncidencia(@Valid @RequestBody TipoIncidenciaRequest tipoIncidenciaRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            TipoIncidencia tipoIncidencia = tipoIncidenciaService.insertarTipoIncidencia(tipoIncidenciaMapper.requestToEntity(tipoIncidenciaRequest));
            return new ResponseEntity<>(BaseResponse.success(tipoIncidencia), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar tipos de incidencia", description = "Este endpoint permite actualizar un tipo de incidencia")
    @PutMapping("/{idTipoIncidencia}")
    public ResponseEntity<BaseResponse> actualizarTipoIncidencia(@PathVariable Integer idTipoIncidencia,@Valid @RequestBody TipoIncidenciaRequest tipoIncidenciaRequest, Errors errors) {
        try {
            if (errors.hasErrors()){
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            if (tipoIncidenciaService.obtenerTipoIncidencia(idTipoIncidencia) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            TipoIncidencia tipoIncidenciaActualizar = tipoIncidenciaMapper.requestToEntity(tipoIncidenciaRequest);
            tipoIncidenciaActualizar.setIdTipoIncidencia(idTipoIncidencia);

            TipoIncidencia tipoIncidencia = tipoIncidenciaService.actualizarTipoIncidencia(tipoIncidenciaActualizar);
            return new ResponseEntity<>(BaseResponse.success(tipoIncidencia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar tipos de incidencia", description = "Este endpoint permite eliminar lógicamente un tipo de incidencia, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idTipoIncidencia}")
    public ResponseEntity<BaseResponse> darBajaTipoIncidencia(@PathVariable Integer idTipoIncidencia) {
        try {
            if (tipoIncidenciaService.obtenerTipoIncidencia(idTipoIncidencia) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            tipoIncidenciaService.darBajaTipoIncidencia(idTipoIncidencia);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
