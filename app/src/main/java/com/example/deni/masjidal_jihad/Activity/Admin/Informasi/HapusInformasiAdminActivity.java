package com.example.deni.masjidal_jihad.Activity.Admin.Informasi;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Informasi.HapusInformasiAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Informasi;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HapusInformasiAdminActivity extends AppCompatActivity {

    @BindView(R.id.recycler_hapus_informasi)
    RecyclerView recyclerHapusInformasi;
    FirebaseDatabase database;
    DatabaseReference informasi;
    ArrayList<Informasi> informasiList;
    HapusInformasiAdapter adapter;
    @BindView(R.id.RefreshLayout)
    SwipeRefreshLayout RefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_informasi_admin);
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

        recyclerHapusInformasi.setLayoutManager(new LinearLayoutManager(this));
        informasiList = new ArrayList<Informasi>();

        database = FirebaseDatabase.getInstance();
        informasi = database.getReference("Informasi");

        informasi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Informasi inf = dataSnapshot1.getValue(Informasi.class);
                    inf.setKey(dataSnapshot1.getKey());
                    informasiList.add(inf);
                }

                adapter = new HapusInformasiAdapter(HapusInformasiAdminActivity.this, informasiList);
                recyclerHapusInformasi.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HapusInformasiAdminActivity.this, "ERROR 404", Toast.LENGTH_SHORT).show();
            }
        });
    }
}