package com.ezzy.devbyte.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ezzy.devbyte.R
import com.ezzy.devbyte.databinding.DevByteItemBinding
import com.ezzy.devbyte.domain.models.Video
import com.ezzy.devbyte.ui.VideoClick

class DevByteAdapter(val callback: VideoClick) :
    RecyclerView.Adapter<DevByteAdapter.DevByteViewHolder>() {

    var videos: List<Video> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val withDataBinding: DevByteItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DevByteViewHolder.LAYOUT,
            parent,
            false
        )
        return DevByteViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    class DevByteViewHolder(
        val viewDataBinding: DevByteItemBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.dev_byte_item
        }
    }

}