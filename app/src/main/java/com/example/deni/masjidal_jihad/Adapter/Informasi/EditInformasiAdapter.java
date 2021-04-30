package com.example.deni.masjidal_jihad.Adapter.Informasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.EditInformasiAdminActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.LihatInformasiAdmin;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Informasi;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditInformasiAdapter extends RecyclerView.Adapter<EditInformasiAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Informasi> mEditInformasi;

    public EditInformasiAdapter(Context mContext, ArrayList<Informasi> mEditInformasi){
        this.mContext = mContext;
        this.mEditInformasi = mEditInformasi;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit_informasi_admin, parent, false);


        return new EditInformasiAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtJudul.setText(mEditInformasi.get(position).getJudul());
        holder.txtTanggal.setText(mEditInformasi.get(position).getTanggal());
        holder.txtIsi.setText(mEditInformasi.get(position).getIsi());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = mEditInformasi.get(position).getJudul();
                String tanggal = mEditInformasi.get(position).getTanggal();
                String isi = mEditInformasi.get(position).getIsi();
                String key = mEditInformasi.get(position).getKey();

                Intent intent = new Intent(mContext, EditInformasiAdminActivity.class);
                intent.putExtra(EditInformasiAdminActivity.EXTRA_JUDUL_INFORMASI, judul);
                intent.putExtra(EditInformasiAdminActivity.EXTRA_TANGGAL_INFORMASI, tanggal);
                intent.putExtra(EditInformasiAdminActivity.EXTRA_ISI_INFORMASI, isi);
                intent.putExtra(EditInformasiAdminActivity.EXTRA_KEY_INFORMASI, key);
                mContext.startActivity(intent);
                ((LihatInformasiAdmin)mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEditInformasi.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtTanggal, txtIsi;
        Button btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtjuduleditinformasi);
            txtTanggal = itemView.findViewById(R.id.txttanggaleditinformasi);
            txtIsi = itemView.findViewById(R.id.txtisieditinformasi);
            btnEdit = itemView.findViewById(R.id.btn_edit_informasi);
        }
    }
}
