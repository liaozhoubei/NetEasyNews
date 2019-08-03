package cn.bproject.neteasynews.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.activity.NewsDetailActivity
import cn.bproject.neteasynews.activity.PhotoActivity
import cn.bproject.neteasynews.activity.PicDetailActivity
import cn.bproject.neteasynews.bean.NewsListNormalBean
import com.bumptech.glide.Glide

class NewsListAdapter : PagedListAdapter<NewsListNormalBean, BaseNewsHolder>(REPO_COMPARATOR) {
    private val BIG_IMG = 0
    private val SMALL_IMG = 1
    private val THREE_IMG = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseNewsHolder {
        var view: View
        if (viewType == BIG_IMG) {
            view = View.inflate(parent.context, R.layout.item_news_big_pic, null)
            return BigImgViewHolder(view)
        } else if (viewType == THREE_IMG) {
            view = View.inflate(parent.context, R.layout.item_news_three_pic, null)
            return ThreeImgViewHolder(view)
        } else {
            view = View.inflate(parent.context, R.layout.item_news_normal, null)
            return SmallImgViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var viewType = SMALL_IMG
        val newsListNormalBean = getItem(position)
        val hasAd = newsListNormalBean?.hasAD
        val imgextraBeenlist = newsListNormalBean?.imgextra
        if (hasAd == 1) {
            viewType = BIG_IMG
        } else if (imgextraBeenlist != null && imgextraBeenlist!!.size > 1) {
            viewType = THREE_IMG
        }
        return viewType
    }

    override fun onBindViewHolder(holder: BaseNewsHolder, position: Int) {
        val newsListNormalBean = getItem(position)
        var imgextraBeenlist = newsListNormalBean?.imgextra
        val imageSrc = newsListNormalBean?.imgsrc
        val title = newsListNormalBean?.title
        val source = newsListNormalBean?.source
        val postTime = newsListNormalBean?.ptime
        // 文章的id号
        val docid = newsListNormalBean?.docid
        if (getItemViewType(position) == BIG_IMG) {
            // 一张大图的情况
            val bigImgViewHolder = holder as BigImgViewHolder
            Glide.with(bigImgViewHolder.big_Image.context)
                .load(imageSrc)
                .placeholder(R.drawable.defaultbg_h)
                .into(bigImgViewHolder.big_Image)


        } else if (getItemViewType(position) == THREE_IMG) {

            val threeImgViewHolder = holder as ThreeImgViewHolder
            // 三张图片的情况
            setNetPicture(imageSrc, threeImgViewHolder.one_image)
            for (j in imgextraBeenlist!!.indices) {
                if (j == 0) {
                    setNetPicture(imgextraBeenlist.get(j).imgsrc, threeImgViewHolder.two_image)
                } else if (j == 1) {
                    setNetPicture(imgextraBeenlist.get(j).imgsrc, threeImgViewHolder.three_image)
                }
            }
        } else if (getItemViewType(position) == SMALL_IMG) {
            // 标准视图的情况
            val smallImgViewHolder = holder as SmallImgViewHolder
            // 设置图片
            setNetPicture(imageSrc, smallImgViewHolder.item_news_tv_img)
            holder.item_news_tv_title
        }

        holder.item_news_tv_title.setText(title)
        holder.item_news_tv_time.setText(postTime)
        holder.item_news_tv_source.setText(source)

        holder.itemView.setOnClickListener(){
//            val newsListNormalBean = getItem(position)
            val photosetID = newsListNormalBean!!.photosetID
            val intent: Intent
            if (photosetID != null) {
                intent = Intent(holder.item_news_tv_title.context, PicDetailActivity::class.java)
                val str = photosetID!!.split("\\|".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                //  图片新闻文章所属的类目id
                val tid = str[0].substring(4)
                // 图片新闻的文章id号
                val setid = str[1]
                intent.putExtra("TID", tid)
                intent.putExtra("SETID", setid)
//                LogUtils.d(TAG, "onItemClick: photosetID:" + photosetID!!)
            } else {
                intent = Intent(holder.item_news_tv_title.context, NewsDetailActivity::class.java)
                intent.putExtra("DOCID", newsListNormalBean.docid)

            }
            //论坛、读书、漫画、态度公开课、云课堂 等栏目进入新闻详情页未处理
            holder.item_news_tv_title.context.startActivity(intent)
        }
    }

    private fun setNetPicture(url: String?, img: ImageView) {
        Glide.with(img.context)
            .load(url)
            .placeholder(R.drawable.defaultbg)
            .into(img)
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<NewsListNormalBean>() {
            override fun areItemsTheSame(oldItem: NewsListNormalBean, newItem: NewsListNormalBean): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: NewsListNormalBean, newItem: NewsListNormalBean): Boolean =
                oldItem.url == newItem.url
        }
    }
}