package com.devilpanda.barnoise.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Subject {

    public Subject(String title, String description, String problem, String facts, long categoryId){
        this.title = title;
        this.description = description;
        this.problem = problem;
        this.facts = facts;
        this.categoryId = categoryId;
    }

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;

    private String description;

    private String problem;

    private String facts;

    @ColumnInfo(name = "category_id")
    private long categoryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getFacts() {
        return facts;
    }

    public void setFacts(String facts) {
        this.facts = facts;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
