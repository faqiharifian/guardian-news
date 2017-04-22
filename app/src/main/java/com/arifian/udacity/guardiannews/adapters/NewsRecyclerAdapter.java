package com.arifian.udacity.guardiannews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arifian.udacity.guardiannews.R;
import com.arifian.udacity.guardiannews.entities.News;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faqih on 22/04/17.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    Context context;
    List<News> newsList;

    public NewsRecyclerAdapter(Context context) {
        this.context = context;
        newsList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.titleTextView.setText(news.getTitle());
        holder.sectionTextView.setText(news.getSection());
        holder.dateTextView.setText(news.getDate());

        if(news.getThumbnail() != null){
            Glide.with(context).load(news.getThumbnail()).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(holder.newsImageView);
        }else{
            holder.newsImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setNews(List<News> newsList){
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView newsImageView;
        TextView titleTextView, sectionTextView, dateTextView;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            newsImageView = (ImageView) itemView.findViewById(R.id.image_news);
            titleTextView = (TextView) itemView.findViewById(R.id.text_news_title);
            sectionTextView = (TextView) itemView.findViewById(R.id.text_news_section);
            dateTextView = (TextView)itemView.findViewById(R.id.text_news_date);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_news_image);
        }
    }
}
