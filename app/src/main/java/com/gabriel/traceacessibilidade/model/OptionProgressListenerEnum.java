package com.gabriel.traceacessibilidade.model;

/**
 * Created by gabriel on 06/11/16.
 */

public enum OptionProgressListenerEnum {
    AFTER_LISTENING("AFTER_LISTENING"),
    AFTER_NOT_LISTENING("AFTER_NOT_LISTENING");

    private String id;

    OptionProgressListenerEnum(String id) {
        this.id = id;
    }

    public static boolean isListener(String id) {
        return AFTER_LISTENING.getId().equals(id);
    }

    public String getId() {
        return id;
    }

}
