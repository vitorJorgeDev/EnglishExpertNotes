package com.vitorjorge.englishexpertnotes.englishexpertnotes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.ads.AdRequest.LOGTAG
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern






class MainActivity : AppCompatActivity() {

    //lateinit var ivLogo: ImageView
    private var mAuth: FirebaseAuth? = null
    var editor: SharedPreferences.Editor? = null
    var sharedPref: SharedPreferences? = null
    var googleStoreChecked = false


    protected lateinit var mCallbackManager: CallbackManager
    private val TAG = "FACELOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val emailEditText = (findViewById(R.id.editTextEmailLogin) as EditText)
        val passwordEditText = (findViewById(R.id.editTextPasswordLogin) as EditText)
        googleStoreChecked = checkGooglePlayServicesAvailable()


        mAuth = FirebaseAuth.getInstance()

        mCallbackManager = CallbackManager.Factory.create()
        val loginButton = findViewById(R.id.button_facebook_login) as LoginButton
        loginButton.setReadPermissions("email", "public_profile")

        sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val email = sharedPref?.getString("email", "")
        if (email != null){
            if (email != "") {
                emailEditText.setText(email)
            }
        }


        buttonsignup.setOnClickListener {

            if (googleStoreChecked){
                val intent = Intent(this, SignupActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }else{
                showAlert()
            }


        }

        // Login Firebase AUTH
        buttonLogin.setOnClickListener {

            if (googleStoreChecked){
                val email = emailEditText?.text.toString().trim{ it <= ' '}
                val password = passwordEditText?.text.toString().trim { it <= ' ' }
                checkEmailAndPasswordFormat(email, password)
            }else{
                showAlert()
            }

        }
        //

        if (googleStoreChecked){
            loginButton.isClickable = true
        }else{
            loginButton.isClickable = false
        }


        loginButton.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")

                nextActivity()
                // handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
                // ...
            }
        })

    }

        fun nextActivity(){
            val intent = Intent(this, NavigationActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            this.finish()
         }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun checkEmailAndPasswordFormat(email: String, password: String){

        if (!email.isEmpty()){
            val isEmail = isValidEmailID(email)

            if (isEmail){

                if (!password.isEmpty()){

                    if (password.length >= 6) {

                        //
                        sharedPref= getSharedPreferences("myPref", Context.MODE_PRIVATE);
                        editor= sharedPref!!.edit();
                        editor!!.putString("email", email)
                        editor!!.putString("password", password)
                        editor!!.commit()
                        //

                        signin(email, password)

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

    fun signin(email: String, password: String){

        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK

                Log.d("MAIN", "OK LOGIN" )
                nextActivity()
                this.finish()

            } else {
                Log.d("MAIN", "signInWithEmail:failure", task.getException())
                val message = task.getException()!!.message

                val toast = Toast.makeText(applicationContext,
                        message  , Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
            }
        }
    }

    private fun showAlert(){

        AlertDialog.Builder(this).setTitle(getString(R.string.title_alert_button_main))
                .setMessage(getString(R.string.title_message_main))
                .setPositiveButton(getString(R.string.title_ok_button_update), null)
                .show();
    }

    private fun isValidEmailID(email: String): Boolean {
        val PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern = Pattern.compile(PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun checkGooglePlayServicesAvailable(): Boolean {
        val status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(applicationContext)
        if (status == ConnectionResult.SUCCESS) {
            return true
        }

        Log.e(LOGTAG, "Google Play Services not available: " + GooglePlayServicesUtil.getErrorString(status))

        if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
            val errorDialog = GooglePlayServicesUtil.getErrorDialog(status, this, 1)
            errorDialog?.show()
        }

        return false
    }
}
