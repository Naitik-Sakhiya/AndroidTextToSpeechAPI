package com.naitiks.androidtexttospeechapi;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech txtToSpeech = null;
    private EditText inputText = null;
    private Button actionSpk = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.input_text);
        actionSpk = (Button) findViewById(R.id.btn_speak);
        actionSpk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech();
            }
        });
        txtToSpeech = new TextToSpeech(MainActivity.this, this);
    }

    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){
            int langResult = txtToSpeech.setLanguage(Locale.US);
            if (langResult == TextToSpeech.LANG_MISSING_DATA
                    || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(MainActivity.this, "TTS : This Language is not supported", Toast.LENGTH_SHORT).show();
            } else{
                actionSpk.setEnabled(true);
            }
        }else{
            Toast.makeText(MainActivity.this, "TTS : Unable to init TTS", Toast.LENGTH_SHORT).show();
        }
    }

    public void textToSpeech(){
        String text = inputText.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            txtToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }else if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            txtToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
