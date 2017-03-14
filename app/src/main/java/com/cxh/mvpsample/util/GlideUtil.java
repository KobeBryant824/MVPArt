package com.cxh.mvpsample.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/10 10:40.
 */
public class GlideUtil {
    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式 Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity,
     * 同时将Activity/Fragment作为with()参数的好处是：图片加载会和Activity/Fragment的生命周期保持一致
     */

    //默认加载
    public static void loadImageView(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

    //加载圆形图片
    public static void loadRoundImageView(final Context context, String path, final ImageView imageView) {
        Glide.with(context).load(path).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    //加载指定大小,ps:Glide可以自动计算出任意情况下的ImageView大小
    public static void loadImageViewSize(Context context, String path, int width, int height, ImageView imageView) {
        Glide.with(context).load(path).override(width, height).into(imageView);
    }

    //设置加载中以及加载失败图片
    public static void loadImageViewLoding(Context context, String path, ImageView imageView, int lodingImage, int errorImageView) {
        Glide.with(context).load(path).placeholder(lodingImage).error(errorImageView).into(imageView);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLodingSize(Context context, String path, int width, int height, ImageView imageView, int lodingImage, int errorImageView) {
        Glide.with(context).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(imageView);
    }

    //设置缩略图支持，会先加载缩略图
    public static void loadImageViewThumbnail(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).thumbnail(0.1f).into(imageView);
    }

    //设置动态转换，api提供了比如：
    // centerCrop() 拉伸截取中间部分显示
    // fitCenter() 等比拉伸填满ImageView
    public static void loadImageViewCrop(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).centerCrop().into(imageView);
    }

    //设置跳过内存缓存
    public static void loadImageViewSkipCache(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).skipMemoryCache(true).into(imageView);
    }

    //设置下载优先级
    public static void loadImageViewPriority(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).priority(Priority.NORMAL).into(imageView);
    }

    /**
     * 策略解说：
     * all:缓存源资源和转换后的资源
     * none:不作任何磁盘缓存
     * source:缓存源资源
     * result：缓存转换后的资源,默认的
     */

    //设置缓存全尺寸、转换后的策略
    public static void loadImageViewDiskCache(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    /**
     * api也提供了几个常用的动画：比如crossFade() 淡入淡出
     */

    //设置ViewPropertyAnimation.Animator加载动画
    public static void loadImageViewAnim(Context context, String path, ViewPropertyAnimation.Animator anim, ImageView imageView) {
        Glide.with(context).load(path).animate(anim).into(imageView);
    }

    //设置animationId加载动画
    public static void loadImageViewAnim(Context context, String path, int anim, ImageView imageView) {
        Glide.with(context).load(path).animate(anim).into(imageView);
    }

    //设置动态GIF加载方式，如果不是gif会回调error
    public static void loadImageViewDynamicGif(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).asGif().into(imageView);
    }

    //设置静态GIF加载方式，如果不是gif会回调error
    public static void loadImageViewStaticGif(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).asBitmap().into(imageView);
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

    //设置监听请求接口
    public static void loadImageViewListener(Context context, String path, ImageView imageView, RequestListener<String, GlideDrawable> requstlistener) {
        Glide.with(context).load(path).listener(requstlistener).into(imageView);
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
    public static void loadImageViewContent(Context context, String path, SimpleTarget<GlideDrawable> simpleTarget) {
        Glide.with(context).load(path).centerCrop().into(simpleTarget);
    }

    //清理磁盘缓存
    public static void GuideClearDiskCache(Context context) {
        //清理磁盘缓存 需要在子线程中执行
        Glide.get(context).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context context) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(context).clearMemory();
    }

}
