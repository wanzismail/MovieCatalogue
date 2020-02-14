package com.wanztudio.idcamp.moviecatalogue.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobito.tmmin.utils.OnItemClickListener
import com.mobito.tmmin.utils.addOnItemClickListener
import com.wanztudio.idcamp.catalogue.adapters.MovieAdapter
import com.wanztudio.idcamp.moviecatalogue.R
import com.wanztudio.idcamp.moviecatalogue.activities.DetailActivity
import com.wanztudio.idcamp.moviecatalogue.models.Movie
import com.wanztudio.idcamp.moviecatalogue.utils.Constants
import com.wanztudio.idcamp.moviecatalogue.utils.MovieProvider
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tvshow.*
import java.util.*

class TvShowFragment : Fragment() {

    private lateinit var listMovie: List<Movie>
    private lateinit var movieAdapter: MovieAdapter

    companion object {

        fun newInstance(): TvShowFragment {
            return TvShowFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_tvshow, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initViews()
    }

    private fun initData() {
        val language = Locale.getDefault().language.toString()
        listMovie = if (language.contentEquals("en")){
            MovieProvider.getTvShowsEnglishVersion()
        } else {
            MovieProvider.getTvShowsIndonesianVersion()
        }

        movieAdapter = MovieAdapter(requireContext(), listMovie)
    }

    private fun initViews(){
        rvTvShow.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(Constants.EXTRA_MOVIE, listMovie[position])
                    startActivity(intent)
                }
            })
        }.adapter = movieAdapter
    }
}