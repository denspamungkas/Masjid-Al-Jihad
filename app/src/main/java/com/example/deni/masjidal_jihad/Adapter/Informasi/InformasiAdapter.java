package com.example.deni.masjidal_jihad.Adapter.Informasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Informasi;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InformasiAdapter extends RecyclerView.Adapter<InformasiAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Informasi> mInformasi;

    public InformasiAdapter(Context mContext, ArrayList<Informasi> mInformasi) {
        this.mContext = mContext;
        this.mInformasi = mInformasi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listinformasiuser, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtJudul.setText(mInformasi.get(position).getJudul());
        holder.txtTanggal.setText(mInformasi.get(position).getTanggal());
        holder.txtIsi.setText(mInformasi.get(position).getIsi());


    }

    @Override
    public int getItemCount() {
        return mInformasi.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtTanggal, txtIsi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtjudulinformasi);
            txtTanggal = itemView.findViewById(R.id.txttanggalinformasi);
            txtIsi = itemView.findViewById(R.id.txtisiinformasi);
        }
    }
}
