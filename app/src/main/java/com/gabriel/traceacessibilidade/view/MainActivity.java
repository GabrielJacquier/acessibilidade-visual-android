package com.gabriel.traceacessibilidade.view;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gabriel.traceacessibilidade.R;

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
