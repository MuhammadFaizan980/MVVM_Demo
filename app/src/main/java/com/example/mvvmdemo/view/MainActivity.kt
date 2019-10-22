package com.example.mvvmdemo.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.model.MyModel
import com.example.mvvmdemo.recyclerview_adapter.MyAdapter
import com.example.mvvmdemo.viewmodel.MaiActivityViewModel

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var list: MutableList<MyModel> = ArrayList()
    private var adapter: MyAdapter? = null
    private var edtMessage: EditText? = null
    private var btnAddMessage: Button? = null
    private var viewModel: MaiActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initMvvm()
        populateRecyclerVIew()
    }

    private fun initMvvm() {
        viewModel!!.init()
        viewModel!!.getMessages().observe(this, object : Observer<List<MyModel>> {
            override fun onChanged(t: List<MyModel>?) {
                list.clear()
                for (i in t!!) {
                    list.add(i)
                }
                adapter!!.notifyDataSetChanged()
                Log.i("lol", "list size in main() in ${list.size}")
            }
        })
    }

    private fun populateRecyclerVIew() {
        recyclerView!!.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView!!.adapter = adapter
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.main_list)
        viewModel = ViewModelProviders.of(this@MainActivity).get(MaiActivityViewModel::class.java)
        adapter = MyAdapter(list)
        edtMessage = findViewById(R.id.edt_message)
        btnAddMessage = findViewById(R.id.btn_add)

        btnAddMessage!!.setOnClickListener {
            if (edtMessage!!.text.toString().trim() != "") {
                val obj = MyModel()
                obj.message = edtMessage!!.text.toString().trim()
                viewModel!!.addDay(obj)
            } else {
                Toast.makeText(this@MainActivity, "Fill field first", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
