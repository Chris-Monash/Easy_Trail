package com.example.easytrail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.easytrail.R;
import com.example.easytrail.model.AnimalResult;
import com.google.android.material.card.MaterialCardView;


import java.util.List;

public class AnimalRecyclerViewAdapter extends RecyclerView.Adapter<AnimalRecyclerViewAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView animalDistribution_tv;
        TextView animalName_tv;
        TextView animalHabitat_tv;
        ImageView animalImage_iv;
        MaterialCardView materialCardView;
//        KenBurnsView kenBurnsView;

        public ViewHolder(View itemView){
            super(itemView);
            animalDistribution_tv = itemView.findViewById(R.id.animal_distribution_tv);
            animalName_tv = itemView.findViewById(R.id.animal_name_tv);
            animalHabitat_tv = itemView.findViewById(R.id.animal_habitat_tv);
            animalImage_iv = itemView.findViewById(R.id.kbvAnimalImage);
            materialCardView = itemView.findViewById(R.id.animalContainer_cardView);
        }
    }

    private List<AnimalResult> animalResults;
    public Context context;
    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public AnimalRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View animalsView = inflater.inflate(R.layout.animal_container,parent,false);
        ViewHolder viewHolder = new ViewHolder(animalsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AnimalRecyclerViewAdapter.ViewHolder viewHolder, final int position) {
        final AnimalResult animal = animalResults.get(position);
        TextView tvAnimalDistribution = viewHolder.animalDistribution_tv;
        TextView tvAnimalName = viewHolder.animalName_tv;
        TextView tvAnimalHabitat = viewHolder.animalHabitat_tv;
        ImageView ivAnimalImage = viewHolder.animalImage_iv;
        MaterialCardView cardView = viewHolder.materialCardView;


        tvAnimalDistribution.setText(animal.getAbundance());
        tvAnimalName.setText(animal.getComm_name());
        tvAnimalHabitat.setText(animal.getAnimal_habitat());
        Glide.with(context)
                .load(animalResults.get(position).getAnimal_image())//searchResults.get(position).getImageUrl())
                .placeholder(new ColorDrawable(Color.BLACK))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivAnimalImage);
        //item click
        if (onItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView, position);


                }

            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(viewHolder.itemView, position);
//                    if(viewHolder.materialCardView.isChecked()){
//                    Toast.makeText(context,"clicked " + animal.getComm_name(),Toast.LENGTH_LONG).show();
//                }else{
//                        viewHolder.materialCardView.setChecked(true);
//                    }


                    return true;
                }
            });
        }
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if(viewHolder.materialCardView.isChecked()){
//                    Toast.makeText(context,"clicked " + animal.getComm_name(),Toast.LENGTH_LONG).show();
//                }
//                viewHolder.materialCardView.setChecked(true);
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return animalResults.size();
    }
    public AnimalRecyclerViewAdapter(Context context,List<AnimalResult> animals){
        this.context = context;
        this.animalResults = animals;
    }

    public void addAnimals(List<AnimalResult> animals){
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
