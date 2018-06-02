package com.vitorjorge.englishexpertnotes.englishexpertnotes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments.CadastroFragment
import com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments.ListaFragment
import com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments.SobreFragment
import com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments.mapFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_lista -> {
                changeFragment(ListaFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cadastrar-> {
                changeFragment(CadastroFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                //finish()
                changeFragment(mapFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sobre -> {
                //finish()
                changeFragment(SobreFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sair -> {
                //finish()
                LoginManager.getInstance().logOut()
                FirebaseAuth.getInstance().signOut();
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                return@OnNavigationItemSelectedListener true
            }

        }

        false
    }

    fun changeFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        changeFragment(ListaFragment())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
