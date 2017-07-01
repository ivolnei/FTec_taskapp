package br.com.trinopolo.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnTarefa6;
    private Button btnTarefa9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        setupAction();
    }

    private void setupAction() {

        btnTarefa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTarefa("6");
            }
        });

        btnTarefa9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTarefa("9");
            }
        });
    }

    private void abrirTarefa(String id) {

        Intent i = new Intent(MainActivity.this, TarefaActivity.class);
        i.putExtra("id", id);
        MainActivity.this.startActivity(i);
    }

    private void setup() {

        btnTarefa6 = (Button) findViewById(R.id.btnTarefa6);
        btnTarefa9 = (Button) findViewById(R.id.btnTarefa9);
    }
}
