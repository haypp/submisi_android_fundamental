package com.haypp.githubuser.helper

import androidx.recyclerview.widget.DiffUtil
import com.haypp.githubuser.db.Favorite

class FavoriteDiffCallback(private val mOldNoteList: List<Favorite>, private val mNewNoteList: List<Favorite>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = mOldNoteList[oldItemPosition]
        val new = mNewNoteList[newItemPosition]
        return old.login == new.login && old.avatar_url == new.avatar_url
    }
}