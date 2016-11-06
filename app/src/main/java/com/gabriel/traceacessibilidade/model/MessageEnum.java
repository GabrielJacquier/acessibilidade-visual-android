package com.gabriel.traceacessibilidade.model;

/**
 * Created by gabriel on 06/11/16.
 */

public enum MessageEnum {
    APRESENTACAO("Olá. Bem-vindo ao aplicativo Acessibilidade! Nós podemos te informar os horários dos ônibus. Tudo o que você precisa fazer é só perguntar, e eu vou te ajudar!"),
    PERGUNTARNOMEONIBUS("De qual Ônibus você gostaria de saber o horário?!"),
    RESPOSTAHORARIOONIBUS("O horário previsto para o ônibus {onibus} é de {horarioPontoFinal} no ponto final! E {horarioTerminal} no terminal."),
    DESCULPENAOENTENDI("Desculpe! Não entendi! Você pode repetir?!");

    private String message;
    private Onibus onibus;
    MessageEnum(String message) {
        this.message = message;
    }

    MessageEnum(String message, String parametro) {
        this.message = message;
    }

    public String getMessage() {
        if(onibus != null) {
            message = message.replace("{onibus}", onibus.getNome());
            message = message.replace("{horarioTerminal}", onibus.getHorarioTerminalText());
            message = message.replace("{horarioPontoFinal}", onibus.getHorarioPontoFinalText());
        }
        return message;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }
}
