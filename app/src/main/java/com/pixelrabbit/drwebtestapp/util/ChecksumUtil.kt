package com.pixelrabbit.drwebtestapp.util

import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest

object ChecksumUtil {

    fun calculateSHA1(filePath: String): String {
        return try {
            val buffer = ByteArray(1024)
            val md = MessageDigest.getInstance("SHA-1")

            FileInputStream(File(filePath)).use { fis ->
                var numRead: Int
                while (fis.read(buffer).also { numRead = it } != -1) {
                    md.update(buffer, 0, numRead)
                }
            }

            md.digest().joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            "Ошибка: ${e.message}"
        }
    }
}
