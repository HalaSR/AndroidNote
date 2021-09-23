package com.example.androidnote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author lop
 * @Date 2021/9/1 22:39
 *
 * @Description TODO
 */
interface IFetchBaidu {
    @GET("tn=15007414_13_dg")
    fun fetchHomeData():Call<String>

    @GET("{page}/test")
    fun fetchList(@Path("page") page:Int)

    @POST("api/test")
    fun postTest(@Query("phone") phone:Int,@Query("passWord") passWord:String):Call<String>
}