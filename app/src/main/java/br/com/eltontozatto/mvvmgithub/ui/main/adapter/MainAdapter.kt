package br.com.eltontozatto.mvvmgithub.ui.main.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.eltontozatto.mvvmgithub.R
import br.com.eltontozatto.mvvmgithub.data.model.User
import com.bumptech.glide.Glide

class MainAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            val tvUserName = itemView.findViewById<AppCompatTextView>(R.id.tvUserName)
            tvUserName.text = user.login
            val tvUserEmail = itemView.findViewById<AppCompatTextView>(R.id.tvUserEmail)
            tvUserEmail.text = user.url
            val imgAvatar = itemView.findViewById<ImageView>(R.id.imgAvatar)
            Glide.with(imgAvatar.context)
                .load(user.avatar)
                .into(imgAvatar)
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder = DataViewHolder (
        LayoutInflater.from(parent.context).inflate(
            R.id.item_layout,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}