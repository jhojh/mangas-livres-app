package com.example.mangas_livres;

public class Manga {
    int id;
    String name_prod ;
    float value ;
    int amount;
    String sinopse ;
    int category_id ;
    String categorys_name;
    int authors_id ;
    String authors_name;
    String path;

    public Manga(int id, String path, String authors_name, int authors_id, String categorys_name, int category_id, String sinopse, int amount, float value, String name_prod) {
        this.id = id;
        this.path = path;
        this.authors_name = authors_name;
        this.authors_id = authors_id;
        this.categorys_name = categorys_name;
        this.category_id = category_id;
        this.sinopse = sinopse;
        this.amount = amount;
        this.value = value;
        this.name_prod = name_prod;
    }

    public int getId() {
        return id;
    }

    public String getName_prod() {
        return name_prod;
    }

    public float getValue() {
        return value;
    }

    public int getAmount() {
        return amount;
    }

    public String getSinopse() {
        return sinopse;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategorys_name() {
        return categorys_name;
    }

    public int getAuthors_id() {
        return authors_id;
    }

    public String getAuthors_name() {
        return authors_name;
    }

    public String getPath() {
        return path;
    }
}
