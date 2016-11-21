package com.gabriel.traceacessibilidade.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * Created by gabriel on 06/11/16.
 */

public class PublicTransport implements Serializable{
    private String itinerary;
    private String name;
    private List<Hours> hours;

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getName() {
        return name;
    }

    public List<Hours> getHours() {
        return hours;
    }

    public void setHours(List<Hours> hours) {
        this.hours = hours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String nextHourEndPoint() {
        Date date = new Date();
        int hourNow = date.getHours();
        int minutesNow = date.getMinutes();
        for (Hours hour: hours) {
            if(hour.getMinuteIntEndPoint() > minutesNow && hour.getHourIntEndPoint() == hourNow)
                return hour.getHourEndPointText();
            if(hour.getHourIntEndPoint() > hourNow)
                return hour.getHourEndPointText();
        }
        return hours.get(0).getHourEndPointText();
    }

    public String nextHourBusTerminal() {
        Date date = new Date();
        int hourNow = date.getHours();
        int minutesNow = date.getMinutes();
        for (Hours hour: hours) {
            if(hour.getMinuteIntBusTerminal() > minutesNow && hour.getHourIntBusTerminal() == hourNow)
                return hour.getHourBusTerminalText();
            if(hour.getHourIntBusTerminal() > hourNow)
                return hour.getHourBusTerminalText();
        }
        return hours.get(0).getHourBusTerminalText();
    }
}
