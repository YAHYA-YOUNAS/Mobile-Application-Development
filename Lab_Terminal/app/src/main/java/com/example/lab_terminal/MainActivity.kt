package com.example.lab_terminal

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var rvStudentsList: RecyclerView
    val SENT_ACTION="com.example.labTerminal.SENT_ACTION"
    val DELIVERY_ACTION="com.example.labTerminal.DELIVERY_ACTION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvStudentsList = findViewById(R.id.rvStudentsList)

        // Fetch data from API
        fetchData()

        // Start service
        startService(Intent(this, MyService::class.java))

    }

    private fun fetchData() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://run.mocky.io/v3/b8402fc5-ae31-4d98-bced-b5b3fede6d06"

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null, { response ->
            val response_array = response
            rvStudentsList.layoutManager = LinearLayoutManager(this)
            val adapter = RecyclerAdapter(this, response_array)
            rvStudentsList.adapter = adapter
//            for (i in 0..response_array.length()-1) {
//                val key = JSONObject(response_array[i].toString())

//                Toast.makeText(this, "${key.get("name")}, ${key.get("registerationNumber")}, " +
//                                                "${key.get("phoneNumber")}", Toast.LENGTH_LONG).show()
//                Log.d("students", "${key.get("name")}, ${key.get("registerationNumber")}, " + "${key.get("phoneNumber")}")
//            }
        }, { error ->
            Toast.makeText(this, "API request did not work", Toast.LENGTH_LONG).show()
            Log.d("students", "API request did not work")
        })

        queue.add(jsonArrayRequest)
    }

    fun sendSms(phoneNumber: String, btnSendSms: Button) {

        // PendingIntents for Sent and Delivery
        val sentIntent = PendingIntent.getBroadcast(this, 100, Intent(SENT_ACTION), 0)
        val deliveryIntent = PendingIntent.getBroadcast(this, 200, Intent(DELIVERY_ACTION), 0)

        // Receiver for sent
        val sentReceiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("SMS","sent")
            }
        }

        // register sent receiver
        val it = IntentFilter(SENT_ACTION)
        registerReceiver(sentReceiver, it)

        // register deliver receiver
        registerReceiver(object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("SMS ", "delivered")
            }
        }, IntentFilter(DELIVERY_ACTION))


        // send reply
        btnSendSms.setOnClickListener {
            var sms = SmsManager.getDefault()
            sms.sendTextMessage(
                phoneNumber,
                "From Lab Terminal",
                "Hello from student",
                sentIntent,
                deliveryIntent
            )
        }

    }
}