package com.cxh.mvpart.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cxh.mvpart.R;
import com.cxh.mvpart.ui.widget.GlideCircleTransformation;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/29
 */
public class GlideUtils {

    public static void loadImage(Object path, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(path)
                .apply(requestOptions(R.mipmap.ic_launcher, R.mipmap.ic_launcher))
                .into(imageView);
    }

    public static void loadImage(Object path, int placeholderResId, int errorResId, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(path)
                .apply(requestOptions(placeholderResId, errorResId))
                .into(imageView);
    }

    public static void loadCircleImage(Object path, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(path)
                .apply(circleRequestOptions(R.mipmap.ic_launcher, R.mipmap.ic_launcher))
                .into(imageView);
    }

    public static void loadCircleImage(Object path, int placeholderResId, int errorResId, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(path)
                .apply(circleRequestOptions(placeholderResId, errorResId))
                .into(imageView);
    }

    private static RequestOptions requestOptions(int placeholderResId, int errorResId) {
        return new RequestOptions()
                .placeholder(placeholderResId)
                .error(errorResId);
    }

    private static RequestOptions circleRequestOptions(int placeholderResId, int errorResId) {
        return requestOptions(placeholderResId, errorResId)
                .transform(new GlideCircleTransformation());
    }
}
