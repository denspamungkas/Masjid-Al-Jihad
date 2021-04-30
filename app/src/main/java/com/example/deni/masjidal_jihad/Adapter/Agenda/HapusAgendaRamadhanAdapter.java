package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.example.deni.masjidal_jihad.model.AgendaRamadhan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HapusAgendaRamadhanAdapter extends RecyclerView.Adapter<HapusAgendaRamadhanAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AgendaRamadhan> mHapusRamadhan;
    private HapusAgendaRamadhanAdapter adapter;

    public HapusAgendaRamadhanAdapter(Context mContext, ArrayList<AgendaRamadhan> mHapusRamadhan) {
        this.mContext = mContext;
        this.mHapusRamadhan = mHapusRamadhan;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hapus_agenda_ramadhan, parent, false);
        return new HapusAgendaRamadhanAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtHapusTanggal.setText(mHapusRamadhan.get(position).getTanggal());
        holder.txtHapusNama.setText(mHapusRamadhan.get(position).getNama());
        holder.txtHapusAlamat.setText(mHapusRamadhan.get(position).getAlamat());

        final String key = mHapusRamadhan.get(position).getKey();
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(mContext);

                dialog.setContentView(R.layout.dialog_hapus_konfirmasi);
                dialog.setTitle("Konfirmasi");

                final TextView txtConfirmHapus = dialog.findViewById(R.id.text_confirm_hapus);
                final TextView txtConfirmBatal = dialog.findViewById(R.id.text_confirm_batal);

                dialog.show();

                txtConfirmHapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = database.getReference();

                        databaseReference.child("AgendaRamadhan").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(mContext, "Hapus Data Berhasil", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        });
                    }
                });

                txtConfirmBatal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHapusRamadhan.size();    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtHapusTanggal, txtHapusNama, txtHapusAlamat;
        Button btnHapus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHapusTanggal = itemView.findViewById(R.id.txttanggalhapusramadhan);
            txtHapusNama = itemView.findViewById(R.id.txtnamahapusramadhan);
            txtHapusAlamat = itemView.findViewById(R.id.txtalamathapusramadhan);
            btnHapus = itemView.findViewById(R.id.btn_hapus_agenda_ramadhan);
        }
    }
}
