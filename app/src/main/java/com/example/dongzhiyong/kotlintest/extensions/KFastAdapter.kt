package com.example.dongzhiyong.kotlintest.extensions

import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 *  优点：
 *  1.可以实现1种实体对应多种布局
 *  2.可以实现某个item单独刷新
 *  缺点：
 *  1.不能能实现多种实体对应多种布局
 *  2.不能实现单个item中某个view的局部刷新
 */

/**
 * 对应多种布局
 */
fun <T> RecyclerView.bind(items:List<T>): FastAdapter<T> {
    layoutManager = LinearLayoutManager(context)
    val _adapter = FastAdapter(items, this);
    adapter = _adapter
    return _adapter

}

/**
 * 一种布局
 */
fun <T> RecyclerView.bind(items: List<T>, @LayoutRes layoutId: Int, singleBind: View.(item: T) -> Unit) : FastAdapter<T> {
    layoutManager = LinearLayoutManager(context)
    val _adapter = FastAdapter(items, this).map(layoutId, { true }, singleBind)
    adapter = _adapter
    return _adapter
}

/**
 * 使用DiffUtil刷新某个item整体
 * 通过比较T的equal方法比较对象是否相等，需要比较全部的字段，
 */
fun <T> RecyclerView.update(newItems:List<T>){
    (adapter as FastAdapter<T>)?.update(newItems){ old, cur -> old == cur}
}

open class FastAdapter<T>(private var items: List<T>,
                          private var list: RecyclerView) : RecyclerView.Adapter<FastAdapter.FastListViewHolder<T>>() {

    private inner class BindMap(val layoutId: Int,val viewType: Int,val predicate: (T) -> Boolean,val bind: View.(item: T) -> Unit)

    private val bindMap = ArrayList<BindMap>()

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FastListViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(bindMap.first { it.viewType==viewType }.layoutId, parent, false)
        return FastListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FastListViewHolder<T>, position: Int) {
        holder.bind(items[position], bindMap.first { it.viewType == holder.itemViewType }.bind)
    }

    override fun getItemViewType(position: Int): Int = try {
        bindMap.first { it.predicate(items[position]) }.viewType
    } catch (e: Exception) {
        0
    }

    /**
     *  通过该方法来映射布局和实体的对应绑定关系
     */
    fun map(@LayoutRes layoutId: Int, predicate: (T) -> Boolean,bind: View.(item: T) -> Unit): FastAdapter<T> {
        bindMap.add(BindMap(layoutId,bindMap.size,predicate,bind))
        return this
    }


    fun update(newItems: List<T>, compareContent:(T, T)->Boolean){
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return items[oldItemPosition]== newItems[newItemPosition]
            }


            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compareContent(items[oldItemPosition], newItems[newItemPosition])
            }

            /**
             * 如果要封装某个item的局部刷新，不但这里需要改，Adapter的bindviewholder也不具备通用性了
             * 要更新这种情况，还是再外部创建DiffUtil.calculateDiff比较方便
             */
            /*override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                val args = Bundle ()
                args.putString("", "")
                return args
            }*/

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = newItems.size
        })

        diffResult.dispatchUpdatesTo(this)
        items = newItems

    }


    class FastListViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewArrays: SparseArray<View> = SparseArray()

        fun bind(item: T, block: View.(item: T) -> Unit) {
            itemView.apply {
                block(item)
            }

        }

        //目前已经用不到了，但是为了之后拓展的
        fun <T : View> getView(viewId:Int): T{
            var view = viewArrays.get(viewId)
            if (view == null) {
                view =  itemView.findViewById<T>(viewId)
                viewArrays.put(viewId, view)
            }
            return view as T
        }

    }

}