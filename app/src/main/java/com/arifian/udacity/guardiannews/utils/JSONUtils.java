package com.arifian.udacity.guardiannews.utils;

import com.arifian.udacity.guardiannews.entities.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by faqih on 22/04/17.
 */

public class JSONUtils {
    public static List<News> parseJSON(String jsonString) throws JSONException, ParseException{
        List<News> newsList = new ArrayList<>();

        JSONObject rootJson = new JSONObject(jsonString);
        JSONObject responseJson = rootJson.getJSONObject("response");
        if(responseJson.has("results")) {
            JSONArray resultsJson = responseJson.getJSONArray("results");
            for(int i = 0; i < resultsJson.length(); i++){
                JSONObject resultJson = resultsJson.getJSONObject(i);
                String title = resultJson.getString("webTitle");
                String section = resultJson.getString("sectionName");
                String link = resultJson.getString("webUrl");
                String date = getReadableDate(resultJson.getString("webPublicationDate"));

                String thumbnail = null;
                if(resultJson.has("fields")){
                    JSONObject fieldsJson = resultJson.getJSONObject("fields");
                    if(fieldsJson.has("thumbnail")){
                        thumbnail = fieldsJson.getString("thumbnail");
                    }
                }

                newsList.add(new News(thumbnail, title, section, date, link));
            }
        }

        return newsList;
    }

    private static String getReadableDate(String webPublicationDate) throws ParseException {
        SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Calendar calendar = Calendar.getInstance();
        Date date = sdfParse.parse(webPublicationDate);
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        Date now = new Date();
        calendar.setTime(now);
        int yearNow = calendar.get(Calendar.YEAR);

        SimpleDateFormat sdfFormat = new SimpleDateFormat("dd MMM");
        if(year != yearNow)
            sdfFormat = new SimpleDateFormat("dd MMM'\n'yyyy");

        return sdfFormat.format(date);
    }
}
