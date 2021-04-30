package com.example.deni.masjidal_jihad.Activity.User;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.AgendaKhutbahAdapter;
import com.example.deni.masjidal_jihad.Adapter.Informasi.InformasiAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;
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

public class KhutbahJamaahActivity extends AppCompatActivity {

    @BindView(R.id.rv_khutbah)
    RecyclerView rvKhutbah;
    FirebaseDatabase database;
    DatabaseReference khutbah;
    ArrayList<AgendaKhutbah> khutbahList;
    AgendaKhutbahAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khutbah_jamaah);
        ButterKnife.bind(this);


        rvKhutbah.setLayoutManager(new LinearLayoutManager(this));
        khutbahList = new ArrayList<AgendaKhutbah>();

        database = FirebaseDatabase.getInstance();
        khutbah = database.getReference("AgendaKhutbah");
        khutbah.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AgendaKhutbah khutbah = dataSnapshot1.getValue(AgendaKhutbah.class);
                    khutbahList.add(khutbah);

                }

                adapter = new AgendaKhutbahAdapter(KhutbahJamaahActivity.this, khutbahList);
                rvKhutbah.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(KhutbahJamaahActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}