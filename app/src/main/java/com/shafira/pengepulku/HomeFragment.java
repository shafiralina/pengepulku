package com.shafira.pengepulku;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shafira.pengepulku.viewpager.ImageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CardView harga = view.findViewById(R.id.cv_harga);
        CardView jual = view.findViewById(R.id.cv_jual);
        CardView riwayat = view.findViewById(R.id.cv_riwayat);
        ViewPager viewPager = view.findViewById(R.id.viewpager);

        harga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent harga = new Intent(getActivity(), HargaActivity.class);
                startActivity(harga);
            }
        });

        jual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jual = new Intent(getActivity(), ProductsActivity.class);
                startActivity(jual);
            }
        });

        ImageAdapter imageAdapter = new ImageAdapter(this.getContext());
        viewPager.setAdapter(imageAdapter);

        return view;
    }



}
