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

import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaIdulAdhaAdapter;
import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaMaulidAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HapusAgendaIdulAdhaActivity extends AppCompatActivity {
    @BindView(R.id.recycler_hapus_adha)
    RecyclerView recyclerHapusAdha;
    FirebaseDatabase database;
    DatabaseReference adha;
    ArrayList<AgendaIdulAdha> agendaadhalist;
    HapusAgendaIdulAdhaAdapter adapter;
    @BindView(R.id.RefreshLayoutAdha)
    SwipeRefreshLayout RefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_agenda_idul_adha);

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

        recyclerHapusAdha.setLayoutManager(new LinearLayoutManager(this));
        agendaadhalist = new ArrayList<AgendaIdulAdha>();

        database = FirebaseDatabase.getInstance();
        adha = database.getReference("AgendaAdha");

        adha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaIdulAdha adha = dataSnapshot1.getValue(AgendaIdulAdha.class);
                    adha.setKey(dataSnapshot1.getKey());
                    agendaadhalist.add(adha);
                }

                adapter = new HapusAgendaIdulAdhaAdapter(HapusAgendaIdulAdhaActivity.this, agendaadhalist);
                recyclerHapusAdha.setAdapter(adapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HapusAgendaIdulAdhaActivity.this, "ERROR 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}