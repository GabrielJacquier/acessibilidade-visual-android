package com.gabriel.traceacessibilidade.service;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;

import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.model.OptionProgressListenerEnum;
import com.gabriel.traceacessibilidade.view.MainActivity;

import java.util.Locale;

/**
 * Created by gabriel on 06/11/16.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class OutputVoiceMessageService implements TextToSpeech.OnInitListener {

    private float voiceRate = 3;
    private TextToSpeech textToSpeech = null;
    private OutputProgressListener outputProgressListener = null;

    public OutputVoiceMessageService(MainActivity activity, InputVoiceMessageService inputVoiceMessageService) {
        textToSpeech = new TextToSpeech(activity, this);
        textToSpeech.setSpeechRate(voiceRate);
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

    public void upRateVoice() {
        speechToUser(MessageEnum.AFTER_ALTER_RATE_SPEECH.getMessage());
        voiceRate += 1;
        textToSpeech.setSpeechRate(voiceRate);
    }

    public void downRateVoice() {
        speechToUser(MessageEnum.AFTER_ALTER_RATE_SPEECH.getMessage());
        if(voiceRate != 1) {
            voiceRate -= 1;
            textToSpeech.setSpeechRate(voiceRate);
        }
    }


    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(Locale.getDefault());
        }
    }
}
