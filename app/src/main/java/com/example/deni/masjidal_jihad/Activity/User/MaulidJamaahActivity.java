package com.example.deni.masjidal_jihad.Activity.User;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Adapter.Agenda.AgendaIdulAdhaAdapter;
import com.example.deni.masjidal_jihad.Adapter.Agenda.AgendaMaulidAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
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

public class MaulidJamaahActivity extends AppCompatActivity {

    @BindView(R.id.rv_maulid)
    RecyclerView rvMaulid;
    FirebaseDatabase database;
    DatabaseReference maulid;
    ArrayList<AgendaMaulid> maulidList;
    AgendaMaulidAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maulid_jamaah);
        ButterKnife.bind(this);


        rvMaulid.setLayoutManager(new LinearLayoutManager(this));
        maulidList = new ArrayList<AgendaMaulid>();

        database = FirebaseDatabase.getInstance();
        maulid = database.getReference("AgendaMaulid");
        maulid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AgendaMaulid maulid = dataSnapshot1.getValue(AgendaMaulid.class);
                    maulidList.add(maulid);

                }

                adapter = new AgendaMaulidAdapter(MaulidJamaahActivity.this, maulidList);
                rvMaulid.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MaulidJamaahActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });
    }
}