package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.volley_txt)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://run.mocky.io/v3/f55b0ebf-0d70-4a9d-9bd3-02fd4dc7de0b"

        // String Request
//        val stringRequest = StringRequest(Request.Method.GET, url,
//            { response ->
//                var result = JSONObject(response)
//                textView.setText("Name is: ${result["name"]}, and Age is ${result["age"]}") },
//            {
//                textView.setText("Did not work")
//            })

        // JSONObject Request
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//            { response ->
//                textView.setText("Name is: ${response["name"]}, and Age is ${response["age"]}")
//            },
//            { error ->
//                textView.setText("Did not work")
//            }
//        )

        // JSONArray Request
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                var resp_arr=response
                for (i in 0..resp_arr.length()-1)
                {
                    var key = JSONObject(resp_arr[i].toString())
                    textView.setText("${textView.text} , ${key.get("id")}, ${key.get("name")}, ${key.get("age")}")
                }
            },
            { error ->
                textView.setText("Did not work!")
            }
        )

        // Add the request to the RequestQueue.
//        queue.add(stringRequest)
//        queue.add(jsonObjectRequest)
        queue.add(jsonArrayRequest)
    }
}