package com.example.deni.masjidal_jihad.Adapter.Iuran;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.Activity.Admin.Iuran.IuranAdminActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Iuran.KonfirmasiPembayaranDkmActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Iuran;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IuranAdapter extends RecyclerView.Adapter<IuranAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Iuran> mIuran;

    public IuranAdapter(Context mContext, ArrayList<Iuran> mIuran) {
        this.mContext = mContext;
        this.mIuran = mIuran;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pembayaran, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtNama.setText(mIuran.get(position).getNama());
        holder.txtBulan.setText(mIuran.get(position).getBulan());
        holder.txtIuran.setText(mIuran.get(position).getIuran());

        final String nama = mIuran.get(position).getNama();
        final String bulan = mIuran.get(position).getBulan();
        final String jumlahIuran = mIuran.get(position).getIuran();
        final String image = mIuran.get(position).getImage();
        final String username = mIuran.get(position).getUsername();
        final String key = mIuran.get(position).getKey();
        final String validasi = mIuran.get(position).getValidasi();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validasi.equals("Diterima") || validasi.equals("Ditolak")){
                    final Dialog dialog = new Dialog(mContext);
                    dialog.setContentView(R.layout.dialog_validasi_iuran);
                    dialog.setTitle("Peringatan");
                    final TextView textTutup = dialog.findViewById(R.id.text_tutup_dialog_validasi);
                    final TextView textValidasi = dialog.findViewById(R.id.status_data_iuran);
                    textValidasi.setText("Data Iuran "+validasi);
                    dialog.show();
                    textTutup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }else {
                    Intent intentKonfirmasi = new Intent(mContext, KonfirmasiPembayaranDkmActivity.class);
                    intentKonfirmasi.putExtra(KonfirmasiPembayaranDkmActivity.EXTRA_NAMA_IURAN, nama);
                    intentKonfirmasi.putExtra(KonfirmasiPembayaranDkmActivity.EXTRA_BULAN_IURAN, bulan);
                    intentKonfirmasi.putExtra(KonfirmasiPembayaranDkmActivity.EXTRA_JUMLAH_IURAN, jumlahIuran);
                    intentKonfirmasi.putExtra(KonfirmasiPembayaranDkmActivity.EXTRA_IMAGE_IURAN, image);
                    intentKonfirmasi.putExtra(KonfirmasiPembayaranDkmActivity.EXTRA_USERNAME_IURAN, username);
                    intentKonfirmasi.putExtra(KonfirmasiPembayaranDkmActivity.EXTRA_KEY_IURAN, key);
                    intentKonfirmasi.putExtra(KonfirmasiPembayaranDkmActivity.EXTRA_VALIDASI_IURAN, validasi);
                    mContext.startActivity(intentKonfirmasi);
                    ((IuranAdminActivity) mContext).finish();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return mIuran.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtBulan, txtIuran;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_iuran_admin);
            txtBulan = itemView.findViewById(R.id.txt_bulan_iuran_admin);
            txtIuran = itemView.findViewById(R.id.jumlah_tagihan_admin);

        }
    }
}
