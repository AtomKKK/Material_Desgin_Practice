package com.qtking.mdpractice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qtking.mdpractice.FruitActivity;
import com.qtking.mdpractice.R;
import com.qtking.mdpractice.bean.Fruit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Think on 2016/12/9.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruits;
    private Context mContext;

    public FruitAdapter(List<Fruit> fruits, Context context) {
        this.mFruits = fruits;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruits.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruits.get(position);
        holder.tvFruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.ivFruit);

    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fruit_image)
        ImageView ivFruit;
        @BindView(R.id.fruit_name)
        TextView tvFruitName;
        @BindView(R.id.cardview)
        CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
