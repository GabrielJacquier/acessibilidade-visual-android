package com.gabriel.traceacessibilidade.model;

/**
 * Created by gabriel on 06/11/16.
 */

public enum MessageEnum {
    PRESENTATION("Bem-vindo ao Acessibilidade!"),
    ASK_NAME_TRANSPORT_PUBLIC("De qual Ônibus você gostaria de saber o horário?!"),
    RESPONSE_HOUR_PUBLIC_TRANSPORT("A previsão para o {onibus} é de {horarioPontoFinal} no ponto final! E {horarioTerminal} no terminal! diga horário para pesquisar novamente!"),
    ASK_NAME_USER("Como é seu nome!?"),
    RESPONSE_TIP_TO_USER("Para saber o horário de um ônibus, diga Horário!"),
    RESPONSE_TIP_HELP_TO_USER("Olá {nomeUsuario}! para saber sobre configurações diga Configurações! Para saber o horário de um ônibus, diga Horário!"),
    THANK_YOU("{nomeUsuario}! Obrigada por usar o Acessibilidade!!"),
    SORRY_NOT_UNDERSTAND("Não entendi! Fale novamente!"),
    HELP_USER("Olá {nomeUsuario}! para alterar a velocidade da minha voz diga! " +
            "Fale mais Rápido! Ou! Fale mais devagar! Para encerrar o aplicativo diga a palavra Xau! " +
            "Espere até ouvir um sinal sonoro para responder! Obrigado!"),
    AFTER_ALTER_RATE_SPEECH("Ok!");

    private String messageTemplate;
    private PublicTransport publicTransport;
    private String userName = "";

    MessageEnum(String message) {
        this.messageTemplate = message;
    }

    public String getMessage() {
        String message = this.messageTemplate;
        if(publicTransport != null) {
            message = message.replace("{onibus}", publicTransport.getName());
            message = message.replace("{horarioTerminal}", publicTransport.nextHourBusTerminal());
            message = message.replace("{horarioPontoFinal}", publicTransport.nextHourEndPoint());
        }
        message = message.replace("{nomeUsuario}", userName);
        return message;
    }

    public void setPublicTransport(PublicTransport publicTransport) {
        this.publicTransport = publicTransport;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
