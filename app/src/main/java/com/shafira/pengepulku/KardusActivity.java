package com.shafira.pengepulku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.shafira.pengepulku.Database.Database;
import com.shafira.pengepulku.Model.Order;

public class KardusActivity extends AppCompatActivity {

    TextView pname, price;
    ElegantNumberButton numberButton;
    Button masukkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kardus);

        pname = findViewById(R.id.kertas);
        price = findViewById(R.id.harga);
        numberButton = findViewById(R.id.amount_kardus);
        masukkan = findViewById(R.id.btn_kardus);

        masukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        "03",
                        "Kardus",
                        numberButton.getNumber(),
                        "140"
                ));

                Toast.makeText(KardusActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
