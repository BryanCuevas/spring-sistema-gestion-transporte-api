package com.sise.sistema_gestion_transporte_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.sistema_gestion_transporte_api.services.IAlmacenService;

@RestController
@RequestMapping("almacenes")
public class AlmacenController {
    @Autowired
    private IAlmacenService almacenService;

}
