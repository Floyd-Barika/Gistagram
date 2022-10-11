package barika.tech.finalgist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Context, val userList: MutableList<User>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view){
        val userName = view.findViewById<TextView>(R.id.user_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_preview, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = user.name

        holder.userName.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("NAME_KEY", user.name)
            intent.putExtra("UID_KEY", user.uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = userList.size
}