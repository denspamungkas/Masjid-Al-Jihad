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

import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.HapusInformasiAdminActivity;
import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaMaulidAdapter;
import com.example.deni.masjidal_jihad.Adapter.Informasi.HapusInformasiAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.example.deni.masjidal_jihad.model.Informasi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HapusAgendaMaulidActivity extends AppCompatActivity {
    @BindView(R.id.recycler_hapus_maulid)
    RecyclerView recyclerHapusMaulid;
    FirebaseDatabase database;
    DatabaseReference maulid;
    ArrayList<AgendaMaulid> agendamaulidList;
    HapusAgendaMaulidAdapter adapter;
    @BindView(R.id.RefreshLayoutMaulid)
    SwipeRefreshLayout RefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_agenda_maulid);
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

        recyclerHapusMaulid.setLayoutManager(new LinearLayoutManager(this));
        agendamaulidList = new ArrayList<AgendaMaulid>();

        database = FirebaseDatabase.getInstance();
        maulid = database.getReference("AgendaMaulid");

        maulid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AgendaMaulid maulid = dataSnapshot1.getValue(AgendaMaulid.class);
                    maulid.setKey(dataSnapshot1.getKey());
                    agendamaulidList.add(maulid);
                }

                adapter = new HapusAgendaMaulidAdapter(HapusAgendaMaulidActivity.this, agendamaulidList);
                recyclerHapusMaulid.setAdapter(adapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HapusAgendaMaulidActivity.this, "ERROR 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}