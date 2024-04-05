package com.pbl5.chickenapplication.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pbl5.chickenapplication.R;
import com.pbl5.chickenapplication.model.ChickenBreed;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChickenAdapter extends RecyclerView.Adapter<ChickenAdapter.ViewHolder> {
    private List<ChickenBreed>chickensList;
    private OnChickenListener onChickenListener;
    public ChickenAdapter(List<ChickenBreed> chickensList,OnChickenListener onChickenListener){
        this.chickensList=chickensList;
        this.onChickenListener=onChickenListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chicken_item, parent, false);

        return new ViewHolder(view,onChickenListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChickenBreed chickenBreed = chickensList.get(position);
        holder.tvChickenName.setText(chickenBreed.getUuid());
        Picasso.get().load(chickenBreed.getUrl()).into(holder.ivChickenImage);


    }

    @Override
    public int getItemCount() {
        return chickensList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivChickenImage;
        private TextView tvChickenName;

        OnChickenListener onChickenListener;
        public ViewHolder(View view,OnChickenListener onChickenListener) {
            super(view);
            // Define click listener for the ViewHolder's View
            ivChickenImage = view.findViewById(R.id.iv_chickenImage);
            tvChickenName = view.findViewById(R.id.tv_chickenName);
            this.onChickenListener=onChickenListener;
            view.setOnClickListener(this);
            // textView = (TextView) view.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            onChickenListener.onChickenClick(getAdapterPosition());

        }


//        public TextView getTextView() {
//            return textView;
//        }
    }
    public interface OnChickenListener{
        void onChickenClick(int position);
    }
}

