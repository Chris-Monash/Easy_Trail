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
import com.example.easytrail.entity.LocalAnimal;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AnimalInfoRecyclerViewAdapter extends RecyclerView.Adapter<AnimalInfoRecyclerViewAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView animalInfo_image_iv;
        TextView animalInfo_name_tv;
        TextView animalInfo_abundance_tv;
        TextView animalInfo_conservation_tv;
        TextView animalInfo_habitat_tv;
        TextView animalInfo_score_tv;

        public ViewHolder(View itemView){
            super(itemView);
            animalInfo_image_iv = itemView.findViewById(R.id.animalInfo_image_iv);
            animalInfo_name_tv = itemView.findViewById(R.id.animalInfo_name_tv);
            animalInfo_abundance_tv = itemView.findViewById(R.id.animalInfo_abundance_tv);
            animalInfo_conservation_tv = itemView.findViewById(R.id.animalInfo_conservation_tv);
            animalInfo_habitat_tv = itemView.findViewById(R.id.animalInfo_habitat_tv);
            animalInfo_score_tv = itemView.findViewById(R.id.animalInfo_score_tv);
        }

    }

    private List<LocalAnimal> animals;
    public Context context;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View localAnimalsView = inflater.inflate(R.layout.animal_info_container,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(localAnimalsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {
        final LocalAnimal localAnimal = animals.get(i);
        ImageView ivAnimalInfoImage = viewHolder.animalInfo_image_iv;
        TextView tvAnimalInfoName = viewHolder.animalInfo_name_tv;
        TextView tvAnimalInfoAbundance = viewHolder.animalInfo_abundance_tv;
        TextView tvAnimalInfoConservation = viewHolder.animalInfo_conservation_tv;
        TextView tvAnimalInfoHabitat = viewHolder.animalInfo_habitat_tv;
        TextView tvAnimalInfoScore = viewHolder.animalInfo_score_tv;

        Glide.with(context)
                .load(localAnimal.getLocalAnimal_image())
                .placeholder(new ColorDrawable(Color.BLACK))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivAnimalInfoImage);

        tvAnimalInfoName.setText(localAnimal.getLocalAnimal_name());
        tvAnimalInfoAbundance.setText(localAnimal.getLocalAnimal_abundance());
        tvAnimalInfoConservation.setText(localAnimal.getLocalAnimal_conservation());
        tvAnimalInfoHabitat.setText(localAnimal.getLocalAnimal_habitat());
        tvAnimalInfoScore.setText(String.valueOf(localAnimal.localAnimal_score));
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public AnimalInfoRecyclerViewAdapter (Context context, List<LocalAnimal> animals){
        this.context = context;
        this.animals = animals;
    }

    public void addLocalAnimals(List<LocalAnimal> localAnimals){
        animals = localAnimals;
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
