package com.example.appmovil_iot;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class incidencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_incidencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ArrayList<Incidencias> listaincidencias = new ArrayList<>();
        IncidenciasAdapter adcincidencia = new IncidenciasAdapter(listaincidencias);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        RecyclerView rvincidencia = findViewById(R.id.lista_incidentes);
        rvincidencia.setLayoutManager(lm);
        rvincidencia.setAdapter(adcincidencia);

        FirebaseDatabase.getInstance().getReference().child("incidencias").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    listaincidencias.clear();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Incidencias incidencia = dataSnapshot.getValue(Incidencias.class);
                        listaincidencias.add(incidencia);
                        adcincidencia.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}