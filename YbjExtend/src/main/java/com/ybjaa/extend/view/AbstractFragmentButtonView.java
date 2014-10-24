package com.ybjaa.extend.view;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ybjaa.extend.R;


/**
 * Created by 杨冰剑 on 2014/9/17.
 *
 * 导航窗体所存放的点击控件的基类
 *
 */
public abstract class AbstractFragmentButtonView extends LinearLayout {


    /**
     *
     * 按下所跳转的fragment
     *
     */
    protected Fragment mFragment =null;


    /**
     *
     * 是否是默认点击的第一项
     *
     */
    protected boolean mIsDefault =false;


    /**
     *
     * 针对于按下按钮改变其状态时的函数调用
     *
     * @state true:选中，false：未选中
     *
     */
    public abstract void stateChange(boolean state);




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
     * 设置是否是默认的第一项
     *
     * @param flag  设置是否是默认的第一项
     */
    public void setIsDefault(Boolean flag)
    {
        mIsDefault =flag;
        if(isDefault())
        {
            this.performClick();
        }
    }


    /**
     *
     * 获得是否是默认的第一项
     *
     *
     */
    public Boolean isDefault()
    {
        return mIsDefault;
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
        String fragmentName= a.getString(R.styleable.nav_fragment_view_style_fragment_name);

        mIsDefault=a.getBoolean(R.styleable.nav_fragment_view_style_default_first,false);

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
                Toast.makeText(context,context.getString(R.string.exception_ybjaa_created_fragemt_fail)+s1,Toast.LENGTH_SHORT).show();
                Log.e("ybjExtend", e.toString());
            }

        }

    }


}
