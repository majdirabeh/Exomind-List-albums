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
import fr.dev.majdi.testexomind.model.Album
import fr.dev.majdi.testexomind.model.Picture
import fr.dev.majdi.testexomind.model.User

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@SuppressLint("SetTextI18n")
class PictureAdapter(private val pictures: List<Picture>) :
    RecyclerView.Adapter<PictureAdapter.ViewHolder>() {

    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return pictures.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture = pictures[position]
        holder.titlePicture.text = picture.title
        Glide.with(holder.itemView.context)
            .load(picture.thumbnailUrl)
            .error(R.drawable.placeholder)
            .skipMemoryCache(true)
            .placeholder(R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image)
        val titlePicture = itemView.findViewById<TextView>(R.id.title_picture)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(itemView, 0)
            }
        }
    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


}