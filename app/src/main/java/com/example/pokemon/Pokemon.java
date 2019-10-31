package com.example.pokemon;

import java.util.ArrayList;

public class Pokemon {
    public String name;
    public int height;
    public int weight;
    public Sprites sprites;
    public ArrayList<Form> forms;

    public class Sprites {
        public String front_default;
    }

    public class Form {
        public String name;
    }
}
