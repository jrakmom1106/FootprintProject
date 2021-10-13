package kr.ac.mjc.footprint

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class HomeFragment: Fragment() {

    lateinit var fab: Button


    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val v: View = inflater.inflate(R.layout.fragment_home, container, false)
        fab = v.findViewById(R.id.fab)



        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            var intent = Intent(activity,MapActivity::class.java)
            startActivity(intent)
        }


//        postList = ArrayList<Post>()
//        addAdapter = AddAdapter(activity!!, postList)
    }
}