package com.lacourt.myapplication.domainmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.lacourt.myapplication.R
import com.lacourt.myapplication.dto.GenreXDTO
import com.lacourt.myapplication.dto.VideoDTO

data class Details(
    val backdrop_path: String?,
    val genres: List<GenreXDTO>?,
    val id: Int?,
    val overview: String?,
    val poster_path: String?,
    var release_date: String?,
    var runtime: Int?,
    val title: String?,
    val vote_average: Double?,
    val videos: ArrayList<VideoDTO>?
) {
    init {
        release_date = release_date?.subSequence(0, 4).toString()

        val trailer = getTrailer()
        videos?.clear()
        trailer?.let { videos?.add(it) }
    }

    fun getTrailer(): VideoDTO? {
        var trailer: VideoDTO? = null

        videos?.forEach { video ->
            val name = video.name?.toLowerCase()
            if (name != null) {
                if (name.contains("official trailer"))
                    trailer = video
            }
        }

        if (trailer == null) {
            videos?.forEach { video ->
                val name = video.name?.toLowerCase()
                if (name != null) {
                    if (name.contains("trailer"))
                        trailer = video
                }
            }
        }

        if (trailer == null)
            trailer = videos?.get(0)

        return trailer
    }
}

fun Details.openYoutube(context: Context?) {
    if (context != null) {
        if (!this.videos.isNullOrEmpty() && !this.videos[0].key.isNullOrEmpty()) {
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=${this.videos[0].key}")
            )
            context.startActivity(webIntent)
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.no_video_to_show),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
