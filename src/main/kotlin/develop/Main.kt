package develop


import develop.SecurityUtil.Companion.aliases
import develop.SecurityUtil.Companion.keyPair
import develop.SecurityUtil.Companion.loadKeyStore
import java.io.File
import java.net.URL


fun main() {

    val pfxUrl: URL = ClassLoader.getSystemResource("certificate.pfx")

    val keyStore = loadKeyStore(File(pfxUrl.toURI()))

    val firstAlias = aliases(keyStore)[0]

    println("Alias: $firstAlias")
    println("----------------------------------------------------------------")

    val keyPair = keyPair(keyStore, firstAlias)

    println(keyPair.public)
    println("----------------------------------------------------------------")
    println(keyPair.private)
}