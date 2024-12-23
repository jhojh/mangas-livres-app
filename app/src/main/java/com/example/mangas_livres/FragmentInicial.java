package com.example.mangas_livres;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentInicial extends Fragment {

    ArrayList<Manga> mangas = new ArrayList<>();

    private RecyclerView ryvMang;
    private MeuAdapatador meuadp;

    public FragmentInicial() {
        // Required empty public constructor
    }


    public static FragmentInicial newInstance() {
        FragmentInicial fragmento = new FragmentInicial();
        Bundle args = new Bundle();
        fragmento.setArguments(args);
        return fragmento;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_inicial, container, false);
    }

   private void getMangas(){
        Call<List<Manga>> call = RetrofitClient.getInstance().getMyApi().getMangas();
        call.enqueue(new Callback<List<Manga>>() {
            @Override
            public void onResponse(Call<List<Manga>> call, Response<List<Manga>> response) {
                mangas.clear();
                mangas.addAll(response.body());
                meuadp.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Manga>> call, Throwable t) {
                Toast.makeText(getContext(), "Erro com o servidor: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

   }

    @Override
    public void onResume() {
        super.onResume();

        ryvMang = getActivity().findViewById(R.id.ryvMang);
        meuadp = new MeuAdapatador(mangas);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ryvMang.setLayoutManager(layout);
        ryvMang.setAdapter(meuadp);

        getMangas();
    }


}