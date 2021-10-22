package kr.ac.mjc.footprint

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import de.hdodenhof.circleimageview.CircleImageView


class NoteFragment:Fragment(), NoteAdapter.OnItemClickListener {
    lateinit var profileIv: CircleImageView
    lateinit var nameTv: TextView
    lateinit var noteRv: RecyclerView

    lateinit var auth: FirebaseAuth //로그인한 사용자
    lateinit var firestore: FirebaseFirestore

    //lateinit var postList:ArrayList<Post> //이후 수업떄 가져옴
    lateinit var noteAdapter:NoteAdapter
    lateinit var postList2:ArrayList<Post2>//

    lateinit var income_text: TextView
    lateinit var exp_text: TextView
    lateinit var fab:FloatingActionButton

    val REQ_CHANGE_PROFILE = 2000

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        var view = inflater.inflate(R.layout.fragment_note,container, false)

        noteRv = view.findViewById(R.id.note_rv)
        fab = view.findViewById(R.id.fab)

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        //postList = ArrayList<Post>()
        postList2 = ArrayList<Post2>()//
        noteAdapter = NoteAdapter(requireActivity(),postList2)

        noteRv.adapter = noteAdapter
        noteRv.layoutManager = LinearLayoutManager(activity)

        // 리스트뷰 재호출 이후 각 UI 초기화.
        profileIv = view.findViewById(R.id.profile_iv)
        nameTv = view.findViewById(R.id.name_tv)
        income_text = view.findViewById(R.id.income_tv)
        exp_text = view.findViewById(R.id.exp_tv)

        fab.setOnClickListener {
            var intent = Intent(activity,AddActivity::class.java)
            startActivity(intent)
        }

        firestore.collection("Post2") //홈 게시글 올릴 때 파이어스토어의 Post 에서 가져온다.
            .orderBy("uploadDate", Query.Direction.ASCENDING)
            .whereEqualTo("userId",auth.currentUser?.email)
            .addSnapshotListener { value, error ->
                if(value!=null){
                    for(dc in value.documentChanges) {
                        //생성된 것에만 넣어준다
                        if (dc.type == DocumentChange.Type.ADDED) {
                            var post = dc.document.toObject(Post2::class.java)
                            post.id = dc.document.id
                            postList2.add(/*0,*/post) //0번째에 넣겠다.
                        }
                    }
                    noteAdapter.notifyDataSetChanged()
                }

            }

        updateProfile()
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
                noteAdapter.notifyDataSetChanged()
            }
    }

    override fun onItemClick(post: Post2) {
        val intent2 = Intent(activity,DetailActivity::class.java)
        intent2.putExtra("id",post.id)
        startActivity(intent2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQ_CHANGE_PROFILE && resultCode == RESULT_OK){
            updateProfile()
        }
    }

}