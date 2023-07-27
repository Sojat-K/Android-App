package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment1 = new Fragment();
        Fragment fragment2 = new Fragment();
        Fragment fragmentForm = new fillFragment();
        Fragment fragmentList = new listFragment();

        fragmentTransaction.add(R.id.write, fragment1);
        fragmentTransaction.add(R.id.display , fragment2);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentTransaction.replace(R.id.fragment1_container, fragmentForm);
            fragmentTransaction.replace(R.id.fragment2_container, fragmentList);
        }

        fragmentTransaction.commit();
    }
}
