package com.devilpanda.barnoise.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class, Subject.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SubjectDao subjectDao();
}
