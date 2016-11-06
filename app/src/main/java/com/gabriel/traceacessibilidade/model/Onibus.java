package com.gabriel.traceacessibilidade.model;

import java.util.Date;
import java.util.Timer;

/**
 * Created by gabriel on 06/11/16.
 */

public class Onibus {
    private String itinerario;
    private String nome;
    private Date horarioPontoFinal;
    private Date horarioTerminal;

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getHorarioPontoFinal() {
        return horarioPontoFinal;
    }

    public void setHorarioPontoFinal(Date horarioPontoFinal) {
        this.horarioPontoFinal = horarioPontoFinal;
    }

    public Date getHorarioTerminal() {
        return horarioTerminal;
    }

    public void setHorarioTerminal(Date horarioTerminal) {
        this.horarioTerminal = horarioTerminal;
    }

    public String getHorarioPontoFinalText() {
        return getTextHoraDate(horarioPontoFinal);
    }
    public String getHorarioTerminalText() {
        return getTextHoraDate(horarioTerminal);
    }

    private String getTextHoraDate(Date data) {
        String horario = "";
        horario += String.valueOf(data.getHours()) + " horas";
        if(data.getMinutes() != 0) {
            horario += " e " + String.valueOf(data.getMinutes());
        }
        return horario;
    }
}
