package com.solis.dummydictionary.network.dto

import com.google.gson.annotations.SerializedName
import com.solis.dummydictionary.data.model.Word

data class WordsResponse (
    @SerializedName ("count")
    val count:Int,
    @SerializedName ("words")
    val words:List<Word>
    )