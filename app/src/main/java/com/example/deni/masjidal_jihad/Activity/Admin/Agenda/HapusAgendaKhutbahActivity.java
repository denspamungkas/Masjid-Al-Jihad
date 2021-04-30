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

import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaKhutbahAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HapusAgendaKhutbahActivity extends AppCompatActivity {
    @BindView(R.id.recycler_hapus_agenda_khutbahi)
    RecyclerView recyclerHapusAgendaKhutbah;
    FirebaseDatabase database;
    DatabaseReference agendakhutbah;
    ArrayList<AgendaKhutbah> agendakhutbahList;
    HapusAgendaKhutbahAdapter adapter;
    @BindView(R.id.RefreshLayoutKhutbah)
    SwipeRefreshLayout RefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_agenda_khutbah);
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
        recyclerHapusAgendaKhutbah.setLayoutManager(new LinearLayoutManager(this));
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

                adapter = new HapusAgendaKhutbahAdapter(HapusAgendaKhutbahActivity.this, agendakhutbahList);
                recyclerHapusAgendaKhutbah.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HapusAgendaKhutbahActivity.this, "ERROR 404", Toast.LENGTH_SHORT).show();
            }

        });
    }
}