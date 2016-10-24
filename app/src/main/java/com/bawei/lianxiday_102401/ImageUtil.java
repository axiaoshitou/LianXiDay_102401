package com.bawei.lianxiday_102401;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by hyh_1 on 2015/11/5.
 */
public class ImageUtil {

    private static Object obj=new Object();

    private ImageLoader imageLoader;
    DisplayImageOptions options;
    //单例设计
    private static ImageUtil imageUtil;
    private ImageUtil(Context context){

        //给ImageLoader设置参数
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                //设置其他参数.
                .memoryCacheExtraOptions(480, 800)
                        //.diskCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG)
                .threadPoolSize(4)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)

                .build();

        //给ImageLoader设置显示参数

        options = new DisplayImageOptions.Builder()
                //设置其他参数
                .cacheInMemory(true)
                .cacheOnDisk(true)
                //.imageScaleType(ImageScaleType.)
                .bitmapConfig(Bitmap.Config.ARGB_4444)

                .build();


        //初始化Imageloader
        imageLoader=ImageLoader.getInstance();
        imageLoader.init(configuration);

    }

    public static  ImageUtil newInstance(Context context) {

        if(imageUtil==null){
            synchronized (obj){
                if(imageUtil==null){

                    imageUtil=new ImageUtil(context);
                }
            }
        }

        return imageUtil;
    }


    /**
     * 根据url，并且传入监听
     * @param uri
     * @param listener
     */
    public  void loadImage(String uri, ImageLoadingListener listener) {
        imageLoader.loadImage(uri,options,listener);
    }

       public void displayImage(String uri, ImageView imageView) {

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageLoader.displayImage(uri,imageView, options);
    }

}
