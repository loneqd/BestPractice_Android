package com.lqd.androidpractice.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.lqd.androidpractice.R
import com.lqd.androidpractice.databinding.ActivityJetpackBinding
import com.lqd.androidpractice.provider.UsersProvider
import com.lqd.androidpractice.viewmodel.NameVMFactory
import com.lqd.androidpractice.viewmodel.NameViewModel
import com.lqd.base.activity.BaseActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class JetpackActivity : BaseActivity() {

    //ViewBinding 视图绑定
//    private val binding: ActivityJetpackBinding? = null


    private val nameViewModel: NameViewModel = NameVMFactory.create(NameViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_jetpack)
        //视图绑定
        val binding = DataBindingUtil.setContentView<ActivityJetpackBinding>(this, R.layout.activity_jetpack)


//        ActivityJetpackBinding.inflate(layoutInflater)


        binding?.btn?.setOnClickListener {
            Log.e("1111", "aaa")
            binding?.btn?.setText("sadsf")
            nameViewModel.currentName.value = "aaa" //修改数据后，会触发监听函数
        }

//        //livedata 数据绑定
        nameViewModel.currentName.observeForever(Observer {
            Log.e("2222", it)
        })

        //协程处理
        GlobalScope.async {
            delay(3000L)
            Log.e("1111", "bbbbb")
        }


    }

    fun onClickAddDetails(view: View?) {
        val values = ContentValues()
        values.put(UsersProvider.name, (findViewById<View>(R.id.txtName) as EditText).text.toString())
        contentResolver.insert(UsersProvider.CONTENT_URI, values)
        Toast.makeText(baseContext, "New Record Inserted", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("Range")
    fun onClickShowDetails(view: View?) {
        // Retrieve employee records
        val resultView = findViewById<View>(R.id.res) as TextView
        val cursor: Cursor? = contentResolver.query(
            Uri.parse("content://com.lqd.androidpractice.provider.UsersProvider/users"),
            null,
            null,
            null,
            null
        )
        if (cursor!!.moveToFirst()) {
            val strBuild = StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append(
                    """
    
    ${cursor!!.getString(cursor!!.getColumnIndex("id"))}-${cursor!!.getString(cursor.getColumnIndex("name"))}
    """.trimIndent()
                )
                cursor!!.moveToNext()
            }
            resultView.text = strBuild
        } else {
            resultView.text = "No Records Found"
        }
    }


}