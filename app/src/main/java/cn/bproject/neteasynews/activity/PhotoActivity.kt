package cn.bproject.neteasynews.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.base.BaseActivity
import cn.bproject.neteasynews.base.BaseFragment
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.page_loading.*
import java.util.ArrayList

class PhotoActivity : BaseActivity() {



    override fun getLayoutId(): Int {
        return R.layout.activity_photo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loading.visibility = View.INVISIBLE
        val imageUrl = intent.getStringExtra("image_url")
        Glide.with(this).load(imageUrl).into(photoview)
    }


}
