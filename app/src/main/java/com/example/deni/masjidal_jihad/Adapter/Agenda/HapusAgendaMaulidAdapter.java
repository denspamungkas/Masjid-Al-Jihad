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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HapusAgendaMaulidAdapter extends RecyclerView.Adapter<HapusAgendaMaulidAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AgendaMaulid> mHapusMaulid;
    private HapusAgendaMaulidAdapter adapter;

    public HapusAgendaMaulidAdapter(Context mContext, ArrayList<AgendaMaulid> mHapusMaulid) {
        this.mContext = mContext;
        this.mHapusMaulid = mHapusMaulid;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hapus_agenda_maulid, parent, false);
        return new HapusAgendaMaulidAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtHapusJudul.setText(mHapusMaulid.get(position).getJudul());
        holder.txtHapusTanggal.setText(mHapusMaulid.get(position).getTanggal());
        holder.txtHapusIsi.setText(mHapusMaulid.get(position).getIsi());

        final String key = mHapusMaulid.get(position).getKey();
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

                        databaseReference.child("AgendaMaulid").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
        return mHapusMaulid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtHapusJudul, txtHapusTanggal, txtHapusIsi;
        Button btnHapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHapusJudul = itemView.findViewById(R.id.txtjudulhapusmaulid);
            txtHapusTanggal = itemView.findViewById(R.id.txttanggalhapusmaulid);
            txtHapusIsi = itemView.findViewById(R.id.txtisihapusmaulid);
            btnHapus = itemView.findViewById(R.id.btn_hapus_maulid);
        }
    }
}
