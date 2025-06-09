package com.example.studentmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var dao: StudentDao
    private lateinit var adapter: StudentAdapter // bạn sẽ tạo sau

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDatabase(this)
        dao = db.studentDao()

        // Ví dụ thêm sinh viên
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val age = findViewById<EditText>(R.id.etAge).text.toString().toInt()
            lifecycleScope.launch {
                dao.insert(Student(name = name, age = age))
                loadStudents()
            }
        }

        // Hiển thị danh sách
        loadStudents()
    }

    private fun loadStudents() {
        lifecycleScope.launch {
            val students = dao.getAll()
            adapter.submitList(students)
        }
    }
}
