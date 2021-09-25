package id.gits.si.tugas18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.gits.si.tugas18.model.UserResponse
import id.gits.si.tugas18.rest.RetrofitClient
import kotlinx.android.synthetic.main.activity_insert_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_insert_user)

        btn_insert.setOnClickListener {
            RetrofitClient.instance.insertUser(
                et_nama.text.toString().trim(),
                et_email.text.toString().trim(),
                et_no_hp.text.toString().trim(),
                et_alamat.text.toString().trim(),
                et_instagram.text.toString().trim(),
                "insert_user").enqueue(object: Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>?,
                    response: Response<UserResponse>?
                ) {
                    if (response!!.isSuccessful){
                        if (response.body()?.status == 1){
                            et_nama.setText("")
                            et_email.setText("")
                            et_no_hp.setText("")
                            et_alamat.setText("")
                            et_instagram.setText("")
                            val toast = Toast.makeText(this@InsertUserActivity, "User berhasil ditambahkan!", Toast.LENGTH_LONG)
                            toast.show()
                        }
                    } else {
                        val toast = Toast.makeText(this@InsertUserActivity, "User gagal ditambahkan!", Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    val toast = Toast.makeText(this@InsertUserActivity, "Tidak ada respon $t", Toast.LENGTH_LONG)
                    toast.show()
                }
            })
        }

    }
}