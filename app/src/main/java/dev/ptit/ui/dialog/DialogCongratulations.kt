package dev.ptit.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.WindowManager
import dev.ptit.databinding.DialogCongratulationsBinding

class DialogCongratulations(
    context: Context,
    private val description: String,
    private val onNavigateClick: () -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogCongratulationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCongratulationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnDismissListener {
            onNavigateClick()
        }


        binding.tvNavigationButton.setOnClickListener {
            dismiss()
        }
        binding.tvDescription.text = description
    }


    override fun onStart() {
        super.onStart()
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            attributes.width = (getDeviceMetrics(context).widthPixels * 0.75).toInt()
        }

    }

    @Suppress("DEPRECATION")
    private fun getDeviceMetrics(context: Context): DisplayMetrics {
        val metrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getMetrics(metrics)
        return metrics
    }
}