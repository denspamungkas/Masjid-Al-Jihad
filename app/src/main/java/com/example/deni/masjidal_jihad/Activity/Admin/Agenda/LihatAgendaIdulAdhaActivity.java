package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.EditAgendaIdulAdhaAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatAgendaIdulAdhaActivity extends AppCompatActivity {
    @BindView(R.id.recycler_edit_adha)
    RecyclerView lihatagendaadha;
    FirebaseDatabase database;
    DatabaseReference agendaadha;
    ArrayList<AgendaIdulAdha> agendaadhaList;
    EditAgendaIdulAdhaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_agenda_idul_adha);
        ButterKnife.bind(this);

        lihatagendaadha.setLayoutManager(new LinearLayoutManager(this));
        agendaadhaList = new ArrayList<AgendaIdulAdha>();

        database = FirebaseDatabase.getInstance();
        agendaadha = database.getReference("AgendaAdha");

        agendaadha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaIdulAdha Adha = dataSnapshot1.getValue(AgendaIdulAdha.class);
                    Adha.setKey(dataSnapshot1.getKey());
                    agendaadhaList.add(Adha);
                }

                adapter = new EditAgendaIdulAdhaAdapter(LihatAgendaIdulAdhaActivity.this, agendaadhaList);
                lihatagendaadha.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(LihatAgendaIdulAdhaActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}