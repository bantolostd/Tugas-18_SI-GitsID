package id.gits.si.tugas18.rest

import retrofit2.Call
import id.gits.si.tugas18.model.UserResponse
import retrofit2.http.*

interface Api {
    @GET("api.php?function=get_user")
    fun getUser(): Call<UserResponse>
    @FormUrlEncoded
    @POST("api.php")
    fun insertUser(
        @Field("nama") nama : String,
        @Field("email") email : String,
        @Field("no_hp") no_hp : String,
        @Field("alamat") alamat : String,
        @Field("instagram") instagram : String,
        @Query("function") function : String
    ): Call<UserResponse>

}