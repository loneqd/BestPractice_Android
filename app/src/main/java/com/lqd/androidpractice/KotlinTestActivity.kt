package com.lqd.androidpractice

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lqd.androidpractice.activity.JetpackActivity

import com.lqd.androidpractice.databinding.ActivityKotlinTest1Binding
import com.lqd.androidpractice.databinding.ActivityMainBinding
import com.lqd.androidpractice.kotlin.Example1
import com.lqd.androidpractice.kotlin.LoginFragment
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.lqd.base.activity.BaseActivity
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *
 *
 * @author alone
 * @Date 4/6/21
 * @version 1.0
 *
 */

class KotlinTestActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy { MainViewModel() }

    private lateinit var binding: ActivityKotlinTest1Binding

    private lateinit var adapter: RepoAdapter

    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKotlinTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadRepos()
        observeData()
    }

    private fun observeData() {
        viewModel.repos.observe(this) {
            Log.d("KotlinTestActivity", "observeData: $it")
            display(it)
        }
    }

    private fun display(repoList: RepoList) {
        adapter = RepoAdapter(repoList)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
    }


}

class RepoAdapter(private val repoList: RepoList): RecyclerView.Adapter<RepoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        return RepoHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.text.text = repoList.items.getOrNull(position)?.repo
    }

    override fun getItemCount(): Int = repoList.count
}

class RepoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val text : TextView = itemView.findViewById(R.id.text)
}


sealed class ResultX<out R : Any> {
    data class Success<out T : Any>(val data: T) : ResultX<T>()
    data class Error(val exception: Exception) : ResultX<Nothing>()

    object Loading : ResultX<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

data class RepoList(
    val count: Int = 0,
    val items: List<Repo> = listOf(),
    val msg: String = "数据为空"
)

data class Repo(
    val added_stars: String = "",
    val avatars: List<String> = listOf(),
    val desc: String = "",
    val forks: String = "",
    val lang: String = "",
    val repo: String = "",
    val repo_link: String = "",
    val stars: String = ""
)

interface RepoDataSource {
    suspend fun getRepos(): ResultX<RepoList>
}

interface IRepository {
    suspend fun getRepoList(): ResultX<RepoList>
}

object RetrofitClient {

    private const val TAG = "OkHttp"
    private const val BASE_URL = "https://baseUrl.com/"
    private const val TIME_OUT = 10

    val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    val service by lazy { getService(RepoService::class.java, BASE_URL) }

    private val client: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        builder.build()
    }

    private fun <S> getService(
        serviceClass: Class<S>,
        baseUrl: String,
        client: OkHttpClient = this.client
    ): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}

interface RepoService {
    @GET("repo")
    suspend fun repos(@Query("lang") lang: String = "Kotlin", @Query("since") since: String = "weekly"): RepoList
}

object RemoteRepoDataSource : RepoDataSource {
    const val TAG = "RemoteRepoDataSource"
    override suspend fun getRepos(): ResultX<RepoList> =
        withContext(Dispatchers.IO) {
            try {
                ResultX.Success(RetrofitClient.service.repos())
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
                ResultX.Error(e)
            }
        }
}

class MainRepository(
    private val dataSource: RepoDataSource = RemoteRepoDataSource,
    private val localDataSource: RepoDataSource? = null
) : IRepository {
    override suspend fun getRepoList(): ResultX<RepoList> {
        // 暂不处理缓存逻辑
        return dataSource.getRepos()
    }
}

class GetRepoListUseCase(private val repository: IRepository = MainRepository()) {
    suspend operator fun invoke(): ResultX<RepoList> {
        return repository.getRepoList()
    }
}

class MainViewModel(
    val getRepoListUseCase: GetRepoListUseCase = GetRepoListUseCase()
) : ViewModel() {
    val repos: LiveData<RepoList>
        get() = _repos
    private val _repos = MutableLiveData<RepoList>()

    fun loadRepos() {
        viewModelScope.launch {
            val result = getRepoListUseCase()
            when (result) {
                is ResultX.Success -> {
                    _repos.value = result.data
                }
                is ResultX.Error -> {
                    _repos.value = RepoList()
                }
                ResultX.Loading -> {
                    // 展示Loading
                }
            }
        }
    }
}