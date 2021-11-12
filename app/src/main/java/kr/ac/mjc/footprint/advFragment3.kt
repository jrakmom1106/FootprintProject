package kr.ac.mjc.footprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class advFragment3 : Fragment() {

    lateinit var imageIv:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //layout을 가져와서 view 로 리턴
        var view = inflater.inflate(R.layout.frame_1, container, false)
        imageIv = view.findViewById(R.id.imageView1)
        return view

    }
}