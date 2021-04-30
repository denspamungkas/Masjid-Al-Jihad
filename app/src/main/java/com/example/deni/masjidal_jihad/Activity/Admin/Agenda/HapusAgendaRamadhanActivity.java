package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaMaulidAdapter;
import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaRamadhanAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.example.deni.masjidal_jihad.model.AgendaRamadhan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HapusAgendaRamadhanActivity extends AppCompatActivity {
    @BindView(R.id.recycler_hapus_ramadhan)
    RecyclerView recyclerHapusRamadhan;
    FirebaseDatabase database;
    DatabaseReference ramadhan;
    ArrayList<AgendaRamadhan> agendaramadhanlist;
    HapusAgendaRamadhanAdapter adapter;
    @BindView(R.id.RefreshLayoutRamadhan)
    SwipeRefreshLayout RefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_agenda_ramadhan);
        ButterKnife.bind(this);

        LoadRecycler();

        RefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadRecycler();
                RefreshLayout.setRefreshing(false);
            }
        });
    }

    private void LoadRecycler() {
        recyclerHapusRamadhan.setLayoutManager(new LinearLayoutManager(this));
        agendaramadhanlist = new ArrayList<AgendaRamadhan>();

        database = FirebaseDatabase.getInstance();
        ramadhan = database.getReference("AgendaRamadhan");

        ramadhan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaRamadhan ramadhan = dataSnapshot1.getValue(AgendaRamadhan.class);
                    ramadhan.setKey(dataSnapshot1.getKey());
                    agendaramadhanlist.add(ramadhan);
                }

                adapter = new HapusAgendaRamadhanAdapter(HapusAgendaRamadhanActivity.this, agendaramadhanlist);
                recyclerHapusRamadhan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HapusAgendaRamadhanActivity.this, "ERROR 404", Toast.LENGTH_SHORT).show();

            }
        });

    }
}