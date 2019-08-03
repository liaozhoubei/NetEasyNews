package cn.bproject.neteasynews.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cn.bproject.neteasynews.R
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    val TAG:String ="SplashActivity"
    val PERRMISION_REQUEST_FROM_SETTING = 999;
    val PERMISSION_REQUEST_CODE = 1000

    val permission: Array<String> = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkPermission();
    }


    fun checkPermission() {

        if (EasyPermissions.hasPermissions(this, *permission)) {
            Toast.makeText(this, "有权限，为所欲为", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "应用程序需要读写本地存储及使用网络,请允许读写权限",
                PERMISSION_REQUEST_CODE,
                *permission
            )
        }
    }

    // 必须重写此方法，否则权限申请允许或拒绝的方法不会被调用
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for((index,e) in permissions.withIndex()){
            Log.e(TAG, "permissions ${e}" + grantResults[index])
        }

        // 1. 系统获取权限后先调用 onRequestPermissionsResult
        // 2. 调用后通过 EasyPermissions.onRequestPermissionsResult 将权限允许状态传递给 EasyPermissions
        // 3. EasyPermissions 将状态分发至 onPermissionsDenied / onPermissionsGranted 两个方法中
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.e(TAG, "permissions onPermissionsDenied")
        Toast.makeText(this, "没有权限，为所欲为", Toast.LENGTH_SHORT).show()
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "您选择了拒绝授权" + PERMISSION_REQUEST_CODE, Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(this).setTitle("打开应用程序设置修改应用程序权限")
                .setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which -> finish() })
                .setPositiveButton(
                    "确定", DialogInterface.OnClickListener(
                        function = { dialog, which ->
                            requestPermissionFromSetting(this)
                            finish()
                        })
                ).create().show()
        } else {
            Toast.makeText(this, "您拒绝授权" + PERMISSION_REQUEST_CODE, Toast.LENGTH_SHORT).show()
            checkPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.e(TAG, "permissions onPermissionsGranted")
        Toast.makeText(this, "您同意了授权" + PERMISSION_REQUEST_CODE, Toast.LENGTH_SHORT).show()
        checkPermission()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PERRMISION_REQUEST_FROM_SETTING && resultCode == Activity.RESULT_OK) {
            checkPermission()
        }
    }

    fun requestPermissionFromSetting(context: Context) {
        var intent = Intent()
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // 将用户引导到系统设置页面
            if (Build.VERSION.SDK_INT >= 9) {
                Log.e(TAG, "APPLICATION_DETAILS_SETTINGS")
                intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                intent.data = Uri.fromParts("package", context.getPackageName(), null)
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.action = Intent.ACTION_VIEW
                intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
                intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName())
            }
            context.startActivity(intent)
        } catch (e: Exception) {//抛出异常就直接打开设置页面
            Log.e(TAG, e.localizedMessage)
            intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        }

    }

}
