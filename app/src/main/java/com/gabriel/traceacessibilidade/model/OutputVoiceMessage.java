package com.gabriel.traceacessibilidade.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gabriel on 06/11/16.
 */

public class OutputVoiceMessage {

    private boolean aguardandoNomeOnibus;

    public String responderMensagem(String mensagemUsuario) {
        return escolherMensagemParaResposta(mensagemUsuario);
    }

    private String escolherMensagemParaResposta(String mensagemUsuario) {
        List<String> pedacosMensagem = Arrays.asList(mensagemUsuario.toLowerCase().split(" "));
        if(aguardandoNomeOnibus) {
            aguardandoNomeOnibus = false;
            MessageEnum resposta = MessageEnum.RESPOSTAHORARIOONIBUS;
            resposta.setParametro(mensagemUsuario);
            return resposta.getMessage();
        }

        if(pedacosMensagem.contains("horário") || pedacosMensagem.contains("hora")) {
            aguardandoNomeOnibus = true;
            return MessageEnum.PERGUNTARNOMEONIBUS.getMessage();
        }
        else {
            return MessageEnum.DESCULPENAOENTENDI.getMessage();
        }
    }
}
