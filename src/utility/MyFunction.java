package utility;

public class MyFunction {

    public static void wait(int sn) { // "wait" adında bir metot tanımlanıyor, "sn" adında bir tamsayı parametre alıyor
        try {
            Thread.sleep(1000L * sn); // Belirtilen süre kadar beklemek için Thread.sleep() kullanılıyor
        } catch (InterruptedException e) { // Eğer beklemeyi kesintiye uğratan bir hata oluşursa
            throw new RuntimeException(e); // RuntimeException hatası fırlatılıyor
        }
    }
}
