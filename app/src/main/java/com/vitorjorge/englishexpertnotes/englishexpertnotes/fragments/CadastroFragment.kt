package com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.vitorjorge.englishexpertnotes.englishexpertnotes.Notes
import com.vitorjorge.englishexpertnotes.englishexpertnotes.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CadastroFragment : Fragment(), View.OnClickListener {

    // Properties
    private var _editTextInventoryName: EditText? = null
    private var _buttonSaveInventory: Button? = null
    private var _listViewInventories: ListView? = null
    private var inventoryItems: ArrayList<String>? = null
    private var inventoryItemsAdapter: ArrayAdapter<*>? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSaveInventory -> {
                saveInventory()
            }

            else -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cadastro, container,false)
        _editTextInventoryName =  view.findViewById(R.id.editTextInventoryName);
        _buttonSaveInventory = view.findViewById(R.id.buttonSaveInventory);
        inventoryItems = ArrayList()
        _buttonSaveInventory!!.setOnClickListener(this);

        return view
    }

    private fun saveInventory() {
        val name = _editTextInventoryName!!.text.toString().trim { it <= ' ' }
        _editTextInventoryName?.setText("")

        if (name.equals("", ignoreCase = true)) {
            return
        }

        val inventory =  Notes()
        inventory.name = name
        inventory.save()
        inventoryItems!!.add(name)

    }

}
