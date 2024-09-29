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

import com.sise.sistema_gestion_transporte_api.entities.Vehiculo;
import com.sise.sistema_gestion_transporte_api.services.IVehiculoService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vehículos", description = "Operaciones relacionadas con la gestión de vehículos")
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private IVehiculoService vehiculoService;

    @Operation(summary = "Listar vehículos", description = "Este endpoint permite listar los vehículos con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarVehiculos(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idVehiculo") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Vehiculo> vehiculos = vehiculoService.listarVehiculos(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(vehiculos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar vehículos", description = "Este endpoint permite listar un vehículo solo si " + 
    "el campo estado_auditoria es igual a '1'")
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

    @Operation(summary = "Insertar vehículos", description = "Este endpoint permite insertar un vehículo")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarVehiculo(@RequestBody Vehiculo vehiculoInsertar) {
        try {
            Vehiculo vehiculo = vehiculoService.insertarVehiculo(vehiculoInsertar);
            return new ResponseEntity<>(BaseResponse.success(vehiculo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar vehículos", description = "Este endpoint permite actualizar un vehículo")
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

    @Operation(summary = "Eliminar vehículos", description = "Este endpoint permite eliminar lógicamente un vehículo, " + 
    "cambiando el campo estado_auditoria a '0'")
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
