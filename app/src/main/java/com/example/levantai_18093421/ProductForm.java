package com.example.levantai_18093421;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ProductForm extends AppCompatActivity {

    TextView txtUpdate;
    Button btnSave, btnBack;
    EditText editType, editPrice,editCountry;
    Product product;
    String url="https://60b4f2bbfe923b0017c833fa.mockapi.io/api/products";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);
        txtUpdate =findViewById(R.id.txtUpdate);
        btnSave =findViewById(R.id.btnSave);
        btnBack =findViewById(R.id.btnBack);
        editType =findViewById(R.id.editType);
        editPrice =findViewById(R.id.editPrice);
        editCountry =findViewById(R.id.editCountry);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            product = (Product) bundle.getSerializable("product");
            editType.setText(product.getType());
            editPrice.setText(String.valueOf(product.getPrice()));
            editCountry.setText(product.getCountry());
        }
        if(product.getId()==0){
            txtUpdate.setText("ADD");
            btnSave.setText("CREATE");
        } else {
            txtUpdate.setText("UPDATE"+"  ");
            btnSave.setText("SAVE");
            }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product.getId()==0){
                    addProduct();
                } else updateProduct();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

      private  void addProduct(){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,response -> {
                startActivity(new Intent(ProductForm.this, MainActivity.class));
                finish();
            }, error -> {
                error.printStackTrace();
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<>();
                    map.put("type",editType.getText().toString().trim());
                    map.put("price",editPrice.getText().toString().trim());
                    map.put("country",editCountry.getText().toString().trim());
                    return map;
                }
            };
          stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,1,1));
          RequestQueue requestQueue = Volley.newRequestQueue(ProductForm.this);
          requestQueue.add(stringRequest);
      }
      private  void updateProduct(){
          StringRequest stringRequest = new StringRequest(Request.Method.PUT, url+"/"+product.getId(), response -> {
              startActivity(new Intent(ProductForm.this,MainActivity.class));
              finish();;
          }, error -> {
              error.printStackTrace();
          }){
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String,String> map = new HashMap<>();
                  map.put("type",editType.getText().toString().trim());
                  map.put("price",editPrice.getText().toString().trim());
                  map.put("country",editCountry.getText().toString().trim());
                  return map;
              }
          };
          stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,1,1));
          RequestQueue requestQueue = Volley.newRequestQueue(ProductForm.this);
          requestQueue.add(stringRequest);
      }
}
