package com.example.ecomapp.utils


/*fun Context.saveFileToCatch(sourceUri: Uri): File {
    val fileType = checkImageOrVide(sourceUri)
    val fileName = (if (fileType == MediaType.MediaTypeVideo) {
        "${System.currentTimeMillis()}.mp4"
    } else "${System.currentTimeMillis()}.png")

    val sourceFolder = "${FileHelper.documentPath()?.absolutePath}/"
    if (!File(sourceFolder).exists()) File(sourceFolder).mkdir()

    val sourceFile = File(sourceFolder, fileName)
    val inputStream: InputStream? = contentResolver.openInputStream(sourceUri)
    val outputStream = FileOutputStream(sourceFile)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }

    return sourceFile
}*/