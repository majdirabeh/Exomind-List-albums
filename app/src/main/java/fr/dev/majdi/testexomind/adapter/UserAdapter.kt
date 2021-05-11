package fr.dev.majdi.testexomind.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fr.dev.majdi.testexomind.R
import fr.dev.majdi.testexomind.model.User

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@SuppressLint("SetTextI18n")
class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return users.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        holder.name.text = "${user.name} , ${user.username}"
        holder.email.text = user.email
        holder.tel.text = user.phone
        holder.site.text = user.website

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position, user)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val email = itemView.findViewById<TextView>(R.id.email)
        val tel = itemView.findViewById<TextView>(R.id.tel)
        val site = itemView.findViewById<TextView>(R.id.site)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(itemView, 0, null)
            }
        }
    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int, user: User?)
    }


}