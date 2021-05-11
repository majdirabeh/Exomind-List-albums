package fr.dev.majdi.testexomind.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import fr.dev.majdi.testexomind.R
import fr.dev.majdi.testexomind.adapter.AlbumAdapter
import fr.dev.majdi.testexomind.base.BaseActivity
import fr.dev.majdi.testexomind.model.Album
import fr.dev.majdi.testexomind.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_album.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import timber.log.Timber

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@KoinApiExtension
class AlbumActivity : BaseActivity() {

    private var userId: Int = -1
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUserId()
        setContentView(R.layout.activity_album)
        initRecycle(recycler)
        setupToolbar(toolbar, R.color.white)
        if (userId != -1) {
            userViewModel.getAlbumByUser(userId)
            userViewModel.listOfAlbums.observe(
                this,
                Observer(function = fun(albums: List<Album>?) {
                    albums?.let {
                        if (albums.isNotEmpty()) {
                            visibilityRecycle(true, recycler, no_data)
                            val albumAdapter = AlbumAdapter(albums)
                            recycler.adapter = albumAdapter
                            albumAdapter.setItemClickListener(object :
                                AlbumAdapter.ItemClickListener {
                                override fun onItemClick(view: View, position: Int, album: Album?) {
                                    if (album != null) {
                                        val bundle = Bundle()
                                        bundle.putInt("userId", userId)
                                        bundle.putInt("albumId", album.id)
                                        openActivity(
                                            this@AlbumActivity,
                                            PictureActivity::class.java,
                                            bundle
                                        )
                                    }
                                }
                            })
                        } else {
                            visibilityRecycle(false, recycler, no_data)
                        }
                    }
                })
            )
        }
    }

    private fun getUserId() {
        if (intent != null) {
            userId = intent.getIntExtra("userId", -1)
            Timber.i("userId ===> %s", userId)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransitionExit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

}