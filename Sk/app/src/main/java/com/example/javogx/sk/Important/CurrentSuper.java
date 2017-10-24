package com.example.javogx.sk.Important;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.javogx.sk.Recycle.ComprasAdapter;
import com.example.javogx.sk.Recycle.RecyclerViewClickListener;
import com.example.javogx.sk.Recycle.SpinnerAdapter;
import com.example.javogx.sk.Datos.Compra;
import com.example.javogx.sk.Datos.Comp;
import com.example.javogx.sk.Datos.Hist;
import com.example.javogx.sk.Datos.Producto;
import com.example.javogx.sk.Datos.Prod;
import com.example.javogx.sk.R;

import java.util.ArrayList;

public class CurrentSuper extends AppCompatActivity {

    ArrayList<Producto> productos, penus;
    ArrayList<Compra> compras;
    Prod crud;
    Hist crud2;
    Comp crud3;
    Spinner sp;
    EditText et;
    ComprasAdapter adapter;
    RecyclerView rv;
    int cant, tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cant=0;
        tot=0;

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        compras = new ArrayList<Compra>();
        productos = new ArrayList<Producto>();

        crud = new Prod(this);
        crud2 = new Hist(this);
        crud3 = new Comp(this);
        productos = crud.getProductos();
        initializeAdapter();
        initializeAdapter2();

    }

    private void initializeAdapter(){
        SpinnerAdapter adapter = new SpinnerAdapter(this,productos);
        sp.setAdapter(adapter);
    }

    private void initializeAdapter2(){
        adapter = new ComprasAdapter(compras,this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        });
        rv.setAdapter(adapter);
    }
}
