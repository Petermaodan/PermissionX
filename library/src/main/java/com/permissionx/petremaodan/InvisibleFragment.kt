package com.permissionx.petremaodan

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

//typealias关键词可以给任意类型指定一个别名
typealias PermissionCallback=(Boolean,List<String>)->Unit

class InvisibleFragment: Fragment() {
    //定义一个callback变量，将他声明成一种函数类型变量
    private var callback:PermissionCallback?=null

    fun requestNow(cd:PermissionCallback,vararg permissions:String){
        callback=cd
        //立刻申请运行权限
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==1){
            //用来记录所有被用户拒绝的权限
            val deniedList=ArrayList<String>()
            //遍历grantResults数组
            for((index,result) in grantResults.withIndex()){
                //若未授权该权限
                if (result!=PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val allGranted=deniedList.isEmpty()
            //使用callback对运行时的权限进行回调
            callback?.let {
                it(allGranted,deniedList)
            }
        }
    }
}