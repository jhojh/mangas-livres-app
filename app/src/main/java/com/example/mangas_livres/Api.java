package com.example.mangas_livres;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http:/10.0.2.2/mangas-livres/api/";
    @GET("products/product")
    Call<List<Manga>> getMangas();

    @GET("products/product/{id}")
    Call<List<Manga>> getMangabyId(@Path("id") int id);

    @POST("users/login")
    Call<User> login(@Body RequestBody requestBody);

    @POST("users/")
    Call<User>  cadastro(@Body  RequestBody requestBody);

    @DELETE("products/delete-product/{id}")
    Call<Manga> delete(@Path("id") int id);
}
