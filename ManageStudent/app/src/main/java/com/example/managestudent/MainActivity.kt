class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: StudentDatabaseHelper
    private lateinit var studentAdapter: StudentAdapter

    private val addStudentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) loadData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = StudentDatabaseHelper(this)
        studentAdapter = StudentAdapter(this, dbHelper, ::loadData)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        loadData()
    }

    private fun loadData() {
        studentAdapter.submitList(dbHelper.getAllStudents())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            val intent = Intent(this, AddEditStudentActivity::class.java)
            addStudentLauncher.launch(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
