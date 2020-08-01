# tutorial-microservices-ssl-communication

## Generate self signed certificate
> keytool -genkeypair -alias serverkeystore -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore serverkeystore.p12 -validity 3650


or
> keytool -genkey -alias serverkeystore -keyalg RSA -keystore serverkeystore.jks \
          -validity 3650 -storetype JKS \
          -dname "CN=localhost, OU=Spring, O=Pivotal, L=Kailua-Kona, ST=HI, C=US"  1
          -keypass your_key_password -storepass your_store_password
          
* CN is the only important parameter here. It should match the domain you are trying to access, e.g. localhost

** we can use this certificat for client and server which is not recomanded, so we generate public key from this to share to others

## Extract public certificate from generated store
> keytool -export -alias serverkeystore -keystore serverkeystore.p12 -file server.cer -storepass your_store_password

## Let's use the exported certificate for the client by adding it to its truststore
>  keytool -import -v -trustcacerts -file server.cer -keypass your_key_password -storepass your_store_password -keystore clienttruststore.p12
