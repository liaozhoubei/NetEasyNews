package cn.bproject.neteasynews.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.activity.PicDetailActivity

import cn.bproject.neteasynews.bean.PicListBean
import com.bumptech.glide.Glide

/**
 * 不明原因的会偶发的不显示界面，暂时不使用 PagedListAdapter
 */
class PicListAdapter(var tid:String) : PagedListAdapter<PicListBean, PicListAdapter.ViewHolder>(REPO_COMPARATOR) {
    private val BIG_IMG = 0
    private val SMALL_IMG = 1
    private val THREE_IMG = 2
    private val KEY_TID = "TID"  //频道id
    private val KEY_COLUMN = "COLUMN"
    private val SETID = "SETID"  // 图集id

    init {
        Log.e("PicListFragment", "PicListAdapter 获取信息${tid}  column  :")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = View.inflate(parent.context, R.layout.item_pic_layout, null)
        Log.e("PicListFragment", "PicListAdapter data ${getItem(0).toString()} :")
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picListBean = getItem(position)

        val imageSrc = picListBean?.cover
        val title = picListBean?.setname

        holder.tv_title.setText(title)

        Glide.with(holder.iv_pic.context)
            .load(imageSrc)
            .placeholder(R.drawable.defaultbg)
            .into(holder.iv_pic)

        holder.itemView.setOnClickListener(){
            val id = picListBean!!.setid
            val intent = Intent(holder.iv_pic.context, PicDetailActivity::class.java)
            intent.putExtra(KEY_TID, tid)
            intent.putExtra(SETID, id)
            holder.iv_pic.context.startActivity(intent)
        }
    }

    private fun setNetPicture(url: String?, img: ImageView) {
        Glide.with(img.context)
            .load(url)
            .placeholder(R.drawable.defaultbg)
            .into(img)
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<PicListBean>() {
            override fun areItemsTheSame(oldItem: PicListBean, newItem: PicListBean): Boolean  {
                Log.e("PicListFragment", "PicListAdapter areItemsTheSame oldItem.setname${ oldItem.setname}  ")
                return oldItem.setname == newItem.setname
            }


            override fun areContentsTheSame(oldItem: PicListBean, newItem: PicListBean): Boolean {
                Log.e("PicListFragment", "PicListAdapter areContentsTheSame oldItem.setname${ oldItem.setname}  ")
                return   oldItem.setname == newItem.setname
            }

        }
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var rl_root: LinearLayout = view.findViewById(R.id.rl_root)
        var iv_pic: ImageView = view.findViewById(R.id.iv_pic);
        var tv_title: TextView = view.findViewById(R.id.tv_title);
    }
}