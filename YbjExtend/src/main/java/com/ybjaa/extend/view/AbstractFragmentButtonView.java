package com.ybjaa.extend.view;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ybjaa.extend.R;


/**
 * Created by 杨冰剑 on 2014/9/17.
 *
 * 导航窗体所存放的点击事件的基类
 *
 */
public class AbstractFragmentButtonView extends LinearLayout {



    protected static int[] mergeDrawableStates(int[] baseState, int[] additionalState) {
        final int N = baseState.length;
        int i = N - 1;
        while (i >= 0 && baseState[i] == 0) {
            i--;
        }
        System.arraycopy(additionalState, 0, baseState, i + 1, additionalState.length);
        return baseState;
    }



   /* public interface IOnKeyDownListener
    {

        void OnKeyDown(View v,int keyCode, KeyEvent event);

    }



    public IOnKeyDownListener onKeyDownListener=null;*/


  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(onKeyDownListener!=null)
        {
            onKeyDownListener.OnKeyDown(this,keyCode,event);
        }

        return super.onKeyDown(keyCode, event);
    }*/

    protected Fragment mFragment =null;

    protected boolean mChecked=false;




    public AbstractFragmentButtonView(Context context) {
        this(context,null);
    }

    public AbstractFragmentButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractFragmentButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs,defStyleAttr);
    }

    @Override
    public boolean performClick() {
        return super.performClick();

    }

    /**
     *
     * 设置fragment对象
     *
     * @param fragment
     */
    public void setFragment(Fragment fragment)
    {
        if(fragment!=null) {
            mFragment = fragment;
        }
    }


    /**
     *
     * 获得fragment对象
     *
     * @return
     */
    public Fragment getFragment()
    {
        return  mFragment;

    }





    /**
     *
     * 构造函数后初始化对象，即针对于自定义属性进行初始化
     *
     * @param context context对象
     * @param attrs 属性
     */
    protected void initialize(Context context, AttributeSet attrs,int defStyleAttr)  {

        //获取frameLayout的layout
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.nav_fragment_view_style, defStyleAttr, 0);
        String fragmentName= a.getString(R.styleable.nav_fragment_view_fragment_name);
        a.recycle();

        //设置交点规则，先子控件，没有再自身
        setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        if(fragmentName!=null&&!fragmentName.trim().equals(""))
        {
            String s1=context.getPackageName();


            s1=fragmentName.substring(0,1).equals(".")?s1+fragmentName:fragmentName;

            try {
                mFragment =(Fragment)Class.forName(s1).getConstructor().newInstance();
            } catch (Exception e) {
                mFragment =null;
                e.printStackTrace();
                Toast.makeText(context,"创建Fragment对象失败:"+s1,Toast.LENGTH_SHORT).show();
                Log.e("ybjExtend",e.toString());
            }

        }







    }







    /**
     *
     * 设置按下切换fragment的事件
     *
     */
    protected void setFragmentNameViewsOnClickEvent()
    {
        //
    }


}
