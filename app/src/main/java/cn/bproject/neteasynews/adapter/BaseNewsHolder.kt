package cn.bproject.neteasynews.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.bproject.neteasynews.R

open class BaseNewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open public var item_news_tv_title: TextView = itemView.findViewById(R.id.item_news_tv_title)
    open public var item_news_tv_time: TextView = itemView.findViewById(R.id.item_news_tv_time)
    open public var item_news_tv_arrow: TextView = itemView.findViewById(R.id.item_news_tv_arrow)
    open public var item_news_tv_source: TextView = itemView.findViewById(R.id.item_news_tv_source)
}

class BigImgViewHolder(itemView: View) : BaseNewsHolder(itemView) {
    var big_Image = itemView.findViewById(R.id.big_Image) as ImageView

}

class SmallImgViewHolder(itemView: View) : BaseNewsHolder(itemView) {

    var item_news_tv_img = itemView.findViewById(R.id.item_news_tv_img) as ImageView

}

class ThreeImgViewHolder(itemView: View) : BaseNewsHolder(itemView) {

    // 拥有三张图片的布局
    var one_image: ImageView = itemView.findViewById(R.id.one_image)
    var two_image: ImageView = itemView.findViewById(R.id.two_image)
    var three_image: ImageView = itemView.findViewById(R.id.three_image)

}