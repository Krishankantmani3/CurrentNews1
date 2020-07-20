package com.example.android.currentnews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>{

    private Context context;
    private  Article[] articles;

    public ArticleAdapter(Context context, Article[] articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.mylistview,parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        final Article obj=articles[position];
        holder.title.setText(obj.getTitle());
        holder.description.setText(obj.getDescription());
        Glide.with(context).load(obj.getUrlToImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Uri uri=Uri.parse(obj.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);*/

                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("url",obj.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.length;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,description;


        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.desc);
        }
    }

}
