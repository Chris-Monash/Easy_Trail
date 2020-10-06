package com.example.easytrail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.easytrail.R;
import com.example.easytrail.model.TrailResult;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

public class TrailRecyclerViewAdapter extends RecyclerView.Adapter<TrailRecyclerViewAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView trailGrade_iv;
        TextView trailGrade_tv;
        TextView trailName_tv;
        TextView trailEnv_tv;
        KenBurnsView kenBurnsView;


        public ViewHolder(View itemView){
            super(itemView);

            trailGrade_iv = itemView.findViewById(R.id.trail_grade_iv);
            trailGrade_tv = itemView.findViewById(R.id.trail_grade_tv);
            trailName_tv = itemView.findViewById(R.id.trail_name_tv);
            trailEnv_tv = itemView.findViewById(R.id.trail_env_tv);
            kenBurnsView = itemView.findViewById(R.id.kbvImage);
        }

    }

    @Override
    public int getItemCount(){
        return trailResults.size();
    }

    private List<TrailResult> trailResults;
    public Context context;
    private AdapterView.OnItemClickListener onItemClickListener;

    public TrailRecyclerViewAdapter(Context context, List<TrailResult> trails) {
        this.context = context;
        trailResults = trails;
    }

    public void addTrails(List<TrailResult> trails){
        trailResults = trails;
        notifyDataSetChanged();
    }


    @Override
    public TrailRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View trailsView = inflater.inflate(R.layout.trail_container,parent,false);
        ViewHolder viewHolder = new ViewHolder(trailsView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final TrailRecyclerViewAdapter.ViewHolder viewHolder, final int position) {
        final TrailResult trail = trailResults.get(position);
        ImageView ivTrailGrade = viewHolder.trailGrade_iv;
        Glide.with(context)
                .load(trailResults.get(position).getDifficulty_image())//searchResults.get(position).getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivTrailGrade);
        TextView tvTrailGrade = viewHolder.trailGrade_tv;
        tvTrailGrade.setText(trail.getDifficulty_name());
        TextView tvTrailName = viewHolder.trailName_tv;
        tvTrailName.setText(trail.getTrail_name());
        TextView tvTrailEvo = viewHolder.trailEnv_tv;
        tvTrailEvo.setText(trail.getEnvironment());
        KenBurnsView kbv_trailImage = viewHolder.kenBurnsView;
        Glide.with(context)
                .load(trailResults.get(position).getTrail_image())//searchResults.get(position).getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(kbv_trailImage);




        //item click



    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = (AdapterView.OnItemClickListener) listener;
    }
}
