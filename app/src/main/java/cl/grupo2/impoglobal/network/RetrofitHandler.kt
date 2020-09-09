package cl.grupo2.impoglobal.network

import cl.grupo2.impoglobal.productos.data.remote.ProductosApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHandler {


    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("192.168.0.8:3000")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    fun getProductosApi(): ProductosApi {
        return getRetrofit().create(ProductosApi::class.java)
    }
}
