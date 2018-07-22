package com.example.satsv.myinshorts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.satsv.myinshorts.model.Article;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class VerticlePagerAdapter extends PagerAdapter {

    List<Article> mResources;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public VerticlePagerAdapter(List<Article> mResources,Context context) {
        this.mResources=mResources;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.card, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        TextView label = (TextView) itemView.findViewById(R.id.textView1);
        TextView link = (TextView) itemView.findViewById(R.id.textView2);
        TextView head = (TextView) itemView.findViewById(R.id.textView0);
        head.setText(mResources.get(position).getTitle());

        label.setText(mResources.get(position).getDescription());
        link.setText(mResources.get(position).getUrl());
        Picasso.get().load(mResources.get(position).getUrlToImage()).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}