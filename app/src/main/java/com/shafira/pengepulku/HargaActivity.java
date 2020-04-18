package com.shafira.pengepulku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.shafira.pengepulku.Model.Products;


public class HargaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static FirebaseFirestore mDatabase;
    private FirestoreRecyclerAdapter<Products, ProductsViewHolder> adapter;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harga);
        recyclerView = findViewById(R.id.list_harga);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseFirestore.getInstance();
        final CollectionReference datasRef = mDatabase.collection("PRODUCTS");
        query = datasRef.orderBy("productname", Query.Direction.ASCENDING);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirestoreRecyclerOptions<Products> options = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(query, Products.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Products, ProductsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder dataViewHolder, int i, @NonNull Products data) {
                dataViewHolder.setData(data);
            }

            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.harga_layout, parent, false);
                return new ProductsViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

        @Override
        protected void onStop() {
            super.onStop();
            if(adapter != null){
                adapter.stopListening();
            }
        }


    class ProductsViewHolder extends RecyclerView.ViewHolder {
        private View view;
        ImageView picture;

        ProductsViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        void setData(Products products) {
            TextView productname = view.findViewById(R.id.productname);
            TextView price = view.findViewById(R.id.price);
            TextView desc = view.findViewById(R.id.desc);
            picture = view.findViewById(R.id.picture);
            productname.setText(products.getProductname());
            price.setText(products.getPrice());
            desc.setText(products.getDesc());


//            Products products = new Products();
                    Glide.with(getApplicationContext())
                    .load(products.getPicture())
                    .into(picture);

        }
    }
}



