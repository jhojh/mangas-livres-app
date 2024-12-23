package com.example.mangas_livres;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentLogin extends Fragment {

    private static final String ARG_EMAIL = "email";
    private static final String ARG_PASSWORD = "password";
    private View fragmentView;
    Button buttonLogin;
    Button buttonRedirect;
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


        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}