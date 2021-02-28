package com.permissionx.petremaodan

import android.app.Activity
import androidx.fragment.app.FragmentActivity

object PermissionX {
    private const val TAG="InvisibleFragment"
    fun request(activity: FragmentActivity,vararg permissions:String,callback:PermissionCallback){
        //创建一个FragmentManager实例
        val fragmentManager=activity.supportFragmentManager
        //判断Activity中是否包含指定的TAG的Fragment
        val existedFragment=fragmentManager.findFragmentByTag(TAG)
        val fragment=if (existedFragment!=null){
            existedFragment as InvisibleFragment
        }else{
            //创建一个新的InvisibleFragment实例
            val invisibleFragment=InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }
}