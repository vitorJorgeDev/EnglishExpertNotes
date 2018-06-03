package com.vitorjorge.englishexpertnotes.englishexpertnotes


import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments.ListaFragment
import kotlinx.android.synthetic.main.note_row.view.*

class Adapter(val list: ArrayList<String>, val context: Context): RecyclerView.Adapter<CustomViewHolder>(){

    var localList = list

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflanter = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflanter.inflate(R.layout.note_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val listText = localList.get(position)
        holder?.view?.textView_notes?.text = listText

        holder?.view?.setOnClickListener(View.OnClickListener {

            // Mudar de intent
            val intent = Intent(context, UpDate::class.java)
            intent.putExtra("POSITION", ListaFragment().update(position))
            intent.putExtra("POSITIONLIST", position )
            intent.putExtra("ITEMDESCRIPTION", holder?.view?.textView_notes?.text)
            startActivity(context, intent, null)
        })
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}