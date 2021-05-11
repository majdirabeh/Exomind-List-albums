package fr.dev.majdi.testexomind.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.dev.majdi.testexomind.R
import fr.dev.majdi.testexomind.adapter.UserAdapter
import fr.dev.majdi.testexomind.base.BaseActivity
import fr.dev.majdi.testexomind.model.User
import fr.dev.majdi.testexomind.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.no_data
import kotlinx.android.synthetic.main.activity_main.recycler
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : BaseActivity() {

    private val userViewModel: UserViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycle(recycler)
        userViewModel.getUsers()
        userViewModel.listOfUsers.observe(
            this,
            Observer(function = fun(users: List<User>?) {
                users?.let {
                    if (users.isNotEmpty()) {
                        visibilityRecycle(true, recycler, no_data)

                        val userAdapter = UserAdapter(users)
                        recycler.adapter = userAdapter
                        userAdapter.setItemClickListener(object :
                            UserAdapter.ItemClickListener {
                            override fun onItemClick(view: View, position: Int, user: User?) {
                                if (user != null) {
                                    val bundle = Bundle()
                                    bundle.putInt("userId", user.id)
                                    openActivity(
                                        this@MainActivity,
                                        AlbumActivity::class.java,
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