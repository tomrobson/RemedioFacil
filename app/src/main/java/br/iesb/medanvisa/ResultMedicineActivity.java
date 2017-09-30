package br.iesb.medanvisa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.iesb.medanvisa.model.Remedio;

public class ResultMedicineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_medicine);
        inicializaComponentes();

        List<Remedio> remedios = new ArrayList<>();
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));
        remedios.add(new Remedio("123456", "LEVOTIROXINA SÓDICA", "PURAN T4", 5.72, 9.8));

        recyclerView.setAdapter(new RemedioAdapter(remedios, this));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

    }

    private void inicializaComponentes() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
    }
}
