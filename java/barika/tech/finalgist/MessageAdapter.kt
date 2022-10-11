package barika.tech.finalgist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val messageList:MutableList<Message> = mutableListOf()
    val SENT_LAYOUT = 1
    val RECEIVED_LAYOUT = 2

     inner class SentViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtSentMessage:TextView = view.findViewById(R.id.sent_message)
    }

    inner class ReceivedViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtReceivedMessage:TextView = view.findViewById(R.id.received_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.sent_layout, parent, false)
            return SentViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.received_layout, parent, false)
            return ReceivedViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ReceivedViewHolder){
            val message = messageList[position]
            holder.txtReceivedMessage.text = message.message
        }else if(holder is SentViewHolder){
            val message = messageList[position]
            holder.txtSentMessage.text = message.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid == message.senderId){
            //If the last message was sent by me use sent layout. position here is automatically the last index because these are built-in functions
            return SENT_LAYOUT
        }else {
            return RECEIVED_LAYOUT
        }
    }

    override fun getItemCount(): Int = messageList.size
}