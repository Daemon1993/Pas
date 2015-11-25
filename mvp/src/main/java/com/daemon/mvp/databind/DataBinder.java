package com.daemon.mvp.databind;

import com.daemon.mvp.model.IModel;
import com.daemon.mvp.view.IView;

/**
 * Created by Daemon on 2015/11/20.
 */
public interface DataBinder<T extends IView, D extends IModel> {

    /**
     * view绑定model model变化回调
     * @param iView
     * @param data
     */
    void viewBindModel(T iView, D data);

//    /**
//     * model绑定View View改变 回调
//     * @param data
//     * @param iView
//     */
//    void modelBindView(D data, T iView);

}
