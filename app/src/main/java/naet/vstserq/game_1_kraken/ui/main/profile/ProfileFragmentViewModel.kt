package naet.vstserq.game_1_kraken.ui.main.profile

import com.ja_company.gameja.utils.AppPreference
import com.ja_company.gameja.utils.Constants
import naet.vstserq.game_1_kraken.di.BaseViewModel
import java.util.regex.Pattern

class ProfileFragmentViewModel(private val prefs: AppPreference) : BaseViewModel() {

    fun checkEmail(email: String, body: (isValid: Boolean) -> Unit) {
        Pattern.compile(Constants.EMAIL_REGEX, Pattern.CASE_INSENSITIVE).matcher(email).find().let {
            if (it) prefs.email(email)
            body.invoke(it)
        }
    }

    fun getEmail() = prefs.email()
}