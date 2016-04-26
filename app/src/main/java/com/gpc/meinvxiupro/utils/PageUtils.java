package com.gpc.meinvxiupro.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;

/**
 * Created by gupengcheng on 16/4/23.
 */
public class PageUtils {
    public static ViewPager.PageTransformer getPageTransformer(Context context) {
        String sharedTransformer = SharedPreferencesUtils.getTransformer(context);
        if (sharedTransformer.equals(Constant.Transformer.DefaultTransformer)) {
            return new DefaultTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.BackgroundToForegroundTransformer)){
            return new BackgroundToForegroundTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.AccordionTransformer)){
            return new AccordionTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.DepthPageTransformer)){
            return new DepthPageTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.ForegroundToBackgroundTransformer)){
            return new ForegroundToBackgroundTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.RotateDownTransformer)){
            return new RotateDownTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.RotateUpTransformer)){
            return new RotateUpTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.ScaleInOutTransformer)){
            return new ScaleInOutTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.StackTransformer)){
            return new StackTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.TabletTransformer)){
            return new TabletTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.ZoomInTransformer)){
            return new ZoomInTransformer();
        }else if (sharedTransformer.equals(Constant.Transformer.ZoomOutTransformer)){
            return new ZoomOutTranformer();
        }else if (sharedTransformer.equals(Constant.Transformer.ZoomOutSlideTransformer)){
            return new ZoomOutSlideTransformer();
        }
        return new DefaultTransformer();
    }
}
