package org.rizki.mufrizal.signature.rsa.helpers

import java.io.File

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 23 September 2018
 * @Time 6:45 PM
 * @Project Signature-RSA
 * @Package org.rizki.mufrizal.signature.rsa.helpers
 * @File Lang
 *
 */

fun String.isEndSeparator(): Boolean? {
    return this.reversed().startsWith(File.separator)
}