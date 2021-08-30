package com.example.androidnote.media

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.androidnote.R
import kotlinx.android.synthetic.main.activity_main_media.*
import java.io.File

class MainMediaActivity : AppCompatActivity() {
    private lateinit var imageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_media)

        val imageFile = File(getExternalFilesDir(null),"output_Image.jpg")
        if (imageFile.exists()){
            imageFile.delete()
        }
        imageFile.createNewFile()
        Log.e("--------->cacheDir",externalCacheDir.toString())
        Log.e("--------->file path1",imageFile.absolutePath)
        Log.e("--------->file path2",imageFile.path)

        imageUri = FileProvider.getUriForFile(this,"com.example.androidnote.fileprovider",imageFile)
        Log.e("---------->imageUri",imageUri.toString())
        Log.e("---------->imageUri2",imageUri.authority.toString())
        Log.e("---------->imageUri3",imageUri.path.toString())
        Log.e("---------->contacts",ContactsContract.CommonDataKinds.Phone.CONTENT_URI.toString())


        Log.e("---------->uri2",Uri.fromFile(imageFile).toString())
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
        startActivityForResult(intent,1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == RESULT_OK){
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
            imageView.setImageBitmap(bitmap)
        }
    }

}