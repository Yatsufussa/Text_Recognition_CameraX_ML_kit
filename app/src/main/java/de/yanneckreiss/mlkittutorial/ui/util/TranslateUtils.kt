package de.yanneckreiss.mlkittutorial.ui.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

suspend fun translateText(text: String, targetLang: String): String = withContext(Dispatchers.IO) {
    try {
        val apiKey = "AIzaSyCNR4rk--Bp3W6FOG-AV9aqC7QWMf4WwWg" // твой API-ключ
        val url = URL("https://translation.googleapis.com/language/translate/v2?key=$apiKey")

        val requestBody = JSONObject()
            .put("q", text)
            .put("target", targetLang)
            .put("format", "text")

        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        connection.doOutput = true

        OutputStreamWriter(connection.outputStream).use { it.write(requestBody.toString()) }

        val responseCode = connection.responseCode
        val response = BufferedReader(connection.inputStream.reader()).use { it.readText() }

        Log.d("TRANSLATE_API", "HTTP $responseCode")
        Log.d("TRANSLATE_API", "Response: $response")

        if (responseCode != 200) {
            return@withContext "[Translation failed: HTTP $responseCode]"
        }

        val translatedText = JSONObject(response)
            .getJSONObject("data")
            .getJSONArray("translations")
            .getJSONObject(0)
            .getString("translatedText")

        translatedText
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("TRANSLATE_API", "Error: ${e.message}")
        "[Translation failed]"
    }
}
