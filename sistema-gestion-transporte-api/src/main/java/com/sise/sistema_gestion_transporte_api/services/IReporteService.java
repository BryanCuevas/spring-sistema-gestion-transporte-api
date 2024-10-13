package com.sise.sistema_gestion_transporte_api.services;

import com.sise.sistema_gestion_transporte_api.payload.requests.ReporteMaestroRequest;

public interface IReporteService {
    public byte[] reporteMaestro(ReporteMaestroRequest reporteMaestroRequest);

}
