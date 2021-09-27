package com.example.littlestep.mvpBase


abstract class BaseMvpPresenter<out V : BaseMvpContract.IViewContract, out M : BaseMvpContract.IModelContract> :
    BaseMvpContract.IPresenterContract {

    private var mMVPView: V? = null
    private var mModel: M? = null

    override fun register(mvpView: BaseMvpContract.IViewContract) {
        mMVPView = mvpView as V
        mModel = registerModel().newInstance()
    }


    abstract fun registerModel(): Class<out M>

    fun getMvpView() = mMVPView!!

    fun getModel() = mModel!!

    override fun onCreate() {
        mModel!!.onCreate()
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        if (mMVPView != null) mMVPView = null
        if (mModel != null) {
            mModel!!.onDestroy()
            mModel = null
        }
    }

}