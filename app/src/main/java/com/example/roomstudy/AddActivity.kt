package com.example.roomstudy

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import android.app.Activity.RESULT_OK
import androidx.annotation.RequiresApi
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var imageView : ImageView
    val OPEN_CALLERY = 1
    @RequiresApi(Build.VERSION_CODES.N)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        val addImgButton = findViewById<Button>(R.id.add_background_image_btn)
        imageView = findViewById(R.id.background_image)
        val expirationDate =  findViewById<TextView>(R.id.expiration_date)
        val calendarButton =  findViewById<ImageButton>(R.id.calander_btn)
        val disclosureStatus =  findViewById<RadioGroup>(R.id.disclosure_status)
        val passwordText =  findViewById<TextView>(R.id.password_text)
        val inputPassword =  findViewById<EditText>(R.id.input_password)
        val createButton =  findViewById<Button>(R.id.create_btn)

        addImgButton.setOnClickListener{
            loadImage()
        }

        // 방 공개 여부
        disclosureStatus.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                // 공개방이면 -> 비밀번호 입력창 안보이게
                 R.id.public_room-> {
                    passwordText.visibility = View.GONE
                    inputPassword.visibility = View.GONE
                }
                // 비공개면 -> 비밀번호 입력창 보이게
                R.id.private_room -> {
                    passwordText.visibility = View.VISIBLE
                    inputPassword.visibility = View.VISIBLE
                }

            }
        }

        // 캘린더 버튼 누르면 -> 날짜 선택 가능
        calendarButton.setOnClickListener() {
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
                expirationDate.text = "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}"
            }
            datePicker.show(supportFragmentManager, datePicker.toString())
        }

        // 프로젝트 생성 버튼
        createButton.setOnClickListener {
            // 목록 페이지로 이동
            finish()
        }

        // 목표 인원 Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)

        val items = arrayOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        spinner.adapter = myAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    // 이미지 업로드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == OPEN_CALLERY){
            if(resultCode == RESULT_OK){
                var dataUri = data?.data
                try{
                    val imageStream = this.contentResolver.openInputStream(dataUri!!)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    imageView.setImageBitmap(selectedImage)
                }catch (e:Exception){
                    Toast.makeText(this,"$e",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // 갤러리에서 이미지 불러오기
    private fun loadImage() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, OPEN_CALLERY)
    }

}