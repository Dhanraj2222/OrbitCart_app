package com.orbit.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orbit.R;
import com.orbit.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private static final String TAG = "ProductAdapter";
    private List<Product> productList;
    private final OnQuantityChangeListener quantityChangeListener;
    private final Context context;



    public interface OnQuantityChangeListener {
        void onTotalChange(int totalItems, double totalPrice);
    }



    public ProductAdapter(Context context, List<Product> productList, OnQuantityChangeListener listener) {
        this.context = context;
        this.productList = productList;
        this.quantityChangeListener = listener;
    }

    public void updateProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.tvProductName.setText(product.getName());
        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
        holder.tvProductPrice.setText("Price: ₹" + product.getPrice());

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.product_placeholder)
                .error(R.drawable.error_image)
                .into(holder.ivProductImage);

        Log.d(TAG, "Loaded image for product: " + product.getName() + " - URL: " + product.getImageUrl());

        // Plus Button Click Listener
        holder.btnPlus.setOnClickListener(v -> {
            if (product.getQuantity() < product.getMaxQty()) {
                product.setQuantity(product.getQuantity() + 1);
                holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
                updateTotal();
                holder.tvQuantity.setError(null);


                Log.d(TAG, "Quantity increased for product: " + product.getName());
            } else {
                if (holder.tvQuantity.getError() == null) {
                    holder.tvQuantity.setError("Maximum quantity reached");
                    Toast.makeText(context, "Maximum quantity reached!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.btnMinus.setOnClickListener(v -> {
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
                updateTotal();
                holder.tvQuantity.setError(null);

                Log.d(TAG, "Quantity decreased for product: " + product.getName());
            } else {
                if (!"Quantity is already zero.".equals(holder.tvQuantity.getError())) {
                    holder.tvQuantity.setError("Quantity is already zero.");
                    Toast.makeText(context, "Quantity is already zero.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






    private void updateTotal() {
        int totalItems = 0;
        double totalPrice = 0.0;

        for (Product product : productList) {
            totalItems += product.getQuantity();
            totalPrice += product.getPrice() * product.getQuantity();
        }

        // Notify listener with both total items and total price
        quantityChangeListener.onTotalChange(totalItems, totalPrice);
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvQuantity, tvProductPrice;
        ImageButton btnPlus, btnMinus;
        ImageView ivProductImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
        }
    }
}
