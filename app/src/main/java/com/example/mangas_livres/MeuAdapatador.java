package com.example.mangas_livres;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import com.squareup.picasso.Picasso;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.w3c.dom.Text;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class MeuAdapatador extends RecyclerView.Adapter<MeuAdapatador.ViewHolder> {
    ArrayList<Manga> mangas;
    Context context;

    public MeuAdapatador(ArrayList<Manga> mangas) {
        this.mangas = mangas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txvNameProd;
        final TextView txvValue;
        final TextView txvAmount;
        final ImageView mangaImg;

        public ViewHolder(View view) {
            super(view);
            txvNameProd = view.findViewById(R.id.txvNameProd);
            txvValue =  view.findViewById(R.id.txvValue);
            txvAmount =  view.findViewById(R.id.txvAmount);
            mangaImg = view.findViewById(R.id.mangaImg);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_manga, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        context = holder.mangaImg.getContext();
        Manga mang = mangas.get(position);
        holder.txvNameProd.setText(mang.name_prod);
        holder.txvValue.setText("R$: " + mang.value);
        holder.txvAmount.setText("Estoque: " + mang.amount);
        String urlImage = "http://10.0.2.2/mangas-livres/"+mang.getPath();
        Picasso.get().load(urlImage).into(holder.mangaImg);

        holder.mangaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                FragmentDetalhe fragmentDetalhe = FragmentDetalhe.newInstance(mang.getId());
                fragmentTransaction.replace(R.id.fragmentContainerView, fragmentDetalhe);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mangas.size();
    }

}

