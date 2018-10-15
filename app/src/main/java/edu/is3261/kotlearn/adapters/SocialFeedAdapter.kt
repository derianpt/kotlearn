package edu.is3261.kotlearn.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.feed.SocialPost
import android.content.Intent
import android.net.Uri


class MyAdapter(var myDataSet: ArrayList<SocialPost>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(v: CardView) : RecyclerView.ViewHolder(v) {
        var postTitle: TextView = v.findViewById(R.id.post_title)
        var postAuthor: TextView = v.findViewById(R.id.post_author)
        var postTime: TextView = v.findViewById(R.id.post_time)
        var postURL: TextView = v.findViewById(R.id.post_url)
        var onClick = v.setOnClickListener {
            // open URL in browser
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(postURL.text.toString()))
            v.context.startActivity(browserIntent)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.feed_row, parent, false) as CardView
        return MyViewHolder(cardView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        var postDataToUse = myDataSet[position]
        holder.postTitle.text = postDataToUse.title
        holder.postAuthor.text = postDataToUse.author
        holder.postTime.text = postDataToUse.time
        holder.postURL.text = postDataToUse.url
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = myDataSet.size

    // Clean all elements of the recycler
    public fun clear(){
        myDataSet.removeAll(myDataSet)
        this.notifyDataSetChanged()
    }

    // Add a list of items
    public fun addAll(list: ArrayList<SocialPost>){
        myDataSet.addAll(list)
        this.notifyDataSetChanged()
    }
}
