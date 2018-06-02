package com.vitorjorge.englishexpertnotes.englishexpertnotes

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments.ListaFragment
import kotlinx.android.synthetic.main.activity_up_date.*

class UpDate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_date)

        val bundle = intent.extras
        val position = bundle!!.getInt("POSITION")
        val positionList = bundle!!.getInt("POSITIONLIST")
        val itemDescription = bundle!!.getString("ITEMDESCRIPTION")
        val share = findViewById<Button>(R.id.buttonShare)
        editTextNotesName.setText(itemDescription)

        buttonUpdateNotes.setOnClickListener {
            val newItem = editTextNotesName!!.text.toString().trim { it <= ' ' }
            NotesDAO.update(position,  newItem)
            val intent = Intent(this, NavigationActivity::class.java)
            ContextCompat.startActivity(this, intent, null)
            this.finish()
            Log.d("TAG", position.toString())
        }

        buttonCancel.setOnClickListener {
            this.finish()
        }

        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, itemDescription)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)

        }

        buttonDelete.setOnClickListener {
            AlertDialog.Builder(this).setTitle(getString(R.string.title_delete_button_update))
                    .setMessage(getString(R.string.title_message_update) + "  " + itemDescription)
                    .setNegativeButton(getString(R.string.title_cancel_button_update), null)
                    .setPositiveButton(getString(R.string.title_ok_button_update), DialogInterface.OnClickListener { dialog, whichButton ->
                        Toast.makeText(this,    itemDescription + "  "+ getString(R.string.message_toast_update), Toast.LENGTH_SHORT).show()
                        ListaFragment().removeItem(positionList)
                        val intent = Intent(this, NavigationActivity::class.java)
                        ContextCompat.startActivity(this, intent, null)
                        this.finish()
                    })
                    .show();
        }
    }
}
