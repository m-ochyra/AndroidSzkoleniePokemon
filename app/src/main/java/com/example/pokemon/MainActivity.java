package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bPokemon1;
    Button bPokemon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPokemon1 = findViewById(R.id.bPokemon1);
        bPokemon2 = findViewById(R.id.bPokemon2);

        bPokemon1.setOnClickListener(this);
        bPokemon2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        if(v.getId() == R.id.bPokemon1) {
            intent.putExtra("id", 1);
        } else if(v.getId() == R.id.bPokemon2) {
            intent.putExtra("id", 2);
        }

        startActivity(intent);
    }
}
