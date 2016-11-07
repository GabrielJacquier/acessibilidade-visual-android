package com.gabriel.traceacessibilidade.service;

import android.speech.tts.UtteranceProgressListener;

import com.gabriel.traceacessibilidade.model.OptionProgressListenerEnum;
import com.gabriel.traceacessibilidade.view.MainActivity;

/**
 * Created by gabriel on 06/11/16.
 */

public class OutputProgressListener extends UtteranceProgressListener {

    private InputVoiceMessageService inputVoiceMessageService;
    private MainActivity activityMain;

    public OutputProgressListener(MainActivity activity, InputVoiceMessageService inputVoiceMessageService) {
        this.activityMain = activity;
        this.inputVoiceMessageService = inputVoiceMessageService;
    }

    @Override
    public void onStart(String utteranceId) {

    }

    @Override
    public void onDone(String utteranceId) {
        if(OptionProgressListenerEnum.isListener(utteranceId)) {
            activityMain.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    inputVoiceMessageService.listeningUser();
                }
            });
        }
    }

    @Override
    public void onError(String utteranceId) {

    }
}
