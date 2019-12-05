package com.example.crudkontak;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class UbahKontak extends AppCompatActivity {
    private EditText etnama2,etnomor2;
    private TextView tvnama2,tvnomor2;
    private Button btnUbah,btnHapus;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubahkontak);

        etnama2 = findViewById(R.id.etnama2);
        etnomor2 = findViewById(R.id.etnomor2);
        tvnama2 = findViewById(R.id.tvnama2);
        tvnomor2 = findViewById(R.id.tvnomor2);
        btnUbah = findViewById(R.id.btnUbah2);
        progressBar = findViewById(R.id.progressBar2);
        btnHapus =findViewById(R.id.btnHapus);


        final Kontak kontak = getIntent().getParcelableExtra("Ubah");
        {
            etnomor2.setText(kontak.getNomorkontak());
            etnama2.setText(kontak.getNamakontak());
            tvnomor2.setText(kontak.getNomorkontak());
            tvnama2.setText(kontak.getNamakontak());

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (etnomor2.getText().length() > 0 && etnama2.getText().length() > 0) {

                        progressBar.setVisibility(View.VISIBLE);

                        AndroidNetworking.delete("http://seno.idn.sch.id/index.php/kontak")
                                .setPriority(Priority.MEDIUM)
                                .addUrlEncodeFormBodyParameter("nomor", etnomor2.getText().toString())
                                .addUrlEncodeFormBodyParameter("nama", etnama2.getText().toString())
                                .addUrlEncodeFormBodyParameter("id", kontak.getIdkontak())
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(UbahKontak.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        finish();
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        Toast.makeText(UbahKontak.this, "Gagal", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }


                                });
                    } else {
                        Toast.makeText(UbahKontak.this, "Isi Nama dan Nomor", Toast.LENGTH_SHORT).show();
                    }


                }
            });

                    btnUbah.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (etnomor2.getText().length() > 0 && etnama2.getText().length() > 0) {

                                progressBar.setVisibility(View.VISIBLE);

                                AndroidNetworking.put("http://seno.idn.sch.id/index.php/kontak")
                                        .setPriority(Priority.MEDIUM)
                                        .addUrlEncodeFormBodyParameter("nomor", etnomor2.getText().toString())
                                        .addUrlEncodeFormBodyParameter("nama", etnama2.getText().toString())
                                        .addUrlEncodeFormBodyParameter("id", kontak.getIdkontak())
                                        .build()
                                        .getAsJSONObject(new JSONObjectRequestListener() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Toast.makeText(UbahKontak.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                finish();
                                            }

                                            @Override
                                            public void onError(ANError anError) {
                                                Toast.makeText(UbahKontak.this, "Gagal", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }


                                        });
                            } else {
                                Toast.makeText(UbahKontak.this, "Isi Nama dan Nomor", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }

        }











