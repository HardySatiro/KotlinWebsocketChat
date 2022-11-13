package com.example.kotlinwebsocketchat
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdapterRecyclerViewMessage (val context: Context, var messages: ArrayList<Message>):
    RecyclerView.Adapter<AdapterRecyclerViewMessage.ViewHolder>(){

    // aponta qual layound vai ser adicionado os itemns
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            if (viewType == 1)
                R.layout.send_cardview
            else R.layout.receive_cardview, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun getItemCount() = messages.size

    override fun getItemViewType(position: Int): Int {
        if (messages.get(position).send) {
            return 1
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message : Message = messages.get(position)
        holder.message.text = message.message
    }
    fun addMessage(message: Message){
        messages.add(message)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val message: TextView = itemView.findViewById(R.id.TextView_item_message)
    }
}