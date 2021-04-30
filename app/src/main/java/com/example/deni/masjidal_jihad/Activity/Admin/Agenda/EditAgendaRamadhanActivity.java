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

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.example.deni.masjidal_jihad.model.AgendaRamadhan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditAgendaRamadhanActivity extends AppCompatActivity {

    public static final String EXTRA_TANGGAL_RAMADHAN= "extraTanggal";
    public static final String EXTRA_NAMA_RAMADHAN = "extraNama";
    public static final String EXTRA_ALAMAT_RAMADHAN= "extraAlamat";
    public static final String EXTRA_KEY_RAMADHAN = "extraKey";
    @BindView(R.id.tanggaleditramadhan)
    EditText tanggaleditramadhan;
    @BindView(R.id.namaeditramadhan)
    EditText namaeditramadhan;
    @BindView(R.id.alamateditramadhan)
    EditText alamateditramadhan;
    @BindView(R.id.btn_lihat_edit_ramadhan)
    Button btnEditRamadhan;

    DatabaseReference databaseReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agenda_ramadhan);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggaleditramadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });

        tanggaleditramadhan.setText(getIntent().getStringExtra(EXTRA_TANGGAL_RAMADHAN));
        namaeditramadhan.setText(getIntent().getStringExtra(EXTRA_NAMA_RAMADHAN));
        alamateditramadhan.setText(getIntent().getStringExtra(EXTRA_ALAMAT_RAMADHAN));

        btnEditRamadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = tanggaleditramadhan.getText().toString();
                String nama = namaeditramadhan.getText().toString();
                String alamat = alamateditramadhan.getText().toString();

                if (tanggal.isEmpty()) {
                    tanggaleditramadhan.setError("Tanggal Tidak Boleh Kosong");
                    tanggaleditramadhan.requestFocus();
                    return;
                } else if (nama.isEmpty()) {
                    namaeditramadhan.setError("Nama Tidak Boleh Kosong");
                    namaeditramadhan.requestFocus();
                    return;
                }else if (alamat.isEmpty()) {
                    alamateditramadhan.setError("Alamat Tidak Boleh Kosong");
                    alamateditramadhan.requestFocus();
                    return;
                }else{
                    AgendaRamadhan setAgendaRamadhan = new AgendaRamadhan();
                    setAgendaRamadhan.setTanggal(tanggaleditramadhan.getText().toString());
                    setAgendaRamadhan.setNama(namaeditramadhan.getText().toString());
                    setAgendaRamadhan.setAlamat(alamateditramadhan.getText().toString());
                    updateAgendaRamadhan(setAgendaRamadhan);
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

                tanggaleditramadhan.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void updateAgendaRamadhan(AgendaRamadhan setAgendaRamadhan) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference();
        String key = getIntent().getStringExtra(EXTRA_KEY_RAMADHAN);

        databaseReference.child("AgendaRamadhan")
                .child(key)
                .setValue(setAgendaRamadhan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditAgendaRamadhanActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                        tanggaleditramadhan.setText("");
                        namaeditramadhan.setText("");
                        alamateditramadhan.setText("");
                        tanggaleditramadhan.requestFocus();
                        startActivity(new Intent(EditAgendaRamadhanActivity.this, LihatAgendaRamadhanActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EditAgendaRamadhanActivity.this, LihatAgendaRamadhanActivity.class));
        finish();
    }
}