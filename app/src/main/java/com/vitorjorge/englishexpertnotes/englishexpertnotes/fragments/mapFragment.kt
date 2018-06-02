package com.vitorjorge.englishexpertnotes.englishexpertnotes.fragments



import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
private const val PERMISSION_REQUEST = 10
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

        val btn = view?.findViewById<Button>(R.id.buttonFindFriends)



        btn?.setOnClickListener {

            val bruno = LatLng(-23.561089, -46.655882)
            val gab = LatLng(-23.565089, -46.651882)
            val joao = LatLng(-23.555089, -46.641882)
            val felipe = LatLng(-23.559756, -46.658232)
            val bianca = LatLng(-23.558538, -46.662068)
            val fabiana = LatLng(-23.562742, -46.651121)
            val lucas = LatLng(-23.565166, -46.654163)
            val leandro = LatLng(-23.563900, -46.658352)

            mMap.addMarker(MarkerOptions().position(felipe).title("Felipe"))
            mMap.addMarker(MarkerOptions().position(bianca).title("Bianca"))
            mMap.addMarker(MarkerOptions().position(fabiana).title("Fabiana"))
            mMap.addMarker(MarkerOptions().position(lucas).title("Lucas"))
            mMap.addMarker(MarkerOptions().position(leandro).title("leandro"))
            mMap.addMarker(MarkerOptions().position(joao).title("João"))
            mMap.addMarker(MarkerOptions().position(bruno).title("Bruno"))
            mMap.addMarker(MarkerOptions().position(gab).title("Gab"))

        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        val Jorge = LatLng(-23.562636, -46.654263)

        mMap.addMarker(MarkerOptions().position(Jorge).title("Jorge"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jorge, 14.5f))

    }
}







