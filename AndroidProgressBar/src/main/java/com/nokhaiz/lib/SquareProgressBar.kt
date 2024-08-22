package com.nokhaiz.lib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.nokhaiz.lib.utils.CalculationUtil.convertDpToPx
import com.nokhaiz.lib.utils.PercentStyle

/**
 * The basic [SquareProgressBar]. This class includes all the methods you
 * need to modify your [SquareProgressBar].
 *
 * @author Syed Nokhaiz Al Hassan
 * @since 1.0.0
 */
class SquareProgressBar : RelativeLayout {
    /**
     * Returns the [ImageView] that the progress gets drawn around.
     *
     * @return the main ImageView
     * @since 1.0.0
     */
    var imageView: ImageView
        private set
    private val bar: SquareProgressView

    /**
     * If opacity is enabled.
     *
     * @return true if opacity is enabled.
     */
    var isOpacity: Boolean = false
        private set

    /**
     * If greyscale is enabled.
     *
     * @return true if greyscale is enabled.
     */
    var isGreyscale: Boolean = false
        private set
    private var isFadingOnProgress = false
    var roundedCorners: Boolean
        /**
         * Returns a boolean if rounded corners is active or not.
         *
         * @return true if rounded corners is active.
         * @since 1.0.0
         */
        get() = bar.isRoundedCorners
        /**
         * Activates the drawing of rounded corners.
         *
         * @since 1.0.0
         */
        set(useRoundedCorners) {
            bar.setRoundedCorners(useRoundedCorners, 10f)
        }

