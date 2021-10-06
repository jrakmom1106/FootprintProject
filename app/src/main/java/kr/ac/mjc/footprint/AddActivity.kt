package kr.ac.mjc.footprint

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AddActivity:AppCompatActivity() {

    //AddFragment의 내용을 이곳으로 복사할 예정
    lateinit var loadingPb: ProgressBar
    lateinit var imageIv: ImageView
    lateinit var textEt: EditText
    lateinit var submitBtn: Button

    lateinit var auth: FirebaseAuth
    lateinit var storage: FirebaseStorage
    lateinit var firestore: FirebaseFirestore

    lateinit var projectname: EditText
    lateinit var projectnum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        loadingPb=findViewById(R.id.loading_pb)
        textEt=findViewById(R.id.text_et)
        submitBtn=findViewById(R.id.submit_btn)

        //추가
        projectname=findViewById(R.id.project_name)
        projectnum=findViewById(R.id.project_num)

        auth= FirebaseAuth.getInstance()
        storage= FirebaseStorage.getInstance()
        firestore= FirebaseFirestore.getInstance()

    }

}