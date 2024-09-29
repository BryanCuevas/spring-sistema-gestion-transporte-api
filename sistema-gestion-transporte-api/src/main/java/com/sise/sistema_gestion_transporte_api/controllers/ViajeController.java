package com.sise.sistema_gestion_transporte_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.sistema_gestion_transporte_api.entities.Viaje;
import com.sise.sistema_gestion_transporte_api.services.IViajeService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Gestión de viajes", description = "Operaciones relacionadas con la gestión de viajes")
@RestController
@RequestMapping("/api/viajes")
public class ViajeController {
    @Autowired
    private IViajeService viajeService;

    @Operation(summary = "Listar viajes", description = "Este endpoint permite listar todos los viajes solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarViajes() {
        try {
            List<Viaje> viajes = viajeService.listarViajes();
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

            if(viaje == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(viaje), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar viajes", description = "Este endpoint permite insertar un viaje")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarViaje(@RequestBody Viaje viajeInsertar) {
        try {
            Viaje viaje = viajeService.insertarViaje(viajeInsertar);
            return new ResponseEntity<>(BaseResponse.success(viaje), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar viajes", description = "Este endpoint permite actualizar un viaje")
    @PutMapping("/{idViaje}")
    public ResponseEntity<BaseResponse> actualizarViaje(@PathVariable Integer idViaje, @RequestBody Viaje viajeActualizar) {
        try {
            if(viajeService.obtenerViaje(idViaje) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
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
            if(viajeService.obtenerViaje(idViaje) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            viajeService.darBajaViaje(idViaje);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
