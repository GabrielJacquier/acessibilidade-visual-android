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

import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.business.OutputVoiceMessageBusiness;
import com.gabriel.traceacessibilidade.service.InputVoiceMessageService;
import com.gabriel.traceacessibilidade.service.OutputVoiceMessageService;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {

    InputVoiceMessageService inputVoiceMessageService;
    OutputVoiceMessageService outputVoiceMessageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputVoiceMessageService = new InputVoiceMessageService(this);
        outputVoiceMessageService = new OutputVoiceMessageService(this, inputVoiceMessageService);
        inputVoiceMessageService.setOutputVoiceMessageService(outputVoiceMessageService);
    }


}
