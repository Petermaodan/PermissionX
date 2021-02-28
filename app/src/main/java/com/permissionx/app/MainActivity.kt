package com.permissionx.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permissionx.petremaodan.PermissionX
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeCallBtn.setOnClickListener {

            //在lambda表达式中处理权限的申请结果
            PermissionX.request(this,Manifest.permission.CALL_PHONE){allGranted, deniedList ->
                if (allGranted){
                    call()
                }else{
                    Toast.makeText(this,"you denied $deniedList",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent=Intent(Intent.ACTION_CALL)
            intent.data=Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}