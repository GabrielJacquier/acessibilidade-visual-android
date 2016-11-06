package com.gabriel.traceacessibilidade.view;

import android.content.Intent;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.gabriel.traceacessibilidade.R;

import java.util.ArrayList;
import java.util.Locale;

import com.gabriel.traceacessibilidade.model.MessageVoiceEnum;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity implements RecognitionListener {

    private SpeechRecognizer speech = null;
    private Intent recognizerIntent = null;
    private TextView returnText;
    TextToSpeech testToSpeech = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        returnText = (TextView) findViewById(R.id.testeText);

        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);

        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "pt-BR");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, "en-US");

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());

        testToSpeech = new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            testToSpeech.setLanguage(Locale.getDefault());
                            testToSpeech.speak(MessageVoiceEnum.APRESENTACAO.getMessage(), TextToSpeech.QUEUE_FLUSH, null, "ID_TRACE_FALA");
                        }
                    }
                }
        );

        testToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {

            }

            @Override
            public void onDone(String utteranceId) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        speech.stopListening();
                        speech.startListening(recognizerIntent);
                    }
                });
            }

            @Override
            public void onError(String utteranceId) {

            }
        });
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.d("D", "passou");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.d("D", "passou");
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.d("D", "passou");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.d("D", "passou");
    }

    @Override
    public void onEndOfSpeech() {
        Log.d("D", "passou");
    }

    @Override
    public void onError(int error) {
        Log.d("D", "passou");
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = matches.get(0);
        if(text.toLowerCase().contains("estou bem")) {
            testToSpeech.speak("Que bom! eu também estou bem!", TextToSpeech.QUEUE_FLUSH, null, "ID_TRACE_FALA");
        } else {
            testToSpeech.speak("Não entendi, você pode repetir?", TextToSpeech.QUEUE_FLUSH, null, "ID_TRACE_FALA");
        }
        returnText.setText(text);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.d("D", "passou");
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.d("D", "passou");
    }
}
