package com.nokhaiz.androidprogressbar_master

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.nokhaiz.androidprogressbar_master.databinding.ActivityMainBinding
import com.nokhaiz.androidprogressbar_master.dialogs.CustomColourDialog
import com.nokhaiz.androidprogressbar_master.dialogs.PercentDialog
import com.nokhaiz.lib.utils.ColourUtil


class MainActivity : AppCompatActivity() {

    private lateinit var squareFragment: SquareFragment
    private var lastPosition = 0

    private val binding: ActivityMainBinding? by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        squareFragment = SquareFragment()
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, squareFragment)
            .commit()
        setContentView(binding?.root)

        initializeDrawer()
    }

    private fun initializeDrawer() {
        binding?.apply {
            icHamBurger.setOnClickListener { drawerLayout.open() }
            drawerLayout.setDrawerShadow(R.drawable.drawer_shadow_9, GravityCompat.START)
            includeDrawer.drawerListView.adapter = DrawerAdapter()
            // Drawer item click listener
            includeDrawer.drawerListView.onItemClickListener = DrawerItemClickListener()
        }
    }

    private fun closeDrawerLayout() {
        binding?.drawerLayout?.closeDrawers()
    }

    private inner class DrawerAdapter : BaseAdapter() {

        override fun getCount() = 31

        override fun getItem(position: Int) = position

        override fun getItemId(position: Int) = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return when (position) {
                0, 12, 22, 28 -> createHeaderItem(position, parent)
                in 1..10 -> createColourItem(position - 1, parent)
                11 -> createCustomColourItem(parent)
                in 13..21 -> createStyleItem(position, parent)
                in 23..27 -> createImageItem(position, parent)
                29 -> createGithubItem(parent)
                30 -> createSignerItem(parent)
                else -> View(this@MainActivity)
            }
        }

        private fun createHeaderItem(position: Int, parent: ViewGroup): View {
            val headerItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.lv_header_layout, parent, false)
            val title = headerItem.findViewById<TextView>(R.id.lv_list_hdr)
            title.text = when (position) {
                0 -> "Colour"
                12 -> "Style"
                22 -> "Image"
                28 -> "Source"
                else -> ""
            }
            return headerItem
        }

        private fun createColourItem(position: Int, parent: ViewGroup): View {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lv_colour, parent, false)
            val colourView = itemView.findViewById<View>(R.id.colour_preview)
            val textView = itemView.findViewById<TextView>(R.id.colour_name)
            val colourResId = ColourUtil.colourArray[position]

            colourView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, colourResId))
            textView.text = getName(position)

            itemView.setOnClickListener {
                squareFragment.squareProgressBar?.setHoloColor(colourResId)
                selectItem(position + 1)
                lastPosition = position + 1
            }

            return itemView
        }

        private fun createCustomColourItem(parent: ViewGroup): View {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lv_colour_text, parent, false)
            val textView = itemView.findViewById<TextView>(R.id.colour_name_center)
            textView.text = ContextCompat.getString(this@MainActivity, R.string.choose_rgb_colour)

            itemView.setOnClickListener {
                val customColourDialog = CustomColourDialog(this@MainActivity)
                customColourDialog.show()
                customColourDialog.saveButton.setOnClickListener {
                    squareFragment.squareProgressBar?.setColorRGB(customColourDialog.choosenRGB)
                    customColourDialog.dismiss()
                    selectItem(11)
                    lastPosition = 11
                    binding?.drawerLayout?.closeDrawers()
                }
            }
            return itemView
        }

        private fun createStyleItem(position: Int, parent: ViewGroup): View {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lv_style, parent, false)
            val squareProgressBar = squareFragment.squareProgressBar

            when (position) {
                13 -> {
                    val checkBoxOpacity = itemView.findViewById<CheckBox>(R.id.checkBox1)
                    checkBoxOpacity.apply {
                        text = ContextCompat.getString(this@MainActivity, R.string.opacity)
                        isChecked = squareProgressBar?.isOpacity ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.setOpacity(isChecked)
                            if (isChecked) closeDrawerLayout()
                        }
                    }
                }

                14 -> {
                    val checkBoxOutline = itemView.findViewById<CheckBox>(R.id.checkBox1)
                    checkBoxOutline.apply {
                        text = ContextCompat.getString(this@MainActivity, R.string.outline)
                        isChecked = squareProgressBar?.isOutline ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.drawOutline(isChecked)
                            if (isChecked) closeDrawerLayout()
                        }
                    }
                }

                15 -> {
                    val checkBoxStartLine = itemView.findViewById<CheckBox>(R.id.checkBox1)
                    checkBoxStartLine.apply {
                        text = ContextCompat.getString(this@MainActivity, R.string.start_line)
                        isChecked = squareProgressBar?.isStartline ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.drawStartline(isChecked)
                            if (isChecked) closeDrawerLayout()
                        }
                    }
                }

                16 -> {
                    val checkBoxCenterLine = itemView.findViewById<CheckBox>(R.id.checkBox1)
                    checkBoxCenterLine.apply {
                        text = ContextCompat.getString(this@MainActivity, R.string.center_line)
                        isChecked = squareProgressBar?.isCenterline ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.drawCenterline(isChecked)
                            if (isChecked) closeDrawerLayout()
                        }
                    }
                }

                17 -> {
                    val itemViewStyleBox = LayoutInflater.from(parent.context)
                        .inflate(R.layout.lv_style_box, parent, false)
                    val checkBoxPercent = itemViewStyleBox.findViewById<CheckBox>(R.id.checkBox11)
                    val imageView = itemViewStyleBox.findViewById<ImageView>(R.id.imageView1)

                    checkBoxPercent.apply {
                        text = ContextCompat.getString(this@MainActivity, R.string.show_percent)
                        isChecked = squareProgressBar?.isShowProgress ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.showProgress(isChecked)
                            if (isChecked) closeDrawerLayout()
                        }
                    }

                    imageView.setOnClickListener {
                        val percentDialog = PercentDialog(this@MainActivity)
                        percentDialog.show()
                        percentDialog.setPercentStyle(squareProgressBar?.percentStyle!!)
                        percentDialog.saveButton.setOnClickListener {
                            squareProgressBar.percentStyle = percentDialog.settings
                            percentDialog.dismiss()
                            checkBoxPercent.isChecked = true
                            closeDrawerLayout()
                        }
                    }
                    return itemViewStyleBox
                }

                18 -> {
                    val checkBoxGrayscale = itemView.findViewById<CheckBox>(R.id.checkBox1)
                    checkBoxGrayscale.apply {
                        text = ContextCompat.getString(this@MainActivity, R.string.gray_scale)
                        isChecked = squareProgressBar?.isGreyscale ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.setImageGrayscale(isChecked)
                            if (isChecked) closeDrawerLayout()
                        }
                    }
                }

                19 -> {
                    val checkBoxClearOnHundred = itemView.findViewById<CheckBox>(R.id.checkBox1)
                    checkBoxClearOnHundred.apply {
                        text =
                            ContextCompat.getString(this@MainActivity, R.string.clear_at_100_perc)
                        isChecked = squareProgressBar?.isClearOnHundred ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.isClearOnHundred = isChecked
                            if (isChecked) closeDrawerLayout()
                        }
                    }
                }

                20 -> {
                    val checkBoxIndeterminate = itemView.findViewById<CheckBox>(R.id.checkBox1)
                    checkBoxIndeterminate.apply {
                        text = ContextCompat.getString(this@MainActivity, R.string.indeterminate)
                        isChecked = squareProgressBar?.isIndeterminate ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.isIndeterminate = isChecked
                            if (isChecked) closeDrawerLayout()
                        }
                    }
                }

                21 -> {
                    val checkBoxRoundedCorners = itemView.findViewById<CheckBox>(R.id.checkBox1)
                    checkBoxRoundedCorners.apply {
                        text = ContextCompat.getString(this@MainActivity, R.string.rounded_corners)
                        isChecked = squareProgressBar?.roundedCorners ?: false
                        setOnCheckedChangeListener { _, isChecked ->
                            squareProgressBar?.roundedCorners = isChecked
                            if (isChecked) closeDrawerLayout()
                        }
                    }
                }
            }
            return itemView
        }

        private fun createImageItem(position: Int, parent: ViewGroup): View {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lv_image, parent, false)
            val imagePreview = itemView.findViewById<ImageView>(R.id.imageView1)
            val imageDesc = itemView.findViewById<TextView>(R.id.imagetag)

            val (imageResId, desc) = when (position) {
                23 -> R.drawable.blenheim_palace to ContextCompat.getString(
                    this@MainActivity,
                    R.string.blenheim_palace
                )

                24 -> R.drawable.millennium_stadium to ContextCompat.getString(
                    this@MainActivity,
                    R.string.millennium_stadium
                )

                25 -> R.drawable.edinburgh to ContextCompat.getString(
                    this@MainActivity,
                    R.string.edinburgh
                )

                26 -> R.drawable.holyroodpark to ContextCompat.getString(
                    this@MainActivity,
                    R.string.holyroodpark
                )

                27 -> R.drawable.operahuset to ContextCompat.getString(
                    this@MainActivity,
                    R.string.operahuset
                )

                else -> null to null
            }

            imageResId?.let { resId ->
                imagePreview.setImageResource(resId)
                imageDesc.text = desc
                itemView.setOnClickListener {
                    val imgId = ContextCompat.getDrawable(this@MainActivity, resId)
                    squareFragment.squareProgressBar?.setImageDrawable(imgId)
                    selectItem(position + 2)
                    lastPosition = position + 2
                    closeDrawerLayout()
                }
            }

            return itemView
        }

        private fun createGithubItem(parent: ViewGroup): View {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lv_github, parent, false)
            val githubLink = itemView.findViewById<TextView>(R.id.textView1)
            val text =
                ContextCompat.getString(
                    this@MainActivity,
                    R.string.github_repo_lib_android_progress_bar_link_version
                )
            githubLink.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
            itemView.setOnClickListener {
                val uri = ContextCompat.getString(this@MainActivity, R.string.github_link_repo)
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(uri)
                )
                startActivity(intent)
                selectItem(29)
                lastPosition = 29
                closeDrawerLayout()
            }

            return itemView
        }

        private fun createSignerItem(parent: ViewGroup): View {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lv_signer, parent, false)

            itemView.setOnClickListener {
                val url = ContextCompat.getString(this@MainActivity, R.string.github_profile_link)
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                selectItem(30)
                lastPosition = 30
                closeDrawerLayout()
            }

            return itemView
        }

        private fun selectItem(position: Int) {
            squareFragment.squareProgressBar?.invalidate()
            binding?.includeDrawer?.drawerListView?.setItemChecked(position, true)
        }

        private fun getName(position: Int): String {
            val resourcesArray = resources.getStringArray(R.array.colours)
            return if (position < resourcesArray.size) resourcesArray[position] else ContextCompat.getString(
                this@MainActivity,
                R.string.unknown
            )
        }
    }

    private inner class DrawerItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            when (position) {
                in 0..10, in 13..21, in 23..27, 29, 30 -> closeDrawerLayout()
            }
        }
    }
}