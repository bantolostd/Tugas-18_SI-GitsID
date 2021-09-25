package id.gits.si.tugas18

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import id.gits.si.tugas18.model.DataItem
import id.gits.si.tugas18.model.UserResponse
import id.gits.si.tugas18.rest.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_card_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val userAPIAdapter = UserAPIAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDataUser()

        btnTambah.setOnClickListener {
            val intent = Intent(this@MainActivity, InsertUserActivity::class.java)
            startActivity(intent)
        }

    }
    private fun getDataUser() {
        RetrofitClient.instance.getUser()
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>?,
                    response: Response<UserResponse>?
                ) {
                    rv_user.layoutManager = LinearLayoutManager(this@MainActivity)
                    rv_user.adapter = UserAPIAdapter(response?.body()?.data as ArrayList<DataItem>)
                    if (response!!.isSuccessful){
                        tampilUser(response.body())
                        val result = response.body().data
                        for (item in result!!){
                            detailUser(item!!.id)
                        }
                    }else{
                        Toast.makeText(this@MainActivity, "Reponse Gagal", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                    Toast.makeText(this@MainActivity, "Reponse Gagal : ${t}", Toast.LENGTH_LONG).show()
                }

            })
    }
    private fun tampilUser(data : UserResponse){
        val result = data?.data
        userAPIAdapter.setData(result as List<DataItem>)
    }

    private fun detailUser(id: String?){
        RetrofitClient
    }
}