package kr.ac.mjc.footprint

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Year

class NoteAdapter(var context: Context, var postList:ArrayList<Post>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        // item_add
        var titleTv: TextView = itemView.findViewById(R.id.title_tv)
        var textTv2: TextView =itemView.findViewById(R.id.text_tv2)
        var incomeTv2: TextView = itemView.findViewById(R.id.income_tv2)
        var expTv2: TextView = itemView.findViewById(R.id.exp_tv2)
        var dateItem: TextView = itemView.findViewById(R.id.item_date)

        fun bind(post:Post){

            titleTv.text = post.textTitleEt
            textTv2.text = post.contentEt
            incomeTv2.text = post.incomeEt
            expTv2.text = post.expEt
            dateItem.text = post.uploadDate.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        //레이아웃을 가져오는 과정
        var view = LayoutInflater.from(context).inflate(R.layout.item_add,parent,false)
        //가져온 레이아웃을 뷰홀더에 리턴
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
        //몇개의 레이아웃을 보내냐..
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        //바인드로 호출해주면 된다.
        var post=postList[position]
        holder.bind(post)
    }

}