package com.haypp.githubuser.adaptor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haypp.githubuser.activity.DetilUserActivity
import com.haypp.githubuser.R
import com.haypp.githubuser.data.api.Items

class ListUserAdaptor(private val listUser: ArrayList<Items>) : RecyclerView.Adapter<ListUserAdaptor.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_users, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (avatar, name) = listUser[position]
        holder.apply {
            Glide.with(itemView.context)
                .load(avatar)
                .circleCrop()
                .into(imgPhoto)
            tvName.text = name
            holder.itemView.setOnClickListener {
                val mcontext = holder.itemView.context
                val sendlogin = listUser[position].login
                val pindah = Intent(mcontext, DetilUserActivity::class.java)
                pindah.putExtra(DetilUserActivity.extradataa, sendlogin)
                mcontext.startActivity(pindah)
            }
        }
    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
    }

}