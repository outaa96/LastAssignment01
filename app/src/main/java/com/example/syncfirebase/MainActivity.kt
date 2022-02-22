package com.example.syncfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save.setOnClickListener{
            //รับข้อมูลจาก EditText
            val First = fst.text.toString()
            val Last = sur.text.toString()

            //สร้าง Node สำหรับการเยื่อมต่อ Firebase Database
            val fb = FirebaseDatabase.getInstance()
            val ref = fb.getReference("Employee")
            val id:String?= ref.push().key //Save ID ของบันทึก

            val Employee = Employee(id.toString(), First, Last) //สร้าง O0ject ในการเก็บบ่อมูล
            ref.child(id.toString()).setValue(Employee).addOnCompleteListener { //เพิ่มข้อมูลลงfirebase
                Toast.makeText(applicationContext, "Complete", Toast.LENGTH_LONG).show()
                fst.setText("")
                sur.setText("")
            }
        }
    }
}