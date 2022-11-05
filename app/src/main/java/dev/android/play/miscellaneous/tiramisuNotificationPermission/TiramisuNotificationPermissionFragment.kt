package dev.android.play.miscellaneous.tiramisuNotificationPermission

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.android.play.databinding.FragmentTiramisuNotificationErmissionBinding
import dev.android.play.databinding.FragmentTiramisuNotificationPermissionBinding

@AndroidEntryPoint
class TiramisuNotificationPermissionFragment : Fragment(),
    ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var binding: FragmentTiramisuNotificationPermissionBinding

    private val notificationManager: NotificationManager by lazy {
        requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private var requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                refreshUI()
                showNotification()
            } else {
                Snackbar.make(
                    binding.root,
                    "Please grant Notification Permission from app settings",
                    Snackbar.LENGTH_INDEFINITE
                )
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTiramisuNotificationErmissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createNotificationChannel()
        checkPermissions()

        binding.buttonShowNotification.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    showNotification()
                }

                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    Snackbar.make(
                        binding.root,
                        "Please grant Notification Permission from app settings",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Settings") {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        val uri: Uri = Uri.fromParts("package", requireActivity().packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }.show()
                }

                else -> {
                    showNotification()
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
        refreshUI()
    }

    private fun refreshUI() {
        binding.notificationStatus.text =
            if (notificationManager.areNotificationsEnabled()) "TRUE" else "FALSE"
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Important Nottification Channel",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "This Notification contains important announcement"
        }
        notificationManager.createNotificationChannel(channel)
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(requireActivity(), CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg_normal)
            .setContentTitle("Congratulations!")
            .setContentText("You have post a notification to android 13")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(requireActivity())) {
            notify(1, builder.build())
        }

    }

    private fun checkPermissions() {
        val permissionList = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val notGrantedPermissions = permissionList.map {
            Pair(
                it, ContextCompat.checkSelfPermission(
                    requireActivity(),
                    it
                )
            )
        }.filter {
            it.second != PackageManager.PERMISSION_GRANTED
        }
            .map { it.first }
            .toTypedArray()

        if (notGrantedPermissions.isEmpty()) {

        } else {
            requestPermissionLauncher1.launch(notGrantedPermissions)
        }
    }

    private val requestPermissionLauncher1 = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->

        if (result.values.all { it }) {

        } else {
            checkPermissions()
        }
    }

    companion object {
        const val CHANNEL_ID = "sibaprasad.123"
    }

}