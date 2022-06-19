package com.example.gamezone;

import java.time.LocalDate;
import java.util.List;

public class Event {

    private Long id_event;
    private String name;
    private String date;
    private Group group;

    public Event() {
    }

    public Event(Long id_event, String name, String date, Group group) {
        this.id_event = id_event;
        this.name = name;
        this.date = date;
        this.group = group;
    }

    public Long getId_event() {
        return id_event;
    }

    public void setId_event(Long id_event) {
        this.id_event = id_event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id_event=" + id_event +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", group=" + group +
                '}';
    }
}
