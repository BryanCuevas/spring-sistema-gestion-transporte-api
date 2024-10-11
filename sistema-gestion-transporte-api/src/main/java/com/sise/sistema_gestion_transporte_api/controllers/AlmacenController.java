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

import com.sise.sistema_gestion_transporte_api.entities.Almacen;
import com.sise.sistema_gestion_transporte_api.mappers.AlmacenMapper;
import com.sise.sistema_gestion_transporte_api.payload.requests.AlmacenRequest;
import com.sise.sistema_gestion_transporte_api.services.IAlmacenService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;
import com.sise.sistema_gestion_transporte_api.shared.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Almacenes", description = "Operaciones relacionadas con la gestión de almacenes")
@RestController
@RequestMapping("/api/almacenes")
public class AlmacenController {
    @Autowired
    private IAlmacenService almacenService;

    private AlmacenMapper almacenMapper;

    public AlmacenController() {
        almacenMapper = new AlmacenMapper();
    }

    @Operation(summary = "Listar almacenes", description = "Este endpoint permite listar los almacenes con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarAlmacenes(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idAlmacen") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Almacen> almacenes = almacenService.listarAlmacenes(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(almacenes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar almacenes", description = "Este endpoint permite listar un almacén solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idAlmacen}")
    public ResponseEntity<BaseResponse> obtenerAlmacen(@PathVariable Integer idAlmacen) {
        try {
            Almacen almacen = almacenService.obtenerAlmacen(idAlmacen);

            if (almacen == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(almacen), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar almacenes", description = "Este endpoint permite insertar un almacén")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarAlmacen(@Valid @RequestBody AlmacenRequest almacenRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            Almacen almacen = almacenService.insertarAlmacen(almacenMapper.requestToEntity(almacenRequest));
            return new ResponseEntity<>(BaseResponse.success(almacen), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar almacenes", description = "Este endpoint permite actualizar un almacén")
    @PutMapping("/{idAlmacen}")
    public ResponseEntity<BaseResponse> actualizarAlmacen(@PathVariable Integer idAlmacen,@Valid @RequestBody AlmacenRequest almacenRequest, Errors errors) {
        try {
            if (errors.hasErrors()){
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            if (almacenService.obtenerAlmacen(idAlmacen) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            Almacen almacenActualizar = almacenMapper.requestToEntity(almacenRequest);
            almacenActualizar.setIdAlmacen(idAlmacen);

            Almacen almacen = almacenService.actualizarAlmacen(almacenActualizar);
            return new ResponseEntity<>(BaseResponse.success(almacen), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar almacenes", description = "Este endpoint permite eliminar lógicamente un almacén, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idAlmacen}")
    public ResponseEntity<BaseResponse> darBajaAlmacen(@PathVariable Integer idAlmacen) {
        try {
            if (almacenService.obtenerAlmacen(idAlmacen) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            almacenService.darBajaAlmacen(idAlmacen);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
