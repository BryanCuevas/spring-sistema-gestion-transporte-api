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

import com.sise.sistema_gestion_transporte_api.entities.Almacen;
import com.sise.sistema_gestion_transporte_api.services.IAlmacenService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;

import java.util.List;

@RestController
@RequestMapping("/almacenes")
public class AlmacenController {
    @Autowired
    private IAlmacenService almacenService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarAlmacenes() {
        try {
            List<Almacen> almacenes = almacenService.listarAlmacenes();
            return new ResponseEntity<>(BaseResponse.success(almacenes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idAlmacen}")
    public ResponseEntity<BaseResponse> obtenerAlmacen(@PathVariable Integer idAlmacen) {
        try {
            Almacen almacen = almacenService.obtenerAlmacen(idAlmacen);

            if(almacen == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(almacen), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarAlmacen(@RequestBody Almacen almacenInsertar) {
        try {
            Almacen almacen = almacenService.insertarAlmacen(almacenInsertar);
            return new ResponseEntity<>(BaseResponse.success(almacen), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idAlmacen}")
    public ResponseEntity<BaseResponse> actualizarAlmacen(@PathVariable Integer idAlmacen, @RequestBody Almacen almacenActualizar) {
        try {
            if(almacenService.obtenerAlmacen(idAlmacen) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            almacenActualizar.setIdAlmacen(idAlmacen);
            Almacen almacen = almacenService.actualizarAlmacen(almacenActualizar);
            return new ResponseEntity<>(BaseResponse.success(almacen), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/dar-baja/{idAlmacen}")
    public ResponseEntity<BaseResponse> darBajaAlmacen(@PathVariable Integer idAlmacen) {
        try {
            if(almacenService.obtenerAlmacen(idAlmacen) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            almacenService.darBajaAlmacen(idAlmacen);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
