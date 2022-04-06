package com.yandex.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.Button
import com.yandex.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    enum class Action {
        NULL, PRODUCT, SUBTRACT, MULTIPLY, DIVIDE, RESULT
    }

    private var current = "0";
    private var value1 : Float? = 0f;
    private var isFloat = false
    private var action = Action.NULL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.e("MainActiviy", "OnCreate")


        binding.button0.setOnClickListener(this)
        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.button5.setOnClickListener(this)
        binding.button6.setOnClickListener(this)
        binding.button7.setOnClickListener(this)
        binding.button8.setOnClickListener(this)
        binding.button9.setOnClickListener(this)
        binding.buttonDot.setOnClickListener(this)



    }

    override fun onClick(v: View?) {
        if(action == Action.RESULT) {
            action = Action.NULL
            value1 = 0f
            current = ""
        }

        if(current == "0")
            current = ""

        when(v?.id){
            R.id.button_0 -> {
                current += "0"
            }
            R.id.button_1 -> {
                current += "1"
            }
            R.id.button_2 -> {
                current += "2"
            }
            R.id.button_3 -> {
                current += "3"
            }
            R.id.button_4 -> {
                current += "4"
            }
            R.id.button_5 -> {
                current += "5"
            }
            R.id.button_6 -> {
                current += "6"
            }
            R.id.button_7 -> {
                current += "7"
            }
            R.id.button_8 -> {
                current += "8"
            }
            R.id.button_9 -> {
                current += "9"
            }
            R.id.button_dot -> {

                if (!isFloat)
                {
                    current += if(current.isNotEmpty()) {
                        "."
                    } else
                        "0."
                    isFloat = true
                }
            }
        }
        if (current == "")
            current = "0"
        binding.Summary.text = current
    }

    fun onAction(view: View) {

        if(current.endsWith(".")) {
            current.dropLast(1)
        }

        when (action) {
            Action.NULL -> {
                value1 = current.toFloat()
                clear()

                when(view.id){
                    R.id.button_plus -> {
                        action = Action.PRODUCT
                    }
                    R.id.button_minus -> {
                        action = Action.SUBTRACT
                    }
                    R.id.button_mult -> {
                        action = Action.MULTIPLY
                    }
                    R.id.button_div -> {
                        action = Action.DIVIDE
                    }
                }
            }
            Action.RESULT -> {
                when(view.id){
                    R.id.button_plus -> {
                        action = Action.PRODUCT
                    }
                    R.id.button_minus -> {
                        action = Action.SUBTRACT
                    }
                    R.id.button_mult -> {
                        action = Action.MULTIPLY
                    }
                    R.id.button_div -> {
                        action = Action.DIVIDE
                    }
                }
            }
            else -> {
                if (current != "0") {
                    val value2 = current.toFloat()
                    clear()

                    binding.Summary.text = getResult(value2).toString()

                    when(view.id){
                        R.id.button_plus -> {
                            action = Action.PRODUCT
                        }
                        R.id.button_minus -> {
                            action = Action.SUBTRACT
                        }
                        R.id.button_mult -> {
                            action = Action.MULTIPLY
                        }
                        R.id.button_div -> {
                            action = Action.DIVIDE
                        }
                    }
                }
            }
        }
    }

    fun onErase(view: View) {

        if (action == Action.RESULT) {
            action = Action.NULL
            value1 = 0f
            clear()
        }
        else {
            current = if(current.length > 1 ) {
                current.dropLast(1)
            } else
                "0"
        }

        if(!current.contains(".")) {
            isFloat = false
        }

        binding.Summary.text = current
    }

    fun onResult(view: View) {

        if(value1 != 0f) {
            val value2 = current.toFloat()
            clear()

            val result = getResult(value2).toString()
            binding.Summary.text = result
            binding.text2.text =  result
            action = Action.RESULT
        }
    }

    private fun getResult(value2: Float): Float {
        var result = 0f

        when(action) {
            Action.PRODUCT -> {
                result = value1!! + value2
                value1 = result
            }
            Action.SUBTRACT -> {
                result = value1!! - value2
                value1 = result
            }
            Action.MULTIPLY -> {
                result = value1!! * value2
                value1 = result
            }
            Action.DIVIDE -> {
                result = value1!! / value2
                value1 = result
            }
            else -> {
                Log.e("Action", action.toString())
            }
        }
        return result
    }

    private fun clear() {
        current = "0"
        isFloat = false
    }
}