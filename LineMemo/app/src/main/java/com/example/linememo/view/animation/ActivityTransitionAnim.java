package com.example.linememo.view.animation;

import android.app.Activity;
import android.content.Intent;

import com.example.linememo.R;

public class ActivityTransitionAnim {
    public static final int DEFAULT = 9999;
    public static final int NO_TRANSITION = 0;
    public static final int SHOW_DETAIL_PAGE = 1;
    public static final int SHOW_NEW_PAGE = 2;
    public static final int SCALE_UP_FADE_IN = 4;
    public static final int HIDE_DETAIL_PAGE = -1;
    public static final int HIDE_NEW_PAGE = -2;
    public static final int FADE_TRANSITION = 3;
    public static final int SCALE_DOWN_FADE_OUT = 4;

    public static void startActivityWithAnim(Activity activity, int effect, Intent intent) {
        activity.startActivity(intent);
        switch (effect) {
            case NO_TRANSITION:
                activity.overridePendingTransition(R.anim.non_anim, R.anim.non_anim);
                break;
            case SHOW_DETAIL_PAGE:
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left_little);
                break;
            case SHOW_NEW_PAGE:
                activity.overridePendingTransition(R.anim.push_up_in, R.anim.push_down_out_little);
                break;
            case FADE_TRANSITION:
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case SCALE_UP_FADE_IN:
                activity.overridePendingTransition(R.anim.scale_up_fade_in, R.anim.fade_out);
                break;
        }
    }

    public static void startActivityWithAnim(Activity activity, int effect, Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
        switch (effect) {
            case NO_TRANSITION:
                activity.overridePendingTransition(R.anim.non_anim, R.anim.non_anim);
                break;
            case SHOW_DETAIL_PAGE:
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left_little);
                break;
            case SHOW_NEW_PAGE:
                activity.overridePendingTransition(R.anim.push_up_in, R.anim.push_down_out_little);
                break;
            case FADE_TRANSITION:
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }

    public static void finishActivityWithAnim(Activity activity, int effect) {
        activity.finish();
        switch (effect) {
            case NO_TRANSITION:
                activity.overridePendingTransition(R.anim.non_anim, R.anim.non_anim);
                break;
            case HIDE_DETAIL_PAGE:
                activity.overridePendingTransition(R.anim.slide_in_left_little, R.anim.slide_out_right);
                break;
            case HIDE_NEW_PAGE:
                activity.overridePendingTransition(R.anim.push_up_in_little, R.anim.push_down_out);
                break;
            case FADE_TRANSITION:
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case SCALE_DOWN_FADE_OUT:
                activity.overridePendingTransition(R.anim.fade_in, R.anim.scale_down_fade_out);
        }
    }
}
