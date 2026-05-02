# 📚 Kütüphane Yönetim Sistemi

Bu proje, modern Android geliştirme pratikleri kullanılarak hazırlanmış, **Supabase** tabanlı bir kütüphane yönetim uygulamasıdır. Kullanıcıların kitapları arayabildiği, stok durumlarını anlık takip edebildiği ve ödünç alma/iade işlemlerini gerçekleştirebildiği bir çözüm sunar.

## 🌟 Öne Çıkan Özellikler

* **Dinamik Arama & Filtreleme:** Kitap adı veya yazar ismine göre anlık (real-time) filtreleme.
* **Akıllı Stok Yönetimi:** `available_copies` sütunu üzerinden otomatik stok düşürme ve artırma işlemleri.
* **Oturum Yönetimi:** Güvenli Kayıt Ol/Giriş Yap ve "Tek Tıkla" Çıkış Yap özellikleri.
* **Durum Takibi (State Management):** Kitapların kullanıcıda olup olmadığını kontrol eden "Zaten Sende" mantığı.
* **Tarih Bazlı Kontrol:** İade işlemleri için `returned_at` sütunu güncellenerek kayıtların kalıcılığı sağlanır.



## 📸 Uygulama Ekran Görüntüleri

### 1. Ana Sayfa & Kitap Listesi
Uygulama açıldığında kullanıcıyı karşılayan, tüm kitapların listelendiği ve arama yapılabilen ana ekran.
![Kitap Listesi](kitap_listesi.png)

### 2. Kiralamalarım & İade Ekranı
Kullanıcının aktif ödünç aldığı kitapları takip edebildiği ve tek tıkla iade edebildiği ekran.
![Kiralamalarım](kiralamalar.png)

### 3. Supabase Veritabanı (borrow_records)
Arka planda tüm işlemlerin ilişkisel olarak tutulduğu veritabanı tablosu.
![Supabase Kayıtları](supabase_borrow_records.png)


## 🛡️ Güvenlik (RLS)
Projede **Row Level Security (RLS)** kullanılarak verilerin güvenliği sağlanmıştır. SQL Editor üzerinden tanımlanan politikalar sayesinde her kullanıcı sadece kendi kiralama verilerine erişebilir.