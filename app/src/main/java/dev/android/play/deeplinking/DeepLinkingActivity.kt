package dev.android.play.deeplinking

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.android.play.databinding.ActivityDeepLinkingBinding


class DeepLinkingActivity : AppCompatActivity() {

    // www.spcart.com/product/123
   //  https://www.spcart.com/product/id=abcd

    private lateinit var uri: Uri
    lateinit var binding: ActivityDeepLinkingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeepLinkingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.data?.let {
            handleDeepLinking(it)
        }

        val appLinkAction: String? = intent?.action
        val appLinkData: Uri? = intent?.data
        showDeepLinkOffer(appLinkAction, appLinkData)
    }

    private fun handleDeepLinking(uri: Uri) {
        // if the uri is not null then we are getting
        // the path segments and storing it in list.
        val parameters: List<String> = uri.pathSegments

        // after that we are extracting string
        // from that parameters.
        val param = parameters[parameters.size - 1]

        // on below line we are setting that string
        // to our text view which we got as params.
        binding.tvDeepLinkingText.text = param
    }

    // this is for app link
    private fun showDeepLinkOffer(appLinkAction: String?, appLinkData: Uri?) {
        if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null) {
            val promotionCode = appLinkData.getQueryParameter("code")
            if (promotionCode.isNullOrBlank().not()) {
                binding.tvDeepLinkingTextOther.visibility = View.VISIBLE
                binding.tvDeepLinkingTextOther.text = promotionCode
            } else {
                Toast.makeText(
                    this@DeepLinkingActivity,
                    "Product Code Not found",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}