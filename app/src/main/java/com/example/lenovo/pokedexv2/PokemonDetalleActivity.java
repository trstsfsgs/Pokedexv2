package com.example.lenovo.pokedexv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

import com.example.lenovo.pokedexv2.Models.PokemonDetalle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonDetalleActivity extends AppCompatActivity {
    Retrofit retrofit;
    PokemonApi pokemonAPI;

    TextView tvNombre;
    TextView tvBase;
    TextView tvAlto;
    TextView tvPeso;
    TextView tvTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detalle);

        tvNombre=findViewById(R.id.tvNombre);
        tvBase=findViewById(R.id.tvBase);
        tvAlto=findViewById(R.id.tvAlto);
        tvPeso=findViewById(R.id.tvPeso);
        tvTipo=findViewById(R.id.tvTipo);

        retrofit = new PokemonAdapter().getAdapter();

        pokemonAPI=retrofit.create(PokemonApi.class);


        String name = getIntent().getExtras().getString ("nombre");
        Call<PokemonDetalle> pokemonDetalleCall = pokemonAPI.getPokemonDetalle(name);

        pokemonDetalleCall.enqueue(new Callback<PokemonDetalle>() {
            @Override
            public void onResponse(Call<PokemonDetalle> call, Response<PokemonDetalle> response) {
                Log.d("PD",response.body().getName());
                tvNombre.setText("NOMBRE: "+response.body().getName());
                tvBase.setText("BASE: "+response.body().getBase_experience());
                tvAlto.setText("ALTO: "+response.body().getHeight());
                tvPeso.setText("PESO: "+response.body().getWeight());
                tvTipo.setText("TIPO:  "+response.body().getType());
            }

            @Override
            public void onFailure(Call<PokemonDetalle> call, Throwable t) {

            }
        });


}}






