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

class NoteAddActivity:AppCompatActivity() {

    //AddFragment의 내용을 이곳으로 복사할 예정
    lateinit var loadingPb: ProgressBar
    lateinit var textTitleEt: EditText
    lateinit var contentEt: EditText
    lateinit var incomeEt: EditText
    lateinit var expEt: EditText
    lateinit var memoEt: EditText
    lateinit var submitBtn: Button

    lateinit var auth: FirebaseAuth
    lateinit var storage: FirebaseStorage
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        loadingPb=findViewById(R.id.loading_pb)
        textTitleEt=findViewById(R.id.texttitle_et)
        contentEt = findViewById(R.id.content_et)
        incomeEt = findViewById(R.id.income_et)
        expEt = findViewById(R.id.exp_et)
        memoEt = findViewById(R.id.memo_et)
        submitBtn=findViewById(R.id.submit_btn)

        //추가
        auth= FirebaseAuth.getInstance()
        storage= FirebaseStorage.getInstance()
        firestore= FirebaseFirestore.getInstance()

        submitBtn.setOnClickListener {
            var title = textTitleEt.text.toString()
            var content = contentEt.text.toString()
            var income = incomeEt.text.toString()
            var exp = expEt.text.toString()
            var memo = memoEt.text.toString()
            var uuid = UUID.randomUUID().toString()

            if(title.length==0){
                Toast.makeText(this,"제목을 입력해주세요", Toast.LENGTH_SHORT).show() //아무것도 안입력시
                return@setOnClickListener
            }

            startLoading()
            var post = Post2(uuid,title,content,income,exp,memo,auth.currentUser?.email!!)//
            firestore.collection("Post2")//edit
                .document(uuid).set(post)
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
        incomeEt.text.clear()
        expEt.text.clear()
        memoEt.text.clear()
    }

    fun startLoading(){
        loadingPb.visibility = VISIBLE
    }

    fun endLoading() {
        loadingPb.visibility = GONE
    }

}