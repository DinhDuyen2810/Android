class StudentAdapter(
    private val context: Context,
    private val dbHelper: StudentDatabaseHelper,
    private val onDataChanged: () -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var students = listOf<Student>()

    fun submitList(list: List<Student>) {
        students = list
        notifyDataSetChanged()
    }

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvMSSV = view.findViewById<TextView>(R.id.tvMSSV)
        val menuBtn = view.findViewById<ImageView>(R.id.btnMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvMSSV.text = student.mssv

        holder.menuBtn.setOnClickListener {
            val popup = PopupMenu(context, it)
            popup.menuInflater.inflate(R.menu.menu_student_item, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_call -> {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${student.phone}"))
                        context.startActivity(intent)
                    }
                    R.id.menu_email -> {
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${student.email}"))
                        context.startActivity(intent)
                    }
                    R.id.menu_edit -> {
                        val intent = Intent(context, AddEditStudentActivity::class.java).apply {
                            putExtra("student", student)
                        }
                        (context as Activity).startActivityForResult(intent, 1)
                    }
                    R.id.menu_delete -> {
                        AlertDialog.Builder(context)
                            .setTitle("Xác nhận")
                            .setMessage("Bạn muốn xóa sinh viên này?")
                            .setPositiveButton("Xóa") { _, _ ->
                                dbHelper.deleteStudent(student.id)
                                onDataChanged()
                            }
                            .setNegativeButton("Hủy", null)
                            .show()
                    }
                }
                true
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int = students.size
}
