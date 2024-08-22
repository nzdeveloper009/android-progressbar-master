package com.nokhaiz.lib.utils

import android.graphics.Color
import android.graphics.Paint.Align

class PercentStyle {
    @JvmField
    var align: Align? = null
    @JvmField
    var textSize: Float = 0f
    var isPercentSign: Boolean = false

    /**
     * With this you can set a custom text which should get displayed right
     * behind the number of the progress. Per default it displays a *%*.
     *
     * @param customText
     * The custom text you want to display.
     * @since 1.0.0
     */
    @JvmField
    var customText: String = "%"

    /**
     * Set the color of the text that display the current progress. This will
     * also change the color of the text that normally represents a *%*.
     *
     * @param textColor
     * the color to set the text to.
     * @since 1.0.0
     */
    @JvmField
    var textColor: Int = Color.BLACK

    constructor()

    constructor(align: Align?, textSize: Float, percentSign: Boolean) : super() {
        this.align = align
        this.textSize = textSize
        this.isPercentSign = percentSign
    }
}