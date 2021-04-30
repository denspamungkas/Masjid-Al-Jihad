package com.example.deni.masjidal_jihad.Adapter.Iuran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Iuran;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RiwayatTransaksiAdapter extends RecyclerView.Adapter<RiwayatTransaksiAdapter.RiwayatViewHolder> {

    Context mContext;
    List<Iuran> iuranItems;

    public RiwayatTransaksiAdapter(Context mContext, List<Iuran> iuranItems) {
        this.mContext = mContext;
        this.iuranItems = iuranItems;
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_iuran_jamaah, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        holder.txtNama.setText(iuranItems.get(position).getNama());
        holder.txtBulan.setText(iuranItems.get(position).getBulan());
        holder.txtIuran.setText(iuranItems.get(position).getIuran());
    }

    @Override
    public int getItemCount() {
        return iuranItems.size();
    }

    public class RiwayatViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtBulan, txtIuran;
        public RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txt_nama_iuran_user);
            txtBulan = itemView.findViewById(R.id.txt_bulan_iuran_user);
            txtIuran = itemView.findViewById(R.id.jumlah_tagihan_user);
        }
    }
}
