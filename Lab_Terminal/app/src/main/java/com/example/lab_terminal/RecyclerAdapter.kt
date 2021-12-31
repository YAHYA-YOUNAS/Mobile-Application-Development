package com.example.lab_terminal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class RecyclerAdapter(val context: Context, val items: JSONArray): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(context).inflate(R.layout.items_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = JSONObject(items[position].toString())

        holder.tvName.text = item.get("name").toString()
        holder.tvRegistration.text = item.get("registerationNumber").toString()
        holder.tvPhone.text = item.get("phoneNumber").toString()

        // Update background color on the basis of odd/even position
        if (position % 2 == 0) {
            holder.layout.setBackgroundColor(
                ContextCompat.getColor(context, R.color.lightGreen)
            )
        } else {
            holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }

        holder.btnSendSms.setOnClickListener{view ->
            if (context is MainActivity) {
                context.sendSms(item.get("phoneNumber").toString(), holder.btnSendSms)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.length()
    }

    class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val layout = view.findViewById<LinearLayout>(R.id.llMain)
        val tvName = view.findViewById<TextView>(R.id.item_name)
        val tvRegistration = view.findViewById<TextView>(R.id.item_registration)
        val tvPhone = view.findViewById<TextView>(R.id.item_phone)
        val btnSendSms = view.findViewById<Button>(R.id.btn_send_sms)
    }
}
