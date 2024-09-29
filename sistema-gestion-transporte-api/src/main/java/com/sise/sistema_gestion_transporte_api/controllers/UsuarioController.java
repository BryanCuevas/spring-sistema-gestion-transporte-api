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

import com.sise.sistema_gestion_transporte_api.entities.Usuario;
import com.sise.sistema_gestion_transporte_api.services.IUsuarioService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @Operation(summary = "Listar usuarios", description = "Este endpoint permite listar los usuarios con " + 
    "el campo estado_auditoria igual a '1'")
    @GetMapping("")
    public ResponseEntity<BaseResponse> listarUsuarios(@PageableDefault(size = 4) Pageable pageable , 
    @RequestParam(defaultValue = "idUsuario") String sortBy) {
        try {
            Pageable paginaOrdenada = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
            Page<Usuario> usuarios = usuarioService.listarUsuarios(paginaOrdenada);
            return new ResponseEntity<>(BaseResponse.success(usuarios), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar usuarios", description = "Este endpoint permite listar un usuario solo si " + 
    "el campo estado_auditoria es igual a '1'")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<BaseResponse> obtenerUsuario(@PathVariable Integer idUsuario) {
        try {
            Usuario usuario = usuarioService.obtenerUsuario(idUsuario);

            if(usuario == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(BaseResponse.success(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Insertar usuarios", description = "Este endpoint permite insertar un usuario")
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarUsuario(@RequestBody Usuario usuarioInsertar) {
        try {
            Usuario usuario = usuarioService.insertarUsuario(usuarioInsertar);
            return new ResponseEntity<>(BaseResponse.success(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar usuarios", description = "Este endpoint permite actualizar un usuario")
    @PutMapping("/{idUsuario}")
    public ResponseEntity<BaseResponse> actualizarUsuario(@PathVariable Integer idUsuario, @RequestBody Usuario usuarioActualizar) {
        try {
            if(usuarioService.obtenerUsuario(idUsuario) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
            
            usuarioActualizar.setIdUsuario(idUsuario);
            Usuario usuario = usuarioService.actualizarUsuario(usuarioActualizar);
            return new ResponseEntity<>(BaseResponse.success(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar usuarios", description = "Este endpoint permite eliminar lógicamente un usuario, " + 
    "cambiando el campo estado_auditoria a '0'")
    @PatchMapping("/dar-baja/{idUsuario}")
    public ResponseEntity<BaseResponse> darBajaUsuario(@PathVariable Integer idUsuario) {
        try {
            if(usuarioService.obtenerUsuario(idUsuario) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(), HttpStatus.NOT_FOUND);
            }
    
            usuarioService.darBajaUsuario(idUsuario);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
