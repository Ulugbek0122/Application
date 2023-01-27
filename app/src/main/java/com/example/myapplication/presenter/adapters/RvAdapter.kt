package com.example.myapplication.presenter.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.remote.response.ContactResponse

class RvAdapter : ListAdapter<ContactResponse, RvAdapter.Holder>(ContactCallBack) {

    private var onEditClickListener: ((ContactResponse) -> Unit)? = null
    private var onDeleteClicklistener: ((ContactResponse) -> Unit)? = null


    fun setOnEditClickListener(l: (ContactResponse) -> Unit) {
        onEditClickListener = l
    }

    fun setOnDeleteClickListener(l: (ContactResponse) -> Unit) {
        onDeleteClicklistener = l
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            var edit = itemView.findViewById<ImageView>(R.id.image_edit)
            var delete = itemView.findViewById<ImageView>(R.id.image_delete)
            edit.setOnClickListener {
                onEditClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
            delete.setOnClickListener {
                onDeleteClicklistener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            var name = itemView.findViewById<TextView>(R.id.tv_contact_name)
            var phone = itemView.findViewById<TextView>(R.id.tv_contact_phone)
            name.text = getItem(absoluteAdapterPosition).name
            phone.text = getItem(absoluteAdapterPosition).phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        return holder.onBind()
    }

}

object ContactCallBack : DiffUtil.ItemCallback<ContactResponse>() {
    override fun areItemsTheSame(oldItem: ContactResponse, newItem: ContactResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ContactResponse, newItem: ContactResponse): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.phone == newItem.phone
    }

}
