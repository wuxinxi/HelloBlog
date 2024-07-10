package cn.xxstudy.api.utils

import cn.xxstudy.api.configuration.exception.AppRuntimeException
import org.springframework.boot.system.ApplicationHome
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO
import javax.servlet.http.HttpServletRequest

/**
 * @date: 2024/7/10 14:19
 * @author: TangRen
 * @remark:
 */
object ImageUtil {
    fun upload(
        file: MultipartFile,
        childDir: String,
        maxSize: Int = 2 * 1024 * 1024,
        maxPixel: Int = 1024
    ): String {
        val imagePath: String = getUploadImagePath() + childDir
        val imageDir = File(imagePath)
        if (!imageDir.exists()) {
            val mkdirs = imageDir.mkdirs()
        }
        val fileBytes: ByteArray = check(file, maxSize, maxPixel)
        val originalName = file.originalFilename!!
        val suffix = originalName.substring(originalName.lastIndexOf('.'))
        val newName = UUID.randomUUID().toString() + suffix
        val filePath = Paths.get(imagePath, newName)
        Files.write(filePath, fileBytes)
        return "/upload/$childDir/$newName"
    }


    private fun check(file: MultipartFile, maxSize: Int, maxPixel: Int): ByteArray {
        if (file.contentType == null || !file.contentType!!.startsWith("image/")) {
            throw AppRuntimeException(message = "图片文件格式错误")
        }
        if (file.size > maxSize) {
            throw AppRuntimeException(message = String.format("icon大小不能超过%dKB", maxSize / 1000))
        }
        val fileBytes = file.bytes
        val image = ImageIO.read(ByteArrayInputStream(fileBytes))
        if (image.width > maxPixel || image.height > maxPixel) {
            throw AppRuntimeException(message = String.format("图片尺寸不能超过%d*%d像素", maxPixel, maxPixel))
        }
        return fileBytes
    }


    fun getUploadImagePath(): String? {
        val applicationHome = ApplicationHome()
        val path = applicationHome.dir.path
        val separator = File.separator
        val imagePath =
            path + separator + "src" + separator + "main" + separator + "resources" + separator + "static" + separator + "upload" + separator
        val imageDir = File(imagePath)
        if (!imageDir.exists()) {
            val mkdirs = imageDir.mkdirs()
        }
        return imagePath
    }
}