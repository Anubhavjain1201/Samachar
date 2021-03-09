package com.example.samachar

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.samachar.news.SamacharAdapter
import com.example.samachar.network.Samachar
import com.example.samachar.news.SamacharStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Samachar>?) {

    val adapter = recyclerView.adapter as SamacharAdapter

    data?.let {
        adapter.submitList(data)
    }
}

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {

        val imgUri = it.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("title")
fun bindTitle(textView: TextView, text: String?) {

    text?.let {
        textView.text = text.toString()
    }
}

@BindingAdapter("author")
fun bindAuthor(textView: TextView, author: String?) {

    author?.let {
        textView.text = "$author"
    }
}

@BindingAdapter("samacharStatus")
fun bindStatus(statusImageView: ImageView, status: SamacharStatus?) {

    when (status) {

        SamacharStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        SamacharStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        SamacharStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}