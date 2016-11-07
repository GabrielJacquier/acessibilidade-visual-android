package com.gabriel.traceacessibilidade.model;

/**
 * Created by gabriel on 06/11/16.
 */

public enum OptionProgressListenerEnum {
    AFTER_LISTENING("AFTER_LISTENING"),
    AFTER_NOT_LISTENING("AFTER_NOT_LISTENING"),
    AFTER_KILL_APPLICATION("AFTER_KILL_APPLICATION");

    private String id;

    OptionProgressListenerEnum(String id) {
        this.id = id;
    }

    public static boolean isListener(String id) {
        return AFTER_LISTENING.getId().equals(id);
    }

    public static boolean isKill(String id) {
        return AFTER_KILL_APPLICATION.getId().equals(id);
    }

    public String getId() {
        return id;
    }

}
