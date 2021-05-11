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
import fr.dev.majdi.testexomind.model.User

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@SuppressLint("SetTextI18n")
class AlbumAdapter(private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]

        holder.title.text = album.title

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position, album)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)

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
        fun onItemClick(view: View, position: Int, album: Album?)
    }


}