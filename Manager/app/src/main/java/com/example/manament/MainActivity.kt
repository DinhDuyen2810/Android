package com.example.learn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: StudentDatabaseHelper
    private lateinit var adapter: StudentAdapter  // Bạn sẽ tạo
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = StudentDatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = StudentAdapter { student ->
            dbHelper.deleteStudent(student.id)
            loadStudents()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val age = findViewById<EditText>(R.id.etAge).text.toString().toIntOrNull() ?: 0
            dbHelper.insertStudent(Student(name = name, age = age))
            loadStudents()
        }

        loadStudents()
    }

    private fun loadStudents() {
        val students = dbHelper.getAllStudents()
        adapter.submitList(students)
    }
}
