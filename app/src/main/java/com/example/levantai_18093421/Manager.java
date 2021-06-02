package com.example.levantai_18093421;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager extends AppCompatActivity {

    Button btnShow, btnAdd, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        btnShow = findViewById(R.id.btnShow);
        btnAdd = findViewById(R.id.btnAdd);
        btnLogout = findViewById(R.id.btnLogout);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Manager.this, MainActivity.class));
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", new Product());
                Intent intent = new Intent(Manager.this, ProductForm.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}