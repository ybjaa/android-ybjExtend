package com.ybjaa.extend.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ybjaa.extend.IFilterView;
import com.ybjaa.extend.R;
import com.ybjaa.extend.Tools;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 杨冰剑 on 2014/9/17.
 */
public class NavMenuPanel extends LinearLayout {


    /**
     *
     * fragmentManager对象
     *
     * 作用：存放当前Activity的管理fragment的对象，用来针对于不同的fragment切换使用
     *
     */
    FragmentManager mFragmentManager=null;



    /**
     *
     * 查询是否点击了同一个
     *
     */
    AbstractFragmentButtonView oldView=null;


    /**
     *
     * fragmentLayoutID对象
     *
     * 作用：存放当前Activity的管理fragmentLayout的对象，用来针对于不同的fragment切换使用
     *
     */
    int mFrameLayoutID=-1;

    /**
     *
     * 存储子节点的view源
     *
     */
    List<AbstractFragmentButtonView> viewSource=null;


    /**
     *
     * 设置针对于获得按下切换fragment接口的fragment对象
     *
     */
    public IOnFragmentViewClickListener mOnFragmentViewClickListener=null;


    /**
     *
     * 按下FragmentView控件的拓展接口
     *
     */
    public interface IOnFragmentViewClickListener
    {

        /**
         *
         * 是否自动切换fragment
         *
         * @return
         */
        Boolean IsAutoChangeFragment();


        /**
         *
         * 返回点击后的控件
         *
         * @return
         */
         void onClickView(AbstractFragmentButtonView view);


    }

    public OnClickListener mOnClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v instanceof AbstractFragmentButtonView)
            {

                AbstractFragmentButtonView view=(AbstractFragmentButtonView)v;

                if(oldView==null || oldView!=view)
                {
                    if(mOnFragmentViewClickListener==null || mOnFragmentViewClickListener.IsAutoChangeFragment())
                    {
                        fragmentChange(view);
                    }

                    if(mOnFragmentViewClickListener!=null)
                    {
                        mOnFragmentViewClickListener.onClickView(view);
                    }

                    oldView=view;

                }


            }
        }
    };


    /**
     *
     * 按下之前的事件
     *
     */
    public OnTouchListener mOnTouchListener=new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            setViewSourceState(v);
            return false;
        }
    };





    public NavMenuPanel(Context context) {
        this(context,null);
    }

    public NavMenuPanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavMenuPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs,defStyleAttr);
    }



    /**
     *
     * 设置fragmentManager
     *
     * @param fragmentManager activity 的 fragmentManager
     */
    public void setFragmentManager(FragmentManager fragmentManager)
    {
        this.mFragmentManager=fragmentManager;
    }


    /**
     *
     * 设置frameLayout
     *
     * @param frameLayoutID activity 的 需要替换的frameLayout
     */
    public void setFrameLayout(int frameLayoutID)
    {
        this.mFrameLayoutID=frameLayoutID;
    }










    /**
     *
     * 构造函数后初始化对象，即针对于自定义属性进行初始化
     *
     * @param context context对象
     * @param attrs 属性
     */
    public void initialize(Context context, AttributeSet attrs,int defStyleAttr)
    {
        //获取frameLayout的layout
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.nav_menu_panel_style, defStyleAttr, 0);
        int fragmentID= a.getResourceId(R.styleable.nav_menu_panel_style_fragment_layout_id,-1);

        a.recycle();


        //
        setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        mFrameLayoutID=fragmentID;

    }



    /**
     *
     * 设置针对去取到的fragmentView的控件的clcik事件绑定
     *
     */
    public void setOnFragmentButtonViewListener()
    {
        if(viewSource!=null)
        {
            setRemoveFragmentButtonViewListener();
            viewSource.clear();
        }
        else
        {
            viewSource=new ArrayList<AbstractFragmentButtonView>();
        }




        List<View> viewList=getFragmentNameViews();

        AbstractFragmentButtonView fView=null;

        AbstractFragmentButtonView isDefaultView=null;

        for( View v : viewList )
        {

            fView=(AbstractFragmentButtonView)v;

            if(fView!=null) {
                fView.setOnTouchListener(mOnTouchListener);
                fView.setOnClickListener(mOnClickListener);
                if(fView.isDefault())
                {
                    isDefaultView=fView;
                }
                viewSource.add(fView);
            }
        }


        if(isDefaultView!=null)
        {
            setViewSourceState(isDefaultView);
            isDefaultView.performClick();
        }


    }



    /**
     *
     * 设置针对取到的fragmentView的控件的clcik事件绑定的移除
     *
     */
    public void setRemoveFragmentButtonViewListener()
    {
        if(viewSource!=null)
        {
            for( AbstractFragmentButtonView v : viewSource )
            {
                v.setOnTouchListener(null);
                v.setOnClickListener(null);
            }
        }
    }





    /**
     *
     * 获得该控件下所有的实现了IGetFragmentName的子控件
     *
     * @return 获得该控件下所有的实现了IGetFragmentName的子控件
     */
    protected List<View> getFragmentNameViews()
    {
        return Tools.getAllChildViews(this,new IFilterView() {
            @Override
            public boolean IsVerify(View view) {
                return  view instanceof AbstractFragmentButtonView;
            }
        });
    }


    /**
     *
     * 设置各个控件按下时的状态
     *
     * @param view 按下时的按钮
     */
    protected void setViewSourceState(View view)
    {
        if(viewSource==null)
        {
            setOnFragmentButtonViewListener();
        }

        if(viewSource!=null)
        {
            for(AbstractFragmentButtonView v : viewSource)
            {

                if(v!=view)
                {
                    v.stateChange(false);
                    v.setSelected(false);
                }
                else
                {
                    v.stateChange(true);
                    v.setSelected(true);
                }

            }
        }

    }


    /**
     *
     * fragment跳转
     *
     * @param view
     */
    protected void fragmentChange(AbstractFragmentButtonView view)
    {

        Fragment fragment=view.getFragment();


        if(mFragmentManager==null)
        {
            Log.e("ybjException",getContext().getString(R.string.execption_ybjaa_navmenupanel_not_setting_fragmentmanager));

            return;
        }

        if(fragment==null)
        {
            Log.e("ybjException",getContext().getString(R.string.execption_ybjaa_navmenupanel_fragment_is_null));
            return;
        }



        FragmentTransaction transaction= mFragmentManager.beginTransaction();

        if(mFragmentManager.findFragmentById(mFrameLayoutID)==null)
        {
            transaction=transaction.add(mFrameLayoutID,fragment);
        }
        else
        {
            transaction=transaction.replace(mFrameLayoutID,fragment);
        }

        transaction.commit();



    }





}
