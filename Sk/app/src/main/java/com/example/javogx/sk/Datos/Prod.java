package com.example.javogx.sk.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javogx.sk.Datos.Producto;
import com.example.javogx.sk.DB.ProductosContract;
import com.example.javogx.sk.DB.DataBaseHelper;

import java.util.ArrayList;

public class Prod {

    private DataBaseHelper helper;

    public Prod(Context context) {
        helper = new DataBaseHelper(context);
    }

    public long newProduct(Producto item){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductosContract.Entrada.COLUMNA_NOMBRE, item.getNombre());
        values.put(ProductosContract.Entrada.COLUMNA_PRECIO, item.getPrecio());
        values.put(ProductosContract.Entrada.COLUMNA_URL, item.getUrl());

        long newRowId = db.insert(ProductosContract.Entrada.NOMBRE_TABLA, null, values);

        db.close();
        return newRowId;
    }

    public  ArrayList<Producto> getProductos(){

        ArrayList<Producto> items = new ArrayList<Producto>();

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columnas = {
                ProductosContract.Entrada.COLUMNA_ID,
                ProductosContract.Entrada.COLUMNA_NOMBRE,
                ProductosContract.Entrada.COLUMNA_PRECIO,
                ProductosContract.Entrada.COLUMNA_URL,
        };

        Cursor c = db.query(
                ProductosContract.Entrada.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null);

        while (c.moveToNext()){
            items.add(new Producto(
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_PRECIO)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_URL))
            ));
        }

        c.close();
        return items;
    }

    public Producto getProducto(String id){
        Producto item = null;

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columnas = {
                ProductosContract.Entrada.COLUMNA_ID,
                ProductosContract.Entrada.COLUMNA_NOMBRE,
                ProductosContract.Entrada.COLUMNA_PRECIO,
                ProductosContract.Entrada.COLUMNA_URL,
        };

        Cursor c = db.query(
                ProductosContract.Entrada.NOMBRE_TABLA,
                columnas,
                " id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        while (c.moveToNext()){
            item = new Producto(
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_PRECIO)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_URL))
            );
        }

        c.close();
        return item;
    }

    public void updateProducto(Producto item){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductosContract.Entrada.COLUMNA_NOMBRE, item.getNombre());
        values.put(ProductosContract.Entrada.COLUMNA_PRECIO, item.getPrecio());
        values.put(ProductosContract.Entrada.COLUMNA_URL, item.getUrl());

        db.update(
                ProductosContract.Entrada.NOMBRE_TABLA,
                values,
                "id = ?",
                new String[]{String.valueOf(item.getId())}
        );

        db.close();
    }

    public void deleteProducto(Producto item){

        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(
                ProductosContract.Entrada.NOMBRE_TABLA,
                "id = ?",
                new String[]{item.getId()}
        );

       db.close();

    }
}
