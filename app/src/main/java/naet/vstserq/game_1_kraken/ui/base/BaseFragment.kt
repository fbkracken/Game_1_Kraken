package naet.vstserq.game_1_kraken.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract var layoutResId: Int

    abstract var fragmentTag: String

    inline fun <reified Commander> commander(function: Commander.() -> Unit) {
        context?.let {
            if (it is Commander) function(it)
        }
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }
}