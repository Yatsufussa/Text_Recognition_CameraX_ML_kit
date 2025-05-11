@file:OptIn(ExperimentalPermissionsApi::class)

package de.yanneckreiss.mlkittutorial.ui

import android.Manifest
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import de.yanneckreiss.mlkittutorial.ui.camera.CameraScreen
import de.yanneckreiss.mlkittutorial.ui.language.LanguageSelectionScreen
import de.yanneckreiss.mlkittutorial.ui.no_permission.NoPermissionScreen
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun MainScreen() {
    val cameraPermissionState: PermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var selectedLanguage by rememberSaveable { mutableStateOf<String?>(null) }

    when {
        selectedLanguage == null -> {
            LanguageSelectionScreen(onLanguageSelected = { selectedLanguage = it })
        }
        cameraPermissionState.status.isGranted -> {
            CameraScreen(targetLanguage = selectedLanguage!!)
        }
        else -> {
            NoPermissionScreen(onRequestPermission = cameraPermissionState::launchPermissionRequest)
        }
    }
}

@Preview
@Composable
private fun Preview_MainScreen() {
    // Предпросмотр с фиктивным языком (камера не будет активна в превью)
    CameraScreen(targetLanguage = "en")
}
