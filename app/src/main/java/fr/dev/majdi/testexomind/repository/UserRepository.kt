package fr.dev.majdi.testexomind.repository

import fr.dev.majdi.testexomind.model.Album
import fr.dev.majdi.testexomind.model.Picture
import fr.dev.majdi.testexomind.model.User
import fr.dev.majdi.testexomind.network.UserApi
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class UserRepository(val userApi: UserApi) {
    /**
     * Start request Api to get all users
     */
    fun getUsers(onUserApiInteraction: OnUserApiInteraction) {
        userApi.getAllUsers()
            .enqueue(object : retrofit2.Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    onUserApiInteraction.onSuccess((response.body() as List<User>))
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    onUserApiInteraction.onFailure()
                }
            })
    }

    /**
     * Start request Api to get all albums of user
     */
    fun getAlbumsByUser(onAlbumsApiInteraction: OnAlbumsApiInteraction, userId: Int) {
        userApi.getAlbumsByUser(userId, userId)
            .enqueue(object : retrofit2.Callback<List<Album>> {
                override fun onResponse(
                    call: Call<List<Album>>,
                    response: Response<List<Album>>
                ) {
                    onAlbumsApiInteraction.onSuccess((response.body() as List<Album>))
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    onAlbumsApiInteraction.onFailure()
                }
            })
    }

    /**
     * Start request Api to get all pictures by album
     */
    fun getPicturesByAlbum(
        onPictureApiInteraction: OnPictureApiInteraction,
        userId: Int,
        albumId: Int
    ) {
        userApi.getPicturesByAlbum(userId, albumId)
            .enqueue(object : retrofit2.Callback<List<Picture>> {
                override fun onResponse(
                    call: Call<List<Picture>>,
                    response: Response<List<Picture>>
                ) {
                    onPictureApiInteraction.onSuccess((response.body() as List<Picture>))
                }

                override fun onFailure(call: Call<List<Picture>>, t: Throwable) {
                    onPictureApiInteraction.onFailure()
                }
            })
    }

    interface OnUserApiInteraction {
        fun onSuccess(users: List<User>)
        fun onFailure()
    }

    interface OnAlbumsApiInteraction {
        fun onSuccess(albums: List<Album>)
        fun onFailure()
    }

    interface OnPictureApiInteraction {
        fun onSuccess(pictures: List<Picture>)
        fun onFailure()
    }

}