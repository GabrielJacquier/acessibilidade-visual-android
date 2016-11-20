package com.gabriel.traceacessibilidade.business;

import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.model.PublicTransport;
import com.gabriel.traceacessibilidade.service.OutputVoiceMessageService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by gabriel on 06/11/16.
 */

public class OutputVoiceMessageBusiness {

    private boolean waitingByName;
    private String userName;
    private OutputVoiceMessageService outputVoiceMessageService;
    private String userMessage;

    public OutputVoiceMessageBusiness(OutputVoiceMessageService outputVoiceMessageService) {
        this.outputVoiceMessageService = outputVoiceMessageService;
    }

    public void responseMessage(String userMessage) {
        this.userMessage = userMessage;
        if(!executeCommandVoice()) {
            String message = chooseMenssageToResponse();
            outputVoiceMessageService.speechToUserAfterListening(message);
        }
    }

    private boolean executeCommandVoice() {
        boolean commandVoiceKill = checkMessage("xau") || checkMessage("tchau") || checkMessage("até logo");
        if(commandVoiceKill) {
            MessageEnum response = MessageEnum.THANK_YOU;
            response.setUserName(userName);
            outputVoiceMessageService.speechAfterKillAplication(response.getMessage());
        }

        if((checkMessage("mais") && checkMessage("rápido")))
            outputVoiceMessageService.upRateVoice();

        if((checkMessage("mais") && (checkMessage("devagar") || checkMessage("lento"))))
            outputVoiceMessageService.downRateVoice();

        return commandVoiceKill;
    }

    private String chooseMenssageToResponse() {
        List<String> piecesMessage = Arrays.asList(userMessage.toLowerCase().split(" "));
        MessageEnum response = null;

        if(userName == null) {
            userName = userMessage;
            response = MessageEnum.RESPONSE_TIP_TO_USER;
        }

        if(waitingByName) {
            waitingByName = false;
            response = MessageEnum.RESPONSE_HOUR_PUBLIC_TRANSPORT;
            response.setPublicTransport(serchHourPublicTransport(userMessage));
        }

        if(piecesMessage.contains("horário") || piecesMessage.contains("hora")) {
            waitingByName = true;
            response = MessageEnum.ASK_NAME_TRANSPORT_PUBLIC;

        } else if (response == null) {
            response = MessageEnum.SORRY_NOT_UNDERSTAND;
        }

        response.setUserName(userName);
        return response.getMessage();
    }

    private boolean checkMessage(String targetMessage) {
        return this.userMessage.contains(targetMessage);
    }

    private PublicTransport serchHourPublicTransport(String namePublicTransport) {
        PublicTransport publicTransport = new PublicTransport();
        publicTransport.setHourBusTerminal(new Date());
        publicTransport.setHourEndPoint(new Date());
        publicTransport.setName(namePublicTransport);
        return publicTransport;
    }
}
