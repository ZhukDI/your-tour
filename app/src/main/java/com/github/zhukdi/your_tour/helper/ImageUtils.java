package com.github.zhukdi.your_tour.helper;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static com.github.zhukdi.your_tour.settings.AppSettings.GOOGLE_PLACES_API_KEY;

/**
 * Created by Dmitry on 5/14/2018.
 */

public class ImageUtils {

    private static final String PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo?photoreference=%s&key=%s&maxheight=200";

    public static void loadGooglePhoto(ImageView imageView, String photoreference) {
        String url = String.format(PHOTO_URL, photoreference, GOOGLE_PLACES_API_KEY);
        Picasso.get().load(url).into(imageView);
    }

}
