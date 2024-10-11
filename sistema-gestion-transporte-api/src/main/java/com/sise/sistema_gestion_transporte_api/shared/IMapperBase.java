package com.sise.sistema_gestion_transporte_api.shared;

public interface IMapperBase<TEntity, TRequest> {
    TEntity requestToEntity(TRequest request);

}
