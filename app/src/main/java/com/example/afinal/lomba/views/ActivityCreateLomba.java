package com.example.afinal.lomba.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.afinal.R;
import com.example.afinal.api.ApiService;
import com.example.afinal.api.ApiUtils;
import com.example.afinal.artikel.views.ActivityArtikel;
import com.example.afinal.helpers.RecyclerItemClickListener;
import com.example.afinal.home.views.ActivityHome;
import com.example.afinal.lomba.adapter.AdapterListKategori;
import com.example.afinal.helpers.BaseResponse;
import com.example.afinal.lomba.model.Kategori;
import com.example.afinal.profil.views.ActivityProfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCreateLomba extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView navigationView;
    EditText etNama, etTema, etKategori, etPenyelenggara, etLokasi, etTanggal, etHadiah, etDeskripsi;
    ImageButton btnAddImg;
    Button btnSubmit;
    String idKategori;
    List<Kategori> listKategori = new ArrayList<Kategori>();
    ApiService apiService = ApiUtils.getAPIService();
    Context mContext = this;
    AdapterListKategori adapterListKategori;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    Bitmap bitmap;
    ProgressDialog loading;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }
        getDataKategori();
        setContentView(R.layout.activity_create_lomba);
        toolbar = findViewById(R.id.tbCreateLomba);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tambah Lomba");
        navigationView = findViewById(R.id.navigation);
        etNama = findViewById(R.id.etNama);
        etKategori = findViewById(R.id.etKategori);
        etPenyelenggara = findViewById(R.id.etPenyelenggara);
        etTema = findViewById(R.id.etTema);
        etTanggal = findViewById(R.id.etTanggal);
        etLokasi = findViewById(R.id.etLokasi);
        etHadiah = findViewById(R.id.etHadiah);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        btnAddImg = findViewById(R.id.btnAddImg);
        btnSubmit = findViewById(R.id.btnSubmit);
        mContext = this;



        navigationView.setSelectedItemId(R.id.menuTambah);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(ActivityCreateLomba.this, ActivityHome.class));
                        break;
                    case R.id.menuLomba:
                        startActivity(new Intent(ActivityCreateLomba.this, ActivityLomba.class));
                        break;
                    case R.id.menuArtikel:
                        startActivity(new Intent(ActivityCreateLomba.this, ActivityArtikel.class));
                        break;
                    case R.id.menuProfil:
                        startActivity(new Intent(ActivityCreateLomba.this, ActivityProfil.class));
                        break;
                    case R.id.menuTambah:
                        break;
                }
                return true;
            }
        });

        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        etKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildDialog();
            }
        });

        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                insertData();
            }
        });
    }


    private void getDataKategori(){
        apiService.getKategori().enqueue(new Callback<BaseResponse<List<Kategori>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Kategori>>> call, Response<BaseResponse<List<Kategori>>> response) {
                if (response.isSuccessful()){
                    listKategori = response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Kategori>>> call, Throwable t) {

            }
        });
    }

    void buildDialog(){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_kategori);
        RecyclerView recyclerView =(RecyclerView) dialog.findViewById(R.id.listKategori);
        adapterListKategori = new AdapterListKategori(listKategori, mContext);
        recyclerView.setAdapter(adapterListKategori);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        etKategori.setText(listKategori.get(position).getNamaKategori());
                        idKategori = listKategori.get(position).getId();
                        dialog.dismiss();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        dialog.show();
    }

    void showTimePicker(){
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                etTanggal.setText(sdf.format(myCalendar.getTime()));

            }
        };
        new DatePickerDialog(mContext,date,myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void insertData() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        // gambar yang telah jadi string simpan dalam variable encoded
        final String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        // untuk keperluan log
        Log.d("zzzResponse", encoded);

        apiService.insertLomba(idKategori,etNama.getText().toString(),etPenyelenggara.getText().toString(),
                etTanggal.getText().toString(),etTema.getText().toString(),etLokasi.getText().toString(),
                etHadiah.getText().toString(),etDeskripsi.getText().toString(),encoded).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.i("debug", "onResponse: Berhasil");
                    loading.dismiss();
                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                            if (jsonRESULTS.getString("error").equals("false")){
                                Toast.makeText(mContext, "Berhasil Menambahkan Lomba", Toast.LENGTH_SHORT).show();
                                onRestart();
                            } else {
                                String error_message = jsonRESULTS.getString("error_msg");
                                Toast.makeText(mContext, error_message, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                }else {
                    Log.i("debug", "onResponse: Tidak Berhasil");
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            //getting the image Uri
            Uri imageUri = data.getData();
            try {
                //getting bitmap object from uri
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                //displaying selected image to imageview
                btnAddImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onRestart() {
        etTanggal.setText("");
        etKategori.setText("");
        etDeskripsi.setText("");
        etHadiah.setText("");
        etLokasi.setText("");
        etTema.setText("");
        etPenyelenggara.setText("");
        etNama.setText("");
        super.onRestart();
    }
}