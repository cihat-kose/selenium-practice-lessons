package utility;

public class MyFunction {

    /**
     * Eğitim örneklerinde sabit süreli beklemeyi (Thread.sleep) göstermek için kullanılır.
     * Tarayıcının hazır olup olmadığını kontrol etmez; süre dolana kadar her durumda bekler.
     * Gerçek senaryolarda, koşul gerçekleşir gerçekleşmez devam etmek için
     * WebDriverWait.until(...) tercih edilmelidir.
     *
     * @param seconds beklenecek saniye sayısı
     */
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            // Kesilme bilgisini kaybetmemek için thread'in interrupt durumunu geri yükle.
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sabit bekleme kesintiye uğradı.", e);
        }
    }
}
