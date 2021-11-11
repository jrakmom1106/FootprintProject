package kr.ac.mjc.footprint

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView

class AddAdapter(var context: Context, var postList:ArrayList<Post>): RecyclerView.Adapter<AddAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener?=null

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView!!){

        var titleTv: TextView = itemView.findViewById(R.id.title_tv)
        var textTv2: TextView =itemView.findViewById(R.id.text_tv2)
        var dateItem: TextView = itemView.findViewById(R.id.item_date)

        var writerTv: TextView = itemView.findViewById(R.id.writer_tv)
        var profileIv3: CircleImageView = itemView.findViewById(R.id.profile_iv3)

        var user=User()

        fun bind(post:Post){

            titleTv.text = post.textTitleEt
            textTv2.text = post.contentEt
            dateItem.text = post.uploadDate.toString()

            var firestore = FirebaseFirestore.getInstance()
            firestore.collection("User").document(post.userId).get()
                .addOnSuccessListener {
                    var user = it.toObject(User::class.java)
                    Glide.with(profileIv3).load(user?.profileUrl).into(profileIv3)
                    writerTv.text = user?.name
                }

            itemView.setOnClickListener {
                    onItemClickListener?.onItemClick(post)
                    val intent = Intent(itemView?.context, CommunityDetailActivity::class.java)
                    intent.putExtra("id",post.id)
                    startActivity(itemView?.context, intent, null)
            }

        }
    }

    interface OnItemClickListener{
        fun onItemClick(post:Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAdapter.ViewHolder {
        //레이아웃을 가져오는 과정
        var view = LayoutInflater.from(context).inflate(R.layout.item_community,parent,false)
        //가져온 레이아웃을 뷰홀더에 리턴
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
        //몇개의 레이아웃을 보내냐..
    }

    override fun onBindViewHolder(holder: AddAdapter.ViewHolder, position: Int) {
        //바인드로 호출해주면 된다.
        var post=postList[position]
        holder.bind(post)
    }

}