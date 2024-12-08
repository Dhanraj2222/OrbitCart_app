package com.orbit.network;

import com.orbit.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<List<Product>> getProducts();
}
