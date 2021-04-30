package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaRamadhan;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaRamadhanAdapter extends RecyclerView.Adapter<AgendaRamadhanAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<AgendaRamadhan> mAgendaRamadhan;

    public AgendaRamadhanAdapter(Context mContext, ArrayList<AgendaRamadhan> mAgendaRamadhan) {
        this.mContext = mContext;
        this.mAgendaRamadhan = mAgendaRamadhan;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagendaramadhanuser, parent, false);

        return new AgendaRamadhanAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTanggal.setText(mAgendaRamadhan.get(position).getTanggal());
        holder.txtNama.setText(mAgendaRamadhan.get(position).getNama());
        holder.txtAlamat.setText(mAgendaRamadhan.get(position).getAlamat());
    }

    @Override
    public int getItemCount() {
        return mAgendaRamadhan.size();    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtNama, txtAlamat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txttanggalagendaramadhan);
            txtNama = itemView.findViewById(R.id.txtnamaagendaramadhan);
            txtAlamat = itemView.findViewById(R.id.txtalamatagendaramadhan);
        }
    }
}
