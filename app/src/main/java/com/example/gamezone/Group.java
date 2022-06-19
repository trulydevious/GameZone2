package com.example.gamezone;

public class Group {

    private Long id_group;
    private String name;
    private int picture_id;

    public Group() {
    }

    public Group(Long id_group, String name, int picture_id) {
        this.id_group = id_group;
        this.name = name;
        this.picture_id = picture_id;
    }

    public Long getId_group() {
        return id_group;
    }

    public void setId_group(Long id_group) {
        this.id_group = id_group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id_group=" + id_group +
                ", name='" + name + '\'' +
                ", picture_id=" + picture_id +
                '}';
    }
}
