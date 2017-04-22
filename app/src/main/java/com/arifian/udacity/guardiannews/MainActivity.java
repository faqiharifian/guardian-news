package com.arifian.udacity.guardiannews;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arifian.udacity.guardiannews.adapters.NewsRecyclerAdapter;
import com.arifian.udacity.guardiannews.entities.News;
import com.arifian.udacity.guardiannews.loaders.NewsAsyncTaskLoader;
import com.arifian.udacity.guardiannews.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>  {
    private final int ID_LOADER = 0;
    RecyclerView newsRecyclerView;
    NewsRecyclerAdapter newsAdapter;
    ProgressDialog progressDialog;
    TextView errorNoInternetTextView, errorEmptyTextView, errorServerTextView, errorResponseTextView;

    BroadcastReceiver receiver;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorNoInternetTextView = (TextView) findViewById(R.id.text_error_no_internet);
        errorEmptyTextView = (TextView) findViewById(R.id.text_error_empty);
        errorServerTextView = (TextView) findViewById(R.id.text_error_server);
        errorResponseTextView = (TextView) findViewById(R.id.text_error_response);

        newsRecyclerView = (RecyclerView) findViewById(R.id.recycler_book);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        newsAdapter = new NewsRecyclerAdapter(this);
        newsRecyclerView.setAdapter(newsAdapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        getSupportLoaderManager().initLoader(ID_LOADER, null, this).forceLoad();

        if(!NetworkUtils.isConnected(this)){
            showErrorNoInternet();
        }else{
            hideErrors();
        }

        receiver = new NetworkStateChangeReceiver();
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, filter);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        showProgressDialog();
        return new NewsAsyncTaskLoader(this, "", newsAdapter);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        hideProgressDialog();
        if(data.size() == 0) showErrorEmpty();
        newsAdapter.setNews(data);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        hideProgressDialog();
        showErrorEmpty();
        newsAdapter.setNews(new ArrayList<News>());
    }

    private void showProgressDialog() {
        progressDialog.show();
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_bar);
    }

    private void hideProgressDialog() {
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
    }

    public void showErrorNoInternet(){
        errorNoInternetTextView.setVisibility(View.VISIBLE);
    }

    public void showErrorEmpty(){
        hideErrors();
        newsRecyclerView.setVisibility(View.GONE);
        errorEmptyTextView.setVisibility(View.VISIBLE);
    }

    public void showErrorServer(){
        hideErrors();
        newsRecyclerView.setVisibility(View.GONE);
        errorServerTextView.setVisibility(View.VISIBLE);
    }

    public void showErrorResponse(){
        hideErrors();
        newsRecyclerView.setVisibility(View.GONE);
        errorResponseTextView.setVisibility(View.VISIBLE);
    }

    public void hideErrors(){
        newsRecyclerView.setVisibility(View.VISIBLE);
        errorEmptyTextView.setVisibility(View.GONE);
        errorNoInternetTextView.setVisibility(View.GONE);
        errorServerTextView.setVisibility(View.GONE);
        errorResponseTextView.setVisibility(View.GONE);
    }

    public class NetworkStateChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            if(!NetworkUtils.isConnected(context)){
                showErrorNoInternet();
            }else{
                hideErrors();
                if(newsAdapter.getItemCount() == 0){
                    getSupportLoaderManager().restartLoader(ID_LOADER, null, MainActivity.this).forceLoad();
                }
            }
        }
    }
}
