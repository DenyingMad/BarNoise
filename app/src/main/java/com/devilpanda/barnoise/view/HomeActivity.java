package com.devilpanda.barnoise.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.devilpanda.barnoise.R;
import com.devilpanda.barnoise.adapters.HomeAdapter;
import com.devilpanda.barnoise.model.App;
import com.devilpanda.barnoise.model.AppDatabase;
import com.devilpanda.barnoise.model.Category;
import com.devilpanda.barnoise.model.SubjectDao;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnCategoryListener {

    private static final String TAG = "HomeActivity";

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

    @Override
    public void onListener(int position) {
        Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
        intent.putExtra("category", position);
        startActivity(intent);
    }

    class RecyclerViewInit extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            homeAdapter = new HomeAdapter(HomeActivity.this, categoryList, HomeActivity.this);
            recyclerView.setAdapter(homeAdapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryList = dao.getAllCategories();
            return null;
        }
    }
}