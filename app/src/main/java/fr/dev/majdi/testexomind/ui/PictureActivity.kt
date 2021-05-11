package fr.dev.majdi.testexomind.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import fr.dev.majdi.testexomind.R
import fr.dev.majdi.testexomind.adapter.PictureAdapter
import fr.dev.majdi.testexomind.base.BaseActivity
import fr.dev.majdi.testexomind.model.Picture
import fr.dev.majdi.testexomind.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_picture.*
import kotlinx.android.synthetic.main.activity_picture.no_data
import kotlinx.android.synthetic.main.activity_picture.recycler
import kotlinx.android.synthetic.main.activity_picture.toolbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import timber.log.Timber

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@KoinApiExtension
class PictureActivity : BaseActivity() {

    private var userId: Int = -1
    private var albumId: Int = -1
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUserIdAndAlbumId()
        setContentView(R.layout.activity_picture)
        //initRecyclePictures(recycler)
        initRecycle(recycler)
        setupToolbar(toolbar, R.color.white)
        if (userId != -1 && albumId != -1) {
            userViewModel.getPicturesByAlbum(userId, albumId)
            userViewModel.listOfPictures.observe(
                this,
                Observer(function = fun(pictures: List<Picture>?) {
                    pictures?.let {
                        if (pictures.isNotEmpty()) {
                            visibilityRecycle(true, recycler, no_data)
                            val pictureAdapter = PictureAdapter(pictures)
                            recycler.adapter = pictureAdapter
                            pictureAdapter.setItemClickListener(object :
                                PictureAdapter.ItemClickListener {
                                override fun onItemClick(view: View, position: Int) {

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

    private fun getUserIdAndAlbumId() {
        if (intent != null) {
            userId = intent.getIntExtra("userId", -1)
            albumId = intent.getIntExtra("albumId", -1)
            Timber.i("userId ===> %s", userId)
            Timber.i("albumId ===> %s", albumId)
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