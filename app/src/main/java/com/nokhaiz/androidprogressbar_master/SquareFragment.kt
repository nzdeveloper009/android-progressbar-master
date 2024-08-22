package com.nokhaiz.androidprogressbar_master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nokhaiz.lib.SquareProgressBar
import java.util.Random

class SquareFragment : Fragment() {
    @JvmField
    var squareProgressBar: SquareProgressBar? = null
    private var progressSeekBar: SeekBar? = null
    private var widthSeekBar: SeekBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.square_layout, container, false)

        val progressView = view
            .findViewById<View>(R.id.progressDisplay) as TextView
        progressView.text = "32%"

        squareProgressBar = view.findViewById<View>(R.id.subi2) as SquareProgressBar
        squareProgressBar!!.setImage(R.drawable.blenheim_palace)
        squareProgressBar!!.setColor("#C9C9C9")
        squareProgressBar!!.setProgress(32)
        squareProgressBar!!.width = 8
        squareProgressBar!!.setOnClickListener {
            val random = Random()
            // random progress
            setProgressBarProgress(random.nextInt(100), progressView)

            // random width
            val randWidth = random.nextInt(17) + 4
            widthSeekBar!!.progress = randWidth
            squareProgressBar!!.width = randWidth

            // random colour
            squareProgressBar!!.setColorRGB(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
        }

        progressSeekBar = view
            .findViewById<View>(R.id.progressSeekBar) as SeekBar
        progressSeekBar?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // nothing to do
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // nothing to do
                }

                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int, fromUser: Boolean
                ) {
                    setProgressBarProgress(progress, progressView)
                }
            })

        widthSeekBar = view.findViewById<View>(R.id.widthSeekBar) as SeekBar
        widthSeekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // nothing to do
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // nothing to do
            }

            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                squareProgressBar!!.width = progress
            }
        })
        return view
    }

    private fun setProgressBarProgress(progress: Int, progressView: TextView) {
        squareProgressBar!!.setProgress(progress)
        val progressText = "$progress%"
        progressView.text = progressText
        progressSeekBar!!.progress = progress
    }
}
