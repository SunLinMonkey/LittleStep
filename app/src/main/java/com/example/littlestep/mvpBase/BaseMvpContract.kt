package com.example.littlestep.mvpBase

/**
 * create on 2021/9/27 23:43
 * Email:923998007@qq.com
 * @author lin
 */
class BaseMvpContract {

    /**
     * M层对P层放开的方法
     */
    interface IModelContract {
        fun onCreate()

        fun onDestroy()
    }

    /**
     * V层对P层放开的方法
     */
    interface IViewContract

    /**
     * P层对V层放开的方法
     */
    interface IPresenterContract {
        fun onCreate()

        fun onStart()

        fun onResume()

        fun onPause()

        fun onStop()

        fun onDestroy()

        fun register(mvpView: IViewContract)
    }


}