package com.example.roomstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class AddProjectActivity : AppCompatActivity() {

    private var projectDB : ProjectDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        projectDB = ProjectDB.getInstance(this)

        val inputTitle = findViewById<EditText>(R.id.input_title)
        val inputTotalAmount = findViewById<EditText>(R.id.input_amount)
        val deadline = findViewById<TextView>(R.id.expiration_date)
        val calendarButton =  findViewById<ImageButton>(R.id.calander_btn)
        val createButton =  findViewById<Button>(R.id.create_btn)

        /* 새로운 project 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
        val addRunnable = Runnable {
            val newProject = Project()
            // 제목
            newProject.title = inputTitle.text.toString()

            // 현재 인원
            newProject.currentNum = 2

            // 목표 인원
            val spinner = findViewById<Spinner>(R.id.spinner)

            val items = arrayOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)
            val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

            spinner.adapter = myAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    newProject.totalNum = items[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }

            // 현재 금액
            newProject.currentAmount = 20000

            // 목표 금액
            newProject.totalAmount = inputTotalAmount.text.toString().toInt()

            // 캘린더 버튼 누르면 -> 날짜 선택 가능
            calendarButton.setOnClickListener {
                //오늘 이후만 선택 가능
                val calendarConstraintBuilder = CalendarConstraints.Builder()
                calendarConstraintBuilder.setValidator(DateValidatorPointForward.now())

                // 날짜 선택 화면 구현
                val builder = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("날짜 선택")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .setCalendarConstraints(calendarConstraintBuilder.build());

                val datePicker = builder.build()

                // 날짜 선택 하면 expirationDate에 저장
                datePicker.addOnPositiveButtonClickListener {
                    val calendar = Calendar.getInstance()
                    calendar.time = Date(it)
                    deadline.text = "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(
                        Calendar.DAY_OF_MONTH)}"
                }
                datePicker.show(supportFragmentManager, datePicker.toString())
            }

            projectDB?.projectDAO()?.insert(newProject)
        }

        createButton.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        ProjectDB.destroyInstance()
        super.onDestroy()
    }
}