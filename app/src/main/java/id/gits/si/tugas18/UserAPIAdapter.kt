package id.gits.si.tugas18

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.gits.si.tugas18.model.DataItem

class UserAPIAdapter(val results : ArrayList<DataItem>) : RecyclerView.Adapter<UserAPIAdapter.ViewHolder>() {
    fun setData(data : List<DataItem>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAPIAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAPIAdapter.ViewHolder, position: Int) {
        val data = results[position]
        val URL_FOTO = ""
        Glide.with(holder.itemView).load(URL_FOTO+data.nama).apply(RequestOptions().override(320,320)).into(holder.foto)
        holder.nama.text = data.nama
        holder.no_hp.text = data.noHp
        holder.instagram.text = data.instagram
    }

    override fun getItemCount(): Int {
        return results.size
    }

    inner class ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView) {
        val nama : TextView = itemView.findViewById(R.id.tv_nama)
        val no_hp : TextView = itemView.findViewById(R.id.tv_no_hp)
        val instagram : TextView = itemView.findViewById(R.id.tv_instagram)
        val follower : TextView = itemView.findViewById(R.id.tv_follower)
        val foto : ImageView = itemView.findViewById(R.id.iv_foto)

    }
}