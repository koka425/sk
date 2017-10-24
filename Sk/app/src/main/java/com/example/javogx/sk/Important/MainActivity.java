package com.example.javogx.sk.Important;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.javogx.sk.Recycle.RVAdapter;
import com.example.javogx.sk.Recycle.RecyclerViewClickListener;
import com.example.javogx.sk.Datos.Producto;
import com.example.javogx.sk.Datos.Prod;
import com.example.javogx.sk.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewClickListener listener;
    private RecyclerView rv;
    private ArrayList<Producto> productos;
    private Prod crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<Producto>();
        crud = new Prod(this);
        productos = crud.getProductos();

        rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeAdapter();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fbAdd = (FloatingActionButton) findViewById(R.id.plusbutt);

        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act = new Intent(MainActivity.this,AddActivity.class);
                startActivity(act);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(productos,this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent act = new Intent(MainActivity.this, Modify.class);
                act.putExtra("indice", productos.get(position).getId());
                act.putExtra("nombre",productos.get(position).getNombre());
                act.putExtra("precio",productos.get(position).getPrecio());
                act.putExtra("url",productos.get(position).getUrl());
                startActivity(act);
            }
        });
        rv.setAdapter(adapter);
    }
}
