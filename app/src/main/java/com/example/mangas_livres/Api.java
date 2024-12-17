package com.example.mangas_livres;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http:/10.0.2.2/mangas-livres/api/";
    @GET("products/product")
    Call<List<Manga>> getMangas();
}
