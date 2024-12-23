package com.example.mangas_livres;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentLogin extends Fragment {

    private static final String ARG_EMAIL = "email";
    private static final String ARG_PASSWORD = "password";
    private View fragmentView;
    Button btnLogin;
    Button btnCadastro;
    User user;

    public FragmentLogin() {

    }


    public static FragmentLogin newInstance() {
        FragmentLogin fragment = new FragmentLogin();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = fragmentView.findViewById(R.id. btnLogin);
        btnCadastro = fragmentView.findViewById(R.id.btnCadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                FragmentCadastro fragmentCadastro =FragmentCadastro.newInstance();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragmentCadastro);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        return fragmentView;
    }
    private void login(){
        TextView edEmail= fragmentView.findViewById(R.id.edEmail);
        TextView edPassword= fragmentView.findViewById(R.id.edPassword);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", edEmail.getText().toString())
                .addFormDataPart("password", edPassword.getText().toString())
                .build();
        Call<User> call=RetrofitClient.getInstance().getMyApi().login(requestBody);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    user=response.body();
                    if (((MainActivity)getActivity()).savePreferences(user.name,user.email)==false){
                        Toast.makeText(requireContext(), "seus dados não foram salvos", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(requireContext(), "Seu login foi feito com sucesso", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        FragmentInicial fragmentInicial = FragmentInicial.newInstance();
                        fragmentTransaction.replace(R.id.fragmentContainerView, fragmentInicial);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }else {
                    Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(requireContext(), "Não permitido", Toast.LENGTH_SHORT).show();
            }
        });
    }

}