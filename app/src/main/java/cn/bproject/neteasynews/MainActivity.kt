package cn.bproject.neteasynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import cn.bproject.neteasynews.base.BaseActivity
import cn.bproject.neteasynews.fragment.AboutFragment
import cn.bproject.neteasynews.fragment.NewsFragment
import cn.bproject.neteasynews.fragment.PhotoFragment
import cn.bproject.neteasynews.fragment.VideoFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.page_loading.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolBarTextView?.text="主页"
        loading.visibility = View.GONE
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_news -> {
                var transaction:FragmentTransaction =supportFragmentManager.beginTransaction();
                var news = NewsFragment();
                transaction.replace(R.id.realtabcontent, news)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_photo -> {
                var transaction:FragmentTransaction =supportFragmentManager.beginTransaction();
                var photo = PhotoFragment();
                transaction.replace(R.id.realtabcontent, photo)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_video -> {
                var transaction:FragmentTransaction =supportFragmentManager.beginTransaction();
                var photo = VideoFragment();
                transaction.replace(R.id.realtabcontent, photo)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                var transaction:FragmentTransaction =supportFragmentManager.beginTransaction();
                var photo = AboutFragment();
                transaction.replace(R.id.realtabcontent, photo)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun hasBackButton(): Boolean {
        return false
    }



}
