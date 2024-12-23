package com.example.mangas_livres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    public  String PREFS = "prefs";
    public  String KEY_NAME = "name";
    public  String KEY_EMAIL = "email";
    ImageButton imageAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageAccount = findViewById(R.id.imageAccount);

        imageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                FragmentLogin fragmentLogin = FragmentLogin.newInstance();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragmentLogin);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    public User readPreferences() {
        try {
            SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);
            String name = preferences.getString(KEY_NAME, "name");
            String email = preferences.getString(KEY_EMAIL, "email");
            return new User( name ,email);
        }catch (Exception e){
            return null;
        }

    }

    public boolean savePreferences(String name,String email) {
        try {
            SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_NAME, name);
            editor.putString(KEY_EMAIL, email);
            editor.commit();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}