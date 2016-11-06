package com.gabriel.traceacessibilidade.model;

/**
 * Created by gabriel on 06/11/16.
 */

public enum MessageEnum {
    APRESENTACAO("Olá. Bem-vindo ao aplicativo Acessibilidade! Nós podemos te informar os horários dos ônibus. Tudo o que você precisa fazer é só perguntar, e eu vou te ajudar!"),
    PERGUNTARNOMEONIBUS("De qual Ônibus você gostária de saber o horário?"),
    RESPOSTAHORARIOONIBUS("O horário previsto para o ônibus {} é de 7 horas no ponto final! E 7 horas e 15 no terminal."),
    DESCULPENAOENTENDI("Desculpe! Não entendi! Você pode repetir?");

    private String message;
    private String parametro;

    MessageEnum(String message) {
        this.message = message;
    }

    MessageEnum(String message, String parametro) {
        this.message = message;
    }

    public String getMessage() {
        if(parametro != null)
            return message.replace("{}", parametro);
        return message;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
}
