package com.gabriel.traceacessibilidade.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gabriel on 20/11/16.
 */

public class Hours implements Serializable {

    private String hourEndPoint;
    private String hourBusTerminal;

    public String getHourEndPoint() {
        return hourEndPoint;
    }

    public void setHourEndPoint(String hourEndPoint) {
        this.hourEndPoint = hourEndPoint;
    }

    public String getHourBusTerminal() {
        return hourBusTerminal;
    }

    public void setHourBusTerminal(String hourBusTerminal) {
        this.hourBusTerminal = hourBusTerminal;
    }

    public String getHourEndPointText() {
        return getTextHoraDate(hourEndPoint);
    }

    public String getHourBusTerminalText() {
        return getTextHoraDate(hourBusTerminal);
    }

    private String getTextHoraDate(String time) {
        String times[] = time.split(":");
        String resultHour = "";

        resultHour += times[0] + " horas";
        if(times[1] != "00") {
            resultHour += " e " + times[1];
        }
        return resultHour;
    }

    public Integer getHourIntEndPoint() {
        return Integer.valueOf(hourEndPoint.split(":")[0]);
    }

    public Integer getMinuteIntEndPoint() {
        return Integer.valueOf(hourEndPoint.split(":")[1]);
    }

    public Integer getHourIntBusTerminal() {
        return Integer.valueOf(hourBusTerminal.split(":")[0]);
    }

    public Integer getMinuteIntBusTerminal() {
        return Integer.valueOf(hourBusTerminal.split(":")[1]);
    }
}
