package com.example.deni.masjidal_jihad.Activity.Admin.Iuran;

import android.os.Bundle;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.LihatAgendaIdulAdhaActivity;
import com.example.deni.masjidal_jihad.Adapter.Agenda.EditAgendaIdulAdhaAdapter;
import com.example.deni.masjidal_jihad.Adapter.Iuran.IuranAdapter;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;
import com.example.deni.masjidal_jihad.model.Iuran;
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

public class IuranAdminActivity extends AppCompatActivity {

    @BindView(R.id.rv_iuran_admin)
    RecyclerView rvIuranAdmin;
    FirebaseDatabase database;
    DatabaseReference iurandatabase;
    ArrayList<Iuran> iuranList;
    IuranAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iuran_admin);
        ButterKnife.bind(this);

        rvIuranAdmin.setLayoutManager(new LinearLayoutManager(this));
        iuranList = new ArrayList<Iuran>();

        database = FirebaseDatabase.getInstance();
        iurandatabase = database.getReference("Iuran");

        iurandatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Iuran iuran3 = dataSnapshot1.getValue(Iuran.class);
                    iuran3.setKey(dataSnapshot1.getKey());
                    iuranList.add(iuran3);
                }

                adapter = new IuranAdapter(IuranAdminActivity.this, iuranList);
                rvIuranAdmin.setAdapter(adapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(IuranAdminActivity.this, "Eror 404", Toast.LENGTH_SHORT).show();

            }
        });

    }
}