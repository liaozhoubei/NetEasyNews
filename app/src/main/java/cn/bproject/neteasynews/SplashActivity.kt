package cn.bproject.neteasynews

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity : AppCompatActivity() , EasyPermissions.PermissionCallbacks{
    val PERMISSION_REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkPermission();
    }

    private val permission: Array<String>
        get() {
            val strings = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET
            )
            return strings
        }

    fun checkPermission(){

        if (EasyPermissions.hasPermissions(this,*permission)) {
//            Toast.makeText(this, "有权限，为所欲为", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            EasyPermissions.requestPermissions(
                this,
                "应用程序需要读写本地存储及使用网络,请允许读写权限",
                PERMISSION_REQUEST_CODE,
                *permission)
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "您拒绝授权,并勾选了不在提醒" + PERMISSION_REQUEST_CODE, Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this).setTitle("打开应用程序设置修改应用程序权限").build().show()
        } else {
            Toast.makeText(this, "您拒绝授权" + PERMISSION_REQUEST_CODE, Toast.LENGTH_SHORT).show()
            checkPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this, "您同意了授权" + PERMISSION_REQUEST_CODE, Toast.LENGTH_SHORT).show()
        checkPermission()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            checkPermission()
        }
    }

}
