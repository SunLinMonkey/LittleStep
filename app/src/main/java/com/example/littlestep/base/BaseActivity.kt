package com.example.littlestep.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * create on 2021/7/19 23:15
 * Email:923998007@qq.com
 * @author lin
 */
abstract class BaseActivity : AppCompatActivity() {

    var butterKnifeBind: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(getLayoutRes())
        butterKnifeBind = ButterKnife.bind(this);
        initView()
        initData()
    }

    abstract fun initData()

    abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        butterKnifeBind?.unbind()
    }

    abstract fun getLayoutRes(): Int;

    protected fun goActivity(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    protected fun goActivity(cls: Class<*>) {
        goActivity(cls, null)
    }
}