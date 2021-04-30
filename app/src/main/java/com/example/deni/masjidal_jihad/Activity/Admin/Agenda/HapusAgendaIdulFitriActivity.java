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

import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaIdulFitriAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulFitri;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HapusAgendaIdulFitriActivity extends AppCompatActivity {
    @BindView(R.id.recycler_hapus_agenda_fitri)
    RecyclerView recyclerHapusAgendaFitri;
    FirebaseDatabase database;
    DatabaseReference fitri;
    ArrayList<AgendaIdulFitri> agendafitriList;
    HapusAgendaIdulFitriAdapter adapter;
    @BindView(R.id.RefreshLayoutFitri)
    SwipeRefreshLayout RefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_agenda_idul_fitri);
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
        recyclerHapusAgendaFitri.setLayoutManager(new LinearLayoutManager(this));
        agendafitriList = new ArrayList<AgendaIdulFitri>();

        database = FirebaseDatabase.getInstance();
        fitri = database.getReference("AgendaFitri");

        fitri.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaIdulFitri fitri = dataSnapshot1.getValue(AgendaIdulFitri.class);
                    fitri.setKey(dataSnapshot1.getKey());
                    agendafitriList.add(fitri);
                }

                adapter = new HapusAgendaIdulFitriAdapter(HapusAgendaIdulFitriActivity.this, agendafitriList);
                recyclerHapusAgendaFitri.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HapusAgendaIdulFitriActivity.this, "ERROR 404", Toast.LENGTH_SHORT).show();

            }
        });
    }


}