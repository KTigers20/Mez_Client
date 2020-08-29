package com.ktigers20.mez.feature.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.FragmentSettingBinding
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.koin.repositoryImpl.SharedPrefRepositoryImpl
import com.ktigers20.mez.feature.login.LoginActivity
import com.ktigers20.mez.singleton.MainViewMode


class SettingFragment : Fragment(), SettingContract.View {
    private lateinit var settingsBinding: FragmentSettingBinding
    private lateinit var presenter: SettingPresenter
    private lateinit var sharedPrefRepositoryImpl: SharedPrefRepositoryImpl

    companion object {
        @JvmStatic
        fun newInstance() = SettingFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = SettingPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpDataBinding(inflater, container)
        initView()
        return settingsBinding.root
    }

    private fun setUpDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        settingsBinding = FragmentSettingBinding.inflate(inflater, container, false)
        settingsBinding.fragment = this
    }

    private fun initView() {
        sharedPrefRepositoryImpl = SharedPrefRepositoryImpl(requireContext())
        if (sharedPrefRepositoryImpl.getPrefsStringValue(Consts.CHART_MODE) == "MY") {
            settingsBinding.changeableBtn.performClick()
        } else {
            settingsBinding.unchangeableBtn.performClick()
        }

        settingsBinding.changeableBtn.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                buttonView.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.button_pressed_background
                )
                buttonView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.kbYellow
                    )
                )
                MainViewMode.mode = "MY"
                sharedPrefRepositoryImpl.writePrefs(Consts.CHART_MODE, "MY")

            } else {
                buttonView.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.button_default_background
                )
                buttonView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey4))
            }
        })



        settingsBinding.unchangeableBtn.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                buttonView.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.button_pressed_background
                )
                buttonView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.kbYellow
                    )
                )

                //초기 '수정가능'버튼 색 변경 위함
                settingsBinding.changeableBtn.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.button_default_background
                )
                settingsBinding.changeableBtn.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey4
                    )
                )
                MainViewMode.mode = "ALL"
                sharedPrefRepositoryImpl.writePrefs(Consts.CHART_MODE, "ALL")

            } else {
                buttonView.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.button_default_background
                )
                buttonView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey4))
            }
        })

    }

    fun logout(v: View) {
        sharedPrefRepositoryImpl.apply {
            writePrefs(Consts.AUTO_LOGIN, false)
            writePrefs(Consts.ACCESS_TOKEN, "")
            writePrefs(Consts.USER_ID, "")
            writePrefs(Consts.CHART_MODE,"MY")
        }
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        requireContext().startActivity(intent)
    }

}
