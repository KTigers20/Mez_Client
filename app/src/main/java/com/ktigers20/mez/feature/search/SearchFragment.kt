package com.ktigers20.mez.feature.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.ktigers20.mez.data.entity.BatchInfo
import com.ktigers20.mez.databinding.FragmentSearchBinding
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.utils.addTo
import com.ktigers20.mez.feature.filter.FilterActivity
import com.ktigers20.mez.feature.searchDetail.SearchDetailActivity
import com.ktigers20.mez.singleton.SearchFilter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.get
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(), SearchContract.View {
    private val compositeDisposable = CompositeDisposable()

    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var presenter: SearchPresenter
    private var mBatchInfoList = ArrayList<BatchInfo>()

    private lateinit var mSearchAdapter: SearchAdapter
    private var searchPage: Long = 0
    private var searchContentSize = 0
    private var searchIsEnd = false

    val searchRecyclerViewScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy >= 0 && searchContentSize > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() >= searchContentSize - 1 && !searchIsEnd) {
                        callSearchApi(searchBinding.searchEditText.text.toString())
                    }
                }
            }
        }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = SearchPresenter(this, get())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpDataBinding(inflater, container)
        initView()
        return searchBinding.root
    }

    override fun onResume() {
        super.onResume()
        searchPage = 0
        initPageInfo()
    }

    override fun setBatchInfoList(batchInfoList: ArrayList<BatchInfo>) {
        mBatchInfoList.addAll(batchInfoList)
        searchContentSize = mBatchInfoList.size
        if (searchContentSize != 0) {
            if (searchPage < 1) {
                mSearchAdapter = SearchAdapter(batchInfoList, batchCardViewClicked)  //?
                searchBinding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = mSearchAdapter
                }
            } else {
                mSearchAdapter.submitList(batchInfoList)
            }
        }
        searchPage++
    }

    override fun setSearchPageIsEnd(isEnd: Boolean) {
        this.searchIsEnd = isEnd
    }

    private fun setUpDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        searchBinding.fragment = this
    }

    private fun initView() {
        RxTextView.textChanges(searchBinding.searchEditText)
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                initPageInfo()
                mSearchAdapter = SearchAdapter(mBatchInfoList, batchCardViewClicked)
                callSearchApi(it.toString())
            }.addTo(compositeDisposable)
    }

    private fun callSearchApi(str: String) {
        SearchFilter.userName = str.trim()
        presenter.getBatchInfo(SearchFilter, searchPage)
    }

    private fun initPageInfo() {
        mBatchInfoList.clear()
        searchPage = 0
        searchContentSize = 0
        searchIsEnd = false
    }

    private val batchCardViewClicked = { orderId: String ->
        startActivity(Intent(activity, SearchDetailActivity::class.java).apply {
            putExtra(Consts.ORDER_ID, orderId)
        })
    }

    fun navigateToFilterActivity() {
        startActivity(Intent(activity, FilterActivity::class.java))
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
