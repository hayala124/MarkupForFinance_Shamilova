package com.example.markupforfinance_shamilova

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AlignmentSpan
import android.text.style.ForegroundColorSpan
import android.text.style.LeadingMarginSpan
import android.text.style.StyleSpan
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.markupforfinance_shamilova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // сделать круглым imageView с id imageWiseWineToken
        val image = BitmapFactory.decodeResource(resources, R.drawable.wise_win_token)
        val round = RoundedBitmapDrawableFactory.create(resources, image)
        round.isCircular = true
        binding.imageWiseWineToken.setImageDrawable(round)

        information(binding.bitcoinSum, binding.bitcoin,"Bitcoin","36 187,43 $", " + 2,68 %", "50,66 $")
        information(binding.ethereumSum, binding.ethereum, "Ethereum","2 220,56$"," + 4,45 %", "117,11 $")
        information(binding.smartChainSum, binding.smartChain, "Smart Chain", "306,82 $", " + 4,17 %", "38 332,44 $")
        information(binding.pancakeSwapSum, binding.pancakeSwap, "PancakeSwap", "14,47 $", " + 5,78 %", "63 412,21 $")
        information(binding.binanceUSDSum, binding.binanceUSD, "Binance USD", "1,00 $", " - 0,01 %", "8 253,32 $")
        information(binding.wiseWineTokenSum, binding.wiseWineToken, "Wise Win Token", "1,32 $", " + 5,31 %", "35 579,93 $")
    }

    fun information(textView: TextView, editText: EditText, _firstPartFirstLine: String, _firstPartSecondLine: String,
                           _secondPartSecondLine: String, _thirdPartSecondLine: String) {
        val firstPartFirstLine = SpannableString(_firstPartFirstLine)
        val secondPartFirstLine = SpannableString(textView.text)
        val firstPartSecondLine = SpannableString(_firstPartSecondLine)
        val secondPartSecondLine = SpannableString(_secondPartSecondLine)
        val thirdPartSecondLine = SpannableString(_thirdPartSecondLine)

        // установка жирного стиля только для первой строки в editText
        firstPartFirstLine.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            firstPartFirstLine.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        // установка жирного стиля только для первой строки в textView
        secondPartFirstLine.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            secondPartFirstLine.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        firstPartSecondLine.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.silver)),
            0,
            firstPartSecondLine.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        secondPartSecondLine.setSpan(
            if (_secondPartSecondLine == " - 0,01 %")
                ForegroundColorSpan(ContextCompat.getColor(this, R.color.orange))
            else
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.green)),
            0,
            secondPartSecondLine.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        thirdPartSecondLine.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.skyBlue)),
            0,
            thirdPartSecondLine.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        with(textView) {
            text = secondPartFirstLine
            append("\n")
            append(thirdPartSecondLine)
        }
        with(editText) {
            isFocusable = false
            isCursorVisible = false
            setText(firstPartFirstLine)
            append("\n")
            append(firstPartSecondLine)
            append(secondPartSecondLine)
        }
    }
}
