package com.gabriel.traceacessibilidade.business;

import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.model.PublicTransport;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by gabriel on 06/11/16.
 */

public class OutputVoiceMessageBusiness {

    private boolean waitingByName;

    public String responseMessage(String userMessage) {
        return chooseMenssageToResponse(userMessage);
    }

    private String chooseMenssageToResponse(String userMessage) {
        List<String> piecesMessage = Arrays.asList(userMessage.toLowerCase().split(" "));
        if(waitingByName) {
            waitingByName = false;
            MessageEnum response = MessageEnum.RESPONSE_HOUR_PUBLIC_TRANSPORT;
            response.setPublicTransport(serchHourPublicTransport(userMessage));
            return response.getMessage();
        }

        if(piecesMessage.contains("horário") || piecesMessage.contains("hora")) {
            waitingByName = true;
            return MessageEnum.ASK_NAME_TRANSPORT_PUBLIC.getMessage();
        }
        else {
            return MessageEnum.SORRY_NOT_UNDERSTAND.getMessage();
        }
    }

    private PublicTransport serchHourPublicTransport(String namePublicTransport) {
        PublicTransport publicTransport = new PublicTransport();
        publicTransport.setHourBusTerminal(new Date());
        publicTransport.setHourEndPoint(new Date());
        publicTransport.setName(namePublicTransport);
        return publicTransport;
    }
}
