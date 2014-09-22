package com.ybjaa.extend.view;

import android.graphics.drawable.Drawable;

/**
 * Created by admin on 2014/9/19.
 */
public interface ISetActivitiedState {

    /**
     *
     * 设置按下按钮之前的Drawable状态
     *
     * @param drawable
     */
    void setBeforeClickedDrawable(Drawable drawable);


    /**
     *
     * 获得按下按钮之前的Drawable状态状态
     *
     * @return
     */
    Drawable getBeforeClickedDrawable();


    /**
     *
     * 设置按下按钮之后的Drawable状态
     *
     * @param drawable
     */
    void setAfterClickedDrawable(Drawable drawable);


    /**
     *
     * 获得按下按钮之后的Drawable状态状态
     *
     * @return
     */
    Drawable getAfterClickedDrawable();


}
