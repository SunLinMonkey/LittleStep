package com.example.littlestep.ninebox

import android.util.Log
import com.example.littlestep.mvpBase.BaseMvpPresenter

/**
 * create on 2021/9/27 23:15
 * Email:923998007@qq.com
 * @author lin
 */
class NineBoxPresenter : BaseMvpPresenter<NineBoxContract.IView, NineBoxContract.IModel>(),
    NineBoxContract.IPresenter {
    override fun registerModel(): Class<out NineBoxContract.IModel> {
        return NineBoxModel::class.java
    }


    init {
        Log.e("mvp", " NineBoxPresenter")
    }
}