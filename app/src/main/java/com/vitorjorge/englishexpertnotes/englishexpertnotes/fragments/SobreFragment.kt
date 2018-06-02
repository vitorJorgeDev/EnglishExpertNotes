package com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.vitorjorge.englishexpertnotes.englishexpertnotes.BuildConfig
import com.vitorjorge.englishexpertnotes.englishexpertnotes.R




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SobreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_sobre, container, false)
        val tetxViewVersion = view.findViewById<TextView>(R.id.textViewVersion);
        val btnCall = view.findViewById<Button>(R.id.buttonCall)

        val versionName = BuildConfig.VERSION_NAME
        tetxViewVersion.text = versionName

        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + "1101234567")
            startActivity(intent)
        }

        return view
    }
}
