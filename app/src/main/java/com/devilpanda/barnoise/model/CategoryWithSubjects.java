package com.devilpanda.barnoise.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithSubjects {
    @Embedded
    private Category category;
    @Relation(
            parentColumn = "id",
            entityColumn = "category_id"
    )
    private List<Subject> subjects;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
