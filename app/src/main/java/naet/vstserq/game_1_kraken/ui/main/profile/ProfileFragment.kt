package naet.vstserq.game_1_kraken.ui.main.profile

import android.os.Bundle
import android.view.View
import com.ja_company.gameja.utils.onClick
import kotlinx.android.synthetic.main.profile_fragment_layout.*
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.base.BaseFragment
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    val viewModel: ProfileFragmentViewModel by viewModel()

    override var fragmentTag = "GameFragment"

    override var layoutResId = R.layout.profile_fragment_layout

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment().apply { arguments = Bundle().apply {} }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email_edt_view.setText(viewModel.getEmail())

        save_changed_btn.onClick {
            viewModel.checkEmail(email_edt_view.text.toString()) {
                toast(if (it) getString(R.string.email_saved) else getString(R.string.email_not_valid))
            }
        }
    }
}