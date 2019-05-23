package com.ar.maloba.myarqcompo.ui.user

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ar.maloba.myarqcompo.R
import com.ar.maloba.myarqcompo.repository.dao.entity.UserEntity
import com.ar.maloba.myarqcompo.utils.inflate
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val listUserEntity: MutableList<UserEntity>,
                  val onUserItemClickListener: (UserEntity) -> Unit)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(parent)

    override fun getItemCount(): Int = listUserEntity.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUserEntity[position])
    }

    inner class UserViewHolder(viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(viewGroup.inflate(R.layout.item_user)) {

        private lateinit var userEntity: UserEntity

        init {
            itemView.setOnClickListener {
                onUserItemClickListener(userEntity)
            }
        }

        fun bind(userEntity: UserEntity) {
            this.userEntity = userEntity
            itemView.tvUserId.text = "${this.userEntity.name} - ${this.userEntity.id}"
        }
    }

    fun addAllUsers(listUserEntity: List<UserEntity>) {
        this.listUserEntity.clear()
        this.listUserEntity.addAll(listUserEntity)
        notifyDataSetChanged()
    }

    fun addUser(userEntity: UserEntity) {
        listUserEntity.add(userEntity)
        notifyDataSetChanged()
    }
}