package dev.android.play.recyclerViewHeader

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swasi.common.recyclerViewHeader.RecyclerviewHeaderAdapter
import com.swasi.common.recyclerViewHeader.viewmodels.ViewModelFactory
import dev.android.play.R
import dev.android.play.recyclerViewHeader.data.ItemData
import dev.android.play.recyclerViewHeader.network.ApiHelper
import dev.android.play.recyclerViewHeader.network.NetworkResult
import dev.android.play.recyclerViewHeader.network.RetrofitBuilder
import dev.android.play.recyclerViewHeader.viewmodels.MainViewModel

class RecyclerViewHeaderActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var recyclerViewHeader: RecyclerView
    lateinit var progress: ProgressBar
    var list = mutableListOf<ItemData>()

    val hashMap = mutableMapOf<String, ItemData>()
    val adapter: RecyclerviewHeaderAdapter by lazy {
        RecyclerviewHeaderAdapter(list, hashMap)
    }

    lateinit var layoutManager: LinearLayoutManager

    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount = 0
    var totalItemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_header)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        recyclerViewHeader = findViewById(R.id.recyclerViewHeader)
        progress = findViewById(R.id.progress)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewHeader.layoutManager = layoutManager

        recyclerViewHeader.adapter = adapter
        recyclerViewHeader.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            Log.v("...", "Last Item Wow !")
                            // Do pagination.. i.e. fetch new data
                            loading = true
                        }
                    }
                }
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this) {
            it?.let { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        response.result.let { monthlyData -> retrieveList(monthlyData) }
                    }
                    is NetworkResult.Error -> {

                    }
                    is NetworkResult.Loading -> {

                    }
                }
            }
        }
    }

    // "createdOn": "2022-06-06 14:13:28"
    private fun retrieveList(monthlyData: List<ItemData>) {
        var monthYear: String = "" // 2022-06-06, later it will be June 2022
        monthlyData.forEach { dayData ->
            if (hashMap.containsKey(dayData.createdOn)) {
                val existingHeaderData: ItemData? = hashMap[dayData.createdOn]
                var sum: Int = existingHeaderData?.totalAmount!!
                sum += dayData.txnAmount.toInt()
                existingHeaderData.totalAmount = sum
                hashMap[dayData.createdOn] = existingHeaderData
            } else {
                dayData.totalAmount = dayData.txnAmount.toInt()
                hashMap[dayData.createdOn] = dayData
            }
        }
        list.addAll(monthlyData)
        adapter.apply {
            notifyDataSetChanged()
        }
    }

}