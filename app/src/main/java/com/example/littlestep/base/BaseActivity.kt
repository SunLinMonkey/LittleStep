package com.example.littlestep.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * create on 2021/7/19 23:15
 * Email:923998007@qq.com
 * @author lin
 */
open abstract class BaseActivity : AppCompatActivity() , BaseView {

    var butterKnifeBind: Unbinder? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        butterKnifeBind = ButterKnife.bind(this);
        initView()
        initData()
    }


    //https://blog.csdn.net/sunluyao_/article/details/50395791
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onDestroy() {
        super.onDestroy()
//        butterKnifeBind?.unbind()
    }

    abstract fun getLayoutRes(): Int;
}