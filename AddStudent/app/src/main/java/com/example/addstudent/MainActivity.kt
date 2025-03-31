package com.example.addstudent

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName = findViewById<TextView>(R.id.txt_inputName)
        val inputMSSV = findViewById<TextView>(R.id.txt_inputMSSV)
        val lstView = findViewById<ListView>(R.id.txtView)
        val items = mutableListOf<String>()

        val adapter = object : ArrayAdapter<String>(this, R.layout.list_item, R.id.textViewItem1, items) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val text1 = view.findViewById<TextView>(R.id.textViewItem1)
                val text2 = view.findViewById<TextView>(R.id.textViewItem2)
                val btnDelete = view.findViewById<Button>(R.id.btnDelete)

                val item = items[position].split("\n")
                text1.text = item[0]  // Hiển thị tên
                text2.text = item[1]  // Hiển thị MSSV


                btnDelete.setOnClickListener {
                    items.removeAt(position)
                    notifyDataSetChanged()
                }

                return view
            }
        }

        lstView.adapter = adapter

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            val name = inputName.text.toString()
            val mssv = inputMSSV.text.toString()
            val newItem = "$name\n$mssv"

            items.add(0, newItem)
            adapter.notifyDataSetChanged()

        }
    }
}
