package develop

import java.io.File
import java.io.IOException
import java.net.URL
import java.security.*
import java.security.cert.CertificateException


class SecurityTool {

    companion object {

        private const val KEY_STORE_TYPE = "pkcs12"

        @Throws(KeyStoreException::class, IOException::class, NoSuchAlgorithmException::class, CertificateException::class)
        fun loadKeyStore(keystoreFile: File?, password: String = "", keyStoreType: String = KEY_STORE_TYPE): KeyStore {
            requireNotNull(keystoreFile) { "Url хранилища ключей не может быть null" }

            val keystoreUrl: URL = keystoreFile.toURI().toURL()
            val keystore: KeyStore = KeyStore.getInstance(keyStoreType)

            keystoreUrl.openStream().use {
                    stream -> keystore.load(stream, password.toCharArray())
            }
            return keystore
        }

        /**
         * Получить по АЛИАСу пару ключей
         */
        fun keyPair(keystore: KeyStore, alias: String?, password: String = ""): KeyPair {
            val cert = keystore.getCertificate(alias)
            val publicKey: PublicKey = cert.publicKey
            val privateKey: PrivateKey = keystore.getKey(alias, password.toCharArray()) as PrivateKey
            return KeyPair(publicKey, privateKey)
        }

        /**
         * Чтение АЛИАСОВ
         */
        fun aliases(keystore: KeyStore) = keystore.aliases().toList()
    }
}