    /**
     * New SquareProgressBar.
     *
     * @param context  the [Context]
     * @param attrs    an [AttributeSet]
     * @param defStyle a defined style.
     * @since 1.0.0
     */
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        val mInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.progress_bar_view, this, true)
        bar = findViewById(R.id.squareProgressBar1)
        imageView = findViewById(R.id.imageView1)
        bar.bringToFront()
    }

    /**
     * New SquareProgressBar.
     *
     * @param context the [Context]
     * @param attrs   an [AttributeSet]
     * @since 1.0.0
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val mInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.progress_bar_view, this, true)
        bar = findViewById<View>(R.id.squareProgressBar1) as SquareProgressView
        imageView = findViewById<View>(R.id.imageView1) as ImageView
        bar.bringToFront()
    }

    /**
     * New SquareProgressBar.
     *
     * @param context the context
     * @since 1.0.0
     */
    constructor(context: Context) : super(context) {
        val mInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.progress_bar_view, this, true)
        bar = findViewById<View>(R.id.squareProgressBar1) as SquareProgressView
        imageView = findViewById<View>(R.id.imageView1) as ImageView
        bar.bringToFront()
    }

    /**
     * Sets the image of the [SquareProgressBar]. Must be a valid
     * ressourceId.
     *
     * @param image the image as a ressourceId
     * @since 1.0.0
     */
    fun setImage(image: Int) {
        imageView.setImageResource(image)
    }

    /**
     * Sets the image of the [SquareProgressBar]. Must be a valid
     * Drawable.
     *
     * @param imageDrawable the image as a Drawable
     * @since 1.0.0
     */
    fun setImageDrawable(imageDrawable: Drawable?) {
        imageView.setImageDrawable(imageDrawable)
    }


    /**
     * Sets the image scale type according to [ScaleType].
     *
     * @param scale the image ScaleType
     * @since 1.0.0
     */
    fun setImageScaleType(scale: ScaleType?) {
        imageView.scaleType = scale
    }

    /**
     * Sets the colour of the [SquareProgressBar] to a predefined android
     * holo color.
     * **Examples:**
     *
     *  * holo_blue_bright
     *  * holo_blue_dark
     *  * holo_blue_light
     *  * holo_green_dark
     *  * holo_green_light
     *  * holo_orange_dark
     *  * holo_orange_light
     *  * holo_purple
     *  * holo_red_dark
     *  * holo_red_light
     *
     *
     * @param androidHoloColor holo color value
     * @since 1.0.0
     */
    fun setHoloColor(androidHoloColor: Int) {
        bar.setColor(ContextCompat.getColor(context, androidHoloColor))
    }

    /**
     * Sets the colour of the [SquareProgressBar]. YOu can give it a
     * hex-color string like *#C9C9C9*.
     *
     * @param colorString the colour of the [SquareProgressBar]
     * @since 1.0.0
     */
    fun setColor(colorString: String?) {
        bar.setColor(Color.parseColor(colorString))
    }

    /**
     * This sets the colour of the [SquareProgressBar] with a RGB colour.
     *
     * @param r red
     * @param g green
     * @param b blue
     * @since 1.0.0
     */
    fun setColorRGB(r: Int, g: Int, b: Int) {
        bar.setColor(Color.rgb(r, g, b))
    }

    /**
     * This sets the colour of the [SquareProgressBar] with a RGB colour.
     * Works when used with
     * `android.graphics.Color.rgb(int)`
     *
     * @param rgb the rgb color
     * @since 1.0.0
     */
    fun setColorRGB(rgb: Int) {
        bar.setColor(rgb)
    }

    /**
     * This sets the width of the [SquareProgressBar].
     *
     * @param width in Dp
     * @since 1.0.0
     */
    fun setWidth(width: Int) {
        val padding = convertDpToPx(width.toFloat(), context)
        imageView.setPadding(padding, padding, padding, padding)
        bar.setWidthInDp(width)
    }

    /**
     * This sets the alpha of the image in the view. Actually I need to use the
     * deprecated method here as the new one is only available for the API-level
     * 16. And the min API level of this library is 14.
     * Use this only as private method.
     *
     * @param progress the progress
     */
    private fun setOpacity(progress: Int) {
        imageView.imageAlpha = ((2.55 * progress).toInt())
    }

    /**
     * Switches the opacity state of the image. This forces the
     * SquareProgressBar to redraw with the current progress. As bigger the
     * progress is, then more of the image comes to view. If the progress is 0,
     * then you can't see the image at all. If the progress is 100, the image is
     * shown full.
     *
     * @param opacity true if opacity should be enabled.
     * @since 1.0.0
     */
    fun setOpacity(opacity: Boolean) {
        this.isOpacity = opacity
        progress = bar.getProgress()
    }

    /**
     * Switches the opacity state of the image. This forces the
     * SquareProgressBar to redraw with the current progress. As bigger the
     * progress is, then more of the image comes to view. If the progress is 0,
     * then you can't see the image at all. If the progress is 100, the image is
     * shown full.
     * You can also set the flag if the fading should get inverted so the image
     * disappears when the progress increases.
     *
     * @param opacity            true if opacity should be enabled.
     * @param isFadingOnProgress default false. This changes the behavior the opacity works. If
     * the progress increases then the images fades. When the
     * progress reaches 100, then the image disappears.
     * @since 1.0.0
     */
    fun setOpacity(opacity: Boolean, isFadingOnProgress: Boolean) {
        this.isOpacity = opacity
        this.isFadingOnProgress = isFadingOnProgress
        progress = bar.getProgress()
    }

    /**
     * You can set the image to b/w with this method. Works fine with the
     * opacity.
     *
     * @param greyscale true if the grayscale should be activated.
     * @since 1.0.0
     */
    fun setImageGrayscale(greyscale: Boolean) {
        this.isGreyscale = greyscale
        if (greyscale) {
            val matrix = ColorMatrix()
            matrix.setSaturation(0f)
            imageView.colorFilter = ColorMatrixColorFilter(matrix)
        } else {
            imageView.colorFilter = null
        }
    }

    /**
     * Draws an outline of the progressbar. Looks quite cool in some situations.
     *
     * @param drawOutline true if it should or not.
     * @since 1.0.0
     */
    fun drawOutline(drawOutline: Boolean) {
        bar.isOutline = drawOutline
    }

    val isOutline: Boolean
        /**
         * If outline is enabled or not.
         *
         * @return true if outline is enabled.
         */
        get() = bar.isOutline

    /**
     * Draws the startline. this is the line where the progressbar starts the
     * drawing around the image.
     *
     * @param drawStartline true if it should or not.
     * @since 1.0.0
     */
    fun drawStartline(drawStartline: Boolean) {
        bar.isStartline = drawStartline
    }

    val isStartline: Boolean
        /**
         * If the startline is enabled.
         *
         * @return true if startline is enabled or not.
         */
        get() = bar.isStartline

    /**
     * Defines if the percent text should be shown or not. To modify the text
     * checkout [.setPercentStyle].
     *
     * @param showProgress true if it should or not.
     * @since 1.0.0
     */
    fun showProgress(showProgress: Boolean) {
        bar.isShowProgress = showProgress
    }

    val isShowProgress: Boolean
        /**
         * If the progress text inside of the image is enabled.
         *
         * @return true if it is or not.
         */
        get() = bar.isShowProgress

    var percentStyle: PercentStyle?
        /**
         * Returns the [PercentStyle] of the percent text. Maybe returns the
         * default value, check [.setPercentStyle] fo that.
         *
         * @return the percent style of the moment.
         */
        get() = bar.percentStyle
        /**
         * Sets a custom percent style to the text inside the image. Make sure you
         * set [.showProgress] to true. Otherwise it doesn't shows.
         * The default settings are:
         * Text align: CENTER
         * Text size: 150 [dp]
         * Display percentsign: true
         * Custom text: %
         *
         * @param percentStyle the percent style
         */
        set(percentStyle) {
            bar.percentStyle = percentStyle!!
        }

    var isClearOnHundred: Boolean
        /**
         * If the progressbar disappears when the progress reaches 100%.
         *
         * @return if "clearOnHundred" is enabled or not
         * @since 1.0.0
         */
        get() = bar.isClearOnHundred
        /**
         * If the progress hits 100% then the progressbar disappears if this flag is
         * set to `true`. The default is set to false.
         *
         * @param clearOnHundred if it should disappear or not.
         * @since 1.0.0
         */
        set(clearOnHundred) {
            bar.isClearOnHundred = clearOnHundred
        }


    /**
     * Set an image resource directly to the ImageView.
     *
     * @param bitmap the [android.graphics.Bitmap] to set.
     */
    fun setImageBitmap(bitmap: Bitmap?) {
        imageView.setImageBitmap(bitmap)
    }

    var isIndeterminate: Boolean
        /**
         * Returns the status of the indeterminate mode. The default status is false.
         *
         * @return if "indeterminate" is enabled or not
         * @since 1.0.0
         */
        get() = bar.isIndeterminate
        /**
         * Set the status of the indeterminate mode. The default is false. You can
         * still configure colour, width and so on.
         *
         * @param indeterminate true to enable the indeterminate mode (default true)
         * @since 1.0.0
         */
        set(indeterminate) {
            bar.isIndeterminate = indeterminate
        }

    /**
     * Draws a line in the center of the way the progressbar has to go.
     *
     * @param drawCenterline true if it should or not.
     * @since 1.0.0
     */
    fun drawCenterline(drawCenterline: Boolean) {
        bar.isCenterline = drawCenterline
    }

    val isCenterline: Boolean
        /**
         * If the centerline is enabled or not.
         *
         * @return true if centerline is enabled.
         * @since 1.0.0
         */
        get() = bar.isCenterline

    var progress: Double
        /**
         * Returns the progress of the progressbar as a double.
         *
         * @return the current progress as double.
         * @since 1.0.0
         */
        get() = bar.getProgress()
        /**
         * Sets the progress of the [SquareProgressBar]. If opacity is
         * selected then here it sets it. See [.setOpacity] for more
         * information.
         *
         * @param progress the progress
         * @since 1.0.0
         */
        set(progress) {
            bar.setProgress(progress)
            if (isOpacity) {
                if (isFadingOnProgress) {
                    setOpacity(100 - progress.toInt())
                } else {
                    setOpacity(progress.toInt())
                }
            } else {
                setOpacity(100)
            }
        }

    /**
     * Sets the progress as an integer value. This is mainly used for animations.
     *
     * @param progress as an integer value.
     * @since 1.0.0
     */
    fun setProgress(progress: Int) {
        this.progress = progress.toDouble()
    }

    /**
     * Activates the drawing of rounded corners with a given radius.
     *
     * @since 1.0.0
     */
    fun setRoundedCorners(useRoundedCorners: Boolean, radius: Float) {
        bar.setRoundedCorners(useRoundedCorners, radius)
    }
}