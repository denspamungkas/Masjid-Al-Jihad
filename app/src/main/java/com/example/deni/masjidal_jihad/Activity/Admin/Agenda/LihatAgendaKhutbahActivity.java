package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.EditAgendaKhutbahAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;
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

public class LihatAgendaKhutbahActivity extends AppCompatActivity {

    @BindView(R.id.recycler_edit_khatib)
    RecyclerView lihatagendakhutbah;
    FirebaseDatabase database;
    DatabaseReference agendakhutbah;
    ArrayList<AgendaKhutbah> agendakhutbahList;
    EditAgendaKhutbahAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_agenda_khutbah);
        ButterKnife.bind(this);
        lihatagendakhutbah.setLayoutManager(new LinearLayoutManager(this));
        agendakhutbahList = new ArrayList<AgendaKhutbah>();

        database = FirebaseDatabase.getInstance();
        agendakhutbah = database.getReference("AgendaKhutbah");

        agendakhutbah.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaKhutbah khutbah = dataSnapshot1.getValue(AgendaKhutbah.class);
                    khutbah.setKey(dataSnapshot1.getKey());
                    agendakhutbahList.add(khutbah);

                }

                adapter = new EditAgendaKhutbahAdapter(LihatAgendaKhutbahActivity.this, agendakhutbahList);
                lihatagendakhutbah.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LihatAgendaKhutbahActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });


    }
}