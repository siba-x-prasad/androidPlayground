package dev.android.play.deeplinking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.android.play.databinding.ActivityDeepLinkingBinding;

public class AppLinkingActivity extends AppCompatActivity {

    private ActivityDeepLinkingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeepLinkingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }
}
