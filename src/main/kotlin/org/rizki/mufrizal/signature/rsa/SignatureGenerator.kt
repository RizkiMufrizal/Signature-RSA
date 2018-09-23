package org.rizki.mufrizal.signature.rsa

import org.rizki.mufrizal.signature.rsa.helpers.isEndSeparator
import org.rizki.mufrizal.signature.rsa.readers.PrivateKeyReader
import org.rizki.mufrizal.signature.rsa.readers.PrivateKeyReaderStorage
import java.io.File
import java.nio.charset.Charset
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.Signature
import java.security.SignatureException
import java.util.Base64

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 23 September 2018
 * @Time 6:44 PM
 * @Project Signature-RSA
 * @Package org.rizki.mufrizal.signature.rsa
 * @File SignatureGenerator
 *
 */

class SignatureGenerator {
    companion object {
        @JvmStatic
        fun generateSignature(plainText: String? = null, privateKey: String? = null, rsaAlgorithm: String? = null): String? {
            val privateKeyReader = PrivateKeyReader.get(privateKey = privateKey)
            try {
                val signature = Signature.getInstance(rsaAlgorithm)
                signature.initSign(privateKeyReader)
                signature.update(plainText?.toByteArray(Charset.defaultCharset()))
                return Base64.getEncoder().encodeToString(signature.sign())
            } catch (ex: NoSuchAlgorithmException) {
                ex.printStackTrace()
            } catch (ex: InvalidKeyException) {
                ex.printStackTrace()
            } catch (ex: SignatureException) {
                ex.printStackTrace()
            }
            return null
        }

        @JvmStatic
        fun generateSignature(plainText: String? = null, path: String? = null, fileName: String? = null, rsaAlgorithm: String? = null): String? {
            val pathSeparator = if (path?.isEndSeparator() == false) "$path${File.separator}$fileName" else "$path$fileName"
            val privateKeyReader = PrivateKeyReaderStorage.get(pathSeparator)
            try {
                val signature = Signature.getInstance(rsaAlgorithm)
                signature.initSign(privateKeyReader)
                signature.update(plainText?.toByteArray(Charset.defaultCharset()))
                return Base64.getEncoder().encodeToString(signature.sign())
            } catch (ex: NoSuchAlgorithmException) {
                ex.printStackTrace()
            } catch (ex: InvalidKeyException) {
                ex.printStackTrace()
            } catch (ex: SignatureException) {
                ex.printStackTrace()
            }
            return null
        }
    }
}