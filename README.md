# eshop

Advanced Programming Genap 2024/2025 Tutorial. Fadhlurohman Dzaki - 2306202132

## Tutorial 1

### Reflection 1

> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

Selama mengimplementasikan dua fitur baru menggunakan **Spring Boot**, saya telah menerapkan beberapa prinsip **clean code** dan **secure coding**. Salah satu prinsip **secure coding** yang saya gunakan adalah pemanfaatan **UUID** sebagai ID objek, yang membantu mencegah prediksi ID secara mudah dibandingkan dengan ID berurutan.

Dari sisi clean code, saya telah menerapkan **Dependency Injection (DI)** untuk mengelola ketergantungan antar komponen, tetapi dalam implementasi `ProductServiceImpl.java`, saya masih menggunakan **field injection** dengan anotasi `@Autowired`. Meskipun ini membuat kode lebih ringkas, pendekatan ini memiliki kelemahan, seperti sulitnya melakukan unit testing karena dependensi langsung disuntikkan ke dalam field tanpa melalui constructor.

Untuk meningkatkan kualitas kode dan memastikan prinsip clean code diterapkan dengan lebih baik, saya dapat mengubah field injection menjadi constructor injection. Pendekatan ini memungkinkan dependensi disuntikkan melalui konstruktor saat objek dibuat, sehingga menjadikannya **immutable** dan memastikan bahwa semua dependensi tersedia sejak awal. Selain itu, penggunaan constructor injection juga memudahkan pengujian karena memungkinkan penyuntikan dependensi secara eksplisit dalam unit test menggunakan mock object tanpa perlu bergantung pada framework Spring. Dengan cara ini, kode menjadi lebih fleksibel dan dapat digunakan di luar konteks Spring jika diperlukan, sehingga meningkatkan keterpisahan dan modularitas komponen. Selain itu, constructor injection menghilangkan ketergantungan pada mekanisme refleksi yang digunakan dalam field injection, sehingga dapat meningkatkan efisiensi dan keamanan kode. Dengan menerapkan perubahan ini, struktur kode menjadi lebih bersih dan lebih mudah dipelihara.

### Reflection 2

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?
>
> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
> **What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!**


1. Setelah membuat unit test, saya menjadi lebih yakin apakah kode yang saya buat berjalan dengan baik atau tidak. Unit test membantu saya mendeteksi kesalahan lebih awal dan memastikan setiap fungsi bekerja sesuai yang diharapkan. Menurut saya, tidak perlu membuat terlalu banyak unit test, cukup fokus pada fungsionalitas inti program yang paling penting dan rentan terhadap kesalahan. Namun, untuk memastikan bahwa unit test yang dibuat sudah cukup untuk memverifikasi program, sebaiknya mempertimbangkan penggunaan metrik **code coverage**.Code coverage membantu kita memahami seberapa banyak bagian kode yang telah diuji. Meskipun mencapai **100% code coverage** dapat memberikan keyakinan bahwa seluruh baris kode telah diuji, hal itu tidak selalu menjamin bahwa program benar-benar bebas dari bug atau kesalahan. Ini karena code coverage hanya mengukur apakah suatu bagian kode dieksekusi saat pengujian, tetapi tidak menjamin bahwa semua skenario atau kasus edge telah diuji. Oleh karena itu, selain memastikan cakupan kode yang memadai, penting juga untuk mempertimbangkan berbagai kemungkinan kasus yang dapat terjadi seperti edge case dan negative scenario case agar dapat meningkatkan fungsionalitas program terhadap berbagai skenario yang mungkin terjadi di dunia nyata.


2. Menurut saya, **cleanliness** dari kode pada functional test suite yang baru akan berkurang karena program tersebut melakukan task yang sama persis dengan program sebelumnya, sehingga menyebabkan **ketidakefisienan**. Hal ini melanggar prinsip **DRY (Don't Repeat Yourself)** dan dapat menyebabkan **duplikasi kode** yang meningkatkan risiko inkonsistensi dan bug saat ada perubahan. Selain itu, kode yang duplikatif juga lebih sulit dipelihara dan dibaca, karena pengembang harus memahami perbedaan antara dua test yang tampak serupa. Untuk mengatasi masalah ini, sebaiknya kita melakukan **refactoring** dengan mengekstrak bagian kode yang sama ke dalam metode atau kelas terpisah yang dapat digunakan kembali.



## Tutorial 2

### Reflection
>You have implemented a CI/CD process that automatically runs the test suites, analyzes code quality, and deploys to a PaaS. Try to answer the following questions in order to reflect on your attempt completing the tutorial and exercise.
>1.List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
>2.Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

1. Adapun code quality issue yang saya perbaiki adalah mengurangi penggunaan import yang tidak terpakai. Sebelumnya, saya menggunakan import org.springframework.web.bind.annotation.*, yang secara implisit mengimpor semua kelas dalam paket tersebut. Namun, praktik ini dapat menyebabkan kode menjadi kurang eksplisit dan meningkatkan kemungkinan konflik nama di masa mendatang.
Sebagai solusinya, saya mengganti wildcard import tersebut dengan hanya mengimpor kelas-kelas yang benar-benar digunakan, seperti import org.springframework.web.bind.annotation.PostMapping;. Dengan pendekatan ini, kode menjadi lebih bersih, lebih mudah dipahami, serta mempermudah proses debugging dan maintanence di kemudian hari.
2. Saya merasa implementasi kode saya sudah sesuai dengan prinsip Continuous Integration dan Continuous Deployment. Setiap kali ada perubahan kode yang di-push, workflow otomatis akan menjalankan pengujian dan memeriksa apakah ada masalah dalam kode. Ini membantu saya memastikan bahwa aplikasi tetap berjalan dengan baik, bahkan setelah fitur baru ditambahkan ke branch utama. Selain itu, sistem deployment yang saya gunakan, yaitu Koyeb, secara otomatis melakukan redeploy setiap kali ada perubahan di branch utama. Dengan begitu, saya bisa yakin bahwa versi yang terdeploy selalu yang terbaru, dan proses integrasi serta deployment berjalan dengan lancar.










