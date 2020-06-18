package com.devilpanda.barnoise.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubjectDao {
    @Query("Select * FROM subject WHERE category_id = :categoryId")
    List<Subject> getByCategory(long categoryId);

    @Insert
    void insertCategories(Category category);

    @Insert
    void insertSubject(Subject subject);

    @Query("DELETE FROM subject")
    void deleteAllSubjects();

    @Query("DELETE FROM category")
    void deleteAllCategories();

    @Query("SELECT * FROM category")
    List<Category> getAllCategories();
}
