package com.shafira.pengepulku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.shafira.pengepulku.Model.Products;

class ProductViewHolder extends RecyclerView.ViewHolder {
    private View view;

    ProductViewHolder(View itemView) {
        super(itemView);
        view = itemView;
    }

    void setProductName(String productName) {
        TextView textView = view.findViewById(R.id.product_name);
        textView.setText(productName);
    }
}

public class RiwayatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        RecyclerView recyclerView = findViewById(R.id.rv_riwayat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query query = rootRef.collection("ORDER")
                .orderBy("order", Query.Direction.ASCENDING);
    }
}
