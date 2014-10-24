package com.ybjaa.extend;

import android.view.View;

/**
 * Created by 杨冰剑 on 2014/9/17.
 *
 * 过滤view的接口
 *
 */
public interface IFilterView {

    /**
     *
     * 验证该view是否通过验证
     *
     * @param view 所要验证的控件
     * @return 验证通过返回true，否则返回false
     */
    boolean IsVerify(View view);
}
