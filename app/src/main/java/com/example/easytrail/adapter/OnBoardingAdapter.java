package com.example.easytrail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easytrail.R;
import com.example.easytrail.model.OnBoardingItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.ViewHolder> {

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView textTitle_tv;
        private TextView textDescription_tv;
        private ImageView imageOnBoarding_iv;

        public ViewHolder(View itemView){
            super(itemView);
            textTitle_tv = itemView.findViewById(R.id.textTitle);
            textDescription_tv = itemView.findViewById(R.id.textDescription);
            imageOnBoarding_iv = itemView.findViewById(R.id.imageOnboarding);

        }
    }
    private List<OnBoardingItem> onBoardingItems;
    public Context context;

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View onBoardingItemView = inflater.inflate(R.layout.item_container_boarding,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(onBoardingItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {
        final OnBoardingItem item = onBoardingItems.get(i);
        TextView tvTitle = viewHolder.textTitle_tv;
        TextView tvDescription = viewHolder.textDescription_tv;
        ImageView ivImage = viewHolder.imageOnBoarding_iv;
        tvTitle.setText(item.getTitle());
        tvDescription.setText(item.getDescription());
        ivImage.setImageResource(item.getImage());

    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    public OnBoardingAdapter(Context context, List<OnBoardingItem> onBoardingItems){
        this.context = context;
        this.onBoardingItems = onBoardingItems;
    }

    public void addItems(List<OnBoardingItem> onBoardingItems){
        this.onBoardingItems = onBoardingItems;
        notifyDataSetChanged();
    }
}
