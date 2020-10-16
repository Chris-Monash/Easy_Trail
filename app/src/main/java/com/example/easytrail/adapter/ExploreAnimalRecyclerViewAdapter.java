package com.example.easytrail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.easytrail.R;
import com.example.easytrail.model.AnimalResult;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExploreAnimalRecyclerViewAdapter extends RecyclerView.Adapter<ExploreAnimalRecyclerViewAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView animalScore_tv;
        TextView animalDistribution_tv;
        TextView animalName_tv;
        TextView animalHabitat_tv;
        ImageView animalImage_iv;

        public ViewHolder(View itemView){
            super(itemView);
            animalScore_tv = itemView.findViewById(R.id.exploreAnimal_score_tv);
            animalDistribution_tv = itemView.findViewById(R.id.exploreAnimal_distribution_tv);
            animalName_tv = itemView.findViewById(R.id.exploreAnimal_name_tv);
            animalHabitat_tv = itemView.findViewById(R.id.exploreAnimal_habitat_tv);
            animalImage_iv = itemView.findViewById(R.id.kbvExploreAnimalImage);
        }
    }

    private List<AnimalResult> animalResults;
    private Context context;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View animalView = inflater.inflate(R.layout.explore_animal_container,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(animalView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {
        final AnimalResult animal = animalResults.get(i);
        TextView tvAnimalScore = viewHolder.animalScore_tv;
        TextView tvAnimalDistribution = viewHolder.animalDistribution_tv;
        TextView tvAnimalName = viewHolder.animalName_tv;
        TextView tvAnimalHabitat = viewHolder.animalHabitat_tv;
        ImageView ivAnimalImage = viewHolder.animalImage_iv;

        tvAnimalScore.setText(String.valueOf(animal.getAnimal_score()));
        tvAnimalDistribution.setText(animal.getAbundance());
        tvAnimalName.setText(animal.getComm_name());
        tvAnimalHabitat.setText(animal.getAnimal_habitat());
        Glide.with(context)
                .load(animal.getAnimal_image())
                .placeholder(new ColorDrawable(Color.BLACK))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivAnimalImage);

        //item click
        if (onItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView, i);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(viewHolder.itemView, i);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return animalResults.size();
    }

    public ExploreAnimalRecyclerViewAdapter(Context context, List<AnimalResult> animalResults){
        this.context = context;
        this.animalResults = animalResults;
    }

    public void addExploreAnimals(List<AnimalResult> animals){
        animalResults = animals;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = (OnItemClickListener) listener;
    }
}
