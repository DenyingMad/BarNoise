package com.devilpanda.barnoise.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubjectDao {

    @Insert
    void insertCategory(Category category);

    @Insert
    void insertSubject(Subject subject);

    @Query("Select * FROM subject WHERE category_id = :categoryId")
    List<Subject> getSubjectByCategory(long categoryId);

    @Query("SELECT * FROM Category WHERE id = :id")
    Category getCategoryById(long id);

    @Query("SELECT * FROM category")
    List<Category> getAllCategories();

    @Query("DELETE FROM category")
    void deleteAllCategories();

    @Query("DELETE FROM subject")
    void deleteAllSubjects();
}
