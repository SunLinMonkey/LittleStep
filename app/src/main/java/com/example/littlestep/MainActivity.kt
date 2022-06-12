package com.example.littlestep

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.example.littlestep.base.BaseActivity
import com.example.littlestep.ninebox.NineBoxActivity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

/**
 * create on 2021/7/20 22:10
 * Email:923998007@qq.com
 * @author lin
 */
class MainActivity : BaseActivity() {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView


    override fun initData() {

    }

    override fun initView() {
        requestPermissionsLocal()
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    private fun requestPermissionsLocal() {
        //获取权限，接口回调
        XXPermissions.with(this)
//            .permission(Permission.READ_EXTERNAL_STORAGE)
            .permission(Permission.Group.CALENDAR)
            .request(
                object : OnPermissionCallback {
                    override fun onGranted(permissions: MutableList<String>?, all: Boolean) {
                        if (all) {
                            val intent: Intent =
                                Intent(this@MainActivity, NineBoxActivity::class.java)
                            startActivity(intent)
//                            initListButton();
                        }
                    }

                    override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                        super.onDenied(permissions, never)
                    }
                })
    }

    private fun initListButton() {
//        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//
//        recyclerView.adapter =

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == XXPermissions.REQUEST_CODE) {
            if (XXPermissions.isGranted(this, Permission.RECORD_AUDIO) &&
                XXPermissions.isGranted(this, Permission.Group.CALENDAR)
            ) {
                //toast("用户已经在权限设置页授予了录音和日历权限");
            } else {
                //toast("用户没有在权限设置页授予权限");
            }
        }
    }
}