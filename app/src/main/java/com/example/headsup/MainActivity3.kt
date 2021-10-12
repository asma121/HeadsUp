package com.example.headsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    lateinit var buttondele: Button
    lateinit var buttonup: Button
    lateinit var buttonback: Button
    lateinit var etname2: EditText
    lateinit var ettaboo12: EditText
    lateinit var ettaboo22: EditText
    lateinit var ettaboo32: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        buttondele=findViewById(R.id.buttondele)
        buttonup=findViewById(R.id.buttonup)
        buttonback=findViewById(R.id.buttonback)
        etname2=findViewById(R.id.etname2)
        ettaboo12=findViewById(R.id.ettaboo12)
        ettaboo22=findViewById(R.id.ettaboo22)
        ettaboo32=findViewById(R.id.ettaboo32)

        val strname=intent.getStringExtra("name")
        val id= intent.getStringExtra("id").toString().toInt()
        val strt1=intent.getStringExtra("t1")
        val strt2=intent.getStringExtra("t2")
        val strt3=intent.getStringExtra("t3")


        etname2.setText(strname)
        ettaboo12.setText(strt1)
        ettaboo22.setText(strt2)
        ettaboo32.setText(strt3)

        buttonback.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        buttondele.setOnClickListener {
            delCelebrityDetails(id)
        }

        buttonup.setOnClickListener {
            val f = CelebrityDetails.Datum(etname2.text.toString(), ettaboo12.text.toString(),ettaboo22.text.toString(),ettaboo32.text.toString())
            upDateCelebrityDetails(id,f)

        }

    }

    private fun delCelebrityDetails(id:Int){
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.delCelebrityDetails(id)?.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext,"Celebrity Deleted", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun upDateCelebrityDetails(id:Int,userdata: CelebrityDetails.Datum){
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.upDateCelebrityDetails(id,userdata)?.enqueue(object : Callback<CelebrityDetails.Datum> {
                override fun onResponse(call: Call<CelebrityDetails.Datum>, response: Response<CelebrityDetails.Datum>) {
                    Toast.makeText(applicationContext,"Celebrity Update", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<CelebrityDetails.Datum>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}