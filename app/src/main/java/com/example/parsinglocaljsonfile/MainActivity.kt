package com.example.parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    /*
    put assets folder & JSON file in the right place
    create data class corresponding to JSON content
    use AssetManager to open the File, then get JSON string
    use Gson to parse JSON string to Kotlin object
     */
    lateinit var photosList:ArrayList<images>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photosList= arrayListOf()
        rv_main.adapter=RecyclerAdapter(this, photosList)
        rv_main.layoutManager=LinearLayoutManager(this)

        val jsonFileString= getJsonDataFromAsset("data.json")
        val gson=Gson()
        val listImageType= object : TypeToken<List<images>>(){}.type
        var images:List<images> = gson.fromJson(jsonFileString,listImageType)
        images.forEachIndexed { index, images -> photosList.add(images(images.title,images.url))}
        rv_main.adapter?.notifyDataSetChanged()
    }

    fun getJsonDataFromAsset (fileName:String):String?{
        val jsonString:String
        try {
            jsonString=applicationContext.assets.open(fileName).bufferedReader().use { it.readText()}
        }catch (ioException:IOException){
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}