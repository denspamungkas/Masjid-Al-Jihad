package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.EditAgendaMaulidAdapter;
import com.example.deni.masjidal_jihad.Adapter.Agenda.EditAgendaRamadhanAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.example.deni.masjidal_jihad.model.AgendaRamadhan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatAgendaRamadhanActivity extends AppCompatActivity {

    @BindView(R.id.recycler_edit_ramadhan)
    RecyclerView lihatagendaramadhan;
    FirebaseDatabase database;
    DatabaseReference agendaramadhan;
    ArrayList<AgendaRamadhan> agendaramadhanlist;
    EditAgendaRamadhanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_agenda_ramadhan);
        ButterKnife.bind(this);
        lihatagendaramadhan.setLayoutManager(new LinearLayoutManager(this));
        agendaramadhanlist = new ArrayList<AgendaRamadhan>();

        database = FirebaseDatabase.getInstance();
        agendaramadhan = database.getReference("AgendaRamadhan");

        agendaramadhan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaRamadhan Ramadhan = dataSnapshot1.getValue(AgendaRamadhan.class);
                    Ramadhan.setKey(dataSnapshot1.getKey());
                    agendaramadhanlist.add(Ramadhan);

                }

                adapter = new EditAgendaRamadhanAdapter(LihatAgendaRamadhanActivity.this, agendaramadhanlist);
                lihatagendaramadhan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LihatAgendaRamadhanActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}