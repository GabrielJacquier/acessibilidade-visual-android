package com.gabriel.traceacessibilidade.model;

import java.util.Date;
import java.util.Timer;

/**
 * Created by gabriel on 06/11/16.
 */

public class PublicTransport {
    private String itinerary;
    private String name;
    private Date hourEndPoint;
    private Date hourBusTerminal;

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHourEndPoint() {
        return hourEndPoint;
    }

    public void setHourEndPoint(Date hourEndPoint) {
        this.hourEndPoint = hourEndPoint;
    }

    public Date getHourBusTerminal() {
        return hourBusTerminal;
    }

    public void setHourBusTerminal(Date hourBusTerminal) {
        this.hourBusTerminal = hourBusTerminal;
    }

    public String getHourEndPointText() {
        return getTextHoraDate(hourEndPoint);
    }

    public String getHourBusTerminalText() {
        return getTextHoraDate(hourBusTerminal);
    }

    private String getTextHoraDate(Date date) {
        String hour = "";
        hour += String.valueOf(date.getHours()) + " horas";
        if(date.getMinutes() != 0) {
            hour += " e " + String.valueOf(date.getMinutes());
        }
        return hour;
    }
}
