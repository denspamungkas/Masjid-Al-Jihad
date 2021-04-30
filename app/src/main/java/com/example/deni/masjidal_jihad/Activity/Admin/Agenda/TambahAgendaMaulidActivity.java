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
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahAgendaMaulidActivity extends AppCompatActivity {

    @BindView(R.id.btn_tambah_maulid)
    Button btnTambahMaulid;
    @BindView(R.id.judultambahmaulid)
    EditText judultambahmaulid;
    @BindView(R.id.tanggaltambahmaulid)
    EditText tanggaltambahmaulid;
    @BindView(R.id.isitambahmaulid)
    EditText isitambahmaulid;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaeReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_agenda_maulid);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggaltambahmaulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnTambahMaulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahAgendaMaulid();

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

                tanggaltambahmaulid.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();


    }

    private void TambahAgendaMaulid() {
        databaeReference = database.getReference();
        String judul = judultambahmaulid.getText().toString();
        String tanggal = tanggaltambahmaulid.getText().toString();
        String isi = isitambahmaulid.getText().toString();

        if (judul.isEmpty()) {
            judultambahmaulid.setError("Judul Tidak Boleh Kosong");
            judultambahmaulid.requestFocus();
            return;
        } else if (tanggal.isEmpty()) {
            tanggaltambahmaulid.setError("Tanggal Tidak Boleh Kosong");
            tanggaltambahmaulid.requestFocus();
            return;
        } else if (isi.isEmpty()) {
            isitambahmaulid.setError("Isi Tidak Boleh Kosong");
            isitambahmaulid.requestFocus();
            return;
        } else {

            databaeReference.child("AgendaMaulid").push().setValue(new AgendaMaulid(judul, tanggal, isi)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(TambahAgendaMaulidActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    judultambahmaulid.setText("");
                    tanggaltambahmaulid.setText("");
                    isitambahmaulid.setText("");
                    tanggaltambahmaulid.requestFocus();
                    startActivity(new Intent(TambahAgendaMaulidActivity.this, KelolaAgendaMaulidActivity.class));
                    finish();
                }
            });
        }

    }
}