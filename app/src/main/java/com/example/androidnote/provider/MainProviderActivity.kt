package com.example.androidnote.provider

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import com.example.androidnote.R
import kotlinx.android.synthetic.main.activity_main_provider.*
import java.security.Permission

class MainProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_provider)


        btnReadContact.setOnClickListener {
            requestPermission()
        }
    }


    private fun readContacts() {
        //projection指定查询的列名  selection约束条件 selectionArgs给约束条件的占位符(?)提供具体的值
        //注：刚开始写的时候在selection中and和or的后面没有加空格 这会报sql语法错误 它们是一个关键字最好和前后用空格分开 否则会跟和筛选条件连在一起 这样就造成语法错误了
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}=? and ${ContactsContract.CommonDataKinds.Phone.NUMBER}!=147258 or ${ContactsContract.CommonDataKinds.Phone.NUMBER}=?",
            arrayOf("测试", "123456"), null
        )?.apply {
            while (moveToNext()) {
                Log.e(
                    "---------------->name",
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                )
                Log.e(
                    "---------------->num",
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                )
            }
            close()
        }

    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            request()
        } else {
            readContacts()
        }
    }

    private fun request() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1001)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readContacts()
        } else {
            Toast.makeText(this, "为获取读取联系人权限", Toast.LENGTH_SHORT).show()
        }
    }
}