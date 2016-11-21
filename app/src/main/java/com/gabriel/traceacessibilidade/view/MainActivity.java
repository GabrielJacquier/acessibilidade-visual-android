package com.gabriel.traceacessibilidade.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gabriel.traceacessibilidade.R;

import com.gabriel.traceacessibilidade.business.OutputVoiceMessageBusiness;
import com.gabriel.traceacessibilidade.model.MessageEnum;
import com.gabriel.traceacessibilidade.service.InputVoiceMessageService;
import com.gabriel.traceacessibilidade.service.NetworkingService;
import com.gabriel.traceacessibilidade.service.NetworkingTask;
import com.gabriel.traceacessibilidade.service.OutputVoiceMessageService;
import com.gabriel.traceacessibilidade.service.PersistService;

import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    private Button button = null;

    private InputVoiceMessageService inputVoiceMessageService;
    private OutputVoiceMessageService outputVoiceMessageService;
    private OutputVoiceMessageBusiness outputVoiceMessageBusiness;
    private PersistService persistService;
    private NetworkingTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persistService = new PersistService(this);
        task = new NetworkingTask(getSystemService(Context.CONNECTIVITY_SERVICE), persistService);

        inputVoiceMessageService = new InputVoiceMessageService(this);
        outputVoiceMessageService = new OutputVoiceMessageService(this, inputVoiceMessageService);
        outputVoiceMessageBusiness = new OutputVoiceMessageBusiness(outputVoiceMessageService, persistService);
        inputVoiceMessageService.setOutputVoiceMessageService(outputVoiceMessageBusiness);


        button = (Button) findViewById(R.id.apresentar);
        task.execute("http://private-35a570-acessibilidade.apiary-mock.com/horarios");
    }

    public void apresentacao(View view) {
        outputVoiceMessageService.speechToUserAfterListening(MessageEnum.ASK_NAME_USER.getMessage());
        button.setEnabled(false);
    }

}
