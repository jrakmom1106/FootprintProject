package kr.ac.mjc.footprint

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*

class DetailActivity:AppCompatActivity() {
    lateinit var detailTitle:TextView
    lateinit var detailDiary:TextView
    lateinit var detailMemo:TextView
    lateinit var income:TextView
    lateinit var exp:TextView

    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore
    //private var uid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        income = findViewById(R.id.detail_income)
        exp = findViewById(R.id.detail_exp)

        detailTitle = findViewById(R.id.detail_title)
        detailDiary = findViewById(R.id.detail_diary)
        detailMemo = findViewById(R.id.detail_memo)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        var id = intent.getStringExtra("id")

        if(id!=null) {
            firestore.collection("Post2").document(id).get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        //성공적으로 가져왔을 때
                        var post = it.result?.toObject(Post2::class.java)
                        //이렇게 가져온 정보를 텍스트뷰와 프로필에 넣어준다.

                        income.text = post?.incomeEt
                        exp.text = post?.expEt

                        detailTitle.text = post?.textTitleEt
                        detailDiary.text = post?.contentEt
                        detailMemo.text = post?.memoEt

                    }
                }
        }

    }
}