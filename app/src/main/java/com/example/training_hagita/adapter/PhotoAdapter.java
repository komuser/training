package com.example.training_hagita.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.training_hagita.dao.PhotoDao;
import com.example.training_hagita.R;

import java.util.List;

public abstract class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.RecyclerViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<PhotoDao> mList;

    protected abstract void onRecyclerViewClicked(PhotoDao photoDao);

    public PhotoAdapter(List<PhotoDao> list) {
        this.mList = list;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView path;

        RecyclerViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.title = (TextView) view.findViewById(R.id.title);
            this.path = (TextView) view.findViewById(R.id.path);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, int position) {

        // 再利用できるViewがなかったらLayoutInflaterを使ってrow.xmlをViewにする
        View inflate = mLayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        final RecyclerViewHolder viewHolder = new RecyclerViewHolder(inflate);

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                PhotoDao photoDao = mList.get(pos);
                onRecyclerViewClicked(photoDao);
            }
        });

        // そのViewにデータをセットする
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.image.setImageBitmap(mList.get(position).getImage());
        holder.title.setText(mList.get(position).getTitle());
        holder.path.setText(mList.get(position).getPath());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}