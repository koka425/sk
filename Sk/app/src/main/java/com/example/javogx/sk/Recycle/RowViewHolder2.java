/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.example.javogx.sk.Recycle;
import android.view.View;


public class RowViewHolder2 extends ComprasAdapter.CompraViewHolder implements View.OnClickListener {

    private RecyclerViewClickListener listener;

    public RowViewHolder2(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition());
    }
}
