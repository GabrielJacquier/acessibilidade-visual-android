package com.gabriel.traceacessibilidade.service;

import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.model.Onibus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by gabriel on 06/11/16.
 */

public class OutputVoiceMessageService {

    private boolean aguardandoNomeOnibus;

    public String responderMensagem(String mensagemUsuario) {
        return escolherMensagemParaResposta(mensagemUsuario);
    }

    private String escolherMensagemParaResposta(String mensagemUsuario) {
        List<String> pedacosMensagem = Arrays.asList(mensagemUsuario.toLowerCase().split(" "));
        if(aguardandoNomeOnibus) {
            aguardandoNomeOnibus = false;
            MessageEnum resposta = MessageEnum.RESPOSTAHORARIOONIBUS;
            resposta.setOnibus(procurarHorarioOnibus(mensagemUsuario));
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

    private Onibus procurarHorarioOnibus(String nomeOnibus) {
        Onibus onibus = new Onibus();
        onibus.setHorarioPontoFinal(new Date());
        onibus.setHorarioTerminal(new Date());
        onibus.setNome(nomeOnibus);
        return onibus;
    }
}
