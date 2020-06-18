package com.devilpanda.barnoise.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.devilpanda.barnoise.R;
import com.devilpanda.barnoise.adapters.CategoryAdapter;
import com.devilpanda.barnoise.model.App;
import com.devilpanda.barnoise.model.AppDatabase;
import com.devilpanda.barnoise.model.Subject;
import com.devilpanda.barnoise.model.SubjectDao;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.OnSubjectListener {

    private static final String TAG = "CategoryActivity";
    private SubjectDao dao;
    private RecyclerView recyclerView;
    private TextView categoryTitle;
    private CategoryAdapter categoryAdapter;
    private List<Subject> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryTitle = findViewById(R.id.category_title);

        Intent intent = getIntent();
        int category = intent.getIntExtra("category", 0);

        AppDatabase db = App.getInstance().getDatabase();
        dao = db.subjectDao();

        recyclerView = findViewById(R.id.category_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        GetSubjectsTask task = new GetSubjectsTask();
        task.execute(category);
    }

    class GetSubjectsTask extends AsyncTask<Integer, Void, String>{

        @Override
        protected String doInBackground(Integer... integers) {
            subjects = dao.getSubjectByCategory(integers[0]);
            return dao.getCategoryById(integers[0]).getName();
        }

        @Override
        protected void onPostExecute(String categoryName) {
            super.onPostExecute(categoryName);
            categoryAdapter = new CategoryAdapter(subjects, CategoryActivity.this);
            recyclerView.setAdapter(categoryAdapter);
            categoryTitle.setText(categoryName);
        }
    }

    @Override
    public void onSubjectClick(int position) {
        Log.d(TAG, "onSubjectClick: click: " + position);
    }
}