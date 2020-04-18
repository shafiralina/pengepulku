package com.shafira.pengepulku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        CardView hvs = findViewById(R.id.cv_hvs);
        CardView ivory = findViewById(R.id.cv_ivory);
        CardView kardus = findViewById(R.id.cv_kardus);
        CardView koran = findViewById(R.id.cv_koran);
        Button cart = findViewById(R.id.to_cart);

        hvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hvs = new Intent(ProductsActivity.this, HvsActivity.class);
                startActivity(hvs);
            }
        });

        ivory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ivory = new Intent(ProductsActivity.this, IvoryActivity.class);
                startActivity(ivory);
            }
        });

        kardus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kardus = new Intent(ProductsActivity.this, KardusActivity.class);
                startActivity(kardus);
            }
        });

        koran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent koran = new Intent(ProductsActivity.this, KoranActivity.class);
                startActivity(koran);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent koran = new Intent(ProductsActivity.this, CartActivity.class);
                startActivity(koran);
            }
        });
    }
}
