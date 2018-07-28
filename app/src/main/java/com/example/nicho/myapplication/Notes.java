package com.example.nicho.myapplication;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by shalini on 2/9/2018.
 */

public class Notes implements Serializable {

    private String title;
    private String date;
    private String description;
    private static int counter=1;

    public Notes()
    {
        counter++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", counter=" + counter +
                '}';
    }
}
