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

import com.sise.sistema_gestion_transporte_api.entities.Vehiculo;
import com.sise.sistema_gestion_transporte_api.services.IVehiculoService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Gestión de vehículos", description = "Operaciones relacionadas con la gestión de vehículos")
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private IVehiculoService vehiculoService;

        @GetMapping("")
    public ResponseEntity<BaseResponse> listarVehiculos() {
        try {
            List<Vehiculo> vehiculos = vehiculoService.listarVehiculos();
            return new ResponseEntity<>(BaseResponse.success(vehiculos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idVehiculo}")
    public ResponseEntity<BaseResponse> obtenerVehiculo(@PathVariable Integer idVehiculo) {
        try {
            Vehiculo vehiculo = vehiculoService.obtenerVehiculo(idVehiculo);

            if(vehiculo == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(vehiculo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarVehiculo(@RequestBody Vehiculo vehiculoInsertar) {
        try {
            Vehiculo vehiculo = vehiculoService.insertarVehiculo(vehiculoInsertar);
            return new ResponseEntity<>(BaseResponse.success(vehiculo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idVehiculo}")
    public ResponseEntity<BaseResponse> actualizarVehiculo(@PathVariable Integer idVehiculo, @RequestBody Vehiculo vehiculoActualizar) {
        try {
            if(vehiculoService.obtenerVehiculo(idVehiculo) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            vehiculoActualizar.setIdVehiculo(idVehiculo);
            Vehiculo vehiculo = vehiculoService.actualizarVehiculo(vehiculoActualizar);
            return new ResponseEntity<>(BaseResponse.success(vehiculo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/dar-baja/{idVehiculo}")
    public ResponseEntity<BaseResponse> darBajaVehiculo(@PathVariable Integer idVehiculo) {
        try {
            if(vehiculoService.obtenerVehiculo(idVehiculo) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            vehiculoService.darBajaVehiculo(idVehiculo);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
