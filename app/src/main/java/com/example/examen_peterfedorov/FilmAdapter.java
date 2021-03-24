package com.example.examen_peterfedorov;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder>
{
    public final static String URL = "http://cinema.areas.su/up/images/";
    private List<ParamFilm> mFilms;
    private Context context;

    public FilmAdapter(List<ParamFilm> films)
    {
        this.mFilms = films;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        ParamFilm paramFilm = mFilms.get(position);
        holder.filmText.setText(paramFilm.getName());
        Picasso.with(context).load(URL + paramFilm.getPoster()).resize(260, 320).into(holder.filmImage);
        holder.linearLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context.getApplicationContext(), MovieScreenActivity.class);
                intent.putExtra("name", paramFilm.getName());
                intent.putExtra("image", paramFilm.getPoster());
                intent.putExtra("des", paramFilm.getDescription());
                intent.putExtra("age", paramFilm.getAge());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        if(mFilms == null)
        {
            return 0;
        }
        return mFilms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView filmText;
        private ImageView filmImage;
        private LinearLayout linearLayout;
        ViewHolder(View itemView)
        {
            super(itemView);
            filmText = itemView.findViewById(R.id.filmText);
            filmImage = itemView.findViewById(R.id.filmImage);
            linearLayout = itemView.findViewById(R.id.lin1);
        }
    }
}
