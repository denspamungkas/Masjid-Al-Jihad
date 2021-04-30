package com.example.deni.masjidal_jihad.Activity.User;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.AgendaMaulidAdapter;
import com.example.deni.masjidal_jihad.Adapter.Agenda.AgendaRamadhanAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.example.deni.masjidal_jihad.model.AgendaRamadhan;
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

public class RamadhanJaActivity extends AppCompatActivity {

    @BindView(R.id.rv_ramadhan)
    RecyclerView rvRamadhan;
    FirebaseDatabase database;
    DatabaseReference ramadhan;
    ArrayList<AgendaRamadhan> ramadhanList;
    AgendaRamadhanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramadhan_ja);
        ButterKnife.bind(this);


        rvRamadhan.setLayoutManager(new LinearLayoutManager(this));
        ramadhanList = new ArrayList<AgendaRamadhan>();

        database = FirebaseDatabase.getInstance();
        ramadhan = database.getReference("AgendaRamadhan");
        ramadhan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AgendaRamadhan ramadhan = dataSnapshot1.getValue(AgendaRamadhan.class);
                    ramadhanList.add(ramadhan);

                }

                adapter = new AgendaRamadhanAdapter(RamadhanJaActivity.this, ramadhanList);
                rvRamadhan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RamadhanJaActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}