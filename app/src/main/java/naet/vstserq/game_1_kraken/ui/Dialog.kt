package com.ja_company.gameja.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import naet.vstserq.game_1_kraken.ui.DialogBuilder.Companion.AMOUNT
import naet.vstserq.game_1_kraken.ui.DialogBuilder.Companion.SHOW_EMAIL
import naet.vstserq.game_1_kraken.ui.DialogBuilder.Companion.TEXT
import naet.vstserq.game_1_kraken.ui.DialogBuilder.Companion.TITLE
import naet.vstserq.game_1_kraken.ui.DialogBuilder.Companion.YELLOW_BTN_TEXT
import com.ja_company.gameja.utils.AppPreference
import com.ja_company.gameja.utils.Constants
import com.ja_company.gameja.utils.onClick
import kotlinx.android.synthetic.main.dialog_layout.*
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.DialogBuilder
import java.util.regex.Pattern

class Dialog : DialogFragment() {

    private companion object {
        const val TEXT_VIEW_TAG = "text_view_tag"
    }

    var type = DialogBuilder.Companion.Type.EMPTY
    var yellowClickFun = {}
    var greenClickFun = {}
    var viewCreatedFun = {}
    var emailEdt: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_layout, null)

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.post {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
        }

        try {
            arguments?.run {
                type = getSerializable(DialogBuilder.TYPE) as DialogBuilder.Companion.Type

                val title = getString(TITLE, "")
                val text = getString(TEXT, "")
                val amount = getString(AMOUNT, "")
                val showEmail = getBoolean(SHOW_EMAIL, false)
                val yellowBtnText = getString(YELLOW_BTN_TEXT, "")
                val greenBtnText = getString(DialogBuilder.GREEN_BTN_TEXT, "")

                if (title.isNotEmpty())
                    container.addView(TextView(context).apply {
                        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                        textSize = 18f
                        setText(title)
                        gravity = Gravity.CENTER
                        setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    })

//                if (amount.isNotEmpty())
//                container.addView(XboxCardView(context!!).addAmount(amount))

                if (text.isNotEmpty())
                    container.addView(TextView(context).apply {
                        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                        textSize = 15f
                        tag = TEXT_VIEW_TAG
                        setPadding(0, 20, 0, 0)
                        setText(text)
                        gravity = Gravity.CENTER
                        setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    })

                if (showEmail) {
                    emailEdt = EditText(context).apply {
                        if (AppPreference(context).email().isNotEmpty())
                            setText(AppPreference(context).email())
                        layoutParams =
                            LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply { setMargins(50, 20, 50, 0) }
                        textSize = 15f
                        hint = getString(R.string.enter_your_email_to_receive_reward)
                        setSingleLine(true)
                        setHintTextColor(ColorStateList.valueOf(Color.WHITE))
                        background = ContextCompat.getDrawable(context, R.mipmap.bg_input)
                        setPadding(20, 0, 20, 0)
                        gravity = Gravity.CENTER
                        setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    }
                    container.addView(emailEdt)
                }

                val btnContainer = LinearLayout(context).apply {
                    layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                        setMargins(0, 20.dp(), 0, 10.dp())
                    }
                    weightSum = 1f
                    orientation = HORIZONTAL
                }

                if (yellowBtnText.isNotEmpty() && greenBtnText.isNotEmpty()) {

                    btnContainer.addView(
                        TextView(context).textViewInline(yellowBtnText, R.mipmap.bg_button)
                            .onClick { yellowClickFun() })

                    btnContainer.addView(View(context).apply { layoutParams = LayoutParams(0, 0, 0.1f) })

                    btnContainer.addView(
                        TextView(context).textViewInline(greenBtnText, R.mipmap.bg_button)
                            .onClick { greenClickFun() })

                    container.addView(btnContainer)

                } else if (yellowBtnText.isNotEmpty()) {
                    container.addView(
                        TextView(context).textViewNoInline(
                            yellowBtnText,
                            R.mipmap.bg_button
                        ).onClick { yellowClickFun() })
                } else container.addView(
                    TextView(context).textViewNoInline(
                        greenBtnText,
                        R.mipmap.bg_button
                    ).onClick { greenClickFun() })
            }
        } catch (e: Exception) {
            Log.d("", "")
        }

        viewCreatedFun()
    }

    private fun TextView.textViewNoInline(text: String, @DrawableRes backGround: Int): TextView {
        return this.apply {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                setMargins(0, 20.dp(), 0, 10.dp())
                setPadding(50.dp(), 10.dp(), 50.dp(), 10.dp())
            }
        }.baseParams(text, backGround)
    }

    private fun TextView.textViewInline(text: String, @DrawableRes backGround: Int): TextView {
        return this.apply { layoutParams = LayoutParams(0, WRAP_CONTENT, 0.45f) }.baseParams(text, backGround)
            .apply { setPadding(0, 10.dp(), 0, 10.dp()) }
    }

    private fun TextView.baseParams(text: String, @DrawableRes backGround: Int): TextView {
        return this.apply {
            setText(text)
            textSize = 15f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            background = ContextCompat.getDrawable(context, backGround)
            gravity = Gravity.CENTER
        }
    }

    fun show(fm: FragmentManager) {
        if (!fm.isDestroyed) show(fm, type.name)
    }

    fun yellowClick(clickFun: () -> Unit): Dialog {
        yellowClickFun = clickFun
        return this
    }

    fun greenClick(clickFun: () -> Unit): Dialog {
        greenClickFun = clickFun
        return this
    }

    fun emailIsRight(ret: (isRight: Boolean, text: String) -> Unit) {
        ret(
            Pattern.compile(Constants.EMAIL_REGEX, Pattern.CASE_INSENSITIVE).matcher(emailEdt?.text.toString()).find(),
            emailEdt?.text.toString()
        )
    }

    fun Int.dp(): Int {
        context?.resources?.displayMetrics?.density?.let {
            return (this * it).toInt()
        }

        return 1
    }
}