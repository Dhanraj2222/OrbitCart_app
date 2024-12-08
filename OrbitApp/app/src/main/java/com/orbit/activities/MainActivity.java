package com.orbit.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orbit.R;
import com.orbit.adapters.ProductAdapter;
import com.orbit.models.Product;
import com.orbit.network.ApiClient;
import com.orbit.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnQuantityChangeListener {

    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private TextView tvItemTotal, tvToPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        tvItemTotal = findViewById(R.id.tvItemTotal);
        tvToPay = findViewById(R.id.tvToPay);

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));


        productAdapter = new ProductAdapter(this, new ArrayList<>(), this);
        recyclerViewProducts.setAdapter(productAdapter);

        fetchProducts();
    }

    private void fetchProducts() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Product>> call = apiService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productAdapter.updateProducts(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load products", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public void onTotalChange(int totalItems, double totalPrice) {

        TextView totalItemsTextView = findViewById(R.id.totalItemsTextView);
        totalItemsTextView.setText(String.valueOf(totalItems));


        TextView tvItemTotal = findViewById(R.id.tvItemTotal);
        tvItemTotal.setText("₹" + String.format("%.2f", totalPrice));

        TextView tvToPay = findViewById(R.id.tvToPay);
        tvToPay.setText("₹" + String.format("%.2f", totalPrice));
    }



}
