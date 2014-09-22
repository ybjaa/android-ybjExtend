package com.ybjaa.extend;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by 杨冰剑 on 2014/9/17.
 *
 * @author 杨冰剑
 * 各种工具类
 *
 */
public class Tools {



   //*********************** region ***********************
   // 获得某个控件（Activity的子控件）

    /**
     *
     * 根据过滤条件获得某个控件下面的所有子控件
     *
     * @param rootView 要获得子控件的控件集合
     * @param filterView 过滤条件
     * @return 根据过滤条件获得某个控件下面的所有子控件
     */
    public static List<View> getAllChildViews(View rootView,IFilterView filterView)
    {
        ArrayList<View> viewArrayList=new ArrayList<>();

        if(rootView==null)
        {
            return viewArrayList;
        }

        if(rootView instanceof ViewGroup)
        {


            ViewGroup viewGroup=(ViewGroup) rootView;

            int count=viewGroup.getChildCount();
            View view=null;

            for(int i=0;i<count;i++)
            {
                view=viewGroup.getChildAt(i);

                if(view!=null)
                {
                    if(view instanceof ViewGroup)
                    {
                        viewArrayList.addAll(getAllChildViews(view,filterView));
                    }
                    else
                    {
                        if(filterView==null || filterView.IsVerify(view) )
                        {
                            viewArrayList.add(view);
                        }
                    }
                }
            }

            if(filterView==null || filterView.IsVerify(rootView) )
            {
                viewArrayList.add(rootView);
            }

        }



        return viewArrayList;
    }


    /**
     *
     * 获得某个控件下面的所有子控件
     *
     * @param rootView 要获得子控件的控件集合
     * @return 某个控件下面的所有子控件
     */
    public static List<View> getAllChildViews(View rootView)
    {
        return  getAllChildViews(rootView,null);
    }


    /**
     *
     * 根据过滤条件获得某个activity下面的所有子控件
     *
     * @param activity 要获得子控件的activity
     * @param filterView 过滤条件
     * @return 根据过滤条件获得某个activity下面的所有子控件
     */
    public static List<View> getAllChildViews(Activity activity,IFilterView filterView)
    {
        ArrayList<View> viewArrayList=new ArrayList<>();

        if(activity==null)
        {
            return viewArrayList;
        }

        return getAllChildViews(activity.getWindow().getDecorView(),filterView);
    }


    /**
     *
     * 获得某个activity下面的所有子控件
     *
     * @param activity 要获得子控件的Activity
     * @return 某个activity下面的所有子控件
     */
    public static List<View> getAllChildViews(Activity activity)
    {
        return  getAllChildViews(activity,null);
    }

    //***********************endregion***********************






    //*********************** region ***********************
    //

    //***********************endregion***********************


    //*********************** region ***********************
    //

    //***********************endregion***********************


    //*********************** region ***********************
    //

    //***********************endregion***********************

    //*********************** region ***********************
    //

    //***********************endregion***********************














    //*********************** region ***********************
    //

    //***********************endregion***********************


}
