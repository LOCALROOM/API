package com.anranyus.apirequest

import com.anranyus.apirequest.store.CookieStore
import com.google.gson.Gson
import okhttp3.*

open class BaseService {

    protected val gson = Gson()
    private val client = OkHttpClient()

    /**
     * @param requestUrl 请求Api地址
     * @param key 请求的Key def=null
     * @param cookie 请求头cookie def=null
     * @param params 请求附带参数
     */
    fun request(requestUrl:String,key:String?,vararg params:Pair<String,String>):String{
        val url = StringBuffer()
        url.append("${RequestManager.getBaseUrl()}${requestUrl}?timestamp=${System.currentTimeMillis()}")

        params.forEach{
            url.append("&${it.first}=${it.second}")
        }

        if (key!=null){
            url.append("&key=${key}")
        }
        val request = Request.Builder().apply {
            val cookie = CookieStore.load(RequestManager.getHost())
            println(cookie)
            if (cookie!=null){
                addHeader("cookie", cookie)
            }
            url(url.toString())
        }.build()

        return client.newCall(request).execute().body.string()
    }
}