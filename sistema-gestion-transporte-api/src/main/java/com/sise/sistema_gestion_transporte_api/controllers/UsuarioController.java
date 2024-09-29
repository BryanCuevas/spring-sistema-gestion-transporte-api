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

import com.sise.sistema_gestion_transporte_api.entities.Usuario;
import com.sise.sistema_gestion_transporte_api.services.IUsuarioService;
import com.sise.sistema_gestion_transporte_api.shared.BaseResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Gestión de usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            return new ResponseEntity<>(BaseResponse.success(usuarios), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarUsuario(@RequestBody Usuario usuarioInsertar) {
        try {
            Usuario usuario = usuarioService.insertarUsuario(usuarioInsertar);
            return new ResponseEntity<>(BaseResponse.success(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
