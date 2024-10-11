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

import com.sise.sistema_gestion_transporte_api.entities.Empresa;
import com.sise.sistema_gestion_transporte_api.mappers.EmpresaMapper;
import com.sise.sistema_gestion_transporte_api.payload.requests.EmpresaRequest;
import com.sise.sistema_gestion_transporte_api.services.IEmpresaService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;
import com.sise.sistema_gestion_transporte_api.shared.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Empresas", description = "Operaciones relacionadas con la gestión de empresas")
@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
    @Autowired
    private IEmpresaService empresaService;

    private EmpresaMapper empresaMapper;

    public EmpresaController() {
        empresaMapper = new EmpresaMapper();
    }

    @Operation(summary = "Listar empresas", description = "Este endpoint permite listar las empresas con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarEmpresas(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idEmpresa") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Empresa> empresas = empresaService.listarEmpresas(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(empresas), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar empresas", description = "Este endpoint permite listar una empresa solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idEmpresa}")
    public ResponseEntity<BaseResponse> obtenerEmpresa(@PathVariable Integer idEmpresa) {
        try {
            Empresa empresa = empresaService.obtenerEmpresa(idEmpresa);

            if (empresa == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(empresa), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar empresas", description = "Este endpoint permite insertar una empresa")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarEmpresa(@Valid @RequestBody EmpresaRequest empresaRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            Empresa empresa = empresaService.insertarEmpresa(empresaMapper.requestToEntity(empresaRequest));
            return new ResponseEntity<>(BaseResponse.success(empresa), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar empresas", description = "Este endpoint permite actualizar una empresa")
    @PutMapping("/{idEmpresa}")
    public ResponseEntity<BaseResponse> actualizarEmpresa(@PathVariable Integer idEmpresa,@Valid @RequestBody EmpresaRequest empresaRequest, Errors errors) {
        try {
            if (errors.hasErrors()){
                return new ResponseEntity<BaseResponse>(BaseResponse.error(Util.getOneMessageFromErrors(errors.getFieldErrors())),HttpStatus.BAD_REQUEST);
            }

            if (empresaService.obtenerEmpresa(idEmpresa) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            Empresa empresaActualizar = empresaMapper.requestToEntity(empresaRequest);
            empresaActualizar.setIdEmpresa(idEmpresa);

            Empresa empresa = empresaService.actualizarEmpresa(empresaActualizar);
            return new ResponseEntity<>(BaseResponse.success(empresa), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar empresas", description = "Este endpoint permite eliminar lógicamente una empresa, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idEmpresa}")
    public ResponseEntity<BaseResponse> darBajaEmpresa(@PathVariable Integer idEmpresa) {
        try {
            if (empresaService.obtenerEmpresa(idEmpresa) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            empresaService.darBajaEmpresa(idEmpresa);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
