package com.vitorjorge.englishexpertnotes.englishexpertnotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.regex.Pattern


class SignupActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var confirmPasswordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()
        emailEditText = (findViewById(R.id.edittextemail) as EditText)
        passwordEditText = (findViewById(R.id.edittextpassword) as EditText)
        confirmPasswordEditText = (findViewById(R.id.edittextcheckpassword) as EditText)

        buttonsave.setOnClickListener {
            val email = emailEditText?.text.toString().trim{ it <= ' '}
            val password = passwordEditText?.text.toString().trim { it <= ' ' }
            val confirmpassword = confirmPasswordEditText?.text.toString().trim { it <= ' ' }

            if (!email.isEmpty()){
                val isEmail = isValidEmailID(email)

                if (isEmail){

                    if (!password.isEmpty()){

                        if (password.length >= 6) {
                            if (password == confirmpassword){

                                Log.d("SIGNUP", "OK pode cadastrar" )
                                createAcc(email, password)

                            }else{
                                val toast = Toast.makeText(applicationContext,
                                        R.string.message_password_different_signup, Toast.LENGTH_LONG)
                                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                                toast.show()
                            }
                        }else{
                            val toast = Toast.makeText(applicationContext,
                                    R.string.message_password_less, Toast.LENGTH_LONG)
                            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                            toast.show()
                        }
                    }else{

                        val toast = Toast.makeText(applicationContext,
                                R.string.message_password_is_empty_sigup, Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                        toast.show()
                    }
                }else{

                    val toast = Toast.makeText(applicationContext,
                            R.string.message_email_wrong_format_signup, Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                }

            }else{

                val toast = Toast.makeText(applicationContext,
                        R.string.message_email_empty_signup, Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
            }
        }

    }

    fun createAcc(email: String, password: String){

        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK

                val toast = Toast.makeText(applicationContext,
                        R.string.message_signup_successfully, Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
                this.finish()

            } else {
                // Registration Error
                val toast = Toast.makeText(applicationContext,
                        R.string.message_signup_error, Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
            }
        }
    }

    private fun isValidEmailID(email: String): Boolean {
        val PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern = Pattern.compile(PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
