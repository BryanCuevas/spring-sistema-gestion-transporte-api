package com.sise.sistema_gestion_transporte_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sise.sistema_gestion_transporte_api.entities.Conductor;
import com.sise.sistema_gestion_transporte_api.services.IConductorService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Conductores", description = "Operaciones relacionadas con la gestión de conductores")
@RestController
@RequestMapping("/api/conductores")
public class ConductorController {
    @Autowired
    private IConductorService conductorService;

    @Operation(summary = "Listar conductores", description = "Este endpoint permite listar los conductores con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarConductores(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idConductor") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Conductor> conductores = conductorService.listarConductores(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(conductores), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar conductores", description = "Este endpoint permite listar un conductor solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idConductor}")
    public ResponseEntity<BaseResponse> obtenerConductor(@PathVariable Integer idConductor) {
        try {
            Conductor conductor = conductorService.obtenerConductor(idConductor);

            if(conductor == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(conductor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar conductor", description = "Este endpoint permite insertar un conductor")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarConductor(@RequestBody Conductor conductorInsertar) {
        try {
            Conductor conductor = conductorService.insertarConductor(conductorInsertar);
            return new ResponseEntity<>(BaseResponse.success(conductor), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar conductores", description = "Este endpoint permite actualizar un conductor")
    @PutMapping("/{idConductor}")
    public ResponseEntity<BaseResponse> actualizarConductor(@PathVariable Integer idConductor, @RequestBody Conductor conductorActualizar) {
        try {
            if(conductorService.obtenerConductor(idConductor) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            conductorActualizar.setIdConductor(idConductor);
            Conductor conductor = conductorService.actualizarConductor(conductorActualizar);
            return new ResponseEntity<>(BaseResponse.success(conductor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar conductores", description = "Este endpoint permite eliminar lógicamente un conductor, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idConductor}")
    public ResponseEntity<BaseResponse> darBajaConductor(@PathVariable Integer idConductor) {
        try {
            if(conductorService.obtenerConductor(idConductor) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            conductorService.darBajaConductor(idConductor);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
