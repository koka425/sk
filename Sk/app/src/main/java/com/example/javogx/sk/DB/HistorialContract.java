package com.example.javogx.sk.DB;

import android.provider.BaseColumns;


public final class HistorialContract {
    private HistorialContract(){
    }

    public static class Entrada implements BaseColumns{
        public static final String NOMBRE_TABLA = "historial";
        public static final String COLUMNA_ID_COMPRA = "id_compra";
        public static final String COLUMNA_FECHA = "fecha";
        public static final String COLUMNA_CANTIDAD = "cantidad";
        public static final String COLUMNA_TOTAL = "total";
    }
}
