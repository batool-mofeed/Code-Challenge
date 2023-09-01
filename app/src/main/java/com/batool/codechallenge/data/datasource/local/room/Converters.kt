package com.batool.codechallenge.data.datasource.local.room

import androidx.room.TypeConverter
import com.batool.codechallenge.data.datasource.remote.responsemodel.Media
import com.batool.codechallenge.data.datasource.remote.responsemodel.MetaData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created By Batool Mofeed on 9/1/2023.
 **/
class Converters {

    @TypeConverter
    fun mediaToString(media: List<Media>): String {
        return Gson().toJson(media)
    }

    @TypeConverter
    fun stringToMedia(value: String): List<Media> {
        val listType = object : TypeToken<List<Media>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun metadataToString(metaData: List<MetaData>): String {
        return Gson().toJson(metaData)
    }

    @TypeConverter
    fun stringToMetaData(value: String): MetaData {
        val listType = object : TypeToken<List<MetaData>>() {}.type
        return Gson().fromJson(value, listType)
    }
}