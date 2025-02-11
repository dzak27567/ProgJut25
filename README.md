# eshop

Advanced Programming Genap 2024/2025 Tutorial. Fadhlurohman Dzaki - 2306202132

## Tutorial 1

### Reflection 1

> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

Selama mengimplementasikan dua fitur baru menggunakan **Spring Boot**, saya telah menerapkan beberapa prinsip **clean code** dan **secure coding**. Salah satu prinsip **secure coding** yang saya gunakan adalah pemanfaatan **UUID** sebagai ID objek, yang membantu mencegah prediksi ID secara mudah dibandingkan dengan ID berurutan.

Dari sisi clean code, saya telah menerapkan **Dependency Injection (DI)** untuk mengelola ketergantungan antar komponen, tetapi dalam implementasi `ProductServiceImpl.java`, saya masih menggunakan **field injection** dengan anotasi `@Autowired`. Meskipun ini membuat kode lebih ringkas, pendekatan ini memiliki kelemahan, seperti sulitnya melakukan unit testing karena dependensi langsung disuntikkan ke dalam field tanpa melalui constructor.

Untuk meningkatkan kualitas kode dan memastikan prinsip clean code diterapkan dengan lebih baik, saya dapat mengubah field injection menjadi constructor injection. Pendekatan ini memungkinkan dependensi disuntikkan melalui konstruktor saat objek dibuat, sehingga menjadikannya **immutable** dan memastikan bahwa semua dependensi tersedia sejak awal. Selain itu, penggunaan constructor injection juga memudahkan pengujian karena memungkinkan penyuntikan dependensi secara eksplisit dalam unit test menggunakan mock object tanpa perlu bergantung pada framework Spring. Dengan cara ini, kode menjadi lebih fleksibel dan dapat digunakan di luar konteks Spring jika diperlukan, sehingga meningkatkan keterpisahan dan modularitas komponen. Selain itu, constructor injection menghilangkan ketergantungan pada mekanisme refleksi yang digunakan dalam field injection, sehingga dapat meningkatkan efisiensi dan keamanan kode. Dengan menerapkan perubahan ini, struktur kode menjadi lebih bersih dan lebih mudah dipelihara.
