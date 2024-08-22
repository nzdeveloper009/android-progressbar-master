package com.nokhaiz.androidprogressbar_master.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Paint.Align
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Spinner
import android.widget.TextView
import com.nokhaiz.androidprogressbar_master.PreviewView
import com.nokhaiz.androidprogressbar_master.R
import com.nokhaiz.lib.utils.PercentStyle

/**
 * THe dialog to set some example values for the percent text.
 *
 * @author ysigner
 * @since 1.3.0
 */
class PercentDialog(context: Context?) : Dialog(context!!) {
    private val spinner: Spinner
    private val box: CheckBox

    /**
     * Returns the save button of the dialog.
     *
     * @return the save [Button].
     */
    @JvmField
    val saveButton: Button
    private val bar: SeekBar
    private var size = 0
    private val previewView: PreviewView

    /**
     * The [PercentDialog] to set custom settings for the style of the
     * percent text.
     *
     * @param context
     * the context.
     */
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.percentdialog)
        this.setCancelable(false)
        spinner = findViewById<View>(R.id.spinner1) as Spinner
        val adapter = ArrayAdapter.createFromResource(
            context!!, R.array.alignstyle,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        previewView = findViewById<View>(R.id.previewView1) as PreviewView
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?, arg1: View,
                arg2: Int, arg3: Long
            ) {
                redrawPreview()
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                // nothing to do =)
            }
        }

        val closeButton = findViewById<View>(R.id.returnDialog) as Button
        closeButton.setOnClickListener { dismiss() }
        saveButton = findViewById<View>(R.id.shareDialog) as Button

        val progress = findViewById<View>(R.id.textView3) as TextView

        bar = findViewById<View>(R.id.textSize) as SeekBar
        bar.max = 400
        bar.progress = 125
        bar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(arg0: SeekBar) {
                // nothing to do =)
            }

            override fun onStartTrackingTouch(arg0: SeekBar) {
                // nothing to do =)
            }

            override fun onProgressChanged(arg0: SeekBar, arg1: Int, arg2: Boolean) {
                size = arg1
                progress.text = "$arg1 dp"
                redrawPreview()
            }
        })

        box = findViewById<View>(R.id.checkBox1) as CheckBox
        box.setOnCheckedChangeListener { buttonView, isChecked -> redrawPreview() }
    }

    val settings: PercentStyle
        /**
         * Returns the [PercentStyle] of the current settings.
         *
         * @return a new [PercentStyle].
         */
        get() = PercentStyle(
            Align.valueOf(
                (spinner
                    .selectedItem as String)
            ), bar.progress.toFloat(),
            box.isChecked
        )

    /**
     * Returns the [Align] according to the position in the dropdown.
     *
     * @param position
     * the position in the dropdown.
     * @return the according [Align].
     */
    private fun returnAlign(position: Int): Align {
        return when (position) {
            0 -> Align.CENTER
            1 -> Align.RIGHT
            2 -> Align.LEFT
            else -> Align.CENTER
        }
    }

    /**
     * Redraws the preview canvas.
     */
    private fun redrawPreview() {
        previewView
            .drawText(
                size, returnAlign(spinner.selectedItemPosition),
                box.isChecked
            )
    }

    /**
     * Sets the [PercentStyle] to the settings in the dialog.
     *
     * @param settings
     * The [PercentStyle], this is most likely the default
     * settings.
     */
    fun setPercentStyle(settings: PercentStyle) {
        when (settings.align) {
            Align.CENTER -> spinner.setSelection(0)
            Align.RIGHT -> spinner.setSelection(1)
            Align.LEFT -> spinner.setSelection(2)
            else -> spinner.setSelection(0)
        }
        bar.progress = Math.round(settings.textSize)
        box.isChecked = settings.isPercentSign
    }
}
