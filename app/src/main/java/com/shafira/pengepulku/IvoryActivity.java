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

public class IvoryActivity extends AppCompatActivity {

    TextView pname, price;
    ElegantNumberButton numberButton;
    Button masukkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ivory);

        pname = findViewById(R.id.kertas);
        price = findViewById(R.id.harga);
        numberButton = findViewById(R.id.amount_ivory);
        masukkan = findViewById(R.id.btn_ivory);

        masukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        "02",
                        "Kertas Ivory",
                        numberButton.getNumber(),
                        "70"
                ));

                Toast.makeText(IvoryActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
