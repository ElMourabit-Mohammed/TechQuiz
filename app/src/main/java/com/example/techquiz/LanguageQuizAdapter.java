package com.example.techquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LanguageQuizAdapter extends BaseAdapter {

    private Context context;
    private String listLanguages[];
    private int listImages[];
    private LayoutInflater inflater;

    public LanguageQuizAdapter(Context ctx, String [] languageList, int [] images){
        this.context = ctx;
        this.listLanguages = languageList;
        this.listImages = images;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listLanguages.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = inflater.inflate(R.layout.activity_language_view, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.LanguageName);
        ImageView languageImage = (ImageView) convertView.findViewById(R.id.imageLanguage);
        txtView.setText(listLanguages[i]);
        languageImage.setImageResource(listImages[i]);
        return convertView;
    }
}
