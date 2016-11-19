package com.gabriel.traceacessibilidade.service;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.model.OptionProgressListenerEnum;
import com.gabriel.traceacessibilidade.view.MainActivity;

import java.util.Locale;

/**
 * Created by gabriel on 06/11/16.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class OutputVoiceMessageService implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech = null;
    private OutputProgressListener outputProgressListener = null;

    public OutputVoiceMessageService(MainActivity activity, InputVoiceMessageService inputVoiceMessageService) {
        textToSpeech = new TextToSpeech(activity, this);
        outputProgressListener = new OutputProgressListener(activity, inputVoiceMessageService);
        textToSpeech.setOnUtteranceProgressListener(outputProgressListener);
    }

    public void speechToUserAfterListening(String message) {
        textToSpeech.speak(message, TextToSpeech.QUEUE_ADD, null, OptionProgressListenerEnum.AFTER_LISTENING.getId());
    }

    public void speechToUser(String message) {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, OptionProgressListenerEnum.AFTER_NOT_LISTENING.getId());
    }

    public void speechAfterKillAplication(String message) {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, OptionProgressListenerEnum.AFTER_KILL_APPLICATION.getId());
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(Locale.getDefault());
        }
    }
}
