package otus.homework.coroutines

import com.google.gson.annotations.SerializedName

data class Arguments(
    val fact: Fact? = null,
    val url: CatImage? = null
)

data class Fact(
    @field:SerializedName("createdAt")
    val createdAt: String,
    @field:SerializedName("deleted")
    val deleted: Boolean,
    @field:SerializedName("_id")
    val id: String,
    @field:SerializedName("text")
    val text: String,
    @field:SerializedName("source")
    val source: String,
    @field:SerializedName("used")
    val used: Boolean,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("user")
    val user: String,
    @field:SerializedName("updatedAt")
    val updatedAt: String
)

data class CatImage(
    @field:SerializedName("file")
    val image: String
)