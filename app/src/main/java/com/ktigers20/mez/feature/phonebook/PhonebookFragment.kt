package com.ktigers20.mez.feature.phonebook

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.jakewharton.rxbinding2.widget.RxTextView
import com.ktigers20.mez.R
import com.ktigers20.mez.data.entity.PhoneBookInfo
import com.ktigers20.mez.databinding.FragmentMainBinding
import com.ktigers20.mez.databinding.FragmentPhonebookBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import org.koin.android.ext.android.get
import java.util.concurrent.TimeUnit

class PhonebookFragment : Fragment(), PhonebookContract.View {
    private val compositeDisposable = CompositeDisposable()

    private lateinit var phonebookBinding: FragmentPhonebookBinding
    private lateinit var presenter: PhonebookPresenter
    private var mPhoneBookInfoList = ArrayList<PhoneBookInfo>()

    private lateinit var mPhoneBookAdapter: PhonebookAdapter
    private var phoneBookPage: Long = 0
    private var phoneBookContentSize = 0
    private var phoneBookIsEnd = false

    companion object {
        @JvmStatic
        fun newInstance() = PhonebookFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = PhonebookPresenter(this, get())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpDataBinding(inflater, container)
        initView()
        return phonebookBinding.root
    }

    override fun onResume() {
        super.onResume()
        phoneBookPage = 0
        phoneBookContentSize = 0
        phoneBookIsEnd = false
    }

    private fun setUpDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        phonebookBinding = FragmentPhonebookBinding.inflate(inflater, container, false)
        phonebookBinding.fragment = this
    }

    private fun initView() {

        phonebookBinding.phoneBookTabLayout.addOnTabSelectedListener(object :
        TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        //compositeDisposable.clear()
                        //1초 미만 입력시 서버 통신
                        RxTextView.textChanges(phonebookBinding.searchEditText)
                            .debounce(1000, TimeUnit.MILLISECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                initPageInfo()
                                mPhoneBookAdapter = PhonebookAdapter(mPhoneBookInfoList)
                                presenter.getPhoneBookInfoByName(it.toString(), phoneBookPage)
                            }.addTo(compositeDisposable)
                    }
                    1 -> {
                        //compositeDisposable.clear()
                        //1초 미만 입력시 서버 통신
                        RxTextView.textChanges(phonebookBinding.searchEditText)
                            .debounce(1000, TimeUnit.MILLISECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                initPageInfo()
                                mPhoneBookAdapter = PhonebookAdapter(mPhoneBookInfoList)
                                presenter.getPhoneBookInfoByJob(it.toString(), phoneBookPage)
                            }.addTo(compositeDisposable)

                    }
                }
            }
        })
    }

    override fun setPhoneBookList(phoneBookList: ArrayList<PhoneBookInfo>) {
        mPhoneBookInfoList.addAll(phoneBookList)
        //phoneBookContentSize += phoneBookList.size
        phoneBookContentSize = mPhoneBookInfoList.size
        if (phoneBookContentSize != 0) {
            if (phoneBookPage < 1) {
                mPhoneBookAdapter = PhonebookAdapter(phoneBookList)
                phonebookBinding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = mPhoneBookAdapter
                }
            } else {
                mPhoneBookAdapter.submitList(phoneBookList)
            }
        }
        phoneBookPage++
    }

    override fun setPhoneBookPageIsEnd(isEnd: Boolean) {
        this.phoneBookIsEnd = isEnd
    }

    private fun initPageInfo() {
        mPhoneBookInfoList.clear()
        phoneBookPage = 0
        phoneBookContentSize = 0
        phoneBookIsEnd = false
    }

    val phoneBookRecyclerViewScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy >= 0 && phoneBookContentSize > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() >= phoneBookContentSize - 1 && !phoneBookIsEnd) {
                        presenter.getPhoneBookInfoByName(
                            phonebookBinding.searchEditText.text.toString(),
                            phoneBookPage
                        )
                    }
                }
            }
        }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
