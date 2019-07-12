package com.example.cruduas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText nama, no_hp, et_alamat;
    Spinner spinner;
    String[] pilihan = {"Laki-laki", "Perempuan"};
    Button btn_simpan, btn_tampil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = findViewById(R.id.nama_et);
        no_hp = findViewById(R.id.notlp_et);
        et_alamat = findViewById(R.id.alamat_et);

        btn_simpan = findViewById(R.id.btn_simpan);
        btn_tampil = findViewById(R.id.btn_tampil);

        spinner = findViewById(R.id.jk);
    }

    private void tambahHaji(){
        final String name = nama.getText().toString().trim();
        final String no = no_hp.getText().toString().trim();
        final String alamat = et_alamat.getText().toString().trim();
        final String jk = spinner.getSelectedItem().toString();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_NAMA,name);
                params.put(Konfigurasi.KEY_NoHp,no);
                params.put(Konfigurasi.KEY_Alamat, alamat);
                params.put(Konfigurasi.KEY_Jk,jk);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btn_simpan){
            tambahHaji();
        }

        if(v == btn_tampil){
            startActivity(new Intent(this,tampil_activity.class));
        }
    }

}

