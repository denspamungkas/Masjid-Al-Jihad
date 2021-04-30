package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.EditAgendaIdulFitriAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulFitri;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatAgendaIdulFitriActivity extends AppCompatActivity {
    @BindView(R.id.recycler_edit_fitri)
    RecyclerView lihatagendaidulfitri;
    FirebaseDatabase database;
    DatabaseReference agendafitri;
    ArrayList<AgendaIdulFitri> agendafitriList;
    EditAgendaIdulFitriAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_agenda_idul_fitri);
        ButterKnife.bind(this);
        lihatagendaidulfitri.setLayoutManager(new LinearLayoutManager(this));
        agendafitriList = new ArrayList<AgendaIdulFitri>();

        database = FirebaseDatabase.getInstance();
        agendafitri = database.getReference("AgendaFitri");

        agendafitri.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaIdulFitri fitri = dataSnapshot1.getValue(AgendaIdulFitri.class);
                    fitri.setKey(dataSnapshot1.getKey());
                    agendafitriList.add(fitri);

                }
                adapter = new EditAgendaIdulFitriAdapter(LihatAgendaIdulFitriActivity.this, agendafitriList);
                lihatagendaidulfitri.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LihatAgendaIdulFitriActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();


            }
        });
    }


}