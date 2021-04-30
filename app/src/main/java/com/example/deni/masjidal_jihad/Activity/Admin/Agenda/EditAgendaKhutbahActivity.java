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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditAgendaKhutbahActivity extends AppCompatActivity {

    public static final String EXTRA_TANGGAL_AGENDA_KHUTBAH = "extraTanggal";
    public static final String EXTRA_KHATIB_AGENDA_KHUTBAH = "extraKhatib";
    public static final String EXTRA_PEKAN_AGENDA_KHUTBAH = "extraPekan";
    public static final String EXTRA_KEY_AGENDA_KHUTBAH = "extraKey";



    @BindView(R.id.edittanggalagendakhutbah)
    EditText edittanggalagendakhatib;
    @BindView(R.id.editkhatibagendakhutbah)
    EditText editkhatibagendakhutbah;
    @BindView(R.id.editpekanagendakhutbah)
    EditText editpekanagendakhutbah;
    @BindView(R.id.btn_lihat_edit_agenda_khutbah)
    Button btnEditKhutbah;

    DatabaseReference databaseReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agenda_khutbah);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        edittanggalagendakhatib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });


        edittanggalagendakhatib.setText(getIntent().getStringExtra(EXTRA_TANGGAL_AGENDA_KHUTBAH));
        editkhatibagendakhutbah.setText(getIntent().getStringExtra(EXTRA_KHATIB_AGENDA_KHUTBAH));
        editpekanagendakhutbah.setText(getIntent().getStringExtra(EXTRA_PEKAN_AGENDA_KHUTBAH));


        btnEditKhutbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = edittanggalagendakhatib.getText().toString();
                String khatib = editkhatibagendakhutbah.getText().toString();
                String pekan = editpekanagendakhutbah.getText().toString();

                if (tanggal.isEmpty()) {
                    edittanggalagendakhatib.setError("Tanggal Tidak Boleh Kosong");
                    edittanggalagendakhatib.requestFocus();
                    return;
                } else if (khatib.isEmpty()) {
                    editkhatibagendakhutbah.setError("Nama Khatib Tidak Boleh Kosong");
                    editkhatibagendakhutbah.requestFocus();
                    return;
                }else if (pekan.isEmpty()) {
                    editpekanagendakhutbah.setError("Pekan Tidak Boleh Kosong");
                    editpekanagendakhutbah.requestFocus();
                    return;
                }else{
                    AgendaKhutbah setAgendaKhutbah = new AgendaKhutbah();
                    setAgendaKhutbah.setTanggal(edittanggalagendakhatib.getText().toString());
                    setAgendaKhutbah.setKhatib(editkhatibagendakhutbah.getText().toString());
                    setAgendaKhutbah.setPekan(editpekanagendakhutbah.getText().toString());
                    updateAgendaKhutbah(setAgendaKhutbah);
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

                edittanggalagendakhatib.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void updateAgendaKhutbah(AgendaKhutbah setAgendaKhutbah) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference= database.getReference();
        String key = getIntent().getStringExtra(EXTRA_KEY_AGENDA_KHUTBAH);

        databaseReference.child("AgendaKhutbah")
                .child(key)
                .setValue(setAgendaKhutbah)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditAgendaKhutbahActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                        edittanggalagendakhatib.setText("");
                        editkhatibagendakhutbah.setText("");
                        editpekanagendakhutbah.setText("");
                        edittanggalagendakhatib.requestFocus();
                        startActivity(new Intent(EditAgendaKhutbahActivity.this, LihatAgendaKhutbahActivity.class));
                        finish();
                    }
                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EditAgendaKhutbahActivity.this, LihatAgendaKhutbahActivity.class));
        finish();
    }
}