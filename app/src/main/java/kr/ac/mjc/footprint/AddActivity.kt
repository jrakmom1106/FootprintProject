package kr.ac.mjc.footprint

import android.app.Fragment
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class AddActivity:AppCompatActivity() {

    //AddFragment의 내용을 이곳으로 복사할 예정
    lateinit var loadingPb: ProgressBar
    lateinit var textTitleEt: EditText
    lateinit var contentEt: EditText
    lateinit var submitBtn: Button

    lateinit var auth: FirebaseAuth
    lateinit var storage: FirebaseStorage
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_add)

        loadingPb=findViewById(R.id.loading_pb)
        textTitleEt=findViewById(R.id.comtitle_et)
        contentEt = findViewById(R.id.content_et2)
        submitBtn=findViewById(R.id.submit_btn2)

        //추가
        auth= FirebaseAuth.getInstance()
        storage= FirebaseStorage.getInstance()
        firestore= FirebaseFirestore.getInstance()

        submitBtn.setOnClickListener {
            var title = textTitleEt.text.toString()
            var content = contentEt.text.toString()


            if(title.length==0){
                Toast.makeText(this,"제목을 입력해주세요", Toast.LENGTH_SHORT).show() //아무것도 안입력시
                return@setOnClickListener
            }

            startLoading()
            var post = Post(title,content,auth.currentUser?.email!!)//
            firestore.collection("Post")//edit
                .document(auth.currentUser?.uid!!).set(post)
                .addOnSuccessListener {
                    endLoading()
                    clear()
                    this.finish()
                }
        }

    }

    fun clear(){
        textTitleEt.text.clear()
        contentEt.text.clear()
    }

    fun startLoading(){
        loadingPb.visibility = VISIBLE
    }

    fun endLoading() {
        loadingPb.visibility = GONE
    }

}