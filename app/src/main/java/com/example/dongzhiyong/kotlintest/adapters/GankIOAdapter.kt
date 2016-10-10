package com.example.dongzhiyong.kotlintest.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.dongzhiyong.kotlintest.R
import com.example.dongzhiyong.kotlintest.extensions.inflate
import com.example.dongzhiyong.kotlintest.extensions.loadByUrl
import com.example.dongzhiyong.kotlintest.extensions.onClick
import com.example.dongzhiyong.kotlintest.model.GankInfo
import kotlinx.android.synthetic.main.list_item_gank_layout.view.*
import kotlin.properties.Delegates

/**
 * Created by Dong on 2016/9/28.
 */
class GankIOAdapter(var onItemClickListener: ((GankInfo, Int) -> Unit)) : RecyclerView.Adapter<GankIOAdapter.ViewHolder>() {

    var datas: List<GankInfo> by Delegates.observable(emptyList()) {
        properties, old, new ->
        notifyDataSetChanged()
    }

//    var onItemClickListener: ((GankInfo) -> Unit)? = null

    override fun getItemCount(): Int = datas.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.list_item_gank_layout)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       bind(holder,datas[position], position)
    }

    fun bind(holder: ViewHolder, gankInfo: GankInfo, position: Int) {
        with(gankInfo) {
            holder.itemView.apply {
                if (images != null) {
                    var iconUrl = images[0]
                    iconUrl.let { iv_imageView.loadByUrl(it) }
                } else {
                    iv_imageView.visibility = View.INVISIBLE
                }
                tv_desc.text = desc
                tv_publishedAt.text = publishedAt
                tv_who.text = who
                onClick {
                    onItemClickListener?.invoke(gankInfo, position)
                }

            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*private val iv_imageView: ImageView
        private val tv_desc: TextView
        private val tv_publishedAt: TextView
        private val tv_who: TextView

        init {
            iv_imageView = itemView.findViewById(R.id.iv_imageView) as ImageView
            tv_desc = itemView.findViewById(R.id.tv_desc) as TextView
            tv_publishedAt = itemView.findViewById(R.id.tv_publishedAt) as TextView
            tv_who = itemView.findViewById(R.id.tv_who) as TextView
        }*/

        fun bind(gankInfo: GankInfo, position: Int) {
            with(gankInfo) {

                //way 1
                /* if (images != null) {
                     var iconUrl = images[0]
 //                    Glide.with(iv_imageView.ctx).load(iconUrl).into(iv_imageView)
                     itemView.iv_imageView.loadByUrl(iconUrl)
                 }
                 itemView.tv_desc.text = desc
                 itemView.tv_publishedAt.text = publishedAt
                 itemView.tv_who.text = who
                 itemView.setOnClickListener { itemCLick?.invoke(gankInfo, position) }*/

                //way 2
               /* itemView.apply {
                    if (images != null) {
                        var iconUrl = images[0]
                        iconUrl.let { iv_imageView.loadByUrl(it) }
                    } else {
                        iv_imageView.visibility = View.INVISIBLE
                    }
                    tv_desc.text = desc
                    tv_publishedAt.text = publishedAt
                    tv_who.text = who
                    onClick {
                        onItemClickListener?.invoke(gankInfo, position)
                    }

                }*/
            }
        }
    }
}