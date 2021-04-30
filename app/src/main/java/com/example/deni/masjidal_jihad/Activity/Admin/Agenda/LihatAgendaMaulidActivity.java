package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.EditAgendaMaulidAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatAgendaMaulidActivity extends AppCompatActivity {

    @BindView(R.id.recycler_edit_maulid)
    RecyclerView lihatagendamaulid;
    FirebaseDatabase database;
    DatabaseReference agendamaulid;
    ArrayList<AgendaMaulid> agendamaulidList;
    EditAgendaMaulidAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_agenda_maulid);
        ButterKnife.bind(this);
        lihatagendamaulid.setLayoutManager(new LinearLayoutManager(this));
        agendamaulidList = new ArrayList<AgendaMaulid>();

        database = FirebaseDatabase.getInstance();
        agendamaulid = database.getReference("AgendaMaulid");

        agendamaulid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaMaulid Maulid = dataSnapshot1.getValue(AgendaMaulid.class);
                    Maulid.setKey(dataSnapshot1.getKey());
                    agendamaulidList.add(Maulid);

                }

                adapter = new EditAgendaMaulidAdapter(LihatAgendaMaulidActivity.this, agendamaulidList);
                lihatagendamaulid.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LihatAgendaMaulidActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}