package com.lqd.androidpractice.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.lqd.androidpractice.R
import java.util.logging.Logger


class TestXX():Fragment(){}

// :运算符指明继承关系
class LoginFragment : Fragment() {

    private lateinit var userEditText:EditText

    var index: Int

    init {
        index = 11

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userEditText = view.findViewById(R.id.editText1)
        Logger.getLogger(TAG).info("onViewCreated")

        //单一抽象方法转换
        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {

        }



    }

    //伴生对象
    companion object {
        const val TAG = "LoginFragmen"
    }

    //属性委托
}

class LoginViewModel{}