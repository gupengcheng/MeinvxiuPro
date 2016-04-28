package com.gpc.meinvxiupro.utils;

/**
 * Created by pcgu on 16-3-10.
 */
public class Constant {
    public static final boolean IS_DEBUG = true;
    public static final int STATUS_BAR_HEIGHT = 24;

    public class ContextConstant {
        public static final String BUNDLE_NAME = "bundle_name";
    }

    public class BundleConstant {
        public static final String FRAGMENT_TITLE = "fragment_title";
        public static final String IMAGE_POSITION = "imgPosition";
        public static final String IMAGE_DATAS = "imgDatas";
        public static final String LOAD_TAG = "load_tag";
    }

    public class BroadcastReceiverAction {
        public static final String EXIT_TO_DESKTOP = "com.meinvxiupro.exittodesktop";
        public static final String AUTO_SET_WALLPAPER = "com.gpc.meinvxiupro.autosetwallpaper";
        public static final String BOOT_COMPLETE = "android.intent.action.BOOT_COMPLETED";
    }

    public class SharedPreferencesKey {
        public static final String HOME_TABS_TITLE = "home_tabs_title";
        public static final String APPLICATION_FIRST_INSTALLED = "application_first_installed";
        public static final String TRANSFORMER = "transformer";
        public static final String TRANSFORMER_POSITION = "transformer_position";
        public static final String DEVICE_IMEI = "device_imei";
        public static final String AUTO_SET_WALLPAPER = "auto_set_wallpaper";
        public static final String AUTO_SET_WALLPAPER_MILLI = "auto_set_wallpaper_mill";
        public static final String AUTO_SET_WALLPAPER_CURRENT_FILE_POSITION = "current_file_position";
    }

    public class Transformer {
        public static final String DefaultTransformer = "DefaultTransformer";
        public static final String AccordionTransformer = "AccordionTransformer";
        public static final String BackgroundToForegroundTransformer = "BackgroundToForegroundTransformer";
        public static final String DepthPageTransformer = "DepthPageTransformer";
        public static final String ForegroundToBackgroundTransformer = "ForegroundToBackgroundTransformer";
        public static final String RotateDownTransformer = "RotateDownTransformer";
        public static final String RotateUpTransformer = "RotateUpTransformer";
        public static final String ScaleInOutTransformer = "ScaleInOutTransformer";
        public static final String StackTransformer = "StackTransformer";
        public static final String TabletTransformer = "TabletTransformer";
        public static final String ZoomInTransformer = "ZoomInTransformer";
        public static final String ZoomOutTransformer = "ZoomOutTransformer";
        public static final String ZoomOutSlideTransformer = "ZoomOutSlideTransformer";
    }

    public class CommonData {
        public static final int LOAD_DEFAULT = 0;
        public static final int LOAD_LOCAL = 1;
    }

    public class SettingType {
        public static final int TITLE = 1;
        public static final int ITEM = 2;
        public static final int TRANSFORM_ITEM = 11;
        public static final int AUTO_SET_DOWNLOAD_WALLPAPER_ITEM = 12;
    }
}
