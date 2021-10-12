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

class MainActivity2 : AppCompatActivity() {
    lateinit var buadd: Button
    lateinit var buback: Button
    lateinit var etname: EditText
    lateinit var ettaboo1: EditText
    lateinit var ettaboo2: EditText
    lateinit var ettaboo3: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        buadd=findViewById(R.id.buadd)
        buback=findViewById(R.id.buback)
        etname=findViewById(R.id.etname)
        ettaboo1=findViewById(R.id.ettaboo1)
        ettaboo2=findViewById(R.id.ettaboo2)
        ettaboo3=findViewById(R.id.ettaboo3)

        buadd.setOnClickListener {
            var f = CelebrityDetails.Datum(etname.text.toString(), ettaboo1.text.toString(),ettaboo2.text.toString(),ettaboo3.text.toString())
            addCelebrityDetails(f, onResult = {
                etname.setText("")
                ettaboo1.setText("")
                ettaboo2.setText("")
                ettaboo3.setText("")
            })
        }


        buback.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addCelebrityDetails(f :CelebrityDetails.Datum ,onResult: () -> Unit){
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.addCelebrityDetails(f)?.enqueue(object : Callback<CelebrityDetails.Datum?> {
                override fun onResponse(call: Call<CelebrityDetails.Datum?>, response: Response<CelebrityDetails.Datum?>) {
                    Toast.makeText(applicationContext,"celebrity added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<CelebrityDetails.Datum?>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}