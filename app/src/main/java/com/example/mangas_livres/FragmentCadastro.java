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


public class FragmentCadastro extends Fragment {


    private View fragmentView;
    Button btnCadastrar;
    Button btnLogar;
    User user;

    public FragmentCadastro() {

    }


    public static FragmentCadastro newInstance() {
        FragmentCadastro fragment = new FragmentCadastro();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView =  inflater.inflate(R.layout.fragment_cadastro, container, false);
        btnCadastrar = fragmentView.findViewById(R.id.btnCadastrar);
        btnLogar = fragmentView.findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                FragmentLogin fragmentLogin = FragmentLogin.newInstance();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragmentLogin);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastro();
            }
        });

        return fragmentView;
    }
    private void cadastro(){
        TextView edName = fragmentView.findViewById(R.id.edName);
        TextView edEmail = fragmentView.findViewById(R.id.edEmail);
        TextView edPassword = fragmentView.findViewById(R.id.edPassword);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", edName.getText().toString())
                .addFormDataPart("email", edEmail.getText().toString())
                .addFormDataPart("password", edPassword.getText().toString())
                .build();

        Call<User> call=RetrofitClient.getInstance().getMyApi().cadastro(requestBody);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    FragmentLogin fragmentLogin = FragmentLogin.newInstance();
                    fragmentTransaction.replace(R.id.fragmentContainerView, fragmentLogin);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Toast.makeText(requireContext(), "Suas informações foram cadastradas", Toast.LENGTH_SHORT).show();
                }else{
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