package com.arifian.udacity.guardiannews.entities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.arifian.udacity.guardiannews.R;

/**
 * Created by faqih on 22/04/17.
 */

public class Preference {
    Context context;
    SharedPreferences preferences;

    public Preference(Context context){
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener){
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public String getGeneralOrderBy(){
        String keyOrderBy = context.getString(R.string.setting_general_order_by_key);
        String defaultOrderBy = context.getString(R.string.setting_general_order_by_default_value);
        return preferences.getString(keyOrderBy, defaultOrderBy);
    }

    public String getSearchOrderBy(){
        String keyOrderBy = context.getString(R.string.setting_search_order_by_key);
        String defaultOrderBy = context.getString(R.string.setting_search_order_by_default_value);
        return preferences.getString(keyOrderBy, defaultOrderBy);
    }
}
