class StudentDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "StudentDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                mssv TEXT,
                email TEXT,
                phone TEXT
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS students")
        onCreate(db)
    }

    fun insertStudent(student: Student): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", student.name)
            put("mssv", student.mssv)
            put("email", student.email)
            put("phone", student.phone)
        }
        return db.insert("students", null, values)
    }

    fun updateStudent(student: Student): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", student.name)
            put("mssv", student.mssv)
            put("email", student.email)
            put("phone", student.phone)
        }
        return db.update("students", values, "id = ?", arrayOf(student.id.toString()))
    }

    fun deleteStudent(id: Int): Int {
        val db = writableDatabase
        return db.delete("students", "id = ?", arrayOf(id.toString()))
    }

    fun getAllStudents(): List<Student> {
        val list = mutableListOf<Student>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM students", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(Student(
                    id = cursor.getInt(0),
                    name = cursor.getString(1),
                    mssv = cursor.getString(2),
                    email = cursor.getString(3),
                    phone = cursor.getString(4)
                ))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}
