package com.example.crudkontak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class DataConfig extends AppCompatActivity {
    private EditText etnama,etnomor;
    private TextView tvnama,tvnomor;
    private Button btnsave;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_config);

        etnama = findViewById(R.id.etnama);
        etnomor = findViewById(R.id.etnomor);
        tvnama =findViewById(R.id.tvnama);
        tvnomor =findViewById(R.id.tvnomor);
        btnsave =findViewById(R.id.btnSimpan);
        progressBar =findViewById(R.id.progressBar);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etnama.getText().length()>0&& etnomor.getText().length()>0){

                    progressBar.setVisibility(View.VISIBLE);

                    AndroidNetworking.post("http://seno.idn.sch.id/index.php/kontak")
                            .setPriority(Priority.MEDIUM)
                            .addUrlEncodeFormBodyParameter("nama",etnama.getText().toString())
                            .addUrlEncodeFormBodyParameter("nomor",etnomor.getText().toString())
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(DataConfig.this, "Berhasil Menyimpan", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    finish();
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(DataConfig.this, "Gagal Menyimpan", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }else {
                    Toast.makeText(DataConfig.this, "Isi nama dan nomor", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}


