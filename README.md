# Чтение PFX сертификата

## Create Cert

1. Create a key
```shell
openssl genrsa -out key.pem 2048
```

1. Create certificate sign-in request
```shell
openssl req -new -sha256 -key key.pem -out csr.csr
```
Enter whatever information you wish

1. Create certificate
```shell
openssl req -x509 -sha256 -days 365 -key key.pem -in csr.csr -out certificate.pem
```

1. Convert to .pfx file
```shell
openssl pkcs12 -export -inkey key.pem -in certificate.pem -out certificate.pfx
```