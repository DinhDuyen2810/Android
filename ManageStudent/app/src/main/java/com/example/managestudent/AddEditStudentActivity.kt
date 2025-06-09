class AddEditStudentActivity : AppCompatActivity() {
    private lateinit var dbHelper: StudentDatabaseHelper
    private var editingStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_student)

        dbHelper = StudentDatabaseHelper(this)
        editingStudent = intent.getSerializableExtra("student") as? Student

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtMSSV = findViewById<EditText>(R.id.edtMSSV)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val btnSave = findViewById<Button>(R.id.btnSave)

        editingStudent?.let {
            edtName.setText(it.name)
            edtMSSV.setText(it.mssv)
            edtEmail.setText(it.email)
            edtPhone.setText(it.phone)
        }

        btnSave.setOnClickListener {
            val name = edtName.text.toString()
            val mssv = edtMSSV.text.toString()
            val email = edtEmail.text.toString()
            val phone = edtPhone.text.toString()

            val student = Student(editingStudent?.id ?: 0, name, mssv, email, phone)
            if (editingStudent == null) {
                dbHelper.insertStudent(student)
            } else {
                dbHelper.updateStudent(student)
            }

            setResult(RESULT_OK)
            finish()
        }
    }
}
