package com.example.littlestep.mvpBase

import android.os.Bundle
import com.example.littlestep.base.BaseActivity


abstract class MvpActivity<out P : BaseMvpContract.IPresenterContract> : BaseActivity(),
    BaseMvpContract.IViewContract {

    private var mPresenter: P? = null

    protected fun getPresenter() = mPresenter!!

    abstract fun registerPresenter(): Class<out P>

    private fun initPresenter() {
        mPresenter = registerPresenter().newInstance()
        mPresenter?.register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        mPresenter?.onCreate()
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mPresenter?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.onDestroy()
            mPresenter = null
        }
    }
}