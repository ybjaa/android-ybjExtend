package test01.fragment.ybjaa.testfragment01;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.ybjaa.extend.view.AbstractFragmentButtonView;

import java.util.zip.Inflater;

/**
 * Created by admin on 2014/9/18.
 */
public class FragmentButtonView extends AbstractFragmentButtonView {

    @Override
    public void stateChange(boolean state) {

    }

    public FragmentButtonView(Context context) {
        this(context,null);
    }

    public FragmentButtonView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FragmentButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        super.initialize(context, attrs, defStyleAttr);


        LayoutInflater.from(context).inflate(R.layout.test01,this,true);



    }
}
