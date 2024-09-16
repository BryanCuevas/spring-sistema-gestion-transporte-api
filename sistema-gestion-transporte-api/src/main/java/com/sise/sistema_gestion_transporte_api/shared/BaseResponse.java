package com.sise.sistema_gestion_transporte_api.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String message;
    private boolean success;
    private Object data;

    public BaseResponse(String message, boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static BaseResponse success(Object data) {
        return new BaseResponse("OK", true, data);
    }

    public static BaseResponse success() {
        return new BaseResponse("OK", true, null);
    }
    
    public static BaseResponse error(String message) {
        return new BaseResponse(message, false, null);
    }

    public static BaseResponse errorNotFound() {
        return error("Recursos no encontrado");
    }

}
