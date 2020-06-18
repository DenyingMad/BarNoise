package com.devilpanda.barnoise;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.devilpanda.barnoise.model.App;
import com.devilpanda.barnoise.model.AppDatabase;
import com.devilpanda.barnoise.model.Category;
import com.devilpanda.barnoise.model.Subject;
import com.devilpanda.barnoise.model.SubjectDao;
import com.devilpanda.barnoise.view.HomeActivity;
import com.devilpanda.barnoise.view.LoadingActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class InitDB extends AsyncTask<Void, Integer, Void> {

    private AppDatabase db;
    private SubjectDao dao;
    private WeakReference<Context> context;
    private ProgressBar progressBar;

    public InitDB(WeakReference<Context> context, ProgressBar progressBar){
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        db = App.getInstance().getDatabase();
        dao = db.subjectDao();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int progress = 0;
        dao.deleteAllCategories();
        dao.deleteAllSubjects();

        progress+=10;
        publishProgress(progress);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Setting up categories
        String[] categories = context.get().getResources().getStringArray(R.array.categories);
        for (int i = 0; i < categories.length; i++){
            dao.insertCategories(new Category(i, categories[i]));
        }

        progress+=10;
        publishProgress(progress);

        // Setting up subjects for low alcohol
        String[] lowTitles = context.get().getResources().getStringArray(R.array.low_subjects_titles);
        String[] lowDescriptions = context.get().getResources().getStringArray(R.array.low_subjects_desc);
        String[] lowProblems = context.get().getResources().getStringArray(R.array.low_subjects_problem);
        String[] lowFacts = context.get().getResources().getStringArray(R.array.low_subjects_facts);
        for (int i = 0; i < lowTitles.length; i++){
            dao.insertSubject(new Subject(lowTitles[i], lowDescriptions[i], lowProblems[i], lowFacts[i], 0));
        }

        progress += 20;
        publishProgress(progress);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Setting up subjects for medium alcohol
        String[] midTitles = context.get().getResources().getStringArray(R.array.mid_subjects_titles);
        String[] midDescriptions = context.get().getResources().getStringArray(R.array.mid_subjects_desc);
        String[] midProblems = context.get().getResources().getStringArray(R.array.mid_subjects_problem);
        String[] midFacts = context.get().getResources().getStringArray(R.array.mid_subjects_facts);
        for (int i = 0; i < midTitles.length; i++){
            dao.insertSubject(new Subject(midTitles[i], midDescriptions[i], midProblems[i], midFacts[i], 1));
        }

        progress += 20;
        publishProgress(progress);

        // Setting up subjects for strong alcohol
        String[] strongTitles = context.get().getResources().getStringArray(R.array.strong_subjects_titles);
        String[] strongDescriptions = context.get().getResources().getStringArray(R.array.strong_subjects_desc);
        String[] strongProblems = context.get().getResources().getStringArray(R.array.strong_subjects_problem);
        String[] strongFacts = context.get().getResources().getStringArray(R.array.strong_subjects_facts);
        for (int i = 0; i < strongTitles.length; i++){
            dao.insertSubject(new Subject(strongTitles[i], strongDescriptions[i], strongProblems[i], strongFacts[i], 2));
        }

        progress += 20;
        publishProgress(progress);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Setting up subjects for moonshine
        String[] moonshineTitles = context.get().getResources().getStringArray(R.array.moonshine_subjects_titles);
        String[] moonshineDescriptions = context.get().getResources().getStringArray(R.array.moonshine_subjects_desc);
        String[] moonshineProblems = context.get().getResources().getStringArray(R.array.moonshine_subjects_problem);
        String[] moonshineFacts = context.get().getResources().getStringArray(R.array.moonshine_subjects_facts);
        for (int i = 0; i < moonshineTitles.length; i++){
            dao.insertSubject(new Subject(moonshineTitles[i], moonshineDescriptions[i], moonshineProblems[i], moonshineFacts[i], 3));
        }

        progress += 20;
        publishProgress(progress);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent intent = new Intent(context.get(), HomeActivity.class);
        context.get().startActivity(intent);
    }
}
