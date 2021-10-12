package com.example.headsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var buaddcele:Button
    lateinit var busubmit:Button
    lateinit var etcelename:EditText
    lateinit var rvmain:RecyclerView
    var userdata:List<CelebrityDetails.Datum>?=null
    var displayResponse =ArrayList<ArrayList<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buaddcele=findViewById(R.id.buaddcele)
        busubmit=findViewById(R.id.busubmit)
        etcelename=findViewById(R.id.etcelename)
        rvmain=findViewById(R.id.rvmain)

        getCelebrityDetails(onResult = {
            userdata = it
            val datumList = userdata
            for (datum in datumList!!) {
                displayResponse+= arrayListOf(arrayListOf("${datum.pk}","${datum.name}","${datum.taboo1}","${datum.taboo2}","${datum.taboo3}"))
            }
            rvmain.adapter=myAdapter(displayResponse)
            rvmain.layoutManager= LinearLayoutManager(this)
        })

        buaddcele.setOnClickListener {
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        busubmit.setOnClickListener {
            val n=etcelename.text.toString()
            checkCelebrity(n)
        }
    }

    private fun getCelebrityDetails(onResult: (List<CelebrityDetails.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getCelebrityDetails()?.enqueue(object : Callback<List<CelebrityDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<CelebrityDetails.Datum>>,
                    response: Response<List<CelebrityDetails.Datum>>
                ) {
                    onResult(response.body())
                }
                override fun onFailure(call: Call<List<CelebrityDetails.Datum>>, t: Throwable) {
                    onResult(null)
                }

            })
        }
    }

    private fun checkCelebrity(n:String){
        for (i in displayResponse.indices ){
            if (n==displayResponse[i][1]){
                val id=displayResponse[i][0]
                val strname=displayResponse[i][1]
                val strt1=displayResponse[i][2]
                val strt2=displayResponse[i][3]
                val strt3=displayResponse[i][4]
                val intent= Intent(this,MainActivity3::class.java)
                intent.putExtra("name",strname)
                intent.putExtra("t1",strt1)
                intent.putExtra("t2",strt2)
                intent.putExtra("t3",strt3)
                intent.putExtra("id",id)
                startActivity(intent)}
                else{
                    Toast.makeText(this,"$n not found ", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

