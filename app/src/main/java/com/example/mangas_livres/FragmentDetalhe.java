package com.example.mangas_livres;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Callback;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Path;

public class FragmentDetalhe extends Fragment {


    private static final String ARG_ID = "id";

    private int id;
    private View fragmentView;
    public FragmentDetalhe() {

    }

    public static FragmentDetalhe newInstance(int id) {
        FragmentDetalhe fragment = new FragmentDetalhe();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_detalhe, container, false);

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderManga();
    }

    private void renderManga() {
        Call<List<Manga>> call = RetrofitClient.getInstance().getMyApi().getMangabyId(id);
        call.enqueue(new Callback<List<Manga>>() {
            @Override
            public void onResponse(Call<List<Manga>> call, Response<List<Manga>> response) {
                List<Manga> mangas = response.body();
                Manga mang = mangas.get(0);

                TextView textName_prod = fragmentView.findViewById(R.id.textName_prod);
                TextView textValue = fragmentView.findViewById(R.id.textValue);
                TextView textSinopse = fragmentView.findViewById(R.id.textSinopse);
                ImageView imgManga = fragmentView.findViewById(R.id.imgManga);

                textName_prod.setText(mang.getName_prod());
                textValue.setText("R$ " + mang.getValue());
                textSinopse.setText(mang.getSinopse().toString());
                String urlImage = "http://10.0.2.2/mangas-livres/"+mang.getPath();
                Picasso.get().load(urlImage).into(imgManga);

            }

            @Override
            public void onFailure(Call<List<Manga>> call, Throwable t) {
                Log.d("TESTE", t.toString());
            }
        });

    }
    }