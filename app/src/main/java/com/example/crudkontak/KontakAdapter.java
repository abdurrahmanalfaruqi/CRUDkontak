package com.example.crudkontak;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class KontakAdapter extends RecyclerView.Adapter<KontakAdapter.KontakViewHolder> {
    private ArrayList<Kontak> listkontak;
    private Refreshinterface refreshinterface;

    public KontakAdapter(ArrayList<Kontak> listkontak, Refreshinterface refreshinterface) {
        this.listkontak = listkontak;
        this.refreshinterface =refreshinterface;
    }

    @NonNull
    @Override
    public KontakAdapter.KontakViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kontak, parent, false);
        return new KontakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontakAdapter.KontakViewHolder holder, final int position) {
        final Kontak kontak = listkontak.get(position);

        holder.tvnama.setText(listkontak.get(position).getNamakontak());
        holder.tvnomor.setText(listkontak.get(position).getNomorkontak());
        holder.tvid.setText(listkontak.get(position).getIdkontak());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UbahKontak.class);
                intent.putExtra("Ubah", listkontak.get(position));
                view.getContext().startActivity(intent);
            }
        });
        holder.btnHapus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setTitle("Anda yakin ");
                alertDialogBuilder
                        .setMessage("Ingin Menghapus ?")
                        .setIcon(R.drawable.ic_delete_black_24dp)
                        .setCancelable(false)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AndroidNetworking.delete("http://seno.idn.sch.id/index.php/kontak")
                                        .setPriority(Priority.MEDIUM)
                                        .addUrlEncodeFormBodyParameter("id",listkontak.get(position).getIdkontak())
                                        .build()
                                        .getAsJSONObject(new JSONObjectRequestListener() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Toast.makeText(view.getContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                                                refreshinterface.requestKontak();

                                            }

                                            @Override
                                            public void onError(ANError anError) {
                                                Toast.makeText(view.getContext(),"Gagal",Toast.LENGTH_SHORT).show();
                                                refreshinterface.requestKontak();

                                            }
                                        });
                            }
                        })

                        .setNegativeButton("Gagal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.show();
            }


        });
    }

    @Override
    public int getItemCount() {
        return listkontak.size();
    }

    public class KontakViewHolder extends RecyclerView.ViewHolder {
        private TextView tvnama, tvid, tvnomor;
        private ImageView btnHapus2;

        public KontakViewHolder(@NonNull View itemView) {
            super(itemView);

            btnHapus2 =itemView.findViewById(R.id.btnhapus2);
            tvnama = itemView.findViewById(R.id.namakontak);
            tvid = itemView.findViewById(R.id.idkontak);
            tvnomor = itemView.findViewById(R.id.nomorkontak);
        }
    }
}
