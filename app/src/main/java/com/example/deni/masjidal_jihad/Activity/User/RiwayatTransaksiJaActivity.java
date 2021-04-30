package com.example.deni.masjidal_jihad.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Activity.Admin.Iuran.IuranAdminActivity;
import com.example.deni.masjidal_jihad.Adapter.Iuran.IuranAdapter;
import com.example.deni.masjidal_jihad.Adapter.Iuran.RiwayatTransaksiAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.SharedPreferences.SharedPreferencedConfig;
import com.example.deni.masjidal_jihad.model.Iuran;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RiwayatTransaksiJaActivity extends AppCompatActivity {

    @BindView(R.id.recycler_riwayat_transaksi)
    RecyclerView recyclerRiwayatTransaksi;
    FirebaseDatabase database;
    DatabaseReference riwayatdatabase;
    ArrayList<Iuran> iuranLists;
    RiwayatTransaksiAdapter adapter;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_transaksi_ja);
        ButterKnife.bind(this);
        preferencedConfig = new SharedPreferencedConfig(this);


        recyclerRiwayatTransaksi.setLayoutManager(new LinearLayoutManager(this));
        iuranLists = new ArrayList<Iuran>();

        String username = preferencedConfig.getPreferenceUsername();
        database = FirebaseDatabase.getInstance();
        riwayatdatabase = (DatabaseReference) database.getReference("Iuran").orderByChild("userKey").startAt(username);

        riwayatdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Iuran iuran3 = dataSnapshot1.getValue(Iuran.class);
                    iuran3.setKey(dataSnapshot1.getKey());
                    iuranLists.add(iuran3);
                }
                adapter = new RiwayatTransaksiAdapter(RiwayatTransaksiJaActivity.this, iuranLists);
                recyclerRiwayatTransaksi.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void LoadRecycler() {


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RiwayatTransaksiJaActivity.this, IuranJamaahActivity.class));
        finish();
    }
}