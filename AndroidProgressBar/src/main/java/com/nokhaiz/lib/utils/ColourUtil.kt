package com.nokhaiz.lib.utils

import android.R.color

object ColourUtil {
    val colourArray: ArrayList<Int>
        get() {
            val array = ArrayList<Int>()
            array.add(color.holo_blue_bright)
            array.add(color.holo_blue_dark)
            array.add(color.holo_blue_light)
            array.add(color.holo_green_dark)
            array.add(color.holo_green_light)
            array.add(color.holo_orange_dark)
            array.add(color.holo_orange_light)
            array.add(color.holo_purple)
            array.add(color.holo_red_dark)
            array.add(color.holo_red_light)
            return array
        }
}