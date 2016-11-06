package com.gabriel.traceacessibilidade.model;

/**
 * Created by gabriel on 06/11/16.
 */

public enum MessageVoiceEnum {
    APRESENTACAO("Olá. Bem-vindo ao aplicativo Acessibilidade! Nós podemos te informar os horários dos ônibus. Tudo o que você precisa fazer é só perguntar, e eu vou te ajudar");

    private String message;

    MessageVoiceEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
