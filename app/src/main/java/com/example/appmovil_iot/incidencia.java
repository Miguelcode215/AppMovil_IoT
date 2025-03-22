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

        // Crear la lista que contendrá las incidencias.
        ArrayList<Incidencias> listaIncidencias = new ArrayList<>();

        // Crear el adapter para el RecyclerView.
        IncidenciasAdapter incidenciasAdapter = new IncidenciasAdapter(listaIncidencias);

        // Configurar el RecyclerView.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.lista_incidentes);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(incidenciasAdapter);

        // Configurar el listener de Firebase para escuchar cambios en tiempo real.
        FirebaseDatabase.getInstance().getReference().child("incidencias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Limpiar la lista antes de agregar los nuevos elementos.
                    listaIncidencias.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        // Obtener cada incidencia y agregarla a la lista.
                        Incidencias incidencia = dataSnapshot.getValue(Incidencias.class);
                        listaIncidencias.add(incidencia);
                    }
                    // Notificar al adapter que los datos han cambiado y actualizar la UI.
                    incidenciasAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores si es necesario.
            }
        });
    }
}
