package com.gabriel.traceacessibilidade.service;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;

import com.gabriel.traceacessibilidade.business.OutputVoiceMessageBusiness;
import com.gabriel.traceacessibilidade.view.MainActivity;

import java.util.ArrayList;

/**
 * Created by gabriel on 06/11/16.
 */

public class InputVoiceMessageService implements RecognitionListener {
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent = null;
    private OutputVoiceMessageService outputVoiceMessageService;
    private OutputVoiceMessageBusiness outputVoiceMessage = new OutputVoiceMessageBusiness();


    public InputVoiceMessageService(MainActivity activity) {
        speech = SpeechRecognizer.createSpeechRecognizer(activity);
        speech.setRecognitionListener(this);
        setUpIntent();
    }

    private void setUpIntent() {
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "pt-BR");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, "en-US");
    }

    public void listeningUser() {
        speech.stopListening();
        speech.startListening(recognizerIntent);
    }

    public void setOutputVoiceMessageService(OutputVoiceMessageService outputVoiceMessageService) {
        this.outputVoiceMessageService = outputVoiceMessageService;
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> possibleResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String userMessage = possibleResults.get(0);
        outputVoiceMessageService.speechToUser(outputVoiceMessage.responseMessage(userMessage));
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
