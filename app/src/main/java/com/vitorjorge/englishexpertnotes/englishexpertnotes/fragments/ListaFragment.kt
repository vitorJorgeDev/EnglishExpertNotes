package com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vitorjorge.englishexpertnotes.englishexpertnotes.Adapter
import com.vitorjorge.englishexpertnotes.englishexpertnotes.Notes
import com.vitorjorge.englishexpertnotes.englishexpertnotes.NotesDAO
import com.vitorjorge.englishexpertnotes.englishexpertnotes.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListaFragment : Fragment()  {

    val notesDAO = NotesDAO().findAll()

    private var mContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycleView_main) as RecyclerView;
        val message = view?.findViewById<TextView>(R.id.textViewMessageList)
        var notesItems: ArrayList<String> = ArrayList<String>();

        val notes: List<Notes> = notesDAO

        for(i in notes){
            val note = i.name
            notesItems.add(note)
        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = Adapter(notesItems, context!!)

        if (notesItems.isEmpty()) {
            message.alpha = 1.0f
        }else{
            message.alpha = 0.0f
        }

        return view
    }

    fun update(item: Int): Int{
        return notesDAO.get(item).id.toInt()
    }


    fun removeItem(item: Int){
        NotesDAO.delete(notesDAO.get(item))
    }
}
