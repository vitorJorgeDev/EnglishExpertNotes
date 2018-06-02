package com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vitorjorge.englishexpertnotes.englishexpertnotes.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class mapFragment : Fragment(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    var mMapView: MapView? = null
    var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_map, container, false)


        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMapView = view.findViewById(R.id.map);
        
        if (mMapView != null){
            mMapView!!.onCreate(null)
            mMapView!!.onResume()
            mMapView!!.getMapAsync(this)
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        val fiap = LatLng(-23.563950, -46.652645)
        val masp = LatLng(-23.561089, -46.655882)
        val gab = LatLng(-23.565089, -46.651882)

        mMap.addMarker(MarkerOptions().position(fiap).title("João"))
        mMap.addMarker(MarkerOptions().position(masp).title("Bruno"))
        mMap.addMarker(MarkerOptions().position(gab).title("Gab"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiap, 15.0f))


    }
    
}






