package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.EditInformasiAdminActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.LihatInformasiAdmin;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.example.deni.masjidal_jihad.model.Informasi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditAgendaMaulidActivity extends AppCompatActivity {

    public static final String EXTRA_JUDUL_MAULID= "extraJudul";
    public static final String EXTRA_TANGGAL_MAULID = "extraTanggal";
    public static final String EXTRA_ISI_MAULID= "extraIsi";
    public static final String EXTRA_KEY_MAULID = "extraKey";
    @BindView(R.id.juduleditmaulid)
    EditText juduleditmaulid;
    @BindView(R.id.tanggaleditmaulid)
    EditText tanggaleditmaulid;
    @BindView(R.id.isieditmaulid)
    EditText isieditmaulid;
    @BindView(R.id.btn_lihat_edit_maulid)
    Button btnEditMaulid;

    DatabaseReference databaseReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agenda_maulid);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggaleditmaulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });


        juduleditmaulid.setText(getIntent().getStringExtra(EXTRA_JUDUL_MAULID));
        tanggaleditmaulid.setText(getIntent().getStringExtra(EXTRA_TANGGAL_MAULID));
        isieditmaulid.setText(getIntent().getStringExtra(EXTRA_ISI_MAULID));


        btnEditMaulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = juduleditmaulid.getText().toString();
                String tanggal = tanggaleditmaulid.getText().toString();
                String isi = isieditmaulid.getText().toString();

                if (judul.isEmpty()) {
                    juduleditmaulid.setError("Judul Tidak Boleh Kosong");
                    juduleditmaulid.requestFocus();
                    return;
                } else if (tanggal.isEmpty()) {
                    tanggaleditmaulid.setError("Tanggal Tidak Boleh Kosong");
                    tanggaleditmaulid.requestFocus();
                    return;
                }else if (isi.isEmpty()) {
                    isieditmaulid.setError("Isi Tidak Boleh Kosong");
                    isieditmaulid.requestFocus();
                    return;
                }else{
                    AgendaMaulid setAgendaMaulid = new AgendaMaulid();
                    setAgendaMaulid.setJudul(juduleditmaulid.getText().toString());
                    setAgendaMaulid.setTanggal(tanggaleditmaulid.getText().toString());
                    setAgendaMaulid.setIsi(isieditmaulid.getText().toString());
                    updateAgendaMaulid(setAgendaMaulid);
                }

            }
        });



    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tanggaleditmaulid.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void updateAgendaMaulid(AgendaMaulid setAgendaMaulid) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference();
        String key = getIntent().getStringExtra(EXTRA_KEY_MAULID);

        databaseReference.child("AgendaMaulid")
                .child(key)
                .setValue(setAgendaMaulid)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditAgendaMaulidActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                        juduleditmaulid.setText("");
                        tanggaleditmaulid.setText("");
                        isieditmaulid.setText("");
                        tanggaleditmaulid.requestFocus();
                        startActivity(new Intent(EditAgendaMaulidActivity.this, LihatAgendaMaulidActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EditAgendaMaulidActivity.this, LihatAgendaMaulidActivity.class));
        finish();
    }
}