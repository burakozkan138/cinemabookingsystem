# Sinema Rezervasyon API
Bu proje, sinema rezervasyon işlemlerini yönetmek için bir API sağlar. Projede, Spring Boot kullanılarak bir rest api oluşturulmuştur.

# Kurulum
Aşağıdaki adımları izleyerek projeyi yerel ortamınızda çalıştırabilirsiniz.

# Gereksinimler
Java JDK 21 veya üstü yüklü olmalıdır.
Maven yüklü olmalıdır.
MongoDB

# 1 Adım
Bu depoyu klonlamak için aşağıdaki komutu kullanabilirsiniz:
```bash
git clone https://github.com/burakozkan138/cinemabookingsystem.git
```

# 2 Adım
Öncelikle projenin bulunduğu dizine gidin.
```bash
cd cinemabookingsystem
```

# 3 Adım
İlk olarak aşağıdaki komotu kullanarak docker compose ile birlikte projemizi build edelim.
```bash
docker-compose build --build-arg JWT_SECRET_KEY=mysecretsecretkey
```
Ekleyebileceğiniz diğer argumanlar ve örnek değerleri.
```
SERVER_SERVLET_CONTEXT_PATH=/api/v1
SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/
SPRING_DATA_MONGODB_DATABASE=cinemabookingsystem
JWT_SECRET_KEY=mysecretkey
JWT_EXPIRATION_TIME=86400000
```

# 4 Adım
Ve daha sonra alttaki komutu kullanarak uygulamamızı ayağa kaldırıyoruz.
```bash
docker-compose up -d
```
Uygulama başarıyla başladıysa, *http://localhost:8080* adresinden API'ye erişebilirsiniz.
ayrıca 27017 portu üzerinde mongodb'ye erişebilirsiniz.

# Kullanım
API'yi kullanarak sinema rezervasyonları oluşturabilir, güncelleyebilir, silebilir ve sorgulayabilirsiniz. API ile ilgili daha fazla bilgi için belgeleri inceleyin.
### Postman Koleksiyonu
API isteklerini kolayca yapabilmek için [Postman koleksiyonunu buradan indirebilirsiniz](https://www.postman.com/cinemabookingsystem/workspace/cinema-booking-system/overview). Bu koleksiyon, API isteklerini yapmanızı sağlayacak hazır yapılandırılmış istekler içerir. ```Kullanmak için environment olarak Testi seçin```

# Admin Bilgileri
API'ye erişim yetkileri olan bir yönetici kullanıcısını kullanarak özel işlemler gerçekleştirebilirsiniz. Varsayılan yönetici hesabı bilgileri aşağıdaki gibidir:
- **Kullanıcı Adı:** admin
- **Şifre:** 123456
Bu bilgileri kullanarak API'ye yönetici olarak erişebilir ve özel işlemleri gerçekleştirebilirsiniz.

# Postman İle Gönderilmiş Bazı İstekler

**Kayıt olma işlemi.**
**Endpoint: api/v1/auth/register**

![Kayıt Ol](https://github.com/burakozkan138/cinemabookingsystem/blob/master/postman/img1.png)

**Giriş Yapma işlemi.**
**Endpoint: api/v1/auth/login**
![Giriş Yap](https://github.com/burakozkan138/cinemabookingsystem/blob/master/postman/img2.png)

**Session Oluşturma işlemi**
**Endpoint: api/v1/sessions**
![Session Oluştur](https://github.com/burakozkan138/cinemabookingsystem/blob/master/postman/img3.png)


**Reservation Oluşturma işlemi**
**Endpoint: api/v1/reservations**
![Reservation Oluştur](https://github.com/burakozkan138/cinemabookingsystem/blob/master/postman/img4.png)


**Reservation Oluşturma işlemi Dolu Koltuk Hatası**
**Endpoint: api/v1/reservations**
![Reservation Oluştur Ancak Dolu Koltuğa](https://github.com/burakozkan138/cinemabookingsystem/blob/master/postman/img5.png)