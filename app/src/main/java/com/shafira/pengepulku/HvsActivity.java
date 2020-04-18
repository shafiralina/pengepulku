package com.shafira.pengepulku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shafira.pengepulku.Database.Database;
import com.shafira.pengepulku.Model.Order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class HvsActivity extends AppCompatActivity {

    TextView pname, price;
    ElegantNumberButton numberButton;
    Button masukkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hvs);

        pname = findViewById(R.id.kertas);
        price = findViewById(R.id.harga);
        numberButton = findViewById(R.id.amount_hvs);
        masukkan = findViewById(R.id.btn_hvs);

        masukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        "01",
                        "Kertas HVS",
                        numberButton.getNumber(),
                        "160"
                ));

                Toast.makeText(HvsActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
