# Signature-RSA

Signature-RSA is library for create and verify signature using RSA key. For create signature, we use private key and for verify signature we use public key.

## How To Use

When you use this library, you can generate public and private key using command openssl like this.

for generate private key

```bash
openssl genrsa -passout pass:"rizkimufrizal" -des3 -out private.pem 2048
```

for generate public key

```bash
openssl rsa -in private.pem -outform PEM -pubout -out public.pem -passin pass:"rizkimufrizal"
```

convert private key to pkcs8 format in order to import it from kotlin

```bash
openssl pkcs8 -topk8 -in private.pem -inform pem -out private_key_pkcs8.pem -outform pem -nocrypt -passin pass:"rizkimufrizal"
```

or convert private key to unecnrypted format in order to import it from kotlin

```bash
openssl rsa -in private.pem -out private_unencrypted.pem -outform PEM -passin pass:"rizkimufrizal"
```

You can use one of the private keys. In this example, we use private key in pkcs8 format.

## Algorithm

You can use of this list algorithm :

 * MD2withRSA
 * MD5WithRSA
 * SHA1withRSA
 * SHA224withRSA
 * SHA256withRSA
 * SHA384withRSA
 * SHA512withRSA

## Author

* [Rizki Mufrizal](https://github.com/RizkiMufrizal)

## License

Signature-RSA is Open Source software released under the Apache 2.0 license.
