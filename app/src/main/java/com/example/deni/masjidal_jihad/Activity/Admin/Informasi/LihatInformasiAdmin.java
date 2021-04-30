package com.example.deni.masjidal_jihad.Activity.Admin.Informasi;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Informasi.EditInformasiAdapter;
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
import butterknife.BindView;
import butterknife.ButterKnife;

public class LihatInformasiAdmin extends AppCompatActivity {

    @BindView(R.id.recycler_edit_informasi)
    RecyclerView lihatinformasiadmin;
    FirebaseDatabase database;
    DatabaseReference informasi;
    ArrayList<Informasi> informasiList;
    EditInformasiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_informasi_admin);
        ButterKnife.bind(this);
        lihatinformasiadmin.setLayoutManager(new LinearLayoutManager(this));
        informasiList = new ArrayList<Informasi>();

        database = FirebaseDatabase.getInstance();
        informasi = database.getReference("Informasi");

        informasi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Informasi inf = dataSnapshot1.getValue(Informasi.class);
                    inf.setKey(dataSnapshot1.getKey());
                    informasiList.add(inf);

                }

                adapter = new EditInformasiAdapter(LihatInformasiAdmin.this, informasiList);
                lihatinformasiadmin.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LihatInformasiAdmin.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });




    }
}