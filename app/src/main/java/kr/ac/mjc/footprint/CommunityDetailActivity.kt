package kr.ac.mjc.footprint

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView

class CommunityDetailActivity:AppCompatActivity() {
    lateinit var title: TextView
    lateinit var profile: CircleImageView
    lateinit var nickname: TextView
    lateinit var content: TextView

    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        title = findViewById(R.id.com_title)
        profile = findViewById(R.id.user_iv)
        nickname = findViewById(R.id.username_tv)
        content = findViewById(R.id.com_content)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        var id = intent.getStringExtra("id")

        if(id!=null) {
            firestore.collection("Post").document(id).get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        //성공적으로 가져왔을 때
                        var post = it.result?.toObject(Post::class.java)
                        //이렇게 가져온 정보를 텍스트뷰와 프로필에 넣어준다.

                        title.text = post?.textTitleEt
                        content.text = post?.contentEt

                    }
                }

            // 수정 예정.
            firestore.collection("User").document(auth.currentUser?.email!!).get()
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        //성공적으로 가져왔을 때
                        var user=it.result?.toObject(User::class.java)
                        //이렇게 가져온 정보를 텍스트뷰와 프로필에 넣어준다.
                        //여기서 글라이드가 필요하니 추가할 것
                        nickname.text = user?.name

                        if(user?.profileUrl!=null) {
                            Glide.with(profile).load(user?.profileUrl).into(profile)
                        }
                    }
                }
        }

    }
}