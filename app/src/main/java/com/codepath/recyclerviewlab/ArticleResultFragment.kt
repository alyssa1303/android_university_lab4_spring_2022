package com.codepath.recyclerviewlab

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.recyclerviewlab.R.layout
import com.codepath.recyclerviewlab.models.Article
import com.codepath.recyclerviewlab.networking.CallbackResponse
import com.codepath.recyclerviewlab.networking.NYTimesApiClient

class ArticleResultFragment: Fragment() {
    private val client = NYTimesApiClient()
    private val articles = mutableListOf<Article>()
    private lateinit var progressSpinner: ContentLoadingProgressBar
    private lateinit var rvArticles: RecyclerView

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.action_search).actionView as SearchView
        item.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                loadNewArticlesByQuery(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(layout.fragment_article_result_list, container, false)
        rvArticles = view.findViewById(R.id.list)
        progressSpinner = view.findViewById(R.id.progress)

        val articleAdapter = ArticleResultsRecyclerViewAdapter(articles)
        rvArticles.adapter = articleAdapter
        rvArticles.layoutManager = LinearLayoutManager(requireContext())
        return inflater.inflate(layout.fragment_article_result_list, container, false)
    }

    private fun loadNewArticlesByQuery(query: String) {
        Log.d("ArticleResultFragment", "loading articles for query $query")
        Toast.makeText(context, "Loading articles for \'$query\'", Toast.LENGTH_SHORT).show()
        // TODO(Checkpoint 3): Implement this method to populate articles
        client.getArticlesByQuery(articles as CallbackResponse<List<Article>>, query)
    }

    private fun loadArticlesByPage(page: Int) {
        // TODO(Checkpoint 4): Implement this method to do infinite scroll
    }
}