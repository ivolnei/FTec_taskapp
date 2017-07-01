package br.com.trinopolo.taskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TarefaActivity extends AppCompatActivity {

    private Button btnSalvar;
    private Switch swConcluido;
    private EditText etNome;
    private TarefaService tarefaService;
    private String tarefaId;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        setupRetrofit();
        setup();
        setupAction();
        obter();
    }

    private void setupRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mysterious-meadow-17207.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tarefaService = retrofit.create(TarefaService.class);
    }

    private void obter() {

        tarefaId = getIntent().getStringExtra("id");

        tarefaService.get(tarefaId).enqueue(new Callback<Tarefa>() {
            @Override
            public void onResponse(Call<Tarefa> call, Response<Tarefa> response) {

                tarefa = response.body();
                atualizaTela();
            }

            @Override
            public void onFailure(Call<Tarefa> call, Throwable t) {

            }
        });
    }

    private void atualizaTela() {
        if(tarefa != null) {

            etNome.setText(tarefa.corpo);
            swConcluido.setChecked(tarefa.concluida);
        }
    }

    private void setupAction() {

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar() {

        tarefa.corpo = etNome.getText().toString();
        tarefa.concluida = swConcluido.isChecked();

        tarefaService.put(tarefaId, tarefa).enqueue(new Callback<Tarefa>() {
            @Override
            public void onResponse(Call<Tarefa> call, Response<Tarefa> response) {

                finish();
            }

            @Override
            public void onFailure(Call<Tarefa> call, Throwable t) {

            }
        });
    }

    private void setup() {

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        swConcluido = (Switch) findViewById(R.id.swConcluido);
        etNome = (EditText) findViewById(R.id.etNome);
    }
}
