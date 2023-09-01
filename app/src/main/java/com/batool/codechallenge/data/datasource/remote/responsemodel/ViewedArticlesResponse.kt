package com.batool.codechallenge.data.datasource.remote.responsemodel

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created By Batool Mofeed on 8/31/2023.
 **/
@JsonClass(generateAdapter = true)
data class ViewedArticlesResponse(
    val status: String? = "",
    val copyright: String? = "",
    @Json(name = "num_results")
    val numResults: Int? = 0,
    val results: List<Article>? = emptyList(),
)

@Parcelize
@JsonClass(generateAdapter = true)
data class Article(
    val id: Long,
    val title: String? = "",
    val section: String? = "",
    val updated: String? = "",
    var calculatedDate: String? = null,
    val media: List<Media>? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "media-metadata")
    val metaData: List<MetaData>? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MetaData(
    val url: String? = ""
) : Parcelable