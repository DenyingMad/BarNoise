package com.devilpanda.barnoise.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.devilpanda.barnoise.R;
import com.devilpanda.barnoise.adapters.HomeAdapter;
import com.devilpanda.barnoise.model.App;
import com.devilpanda.barnoise.model.AppDatabase;
import com.devilpanda.barnoise.model.Category;
import com.devilpanda.barnoise.model.SubjectDao;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private SubjectDao dao;
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;
    private List<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AppDatabase db = App.getInstance().getDatabase();
        dao = db.subjectDao();

        recyclerView = findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });


        RecyclerViewInit a = new RecyclerViewInit();
        a.execute();
    }

    class RecyclerViewInit extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            homeAdapter = new HomeAdapter(HomeActivity.this, categoryList);
            recyclerView.setAdapter(homeAdapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryList = dao.getAllCategories();
            return null;
        }
    }
}