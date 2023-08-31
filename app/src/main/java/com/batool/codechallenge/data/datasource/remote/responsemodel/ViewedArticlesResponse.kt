package com.batool.codechallenge.data.datasource.remote.responsemodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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

@JsonClass(generateAdapter = true)
data class Article(
    val id: Long,
    val title:String?="",
    val updated: String? = "",
    var calculatedDate: String? = null,
    val media: List<Media>? = null
)

@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "media-metadata")
    val metaData: List<MetaData>? = null
)

@JsonClass(generateAdapter = true)
data class MetaData(
    val url: String? = ""
)