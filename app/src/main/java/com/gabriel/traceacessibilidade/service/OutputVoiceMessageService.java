package com.gabriel.traceacessibilidade.service;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.view.MainActivity;

import java.util.Locale;

/**
 * Created by gabriel on 06/11/16.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class OutputVoiceMessageService extends UtteranceProgressListener implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech = null;
    private MainActivity activityMain;
    private InputVoiceMessageService inputVoiceMessageService;

    public OutputVoiceMessageService(MainActivity activity, InputVoiceMessageService inputVoiceMessageService) {
        this.activityMain = activity;
        this.inputVoiceMessageService = inputVoiceMessageService;
        textToSpeech = new TextToSpeech(activity, this);
        textToSpeech.setOnUtteranceProgressListener(this);
    }

    public void speechToUser(String message) {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, "ID_TRACE_FALA");
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(Locale.getDefault());
            textToSpeech.speak(MessageEnum.APRESENTATION.getMessage(), TextToSpeech.QUEUE_FLUSH, null, "ID_TRACE_FALA");
        }
    }

    @Override
    public void onStart(String utteranceId) {

    }

    @Override
    public void onDone(String utteranceId) {
        activityMain.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                inputVoiceMessageService.listeningUser();
            }
        });
    }

    @Override
    public void onError(String utteranceId) {

    }
}
