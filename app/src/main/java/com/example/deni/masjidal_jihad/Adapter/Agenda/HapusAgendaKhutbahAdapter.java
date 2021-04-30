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
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HapusAgendaKhutbahAdapter extends RecyclerView.Adapter<HapusAgendaKhutbahAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<AgendaKhutbah> mHapusAgendaKhutbah;
    private HapusAgendaKhutbahAdapter adapter;


    public HapusAgendaKhutbahAdapter(Context mContext, ArrayList<AgendaKhutbah> mHapusAgendaKhutbah) {
        this.mContext = mContext;
        this.mHapusAgendaKhutbah = mHapusAgendaKhutbah;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hapus_agenda_khutbah, parent, false);
        return new HapusAgendaKhutbahAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtHapusTanggal.setText(mHapusAgendaKhutbah.get(position).getTanggal());
        holder.txtHapusKhatib.setText(mHapusAgendaKhutbah.get(position).getKhatib());
        holder.txtHapusPekan.setText(mHapusAgendaKhutbah.get(position).getPekan());

        final String key = mHapusAgendaKhutbah.get(position).getKey();
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

                        databaseReference.child("AgendaKhutbah").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
        return mHapusAgendaKhutbah.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtHapusTanggal, txtHapusKhatib, txtHapusPekan;
        Button btnHapus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHapusTanggal = itemView.findViewById(R.id.txttanggalhapusagendakhutbah);
            txtHapusKhatib = itemView.findViewById(R.id.txtkhatibhapusagendakhutbah);
            txtHapusPekan = itemView.findViewById(R.id.txtpekanhapusagendakhutbah);
            btnHapus = itemView.findViewById(R.id.btn_hapus_agenda_khutbah);
        }
    }
}
