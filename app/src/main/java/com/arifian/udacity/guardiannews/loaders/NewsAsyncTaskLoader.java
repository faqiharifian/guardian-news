package com.arifian.udacity.guardiannews.loaders;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.widget.RecyclerView;

import com.arifian.udacity.guardiannews.MainActivity;
import com.arifian.udacity.guardiannews.entities.News;
import com.arifian.udacity.guardiannews.utils.JSONUtils;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by faqih on 22/04/17.
 */

public class NewsAsyncTaskLoader extends AsyncTaskLoader<List<News>>{
    private final String url = "https://content.guardianapis.com/search";
    Context context;
    String query;
    RecyclerView.Adapter adapter;

    public NewsAsyncTaskLoader(Context context, String query, RecyclerView.Adapter adapter) {
        super(context);
        this.context = context;
        this.query = query;
        this.adapter = adapter;
    }

    @Override
    public List<News> loadInBackground() {
        List<News> newsList = new ArrayList<>();
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(getUrlWithParams());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                String jsonStr = readFromStream(inputStream);
                newsList = JSONUtils.parseJSON(jsonStr);
            } else {
                ((MainActivity)context).showErrorServer();
            }
        } catch (JSONException je){
            ((MainActivity)context).showErrorResponse();
            je.printStackTrace();
        } catch (ParseException pe){
            ((MainActivity)context).showErrorResponse();
            pe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                }catch(IOException io){
                    io.printStackTrace();
                }
            }
        }
        return newsList;
    }

    private String getUrlWithParams(){
        Uri.Builder builder = Uri.parse(url).buildUpon();
//        ?lang=en&show-fields=thumbnail&from-date=2017-04-1&to-date=2017-04-22&order-by=newest&page=1&api-key=test
//        builder.appendQueryParameter("q", "en");
        builder.appendQueryParameter("lang", "en");
        builder.appendQueryParameter("show-fields", "thumbnail");
        builder.appendQueryParameter("page-size", "20");
//        builder.appendQueryParameter("from-date", "thumbnail");
//        builder.appendQueryParameter("to-date", "thumbnail");
//        builder.appendQueryParameter("order-by", "thumbnail");
        builder.appendQueryParameter("page", "1");
        builder.appendQueryParameter("api-key", "test");
        return builder.toString();
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
