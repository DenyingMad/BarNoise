package com.devilpanda.barnoise.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    public Category(long id, String name){
        this.name = name;
        this.id = id;
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
