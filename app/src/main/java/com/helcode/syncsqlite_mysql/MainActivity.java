package com.helcode.syncsqlite_mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.helcode.syncsqlite_mysql.Adapter.AdaptadorContacto;
import com.helcode.syncsqlite_mysql.Api.Singleton;
import com.helcode.syncsqlite_mysql.DataBase.BDContact;
import com.helcode.syncsqlite_mysql.DataBase.database;
import com.helcode.syncsqlite_mysql.databinding.ActivityMainBinding;
import com.helcode.syncsqlite_mysql.model.Contactos;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private static ArrayList<Contactos> arrayList = new ArrayList<Contactos>();
    private static LinearLayoutManager linearLayoutManager;
    private static AdaptadorContacto adaptadorContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);



/*
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
*/


        linearLayoutManager = new LinearLayoutManager(this);
        binding.ListaContactos.setLayoutManager(linearLayoutManager);
        binding.ListaContactos.hasFixedSize();
        binding.ListaContactos.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adaptadorContacto = new AdaptadorContacto(arrayList);
        binding.ListaContactos.setAdapter(adaptadorContacto);
        readContactos(this);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void SincronizarContactos( Context context){
        if (MainActivity.checkNetworkConnection(context)){
            final database data = new database(context);
            final SQLiteDatabase db = data.getWritableDatabase();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://172.16.2.237/android/getContactos.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++)
                        {
                            JSONObject object  =  array.getJSONObject(i);
                            data.getContactos(object.getInt("id"),
                                    object.getString("nombre"),
                                    object.getString("apellidos"),
                                    object.getString("telefono"),db);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            Singleton.getInstance(context).addToRequestQue(stringRequest);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_sinc) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Sicronizar");
            progressDialog.setProgressStyle(ProgressBar.STATUS_BAR_VISIBLE);
            new Sincronizar(this,progressDialog).execute();
        }

        return super.onOptionsItemSelected(item);
    }

    public static  class Sincronizar extends AsyncTask<Void,Void,Void>{
        Context context;
        ProgressDialog progressDialog;

        public Sincronizar(Context context, ProgressDialog progressDialog) {
            this.context = context;
            this.progressDialog = progressDialog;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SincronizarContactos(context);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressDialog.hide();
            Toast.makeText(context,"Sincronizado Correcta",Toast.LENGTH_LONG).show();
          //  readContactos(context);
            sendContactos(context);
        }
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    public static void sendContactos(final Context context)
    {
        final String nombre, apellidos, telefono;
        if(checkNetworkConnection(context))
        {
            final database data = new database(context);
            final SQLiteDatabase db = data.getWritableDatabase();

            nombre = "TEST";
            apellidos = "SEND";
            telefono = "3333333";


            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://172.16.2.237/android/insertContactos.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Informacion Enviada", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error",""+error);
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("nombre",nombre);
                    params.put("apellidos",apellidos);
                    params.put("telefono",telefono);
                    return params;
                }
            };
            Singleton.getInstance(context).addToRequestQue(stringRequest);

        }
    }

    public static void readContactos(Context context){
        arrayList.clear();
        database data = new database(context);
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor c = data.readContacto(db);
        while (c.moveToNext())
        {
            arrayList.add(new Contactos(c.getString(c.getColumnIndex(BDContact.Contactoa.NOMBRE)),
                    c.getString(c.getColumnIndex(BDContact.Contactoa.APELLIDOS)),
                    c.getString(c.getColumnIndex(BDContact.Contactoa.TELEFONO))));
        }
        adaptadorContacto.notifyDataSetChanged();
        c.close();
        data.close();
    }


    public  static boolean checkNetworkConnection( Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
