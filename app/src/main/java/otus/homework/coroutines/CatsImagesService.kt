package otus.homework.coroutines

import retrofit2.http.GET

interface CatsImagesService {

    @GET("/meow")
    suspend fun getCatImage() : CatImage
}