package com.example.afinal.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.afinal.R;
import com.example.afinal.api.ApiService;
import com.example.afinal.api.ApiUtils;
import com.example.afinal.home.views.ActivityHome;
import com.example.afinal.login.session.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {
    Button btnLogin;
    EditText etEmail, etPass;
    ProgressDialog loading;
    Context mContext;
    ApiService apiService;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        mContext = this;
        apiService = ApiUtils.getAPIService();
        session = new Session(mContext);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                loginRequest();
            }
        });
    }

    private void loginRequest() {
        apiService.loginRequest(etEmail.getText().toString(),etPass.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("error").equals("false")){
                            // Jika login berhasil maka data nama yang ada di response API
                            // akan diparsing ke activity selanjutnya.
                            Toast.makeText(mContext, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            session.setLogin(true);
                            session.setNama(jsonRESULTS.getString("nama"));
                            session.setEmail(jsonRESULTS.getString("email"));
                            session.setNo_telp(jsonRESULTS.getString("no_telp"));
                            session.setAlamat(jsonRESULTS.getString("alamat"));
                            Intent intent = new Intent(ActivityLogin.this, ActivitySplash.class);
                            startActivity(intent);

                        } else {
                            // Jika login gagal
                            String error_message = jsonRESULTS.getString("error_msg");
                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    loading.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
            }
        });
    }

}