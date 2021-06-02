package com.example.levantai_18093421;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    List<Product> list;
    Context context;
    String url="https://60b4f2bbfe923b0017c833fa.mockapi.io/api/products";
    public MyAdapter(Context context) {
        this.context = context;
        update();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Product product = list.get(position);
        holder.txtId.setText(String.valueOf(product.getId()));
        holder.txtType.setText(product.getType());
        holder.txtPrice.setText(String.valueOf(product.getPrice()+"$"));
        holder.txtCountry.setText(product.getCountry());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView txtId, txtType, txtPrice, txtCountry;
        Button btnEdit, btnDelete;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtType = itemView.findViewById(R.id.txtType);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtCountry = itemView.findViewById(R.id.txtCountry);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    private void  update(){
        list = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(url, response -> {
            try {
                for(int i=0; i<response.length(); i++){
                    JSONObject jsonObject = (JSONObject) response.get(i);
                    int id = jsonObject.getInt("id");
                    String type = jsonObject.getString("type");
                    double price = jsonObject.getDouble("price");
                    String country = jsonObject.getString("country");
                    Product product = new Product(id,type,price,country);
                    list.add(product);
                }
                notifyDataSetChanged();
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(0,1,1));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }
}
