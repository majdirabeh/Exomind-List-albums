package fr.dev.majdi.testexomind.network

import fr.dev.majdi.testexomind.model.Album
import fr.dev.majdi.testexomind.model.Picture
import fr.dev.majdi.testexomind.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
interface UserApi {

    @GET("/users/")
    fun getAllUsers(): Call<List<User>>

    @GET("/users/{id}/albums")
    fun getAlbumsByUser(
        @Path("id") id: Int,
        @Query("userId") userId: Int
    ): Call<List<Album>>

    @GET("/users/{id}/photos")
    fun getPicturesByAlbum(
        @Path("id") id: Int,
        @Query("albumId") albumId: Int
    ): Call<List<Picture>>

}