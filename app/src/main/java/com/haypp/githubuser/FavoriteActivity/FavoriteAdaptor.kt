package com.haypp.githubuser.FavoriteActivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haypp.githubuser.DetilUserActivity
import com.haypp.githubuser.DetilUserActivity.Companion.extradataa
import com.haypp.githubuser.db.Favorite
import com.haypp.githubuser.databinding.ItemRowUsersBinding
import com.haypp.githubuser.FavoriteActivity.FavoriteAdaptor.FavoriteViewHolder
import com.haypp.githubuser.helper.FavoriteDiffCallback
import java.util.*

class FavoriteAdaptor : RecyclerView.Adapter<FavoriteViewHolder>() {
    private val listFav = ArrayList<Favorite>()
    fun setListNotes(listFav: List<Favorite>) {
        val diffCallback = FavoriteDiffCallback(this.listFav, listFav)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFav.clear()
        this.listFav.addAll(listFav)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFav[position])
    }

    override fun getItemCount(): Int {
        return listFav.size
    }

    inner class FavoriteViewHolder(private val binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fav: Favorite) {
            with(binding) {
                tvItemName.text = fav.login
                Glide.with(itemView.context)
                    .load(fav.avatar_url)
                    .circleCrop()
                    .into(imgItemPhoto)
                    cardView.setOnClickListener {
                    val intent = Intent(it.context, DetilUserActivity::class.java)
                    intent.putExtra(DetilUserActivity.extradataa, fav)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}
