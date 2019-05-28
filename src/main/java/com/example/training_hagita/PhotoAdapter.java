package com.example.training_hagita;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<PhotoDao> mList;

    protected void onRecyclerViewClicked(PhotoDao photoDao) {
    }

    PhotoAdapter(List<PhotoDao> list) {
        this.mList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView path;

        ViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.title = (TextView) view.findViewById(R.id.title);
            this.path = (TextView) view.findViewById(R.id.path);
        }
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int position) {

        // 再利用できるViewがなかったらLayoutInflaterを使ってrow.xmlをViewにする
        View inflate = mLayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        final ViewHolder viewHolder = new ViewHolder(inflate);

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
       holder.image.setImageBitmap(mList.get(position).getImage());
       holder.title.setText(mList.get(position).getTitle());
       holder.path.setText(mList.get(position).getPath());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}