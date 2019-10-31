package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView ivImage;
    TextView tvHeight;
    TextView tvWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivImage = findViewById(R.id.ivImage);
        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);

        int id = getIntent().getIntExtra("id", 1);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://pokeapi.co/api/v2/pokemon/" + id + "/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Pokemon pokemon = new Gson().fromJson(response, Pokemon.class);
                        showPokemon(pokemon);

//                        Konwersja obiektu na JSON (String)
//                        String string = new Gson().toJson(pokemon);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("DetailActivity", error.getLocalizedMessage(), error);
                Toast.makeText(DetailActivity.this, "Wystąpił błąd", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPokemon(Pokemon pokemon) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(pokemon.name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Picasso.get().load(pokemon.sprites.front_default).into(ivImage);

        tvHeight.setText(pokemon.height + " pokemetrów");
        tvWeight.setText(pokemon.weight + " pokegramów");
    }
}
