package cn.bproject.neteasynews.adapter


import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.activity.VideoDetailActivity

import cn.bproject.neteasynews.bean.VideoBean
import com.bumptech.glide.Glide

class VideoListAdapter : PagedListAdapter<VideoBean, VideoListAdapter.ViewHolder>(REPO_COMPARATOR) {

    private val VID = "VID"
    private val MP4URL = "MP4URL"
    private val BIG_IMG = 0
    private val SMALL_IMG = 1
    private val THREE_IMG = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListAdapter.ViewHolder {
        var view: View = View.inflate(parent.context, R.layout.item_video_layout, null)

        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: VideoListAdapter.ViewHolder, position: Int) {
        val videoBean = getItem(position)

        val imageSrc = videoBean?.cover
        val title = videoBean?.title
        val source = videoBean?.videosource
        val postTime = videoBean?.ptime

        holder.tv_title.text = title
        holder.tv_from.text = source
        holder.tv_play_time.text = postTime


        holder.iv_video.setScaleType(ImageView.ScaleType.FIT_XY)
        Glide.with(holder.iv_video.context)
            .load(imageSrc)
            .placeholder(R.drawable.defaultbg_h)
            .into(holder.iv_video)

        holder.iv_video.setOnClickListener(){
            val intent = Intent(holder.iv_video.context, VideoDetailActivity::class.java)
            intent.putExtra(VID, getItem(position)?.vid)
            intent.putExtra(MP4URL, getItem(position)?.mp4_url)
            holder.iv_video.context.startActivity(intent)
        }
    }

    private fun setNetPicture(url: String?, img: ImageView) {
        Glide.with(img.context)
            .load(url)
            .placeholder(R.drawable.defaultbg)
            .into(img)
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<VideoBean>() {
            override fun areItemsTheSame(oldItem: VideoBean, newItem: VideoBean): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: VideoBean, newItem: VideoBean): Boolean =
                oldItem.title == newItem.title
        }
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var tv_from: TextView = view.findViewById(R.id.tv_from);
        var tv_title: TextView = view.findViewById(R.id.tv_title);
        var tv_play_time: TextView = view.findViewById(R.id.tv_play_time);
        var iv_video: ImageView = view.findViewById(R.id.iv_video);
    }
}