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

import com.sise.sistema_gestion_transporte_api.entities.Ruta;
import com.sise.sistema_gestion_transporte_api.services.IRutaService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rutas", description = "Operaciones relacionadas con la gestión de rutas")
@RestController
@RequestMapping("/api/rutas")
public class RutaController {
    @Autowired
    private IRutaService rutaService;

    @Operation(summary = "Listar rutas", description = "Este endpoint permite listar las rutas con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarRutas(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idRuta") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Ruta> rutas = rutaService.listarRutas(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(rutas), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar rutas", description = "Este endpoint permite listar una ruta solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idRuta}")
    public ResponseEntity<BaseResponse> obtenerRuta(@PathVariable Integer idRuta) {
        try {
            Ruta ruta = rutaService.obtenerRuta(idRuta);

            if(ruta == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(ruta), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar rutas", description = "Este endpoint permite insertar una ruta")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarRuta(@RequestBody Ruta rutaInsertar) {
        try {
            Ruta ruta = rutaService.insertarRuta(rutaInsertar);
            return new ResponseEntity<>(BaseResponse.success(ruta), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar rutas", description = "Este endpoint permite actualizar una ruta")
    @PutMapping("/{idRuta}")
    public ResponseEntity<BaseResponse> actualizarRuta(@PathVariable Integer idRuta, @RequestBody Ruta rutaActualizar) {
        try {
            if(rutaService.obtenerRuta(idRuta) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            rutaActualizar.setIdRuta(idRuta);
            Ruta ruta = rutaService.actualizarRuta(rutaActualizar);
            return new ResponseEntity<>(BaseResponse.success(ruta), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar rutas", description = "Este endpoint permite eliminar lógicamente una ruta, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idRuta}")
    public ResponseEntity<BaseResponse> darBajaRuta(@PathVariable Integer idRuta) {
        try {
            if(rutaService.obtenerRuta(idRuta) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            rutaService.darBajaRuta(idRuta);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
