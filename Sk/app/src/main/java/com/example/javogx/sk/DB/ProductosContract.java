package com.example.javogx.sk.DB;

import android.provider.BaseColumns;

public final class ProductosContract {
    private ProductosContract(){

    }

    public static class Entrada implements BaseColumns{
        public static final String NOMBRE_TABLA = "productos";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_PRECIO = "precio";
        public static final String COLUMNA_URL = "url";
    }
}
