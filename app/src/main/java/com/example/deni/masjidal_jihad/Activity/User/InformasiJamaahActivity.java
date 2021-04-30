package com.example.deni.masjidal_jihad.Activity.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Informasi.InformasiAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Informasi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InformasiJamaahActivity extends AppCompatActivity {



    @BindView(R.id.rv_informasi)
    RecyclerView rvInformasi;
    FirebaseDatabase database;
    DatabaseReference informasi;
    ArrayList<Informasi> informasiList;
    InformasiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_jamaah);
        ButterKnife.bind(this);


        rvInformasi.setLayoutManager(new LinearLayoutManager(this));
        informasiList = new ArrayList<Informasi>();

        database = FirebaseDatabase.getInstance();
        informasi = database.getReference("Informasi");
        informasi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Informasi inf = dataSnapshot1.getValue(Informasi.class);
                    informasiList.add(inf);

                }

                adapter = new InformasiAdapter(InformasiJamaahActivity.this, informasiList);
                rvInformasi.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(InformasiJamaahActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });

    }
}