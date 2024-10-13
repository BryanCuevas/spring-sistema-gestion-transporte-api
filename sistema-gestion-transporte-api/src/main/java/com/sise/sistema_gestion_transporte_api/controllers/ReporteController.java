package com.sise.sistema_gestion_transporte_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.sistema_gestion_transporte_api.payload.requests.ReporteMaestroRequest;
import com.sise.sistema_gestion_transporte_api.services.IReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Reporte maestro")
@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    IReporteService reporteService;
    
    @Operation(summary = "Creación de reporte", description = "Este endpoint permite crear un reporte sobre lo que se necesite ")
    @PostMapping("/reporte-maestro")
    public ResponseEntity<byte[]> resporteMaestro(@RequestBody ReporteMaestroRequest reporteMaestroRequest) {
        try {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.setContentDisposition(ContentDisposition.inline().filename("reporte-maestro.pdf").build());
        return new ResponseEntity<byte[]>(reporteService.reporteMaestro(reporteMaestroRequest),header,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<byte[]>((new byte[]{}),HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        
        
    }
}
