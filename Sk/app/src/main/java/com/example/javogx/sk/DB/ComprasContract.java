package com.example.javogx.sk.DB;

import android.provider.BaseColumns;


public final class ComprasContract {
    private ComprasContract(){

    }

    public static class Entrada implements BaseColumns{
        public static final String NOMBRE_TABLA = "compras";
        public static final String COLUMNA_ID_COMPRA = "id_compra";
        public static final String COLUMNA_ID_PRODUCTO = "id_prodcuto";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_PRECIO = "precio";
        public static final String COLUMNA_CANTIDAD = "cantidad";
        public static final String COLUMNA_TOTAL = "total";
    }
}
