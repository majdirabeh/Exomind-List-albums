@file:Suppress("EXPERIMENTAL_API_USAGE")

package fr.dev.majdi.testexomind.module

import androidx.room.Room
import fr.dev.majdi.testexomind.Constants.Companion.BASE_URL
import fr.dev.majdi.testexomind.network.UserApi
import fr.dev.majdi.testexomind.repository.UserRepository
import fr.dev.majdi.testexomind.room.AppDatabase
import fr.dev.majdi.testexomind.viewmodel.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
val mainModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "exomind-db").build() }
    single { get<AppDatabase>().userDAO() }
    single { get<AppDatabase>().albumDAO() }
    single { get<AppDatabase>().pictureDAO() }

    single { createWebService() }
    single { UserRepository(get()) }
    viewModel { UserViewModel(get(), get(), get(), get()) }

}

fun createWebService(): UserApi {

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    return retrofit.create(UserApi::class.java)
}
