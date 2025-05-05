
markdown

Kopyala
# Surveyapp

Bu proje, [projenin kısa açıklaması, örneğin "bir anket yönetim sistemi"]. Spring Boot ve MySQL kullanılarak geliştirilmiştir.

## Gereksinimler
- **Java**: JDK 17 veya üstü
- **Maven**: En son sürüm
- **MySQL**: 8.0 veya üstü
- **Git**: Proje dosyalarını indirmek için

## Kurulum Adımları

### 1. Projeyi İndir
```bash
git clone https://github.com/kullanıcı-adın/proje-adı.git
cd proje-adı
2. Veritabanını Kur ve Uygulamaya Bağla
MySQL sunucunuzu başlatın (örneğin, XAMPP, WAMP veya manuel MySQL kurulumunu kullanabilirsiniz).
MySQL istemcisini açın (örneğin, komut satırında mysql -u root -p komutunu çalıştırın ve şifrenizi girin).
Yeni bir veritabanı oluşturun:
bash

Kopyala
CREATE DATABASE anketdb;
Proje ile birlikte sağlanan db/database.sql dosyasını veritabanına içe aktarın:
bash

Kopyala
mysql -u root -p anketdb < db/database.sql
src/main/resources/application.properties dosyasını açın ve MySQL bağlantı bilgilerinizi kendi sisteminize göre güncelleyin:
properties

Kopyala
spring.datasource.url=jdbc:mysql://localhost:3306/anketdb
spring.datasource.username=root
spring.datasource.password=şifreniz
spring.jpa.hibernate.ddl-auto=update
Not: username, password ve url değerlerini kendi MySQL ayarlarınıza göre düzenleyin.
3. Projeyi Çalıştır
Aşağıdaki komutları sırayla çalıştırın:

bash

Kopyala
mvn clean install
mvn spring-boot:run
Alternatif olarak, JAR dosyasını oluşturup çalıştırabilirsiniz:

bash

Kopyala
mvn clean package
java -jar target/proje-adi.jar
4. Uygulamaya Eriş
Tarayıcınızda şu adrese gidin: http://localhost:8080/surveys

Notlar
Eğer bir hata alırsanız, bağımlılıkların doğru yüklendiğinden emin olun (mvn clean install).
Veritabanı bağlantı ayarlarını kendi sisteminize göre güncellemeyi unutmayın.
database.sql dosyasının projenin db klasöründe olduğundan emin olun.
