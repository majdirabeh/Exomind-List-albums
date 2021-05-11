package fr.dev.majdi.testexomind.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.dev.majdi.testexomind.model.Album
import fr.dev.majdi.testexomind.model.Picture
import fr.dev.majdi.testexomind.model.User
import fr.dev.majdi.testexomind.repository.UserRepository
import fr.dev.majdi.testexomind.room.AlbumDAO
import fr.dev.majdi.testexomind.room.PictureDAO
import fr.dev.majdi.testexomind.room.UserDAO
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import timber.log.Timber

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@KoinApiExtension
@SuppressLint("CheckResult")
class UserViewModel(
    val userRepository: UserRepository,
    private var userDAO: UserDAO,
    private var albumDAO: AlbumDAO,
    private var pictureDAO: PictureDAO
) : ViewModel(),
    KoinComponent {
    var listOfUsers = MutableLiveData<List<User>>()
    var listOfAlbums = MutableLiveData<List<Album>>()
    var listOfPictures = MutableLiveData<List<Picture>>()

    init {
        listOfUsers.value = listOf()
    }

    /**
     * Get All users from server and save it if is not saved
     */
    fun getUsers() {
        userRepository.getUsers(object : UserRepository.OnUserApiInteraction {
            override fun onSuccess(users: List<User>) {
                //Get users from database
                userDAO.getAll().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it != null) {
                            if (it.isEmpty()) {
                                //Use Coroutines to save data in database background thread
                                GlobalScope.launch(Dispatchers.IO) {
                                    //userDAO.deleteUsers()
                                    for (i in users.indices) {
                                        val user = users[i]
                                        insertUser(user)
                                    }
                                }
                            } else {
                                listOfUsers.value = it
                            }

                        }
                    }, {
                        listOfUsers.value = users
                    })

            }

            override fun onFailure() {
                userDAO.getAll().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it != null) {
                            listOfUsers.value = it
                        }
                    }, {
                        listOfUsers.value = listOf()
                    })

            }
        })
    }

    /**
     * Save user in room database
     */
    fun insertUser(user: User) {
        Observable.fromCallable { userDAO.insert(user) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.e("User Inserted")
            }, {

            })
    }

    /**
     * Get All Albums from server with userId
     */
    fun getAlbumByUser(userId: Int) {
        userRepository.getAlbumsByUser(object : UserRepository.OnAlbumsApiInteraction {
            override fun onSuccess(albums: List<Album>) {
                //Get Albums from database
                albumDAO.getAlbumsByUserId(userId).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it != null) {
                            if (it.isEmpty()) {
                                //Use Coroutines to save data in database background thread
                                GlobalScope.launch(Dispatchers.IO) {
                                    //albumDAO.deleteAlbums()
                                    for (i in albums.indices) {
                                        val album = albums[i]
                                        insertAlbum(album)
                                    }
                                }
                            } else {
                                listOfAlbums.value = it
                            }

                        }
                    }, {
                        listOfAlbums.value = albums
                    })

            }

            override fun onFailure() {
                albumDAO.getAlbumsByUserId(userId).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it != null) {
                            listOfAlbums.value = it
                        }
                    }, {
                        listOfAlbums.value = listOf()
                    })

            }
        }, userId)
    }

    /**
     * Save Album in room database
     */
    fun insertAlbum(album: Album) {
        Observable.fromCallable { albumDAO.insert(album) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.e("Album Inserted")
            }, {

            })
    }

    /**
     * Get All Pictures from server with AlbumId
     */
    fun getPicturesByAlbum(userId: Int, albumId: Int) {
        userRepository.getPicturesByAlbum(object : UserRepository.OnPictureApiInteraction {
            override fun onSuccess(pictures: List<Picture>) {
                //Get Pictures from database
                pictureDAO.getPictureByAlbumId(albumId).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it != null) {
                            if (it.isEmpty()) {
                                //Use Coroutines to save data in database background thread
                                GlobalScope.launch(Dispatchers.IO) {
                                    //pictureDAO.deletePictures()
                                    for (i in pictures.indices) {
                                        val picture = pictures[i]
                                        insertPicture(picture)
                                    }
                                }
                            } else {
                                listOfPictures.value = it
                            }

                        }
                    }, {
                        listOfPictures.value = pictures
                    })
            }

            override fun onFailure() {
                listOfPictures.value = listOf()
            }
        }, userId, albumId)
    }

    /**
     * Save Picture in room database
     */
    fun insertPicture(picture: Picture) {
        Observable.fromCallable { pictureDAO.insert(picture) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.e("Picture Inserted")
            }, {

            })
    }

}