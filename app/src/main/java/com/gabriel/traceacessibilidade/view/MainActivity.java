package com.gabriel.traceacessibilidade.view;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gabriel.traceacessibilidade.R;

import com.gabriel.traceacessibilidade.business.OutputVoiceMessageBusiness;
import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.service.InputVoiceMessageService;
import com.gabriel.traceacessibilidade.service.OutputVoiceMessageService;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    private Button button = null;

    private InputVoiceMessageService inputVoiceMessageService;
    private OutputVoiceMessageService outputVoiceMessageService;
    private OutputVoiceMessageBusiness outputVoiceMessageBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputVoiceMessageService = new InputVoiceMessageService(this);
        outputVoiceMessageService = new OutputVoiceMessageService(this, inputVoiceMessageService);
        outputVoiceMessageBusiness = new OutputVoiceMessageBusiness(outputVoiceMessageService);

        inputVoiceMessageService.setOutputVoiceMessageService(outputVoiceMessageBusiness);

        button = (Button) findViewById(R.id.apresentar);
    }

    public void apresentacao(View view) {
        outputVoiceMessageService.speechToUserAfterListening(MessageEnum.ASK_NAME_USER.getMessage());
        button.setEnabled(false);
    }

}
