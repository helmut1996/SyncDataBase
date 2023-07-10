package com.helcode.syncsqlite_mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.helcode.syncsqlite_mysql.Adapter.AdaptadorContacto;
import com.helcode.syncsqlite_mysql.DataBase.database;
import com.helcode.syncsqlite_mysql.databinding.FragmentFirstBinding;
import com.helcode.syncsqlite_mysql.model.Contactos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
//
//public class FirstFragment extends Fragment {
//
//    private static ArrayList<Contactos> arrayList = new ArrayList<Contactos>();
//private static LinearLayoutManager linearLayoutManager;
//private static AdaptadorContacto adaptadorContacto;
//    private FragmentFirstBinding binding;
//    private Context context;
//    @Override
//    public View onCreateView(
//            LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState
//    ) {
//
//        binding = FragmentFirstBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//
//    }
//
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        linearLayoutManager = new LinearLayoutManager(requireContext());
//        binding.ListaContactos.setLayoutManager(linearLayoutManager);
//        binding.ListaContactos.hasFixedSize();
//        binding.ListaContactos.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
//        adaptadorContacto = new AdaptadorContacto(arrayList);
//        binding.ListaContactos.setAdapter(adaptadorContacto);
//
//    }
//
//        public static void SincronizarContactos(){
//                if (MainActivity.checkNetworkConnection(requireContext())){
//                    final database data = new database(requireContext());
//                    final SQLiteDatabase db = data.getWritableDatabase();
//                    StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://172.16.2.196/android/getContactos.php", new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            try {
//                                JSONArray array = new JSONArray(response);
//                                for (int i = 0; i < array.length(); i++)
//                                {
//                                    JSONObject object  =  array.getJSONObject(i);
//                                    data.getContactos(object.getInt("id"),
//                                            object.getString("nombre"),
//                                            object.getString("apellido"),
//                                            object.getString("telefono"),db);
//                                }
//                            }catch (JSONException e){
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    });
//                }
//        }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//
//}