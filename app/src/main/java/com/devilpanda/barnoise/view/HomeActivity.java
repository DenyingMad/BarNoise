package com.devilpanda.barnoise.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.devilpanda.barnoise.InitDB;
import com.devilpanda.barnoise.R;
import com.devilpanda.barnoise.model.App;
import com.devilpanda.barnoise.model.AppDatabase;
import com.devilpanda.barnoise.model.Category;
import com.devilpanda.barnoise.model.Subject;
import com.devilpanda.barnoise.model.SubjectDao;

import java.lang.ref.WeakReference;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private SubjectDao dao;
    private List<Category> s;
    private List<Subject> subjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();
        dao = db.subjectDao();

        abc a = new abc();
        a.execute();
    }

    class abc extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            s = dao.getAllCategories();

            subjects = dao.getByCategory(3);

            return null;
        }
    }
}