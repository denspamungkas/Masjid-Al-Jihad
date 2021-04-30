package com.example.deni.masjidal_jihad.Activity.User;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.AgendaIdulAdhaAdapter;
import com.example.deni.masjidal_jihad.Adapter.Informasi.InformasiAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;
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

public class IdulAdhaJamaahActivity extends AppCompatActivity {

    @BindView(R.id.rv_adha)
    RecyclerView rvAdha;
    FirebaseDatabase database;
    DatabaseReference adha;
    ArrayList<AgendaIdulAdha> adhaList;
    AgendaIdulAdhaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idul_adha_jamaah);
        ButterKnife.bind(this);

        rvAdha.setLayoutManager(new LinearLayoutManager(this));
        adhaList = new ArrayList<AgendaIdulAdha>();

        database = FirebaseDatabase.getInstance();
        adha = database.getReference("AgendaAdha");
        adha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AgendaIdulAdha adha = dataSnapshot1.getValue(AgendaIdulAdha.class);
                    adhaList.add(adha);

                }

                adapter = new AgendaIdulAdhaAdapter(IdulAdhaJamaahActivity.this, adhaList);
                rvAdha.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(IdulAdhaJamaahActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}