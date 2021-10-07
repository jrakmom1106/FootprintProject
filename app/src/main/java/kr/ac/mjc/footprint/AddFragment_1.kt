package kr.ac.mjc.footprint

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView


class AddFragment_1:Fragment() {
    lateinit var profileIv: CircleImageView
    lateinit var nameTv: TextView
    lateinit var diaryRv: RecyclerView
    lateinit var auth: FirebaseAuth //로그인한 사용자
    lateinit var firestore: FirebaseFirestore

    lateinit var postList:ArrayList<Post> //이후 수업떄 가져옴
   //lateinit var addAdapter:AddAdapter

    lateinit var income_text: TextView
    lateinit var exp_text: TextView
    lateinit var fab:FloatingActionButton

    val REQ_CHAGE_PROFILE = 2000

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        var view = inflater.inflate(R.layout.fragment_add_1,container, false)

        profileIv = view.findViewById(R.id.profile_iv)
        nameTv = view.findViewById(R.id.name_tv)
        diaryRv = view.findViewById(R.id.diary_rv)

        income_text = view.findViewById(R.id.income_tv)
        exp_text = view.findViewById(R.id.exp_tv)
        fab = view.findViewById(R.id.fab)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            var intent = Intent(activity,AddActivity::class.java)
            startActivity(intent)
        }
        updateProfile()

//        postList = ArrayList<Post>()
//        addAdapter = AddAdapter(activity!!, postList)
    }

    fun updateProfile(){
        firestore.collection("User").document(auth.currentUser?.email!!).get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    //성공적으로 가져왔을 때
                    var user=it.result?.toObject(User::class.java)
                    //이렇게 가져온 정보를 텍스트뷰와 프로필에 넣어준다.
                    //여기서 글라이드가 필요하니 추가할 것
                    nameTv.text=user?.name
                    income_text.text=user?.profiletext
                    exp_text.text=user?.profile_text2

                    if(user?.profileUrl!=null) {
                        Glide.with(profileIv).load(user?.profileUrl).into(profileIv)
                    }
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQ_CHAGE_PROFILE && resultCode == RESULT_OK){
            updateProfile()
        }
    }


}