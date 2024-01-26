# Sinema Rezervasyon API
```Bu proje, sinema rezervasyon işlemlerini yönetmek için bir API sağlar. Projede, Spring Boot kullanılarak bir rest api oluşturulmuştur.```

# Kurulum
Aşağıdaki adımları izleyerek projeyi yerel ortamınızda çalıştırabilirsiniz.

# Gereksinimler
Java JDK 21 veya üstü yüklü olmalıdır.
Maven yüklü olmalıdır.
MongoDB

# 1 Adım
Docker kullanarak gerekli olan mongodb sunucusu ayağa kaldırın.

```bash
docker run --name mongo-db -p 27017:27017 -d mongo
```
# 2 Adım 
Bu deponun klonlanması
```bash
git clone https://github.com/burakozkan138/cinemabookingsystem.git
```

# 3 Adım
Öncelikle projenin bulunduğu dizine gidin.
```bash
cd cinemabookingsystem
```

# 4 Adım
Şimdi, gerekli ayarları içeren properties dosyasını kopyalayın.
```bash
copy src/main/resources/application.example.properties src/main/resources/application.properties
```
Daha sonra ilgili dosyada JWT ve mongo db için düzeltilmesi gereken alanları düzeltin.

# Uygulamanın Derlenmesi ve Çalıştırılması

# 5 Adım
Proje dizininde, uygulamayı derleyin:
```bash
mvn clean package
Bu komut, proje bağımlılıklarını indirir, testleri çalıştırır ve bir JAR dosyası oluşturur.
```

# 6 Adım
Derleme işlemi tamamlandıktan sonra, uygulamayı başlatmak için aşağıdaki komutu çalıştırın:
```bash Copy code
java -jar target/sinema-rezervasyon-api.jar
Uygulama başarıyla başladıysa, http://localhost:8080 adresinden API'ye erişebilirsiniz.
```


# Kullanım
API'yi kullanarak sinema rezervasyonları oluşturabilir, güncelleyebilir, silebilir ve sorgulayabilirsiniz. API ile ilgili daha fazla bilgi için belgeleri inceleyin.
### Postman Koleksiyonu
API isteklerini kolayca yapabilmek için [Postman koleksiyonunu buradan indirebilirsiniz](https://www.postman.com/cinemabookingsystem/workspace/cinema-booking-system/overview). Bu koleksiyon, API isteklerini yapmanızı sağlayacak hazır yapılandırılmış istekler içerir. ```Kullanmak için environment olarak Testi seçin```