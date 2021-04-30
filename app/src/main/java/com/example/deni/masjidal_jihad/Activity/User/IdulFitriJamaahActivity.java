package com.example.deni.masjidal_jihad.Activity.User;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.AgendaIdulFitriAdapter;
import com.example.deni.masjidal_jihad.Adapter.Informasi.InformasiAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulFitri;
import com.example.deni.masjidal_jihad.model.Informasi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IdulFitriJamaahActivity extends AppCompatActivity {

    @BindView(R.id.rv_fitri)
    RecyclerView rvFitri;
    FirebaseDatabase database;
    DatabaseReference fitri;
    ArrayList<AgendaIdulFitri> fitriList;
    AgendaIdulFitriAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idul_fitri_jamaah);
        ButterKnife.bind(this);

        rvFitri.setLayoutManager(new LinearLayoutManager(this));
        fitriList = new ArrayList<AgendaIdulFitri>();

        database = FirebaseDatabase.getInstance();
        fitri = database.getReference("AgendaFitri");
        fitri.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AgendaIdulFitri fitri = dataSnapshot1.getValue(AgendaIdulFitri.class);
                    fitriList.add(fitri);

                }

                adapter = new AgendaIdulFitriAdapter(IdulFitriJamaahActivity.this, fitriList);
                rvFitri.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(IdulFitriJamaahActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}