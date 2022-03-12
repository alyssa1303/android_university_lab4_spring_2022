package com.codepath.recyclerviewlab

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.recyclerviewlab.models.Article

private const val TAG = "ArticleAdapter"

class ArticleResultsRecyclerViewAdapter(private val articles: List<Article>): RecyclerView.Adapter<ArticleResultsRecyclerViewAdapter.ViewHolder>()  {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvPubDate = itemView.findViewById<TextView>(R.id.article_pub_date)
        val tvHeadline = itemView.findViewById<TextView>(R.id.article_headline)
        val tvSnippet = itemView.findViewById<TextView>(R.id.article_snippet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // Inflate the custom layout
        val articleView = inflater.inflate(R.layout.fragment_article_result, parent, false)

        Log.i(TAG, "onCreateViewHolder")
        // Return a new holder instance
        return ViewHolder(articleView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = articles.get(position)

        holder.tvPubDate.text = article.publishDate
        holder.tvHeadline.text = article.headline.toString()
        holder.tvSnippet.text = article.snippet
    }

}