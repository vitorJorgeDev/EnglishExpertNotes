package com.vitorjorge.englishexpertnotes.englishexpertnotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.facebook.AccessToken
import com.google.firebase.auth.FirebaseAuth



class SplashScreen : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 3500L
    lateinit var ivLogo:ImageView
    private var mAuth: FirebaseAuth? = null
    private var currentToken: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        ivLogo = findViewById(R.id.ivLogo)


        mAuth = FirebaseAuth.getInstance();
        currentToken = AccessToken.isCurrentAccessTokenActive()

        carregar()

    }

    fun carregar(){
        var anim = AnimationUtils.loadAnimation(this,R.anim.splashscreentransiction)
        anim.reset()
        ivLogo!!.clearAnimation()
        ivLogo!!.startAnimation(anim)
        Handler().postDelayed({
            ivLogo.alpha = 0.0F

            if (currentToken){
                nextActivity(NavigationActivity())
            }else{
                var user = mAuth?.currentUser

                if (user == null) {
                    nextActivity(MainActivity())
                } else {
                    nextActivity(NavigationActivity())
                }
            }

        }, SPLASH_DISPLAY_LENGTH)
    }

    fun nextActivity(context: Context){
        val intent = Intent(this, context::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        this.finish()
    }

}
