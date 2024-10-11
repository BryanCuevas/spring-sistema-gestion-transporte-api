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

import com.sise.sistema_gestion_transporte_api.entities.EmpleadoLogistica;
import com.sise.sistema_gestion_transporte_api.mappers.EmpleadoLogisticaMapper;
import com.sise.sistema_gestion_transporte_api.payload.requests.EmpleadoLogisticaRequest;
import com.sise.sistema_gestion_transporte_api.services.IEmpleadoLogisticaService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;
import com.sise.sistema_gestion_transporte_api.shared.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "EmpleadosLogistica", description = "Operaciones relacionadas con la gestión de los empleados del área de logística")
@RestController
@RequestMapping("/api/empleadosLogistica")

public class EmpleadoLogisticaController {
    @Autowired
    private IEmpleadoLogisticaService empleadoLogisticaService;

    private EmpleadoLogisticaMapper empleadoLogisticaMapper;

    public EmpleadoLogisticaController() {
        empleadoLogisticaMapper = new EmpleadoLogisticaMapper();
    }

    @Operation(summary = "Listar empleados", description = "Este endpoint permite listar los empleados con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarEmpleadosLogistica(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idEmpleadoLogistica") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<EmpleadoLogistica> empleados = empleadoLogisticaService.listarEmpleadosLogistica(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(empleados), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar empleados", description = "Este endpoint permite listar un empleado solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idEmpleadoLogistica}")
    public ResponseEntity<BaseResponse> obtenerEmpleadoLogistica(@PathVariable Integer idEmpleadoLogistica) {
        try {
            EmpleadoLogistica empleadoLogistica = empleadoLogisticaService.obtenerEmpleadoLogistica(idEmpleadoLogistica);

            if (empleadoLogistica == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(empleadoLogistica), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar empleados", description = "Este endpoint permite insertar un empleado")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarEmpleadoLogistica(@Valid @RequestBody EmpleadoLogisticaRequest empleadoLogisticaRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            EmpleadoLogistica empleadoLogistica = empleadoLogisticaService.insertarEmpleadoLogistica(empleadoLogisticaMapper.requestToEntity(empleadoLogisticaRequest));
            return new ResponseEntity<>(BaseResponse.success(empleadoLogistica), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar empleados", description = "Este endpoint permite actualizar un empleado")
    @PutMapping("/{idEmpleadoLogistica}")
    public ResponseEntity<BaseResponse> actualizarEmpleadoLogistica(@PathVariable Integer idEmpleadoLogistica,@Valid @RequestBody EmpleadoLogisticaRequest empleadoLogisticaRequest, Errors errors) {
        try {
            if (errors.hasErrors()){
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            if (empleadoLogisticaService.obtenerEmpleadoLogistica(idEmpleadoLogistica) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            EmpleadoLogistica empleadoActualizar = empleadoLogisticaMapper.requestToEntity(empleadoLogisticaRequest);
            empleadoActualizar.setIdEmpleadoLogistica(idEmpleadoLogistica);

            EmpleadoLogistica empleado = empleadoLogisticaService.actualizarEmpleadoLogistica(empleadoActualizar);
            return new ResponseEntity<>(BaseResponse.success(empleado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar empleados", description = "Este endpoint permite eliminar lógicamente un empleado, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idEmpleadoLogistica}")
    public ResponseEntity<BaseResponse> darBajaEmpleadoLogistica(@PathVariable Integer idEmpleadoLogistica) {
        try {
            if (empleadoLogisticaService.obtenerEmpleadoLogistica(idEmpleadoLogistica) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            empleadoLogisticaService.darBajaEmpleadoLogistica(idEmpleadoLogistica);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
