package com.nokhaiz.androidprogressbar_master.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.nokhaiz.androidprogressbar_master.R

/**
 * This gives the user the possibility to set a custom colour to the
 * SquareProgressBar by selecting a RGB-colour.
 *
 * @author yansigner
 * @since 1.4.0
 */
class CustomColourDialog(context: Context?) : Dialog(context!!) {
    /**
     * Returns the save button of the dialog.
     *
     * @return the save [Button].
     */
    @JvmField
    val saveButton: Button
    private val rSeekBar: SeekBar
    private val gSeekBar: SeekBar
    private val bSeekBar: SeekBar

    /**
     * Returns the Color which was chosen in the Dialog.
     *
     * @return the chosen RGB-colour.
     */
    var choosenRGB: Int = 0
        private set

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.customcolourdialog)
        this.setCancelable(false)

        val closeButton = findViewById<View>(R.id.returnColourDialog) as Button
        closeButton.setOnClickListener { dismiss() }
        saveButton = findViewById<View>(R.id.shareColourDialog) as Button

        rSeekBar = findViewById<View>(R.id.rSeekBar) as SeekBar
        rSeekBar.max = 255
        rSeekBar.progress = 111
        rSeekBar.setOnSeekBarChangeListener(rgbOnSeekBarListener())

        gSeekBar = findViewById<View>(R.id.gSeekBar) as SeekBar
        gSeekBar.max = 255
        gSeekBar.progress = 111
        gSeekBar.setOnSeekBarChangeListener(rgbOnSeekBarListener())

        bSeekBar = findViewById<View>(R.id.bSeekBar) as SeekBar
        bSeekBar.max = 255
        bSeekBar.progress = 111
        bSeekBar.setOnSeekBarChangeListener(rgbOnSeekBarListener())

        calculateRGB()
    }

    private fun rgbOnSeekBarListener(): OnSeekBarChangeListener {
        return object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(arg0: SeekBar) {
                // nothing to do =)
            }

            override fun onStartTrackingTouch(arg0: SeekBar) {
                // nothing to do =)
            }

            override fun onProgressChanged(arg0: SeekBar, arg1: Int, arg2: Boolean) {
                calculateRGB()
            }
        }
    }

    /**
     * Calculates the current set RGB value according to the three
     * [SeekBar]s. This also changes the background of the Dialog.
     */
    private fun calculateRGB() {
        val r = rSeekBar.progress
        val g = gSeekBar.progress
        val b = bSeekBar.progress
        (findViewById<View>(R.id.rgbText) as TextView).text =
            ("(" + r + "," + g + ","
                    + b + ")")
        choosenRGB = Color.rgb(r, g, b)
        window!!.setBackgroundDrawable(ColorDrawable(choosenRGB))
    }
}
