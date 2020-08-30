package com.ktigers20.mez.feature.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.ActivityLoginBinding
import com.ktigers20.mez.domain.utils.toastShort
import com.ktigers20.mez.feature.tab.TabActivity

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import org.koin.android.ext.android.get

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityLoginBinding
    private val compositeDisposable = CompositeDisposable()
    private lateinit var presenter: LoginPresenter
    private val userIdBehaviorSubject = BehaviorSubject.createDefault("")
    private val userPasswordBehaviorSubject = BehaviorSubject.createDefault("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        initView()
    }

    private fun setUpDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activity = this
    }

    @SuppressLint("CheckResult")
    private fun initView() {
        presenter = LoginPresenter(this, get(), get())
        binding.etUserId.doOnTextChanged { text, _, _, _ ->
            userIdBehaviorSubject.onNext(
                text.toString()
            )
        }
        binding.etUserPassword.doOnTextChanged { text, _, _, _ ->
            userPasswordBehaviorSubject.onNext(
                text.toString()
            )
        }
        listOf(userIdBehaviorSubject, userPasswordBehaviorSubject).combineLatest {
            it.fold(true, { t1, t2 -> t1 && t2.isNotEmpty() })
        }.subscribe {
            binding.loginButton.isEnabled = it
            if(binding.loginButton.isEnabled) {
                binding.loginButton.setBackgroundResource(R.drawable.background_item_login)
            } else {
                binding.loginButton.setBackgroundResource(R.drawable.background_item_login_deactivate)
            }
        }.addTo(compositeDisposable)

    }

    override fun navigateToMainPage() {
        this.toastShort("로그인 성공!")
        startActivity(Intent(this, TabActivity::class.java))
        finish()
    }

    override fun toastError() {
        this.toastShort("아이디/비밀번호가 일치하지 않습니다.")
    }

    fun loginButtonClicked() {
        presenter.getAccessToken(binding.etUserId.text.toString(), binding.etUserPassword.text.toString())
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
