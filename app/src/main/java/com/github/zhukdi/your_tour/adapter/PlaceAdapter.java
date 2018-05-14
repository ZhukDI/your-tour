package com.github.zhukdi.your_tour.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.zhukdi.your_tour.R;
import com.github.zhukdi.your_tour.helper.ImageUtils;
import com.github.zhukdi.your_tour.model.Place;

import java.util.List;

/**
 * Created by Dmitry on 5/1/2018.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<Place> placeList;
    private Context context;

    public PlaceAdapter(List<Place> placeList, Context context) {
        this.placeList = placeList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.textViewName.setText(place.getName());
        holder.textViewVicinity.setText(place.getVicinity());
        ImageUtils.loadGooglePhoto(holder.imageViewPlacePhoto, place.getPhotos().get(0).getPhotoReference());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewVicinity;
        public ImageView imageViewPlacePhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.list_item_place_name);
            textViewVicinity = (TextView) itemView.findViewById(R.id.list_item_place_vicinity);
            imageViewPlacePhoto = (ImageView) itemView.findViewById(R.id.list_item_place_image);
        }
    }

}
