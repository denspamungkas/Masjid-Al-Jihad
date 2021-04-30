package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TambahAgendaKhutbahActivity extends AppCompatActivity {


    @BindView(R.id.btn_tambah_khutbah)
    Button btnTambahKhutbah;
    @BindView(R.id.tanggaltambahkhutbah)
    EditText tanggaltambahkhutbah;
    @BindView(R.id.khatibtambahkhutbah)
    EditText khatibtambahkhutbah;
    @BindView(R.id.pekantambahkhutbah)
    EditText pekantambahkhutbah;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaeReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_agenda_khutbah);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggaltambahkhutbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnTambahKhutbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahAgendaKhutbah();

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

                tanggaltambahkhutbah.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();


    }

    private void TambahAgendaKhutbah() {
        databaeReference = database.getReference();
        String tanggal = tanggaltambahkhutbah.getText().toString();
        String khatib = khatibtambahkhutbah.getText().toString();
        String pekan = pekantambahkhutbah.getText().toString();

        if (tanggal.isEmpty()) {
            tanggaltambahkhutbah.setError("Tanggal Tidak Boleh Kosong");
            tanggaltambahkhutbah.requestFocus();
            return;
        } else if (khatib.isEmpty()) {
            khatibtambahkhutbah.setError("Khatib Tidak Boleh Kosong");
            khatibtambahkhutbah.requestFocus();
            return;
        } else if (pekan.isEmpty()) {
            pekantambahkhutbah.setError("Pekan Tidak Boleh Kosong");
            pekantambahkhutbah.requestFocus();
            return;
        } else {

            databaeReference.child("AgendaKhutbah").push().setValue(new AgendaKhutbah(tanggal, khatib, pekan)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(TambahAgendaKhutbahActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    tanggaltambahkhutbah.setText("");
                    khatibtambahkhutbah.setText("");
                    pekantambahkhutbah.setText("");
                    tanggaltambahkhutbah.requestFocus();
                    startActivity(new Intent(TambahAgendaKhutbahActivity.this, KelolaAgendaKhutbahActivity.class));
                    finish();
                }
            });
        }

    }
}