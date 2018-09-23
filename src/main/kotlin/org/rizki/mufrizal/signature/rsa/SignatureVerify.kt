package org.rizki.mufrizal.signature.rsa

import org.rizki.mufrizal.signature.rsa.helpers.isEndSeparator
import org.rizki.mufrizal.signature.rsa.readers.PublicKeyReader
import org.rizki.mufrizal.signature.rsa.readers.PublicKeyReaderStorage
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
 * @Time 6:45 PM
 * @Project Signature-RSA
 * @Package org.rizki.mufrizal.signature.rsa
 * @File SignatureVerify
 *
 */

class SignatureVerify {
    companion object {
        @JvmStatic
        fun isValid(paylod: String? = null, encryptText: String? = null, publicKey: String? = null, rsaAlgorithm: String? = null): Boolean? {
            if (encryptText?.isEmpty() == true) {
                return false
            }
            val publicKeyReader = PublicKeyReader.get(publicKey = publicKey)
            try {
                val signature = Signature.getInstance(rsaAlgorithm)
                signature.initVerify(publicKeyReader)
                signature.update(paylod?.toByteArray(Charset.defaultCharset()))
                return signature.verify(Base64.getDecoder().decode(encryptText))
            } catch (ex: NoSuchAlgorithmException) {
                ex.printStackTrace()
            } catch (ex: InvalidKeyException) {
                ex.printStackTrace()
            } catch (ex: SignatureException) {
                ex.printStackTrace()
            }
            return false
        }

        @JvmStatic
        fun isValid(paylod: String? = null, encryptText: String? = null, path: String? = null, fileName: String? = null, rsaAlgorithm: String? = null): Boolean? {
            if (encryptText?.isEmpty() == true) {
                return false
            }
            val pathSeparator = if (path?.isEndSeparator() == false) "$path${File.separator}$fileName" else "$path$fileName"
            val publicKeyReader = PublicKeyReaderStorage.get(pathSeparator)
            try {
                val signature = Signature.getInstance(rsaAlgorithm)
                signature.initVerify(publicKeyReader)
                signature.update(paylod?.toByteArray(Charset.defaultCharset()))
                return signature.verify(Base64.getDecoder().decode(encryptText))
            } catch (ex: NoSuchAlgorithmException) {
                ex.printStackTrace()
            } catch (ex: InvalidKeyException) {
                ex.printStackTrace()
            } catch (ex: SignatureException) {
                ex.printStackTrace()
            }
            return false
        }
    }
}