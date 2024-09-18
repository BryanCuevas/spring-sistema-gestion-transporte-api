package com.sise.sistema_gestion_transporte_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.sistema_gestion_transporte_api.services.IVehiculoService;

@RestController
@RequestMapping("vehiculos")
public class VehiculoController {
    @Autowired
    private IVehiculoService vehiculoService;
}
