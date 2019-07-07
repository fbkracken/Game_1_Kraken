package naet.vstserq.game_1_kraken.ui

import android.content.Context
import android.os.Bundle
import com.ja_company.gameja.ui.Dialog
import naet.vstserq.game_1_kraken.R

class DialogBuilder {

    lateinit var type: Type
    private var title = ""
    private var showEmail = false
    private var text = ""
    private var amount = ""
    private var yellowBtnText = ""
    private var greenBtnText = ""

    companion object {
        const val TYPE = "type"
        const val TITLE = "title"
        const val TEXT = "text"
        const val SHOW_EMAIL = "show_email"
        const val AMOUNT = "image"
        const val YELLOW_BTN_TEXT = "yellow_btn_text"
        const val GREEN_BTN_TEXT = "green_btn_text"

        enum class Type {
            EMPTY, NO_INTERNET_CONNECTION, DAILY_REWARD, CONGRATULATION, QUIT
        }

        fun newBuilder() = DialogBuilder().Builder()
    }

    inner class Builder {

        fun addType(type: Type): Builder {
            this@DialogBuilder.type = type
            return this
        }

        fun addTitle(title: String): Builder {
            this@DialogBuilder.title = title
            return this
        }

        fun addText(text: String): Builder {
            this@DialogBuilder.text = text
            return this
        }

        fun addCardImage(amount: String): Builder {
            this@DialogBuilder.amount = amount
            return this
        }

        fun addYellowBtnText(text: String): Builder {
            this@DialogBuilder.yellowBtnText = text
            return this
        }

        fun addGreenBtnText(text: String): Builder {
            this@DialogBuilder.greenBtnText = text
            return this
        }

        fun build(): Dialog {
            return Dialog().apply {
                arguments = Bundle().apply {
                    putSerializable(TYPE, this@DialogBuilder.type)
                    putString(TITLE, title)
                    putString(TEXT, text)
                    putString(YELLOW_BTN_TEXT, yellowBtnText)
                    putString(GREEN_BTN_TEXT, greenBtnText)
                }
            }
        }

        //        fun buildQuitDialog(context: Context): Dialog {
//            return Dialog().apply {
//                arguments = Bundle().apply {
//                    putSerializable(TYPE, DialogBuilder.Companion.Type.QUIT)
//                    putString(TITLE, context.getString(R.string.quit))
//                    putString(TEXT, context.getString(R.string.are_your_sure_you_want_to_quit))
//                    putString(YELLOW_BTN_TEXT, context.getString(R.string.yes))
//                    putString(GREEN_BTN_TEXT, context.getString(R.string.no))
//                }
//            }
//        }
//
        fun buildNoInternetConnectionDialog(context: Context): Dialog {
            return Dialog().apply {
                arguments = Bundle().apply {
                    putSerializable(TYPE, Type.NO_INTERNET_CONNECTION)
                    putString(TITLE, context.getString(R.string.no_internet))
                    putString(TEXT, context.getString(R.string.check_internet))
                    putString(YELLOW_BTN_TEXT, context.getString(R.string.ok))
                }
            }
        }

        fun notEnoughCoins(context: Context): Dialog {
            return Dialog().apply {
                arguments = Bundle().apply {
                    putSerializable(TYPE, Type.EMPTY)
                    putString(TITLE, context.getString(R.string.sorry))
                    putString(TEXT, context.getString(R.string.dont_enough_coins))
                    putString(YELLOW_BTN_TEXT, context.getString(R.string.start_earning))
                }
            }
        }

        //
//        fun buildCongratulationDialogWithImage(context: Context, amount: Int): Dialog {
//            return Dialog().apply {
//                arguments = Bundle().apply {
//                    putSerializable(TYPE, DialogBuilder.Companion.Type.CONGRATULATION)
//                    putString(TITLE, context.getString(R.string.congratulation))
//                    putString(AMOUNT, amount.toString())
//                    putString(TEXT, String.format(context.getString(R.string.increase_your_reward), amount))
//                    putString(YELLOW_BTN_TEXT, context.getString(R.string.get_now))
//                    putString(GREEN_BTN_TEXT, context.getString(R.string.get_more))
//                }
//            }
//        }
//
//        fun buildSendEmailDialog(context: Context, amount: Int): Dialog {
//            return Dialog().apply {
//                arguments = Bundle().apply {
//                    putSerializable(TYPE, DialogBuilder.Companion.Type.CONGRATULATION)
//                    putString(TITLE, context.getString(R.string.congratulation))
//                    putString(AMOUNT, amount.toString())
//                    putBoolean(SHOW_EMAIL, true)
//                    putString(TEXT, context.getString(R.string.enter_your_email_to_receive_reward))
//                    putString(GREEN_BTN_TEXT, context.getString(R.string.redeem))
//                }
//            }
//        }
//
        fun buildCongratulationDialog(context: Context, text: String): Dialog {
            return Dialog().apply {
                arguments = Bundle().apply {
                    putSerializable(TYPE, Type.CONGRATULATION)
                    putString(TITLE, context.getString(R.string.congratulation))
                    putString(TEXT, text)
                    putString(YELLOW_BTN_TEXT, context.getString(R.string.play))
                    putString(GREEN_BTN_TEXT, context.getString(R.string.later))
                }
            }
        }
    }
}