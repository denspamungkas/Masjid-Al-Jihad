package com.example.deni.masjidal_jihad.Adapter.Informasi;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Informasi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HapusInformasiAdapter extends RecyclerView.Adapter<HapusInformasiAdapter.MyViewHolder> {

    private Context mContext;
    private  ArrayList<Informasi> mHapusInformasi;
    private HapusInformasiAdapter adapter;

    public HapusInformasiAdapter(Context mContext, ArrayList<Informasi> mHapusInformasi) {
        this.mContext = mContext;
        this.mHapusInformasi = mHapusInformasi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit_hapus_informasi_admin, parent, false);
        return new HapusInformasiAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtHapusJudul.setText(mHapusInformasi.get(position).getJudul());
        holder.txtHapusTanggal.setText(mHapusInformasi.get(position).getTanggal());
        holder.txtHapusIsi.setText(mHapusInformasi.get(position).getIsi());

        final String key = mHapusInformasi.get(position).getKey();
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

                        databaseReference.child("Informasi").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
        return mHapusInformasi.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtHapusJudul, txtHapusTanggal, txtHapusIsi;
        Button btnHapus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHapusJudul = itemView.findViewById(R.id.txtjudulhapusinformasi);
            txtHapusTanggal = itemView.findViewById(R.id.txttanggalhapusinformasi);
            txtHapusIsi = itemView.findViewById(R.id.txtisihapusinformasi);
            btnHapus = itemView.findViewById(R.id.btn_hapus_informasi);
        }
    }
}
