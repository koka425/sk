package com.example.javogx.sk.Important;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.javogx.sk.Datos.Prod;
import com.example.javogx.sk.Datos.Producto;
import com.example.javogx.sk.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Modify extends AppCompatActivity {
    private Prod crud;
    private ImageView ivFoto;
    private String indice;
    private String nombre;
    private String precio;
    private String url;
    private EditText edNombre;
    private EditText edPrecio;
    private TextView tv;

    private static final int SELECT_PHOTO = 100;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int REQUEST_CAMERA = 200;
    private static final String FILE_PROVIDER = "com.example.javogx.sk.fileprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificarinter);

        crud = new Prod(this);
        indice = getIntent().getStringExtra("indice");
        nombre = getIntent().getStringExtra("nombre");
        precio = getIntent().getStringExtra("precio");
        url = getIntent().getStringExtra("url");

        ivFoto = (ImageView) findViewById(R.id.ivFoto2);
        edNombre = (EditText) findViewById(R.id.et1);
        edPrecio = (EditText) findViewById(R.id.et2);


        edNombre.setText(nombre);
        edPrecio.setText(precio);


        Button bTomar = (Button) findViewById(R.id.bCamara2);
        Button bSeleccionar = (Button) findViewById(R.id.bFototeca2);

        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .into(ivFoto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fbOk = (FloatingActionButton) findViewById(R.id.tick);
        FloatingActionButton fbDel = (FloatingActionButton) findViewById(R.id.cross);

        bSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verFotos();
            }
        });

        bTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checarCamara();
            }
        });

        fbOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edNombre.getText().toString().equals("")||edPrecio.getText().toString().equals("")||edPrecio.getText().toString().equals("0")){
                    Toast.makeText(Modify.this, "Checar campos", Toast.LENGTH_SHORT).show();
                }else {
                    Producto item = new Producto(indice, edNombre.getText().toString(), edPrecio.getText().toString(), tv.getText().toString());
                    crud.updateProducto(item);
                    Toast.makeText(Modify.this, "Actualizado", Toast.LENGTH_SHORT).show();
                    Intent act = new Intent(Modify.this, MainActivity.class);
                    startActivity(act);
                }
            }
        });

        fbDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto item = new Producto(indice,nombre,precio,url);
                crud.deleteProducto(item);
                Toast.makeText(Modify.this, "Producto eliminado ", Toast.LENGTH_SHORT).show();
                Intent act = new Intent(Modify.this, MainActivity.class);
                startActivity(act);
            }
        });
    }

    public void verFotos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Debemos mostrar un mensaje?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Mostramos una explicación de que no aceptó dar permiso para acceder a la librería
            } else {
                // Pedimos permiso
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            }
        }else{
            checarFototeca();
        }
    }

    private void checarFototeca() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    checarFototeca();
                } else {

                }
                return;

            case REQUEST_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    tomarNuevaFoto();
                } else {

                }
                return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {

            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri imagenSeleccionada = imageReturnedIntent.getData();
                    try{
                        InputStream imagenStream = getContentResolver().openInputStream(imagenSeleccionada);
                        Bitmap imagen = BitmapFactory.decodeStream(imagenStream);
                        ivFoto.setImageBitmap(imagen);
                        tv.setText(imagenSeleccionada.toString());

                    }catch (FileNotFoundException fnte){
                        Toast.makeText(this, fnte.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    return;
                }
                else {
                    tv.setText("");
                }

            case REQUEST_CAMERA:
                if(resultCode == RESULT_OK){
                    Picasso.with(this).load(tv.getText().toString()).into(ivFoto);
                }
                return;
        }
    }


    public void checarCamara() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            }
        }else{
            tomarNuevaFoto();
        }
    }


    private  void tomarNuevaFoto(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            Uri photoURI;
            try {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT){
                    photoFile = createImageFile();
                    if (photoFile != null) {
                        photoURI = Uri.fromFile(photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(cameraIntent, REQUEST_CAMERA);
                    }
                } else {
                    photoFile = createImageFile2();
                    if (photoFile != null) {
                        photoURI = FileProvider.getUriForFile(this, FILE_PROVIDER, photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(cameraIntent, REQUEST_CAMERA);
                    }
                }
            }catch (NullPointerException npe){
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen = "JPEG_" + timeStamp + "_";
        File imagesFolder = new File( Environment.getExternalStorageDirectory(), "Supercito");
        if (!imagesFolder.exists()) {
            imagesFolder.mkdirs();
        }
        File image = new File(imagesFolder, nombreImagen+".jpg");
        String urlName = "file://" + image.getAbsolutePath();
        tv.setText(urlName);
        return image;
    }

    private File createImageFile2() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                nombreImagen,
                ".jpg",
                storageDir
        );

        String urlName = "file://" + image.getAbsolutePath();
        tv.setText(urlName);
        return image;
    }
}


