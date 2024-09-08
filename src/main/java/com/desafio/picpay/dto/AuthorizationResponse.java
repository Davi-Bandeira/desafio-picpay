package com.desafio.picpay.dto;

public record AuthorizationResponse(String message) {
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}