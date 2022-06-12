package com.example.gamezone;

import java.io.Serializable;

public class Game implements Serializable {

    private int id_game;
    private String name;

    public Game(int game_id, String name) {
        this.id_game = id_game;
        this.name = name;
    }

    public Game() {
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id_game +
                ", nazwa='" + name + '\'' +
                '}';
    }
}
