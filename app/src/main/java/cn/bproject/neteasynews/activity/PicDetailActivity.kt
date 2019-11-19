package cn.bproject.neteasynews.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.base.BaseActivity
import cn.bproject.neteasynews.base.BaseFragment
import cn.bproject.neteasynews.bean.ImageDetailBean
import cn.bproject.neteasynews.network.Api
import cn.bproject.neteasynews.network.RetrofitHelper
import cn.bproject.neteasynews.util.DataParse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pic_detail.*
import kotlinx.android.synthetic.main.page_loading.*
import okhttp3.HttpUrl
import java.util.ArrayList

class PicDetailActivity : BaseActivity() {
    private var mContext: Context? = null
    private var tid: String? = null // 图片频道id，用于打开新闻详情页
    private var setid: String? = null  //   图片的分类
    private var mHandler = Handler()
    private val mBaseFragmentArrayList: ArrayList<BaseFragment>? = null
    private val KEY_TID = "TID"  //频道id
    private val SETID = "SETID"
    private var isGone: Boolean = false

    override fun getLayoutId(): Int {
     return R.layout.activity_pic_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext= this;
        loading.visibility = View.INVISIBLE
        initValidata()
    }

    fun initValidata(){
        val intent = intent
        tid = intent.getStringExtra(KEY_TID)
        setid = intent.getStringExtra(SETID)
        requestData()
    }

    private fun requestData() {
        Thread(){
            var call = RetrofitHelper.getInstance(HttpUrl.parse(Api.host)!!, mContext!!)
                .getPhotoDetail(tid!!, setid!!);

            var response = call.execute()
            if (response.isSuccessful){
                var result = response.body()
                var mImageDetailBean = DataParse.ImageDetail(result)
                mHandler.post {
                    if (mImageDetailBean != null){
                        bindData(mImageDetailBean)
                    }else{
                        Toast.makeText(mContext, "无法获取数据，${result}", Toast.LENGTH_SHORT).show()
                    }

                }
            }else{
                mHandler.post { Toast.makeText(mContext, "${response.code()}", Toast.LENGTH_SHORT).show()}
            }
        }.start()
    }

    private fun bindData(mImageDetailBean: ImageDetailBean) {
        val picDetailAdapter = PicDetailAdapter(mImageDetailBean)
        vp_pic.setAdapter(picDetailAdapter)
        vp_pic.setCurrentItem(0)
    }


    inner class PicDetailAdapter(private val mImageDetailBean: ImageDetailBean) : PagerAdapter() {
        override fun getCount(): Int {
            return  mImageDetailBean.photos!!.size
        }


        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = View.inflate(mContext, R.layout.fragment_pic_detail, null)
            val iv_pic = view.findViewById<View>(R.id.iv_pic) as ImageView
            val tv_title = view.findViewById<View>(R.id.tv_title) as TextView
            val tv_pic_sum = view.findViewById<View>(R.id.tv_pic_sum) as TextView
            val sv_content = view.findViewById<View>(R.id.sv_content) as ScrollView
            val tv_pic_content = view.findViewById<View>(R.id.tv_pic_content) as TextView
            val setName = mImageDetailBean.setname
            val postid = mImageDetailBean.postid
            val photosBean = mImageDetailBean.photos!![position]
//            LogUtils.d(TAG, setName)
            val title = photosBean.imgtitle
            val note = photosBean.note
            isGone = false
            if (title != null && title != "") {
                tv_title.text = title
            } else if (setName != null && setName != "") {
                tv_title.text = setName
            } else {
                tv_title.text = postid
            }

            if (note != null) {
                tv_pic_content.text = note
                isGone = false
            } else {
                sv_content.visibility = View.GONE
                isGone = true
            }

            tv_pic_sum.text = (position + 1).toString() + "/" + mImageDetailBean.photos!!.size

            Glide.with(mContext!!)
                .load(photosBean.imgurl)
                .placeholder(R.drawable.defaultbg)
//                .centerCrop()
                .into(iv_pic)

            iv_pic.setOnClickListener {
                if (isGone) {
                    sv_content.visibility = View.VISIBLE
                    isGone = false
                } else {
                    sv_content.visibility = View.GONE
                    isGone = true
                }
            }

            container.addView(view)

            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}
