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
     * @param view
     * @return
     */
    boolean IsVerify(View view);
}
