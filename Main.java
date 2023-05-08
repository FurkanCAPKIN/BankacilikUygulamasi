//Furkan Çapkın, Bankacılık Uygulaması
import java.util.*;


class Kisi {
    private int tcKimlikNo;
    private String ad;
    private String soyad;
    private String email;
    private long telefonNumarasi;

    public int getTcKimlikNo() {
        return tcKimlikNo;
    }
    public void setTcKimlikNo(int tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public long getTelefonNumarasi() {
        return telefonNumarasi;
    }
    public void setTelefonNumarasi(long telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }


    public Kisi(){

    }
    public Kisi(String ad,String soyad,String email,int telefonNumarasi,int tc){
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefonNumarasi = telefonNumarasi;
        this.tcKimlikNo = tc;

    }

    @Override
    public String toString() {
        return super.toString();
    }

}

class BankaPersonel extends Kisi{
    private int personelID;
    ArrayList<Musteri> musteriler = new ArrayList<Musteri>();


    public BankaPersonel(){

    }
    public BankaPersonel (int tcKimlikNo,String ad,String soyad,String email,int telefonNumarasi){


    }

    @Override
    public String toString() {
        return super.toString();
    }

}


class Musteri extends Kisi{
    private int MusteriNumarasi;
    public ArrayList<BankaHesap> hesaplar = new ArrayList<BankaHesap>();
    public ArrayList<KrediKarti> krediKartlari = new ArrayList<KrediKarti>();

    Kisi kisi = new Kisi();


    public Musteri(){

    }
    public Musteri (int tcKimlikNo,int musteriNumarasi,String ad,String soyad,String email,int telefonNumarasi){

    }

    public void hesapEkle(long tcKimlikNo,long musteriNumarasi,String ad,String soyad,String hesapTuru,float faizOrani,double bakiye,int hesapsayisi,String yatirimTuru,int yatirimEkle,double yatirimEklenecekTutar,double yatirimHesabıSilinecekTutar){

        if ( hesapTuru == "Vadeli Hesap"){
            VadeliHesap vadeliHesap = new VadeliHesap(hesapTuru,bakiye,faizOrani);
            hesaplar.add(hesapsayisi,vadeliHesap);

        }else if ( hesapTuru == "Vadesiz Hesap"){
            VadesizHesap vadesizHesap = new VadesizHesap(hesapTuru,bakiye);
            hesaplar.add(hesapsayisi,vadesizHesap);

        }else if ( hesapTuru == "Yatırım Hesabı"){

            YatirimHesabi yatirimHesabi = new YatirimHesabi(hesapTuru, bakiye, yatirimTuru, yatirimEkle);

            if(yatirimEkle == 0){
                hesaplar.add(hesapsayisi, yatirimHesabi);
            }else if(yatirimEkle == 1){

                yatirimHesabi.yatirimBakiye = yatirimHesabi.paraEkle(yatirimHesabi.yatirimBakiye, yatirimTuru, yatirimHesabi.kur, yatirimEklenecekTutar);

            }else if (yatirimEkle == 2){

                yatirimHesabi.yatirimBakiye = yatirimHesabi.paraBoz(yatirimHesabi.yatirimBakiye,yatirimTuru, yatirimHesabi.kur,yatirimHesabıSilinecekTutar);

            }else{
                System.out.println("YATIRIM HESAP EKLE YANLIŞ");
            }


        }else {
            System.out.println("!!!HATALI GİRİŞ!!!");
        }

    }

    public void hesapSil(int tcKimlikNo,long musteriNumarasi,String ad,String soyad,int silinecekHesapIndeksi){

        hesaplar.remove(silinecekHesapIndeksi);

    }

    @Override
    public String toString() {
        return super.toString();
    }

}

class BankaHesap{
    public long iban;
    public Date hesapAcilisTarih;
    public double toplamBakiye;
    public String hesapBilgisi;
    public double islemMiktari;
    public String hesapTuru;

    public int hesapBilgisı;


    public BankaHesap(){

    }
    public BankaHesap(long iban,double toplamBakiye,int hesapBilgisı){

        this.iban = iban;
        this.toplamBakiye = toplamBakiye;
        this.hesapBilgisı = hesapBilgisı;

        if(hesapBilgisı == 1){
            this.hesapBilgisi = "Maaş hesabı";
        }else if(hesapBilgisı == 2){
            this.hesapBilgisi = "Normal hesap";
        }else{
            System.out.println("Hesap bilgisini yanlış girdiniz.");
        }

        System.out.println("Hesap bilgisi: "+this.hesapBilgisi);

        System.out.println("İbanınız: TR28 "+this.iban+"\n");

    }
    public void hesapGoruntuleme(int musteriNumarasi,String ad,String soyad,long iban,double toplamBakiye,String hesapBilgisi){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class VadeliHesap extends BankaHesap{
    public String hesapTuru;
    public double vadeliBakiye;
    public float faizOrani;

    public VadeliHesap(){

    }
    public VadeliHesap(String hesapTuru,double vadeliBakiye,float faizOrani){
        this.hesapTuru = hesapTuru;
        this.vadeliBakiye = vadeliBakiye;
        this.faizOrani = faizOrani;

        System.out.println("\nVADELİ HESAP OLUŞTURULDU.\n\nHesap türü = " +this.hesapTuru+
                "\nVadeli hesap bakiyesi = "+this.vadeliBakiye+
                "\nVadeli hesap faiz oranı = %"+this.faizOrani);

    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class VadesizHesap extends BankaHesap{
    public String hesapTuru;
    public double vadesizBakiye;
    public double guncelBorç;

    public int islemMiktari=1;
    public VadesizHesap(){

    }

    BankaHesap bankaHesap = new BankaHesap();
    public VadesizHesap(String hesapTuru,double vadesizBakiye){
        this.hesapTuru = hesapTuru;
        this.vadesizBakiye = vadesizBakiye;

        System.out.println("\nVADESİZ HESAP OLUŞTURULDU.\n\nHesap türü = " +this.hesapTuru+
                "\nVadeli hesap bakiyesi = "+this.vadesizBakiye);


    }
    public double paraTransfer(String hesapTuru,long iban,double vadesizBakiye){

        if (bankaHesap.hesapBilgisı == 1){
            islemMiktari = 8;
            System.out.println("İslem miktarı :"+islemMiktari);

        }else if(bankaHesap.hesapBilgisı == 2){
            islemMiktari = 0;
            System.out.println("İslem miktarı :"+islemMiktari);

        }else{
            System.out.println("Hesap bilgisi yanlış girildi.");
        }


        return 1.0;
    }
    public double krediKartiBorcOdeme(double vadesizBakiye,KrediKarti güncelBorc){

        this.vadesizBakiye = vadesizBakiye;
        this.guncelBorç = güncelBorc.guncelBorc;

        guncelBorç = guncelBorç - vadesizBakiye;

        return guncelBorç;
    }
    public double krediOdeme(int krediID,Krediler krediMiktari,Krediler taksitMiktari){




        return 1.0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class YatirimHesabi extends BankaHesap{
    public String hesapTuru;
    public double yatirimBakiye;
    public String yatirimTuru;
    public float kur;


    public YatirimHesabi(){

    }
    public YatirimHesabi(String hesapTuru,double yatirimBakiye,String yatirimTuru,int yatirimekleme){
        this.hesapTuru = hesapTuru;
        this.yatirimBakiye = yatirimBakiye;
        this.yatirimTuru = yatirimTuru;

        if( yatirimekleme == 0) {
            System.out.println("\nYATIRIM HESAP OLUŞTURULDU.\n\nHesap türü = " + this.hesapTuru +
                    "\nYatırım türü: " + this.yatirimTuru +
                    "\nYatırım hesabı bakiyesi = " + this.yatirimBakiye);
        }
    }
    public double paraEkle(double yatirimBakiye,String yatirimTuru,float kur,double yatirimEklenecekTutar){

        yatirimBakiye = (yatirimBakiye + yatirimEklenecekTutar);

        return yatirimBakiye;
    }
    public double paraBoz(double yatirimBakiye,String yatirimTuru,float kur,double yatirimHesabıSilinecekTutar){

        yatirimBakiye = (yatirimBakiye - yatirimHesabıSilinecekTutar);

        return yatirimBakiye;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


class KrediKarti{
    private int kartNumarasi;
    public double limit;
    public double guncelBorc;

    public double kullanilabilirLimit;

    public int getKartNumarasi() {
        return kartNumarasi;
    }
    public void setKartNumarasi(int kartNumarasi) {
        this.kartNumarasi = kartNumarasi;
    }




    public KrediKarti(){

    }
    public KrediKarti(int kartNumarasi,double limit,double guncelBorc,double kullanilabilirLimit){

        this.kartNumarasi = kartNumarasi;
        this.limit = limit;
        this.guncelBorc = guncelBorc;
        this.kullanilabilirLimit = kullanilabilirLimit;

    }
    public void krediKartiEkle(int kartNumarasi,double limit,int krediKartSayisi,KrediKarti krediKarti,Musteri musteri){

        musteri.krediKartlari.add(krediKartSayisi,krediKarti);

        System.out.println("\nKREDİ KARTINIZ OLUŞTU.\n" +
                "\nKart numaranız: "+kartNumarasi+
                "\nLimitiniz: " +limit+
                "\nGüncel borcunuz: "+guncelBorc+
                "\nKullanılabilir Limitiniz: "+kullanılabilirLimit(this.kartNumarasi,this.limit,guncelBorc)+"\n\n");


    }
    public void krediKartiSil(int kartNumarasi,double limit,double guncelBorc,KrediKarti krediKarti,Musteri musteri,int silinecekKartIndeksi){

        if (guncelBorc == 0){
            musteri.krediKartlari.remove(silinecekKartIndeksi);
        }else{
            System.out.println("Lütfen öncelikle borç ödemesi yapınız");
        }

    }
    public double kullanılabilirLimit(int kartNumarasi,double limit,double guncelBorc){

        double sonLimit = limit-guncelBorc;
        return sonLimit;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
class Krediler{
    private int krediID;
    public double krediMiktarı;
    public double taksitMiktari;

    public int getKrediID() {
        return krediID;
    }
    public void setKrediID(int krediID) {
        this.krediID = krediID;
    }

    public Krediler(){

    }
    public Krediler(int krediID,long musteriNumarası,int yillikKazanc,int krediMiktari){

        this.krediMiktarı = krediMiktari;
        System.out.println("\nVerilen kredi miktarı :"+krediMiktarı+"\n\n");

    }
    public void kampanya (KrediKarti limit){

    }
    @Override
    public String toString() {
        return super.toString();
    }
}
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        Kisi kisi = new Kisi();
        BankaPersonel bankaPersonel = new BankaPersonel();
        Musteri musteri = new Musteri();
        BankaHesap bankaHesap = new BankaHesap();
        KrediKarti krediKarti = new KrediKarti();
        Krediler krediler = new Krediler();


        int acilanHesapCesidi=0;
        long iban=0;
        int acilanHesapBilgisi=0;
        int silinenHesapCesidi=0;
        String isim;
        String soyisim;
        int tc=0;
        String mail;
        long telNo;
        int secilenIslem;
        int acilanHesapSayisi=0;
        int krediKartSayisi=0;
        int silinecekHesapIndeksii=0;
        int silinecekKrediKartiIndeksi=0;
        int vadeliBakiye=0;
        int vadesizBakiye=0;
        float faizOrani = 0F;
        String yatirimTuru = "yatırım türü girilmedi.";
        double yatirimBakiye=0;
        int kartNumarasi=0;
        double kullanılabilirLimit=0;
        double guncelBorc=0.0;
        int limit=0;
        int yillikKazanc=0;
        int krediID=0;
        int odenecekKrediMiktari=0;
        int krediMiktari=0;
        double yatirimHesabıEklenecekTutar=0;
        int mevcutKur=0;
        int yatirimEkleme=0;
        double yatirimHesabıSilinecekTutar=0;

        //TC kimlik numarası random bir şekilde değer alıyor.1
        tc = (300000 + random.nextInt(100000));
        kisi.setTcKimlikNo(tc);


        while (true) {
            System.out.println("\n\nYapmak istediğiniz işlemi seçin ve tuşlayın:\n" +
                    "1 -> Hesap ekle.\n" +
                    "2 -> Hesap sil.\n"+
                    "3 -> Kredi kartı ekle.\n"+
                    "4 -> Kredi Kartı sil.\n"+
                    "5 -> Kredi çek.\n"+
                    "6 -> Kredi kartı borcumu öde.\n"+
                    "7 -> Kredimi öde.\n"+
                    "8 -> Yatırım hesabına para ekle.\n"+
                    "9 -> Yatırım hesabından para çek.\n"+
                    "0 -> Çıkış");
            secilenIslem = input.nextInt();


            if (secilenIslem == 1) {

                System.out.println("Adınız: ");
                isim = input.next();
                kisi.setAd(isim);

                System.out.println("Soyadınız: ");
                soyisim = input.next();
                kisi.setSoyad(soyisim);

                System.out.println("TC kimlik numaranız: "+tc+"\n");

                System.out.println("Mail adresiniz: ");
                mail = input.next();
                kisi.setEmail(mail);

                System.out.println("Telefon Numaranız: ");
                telNo = input.nextLong();
                kisi.setTelefonNumarasi(telNo);

                iban = (7000000 + random.nextInt(1000000));

                System.out.println("Açmak istediğiniz hesap bilgisini seçiniz:\n\n" +
                        "1 -> Maaş hesabı\n" +
                        "2 -> Normal hesap\n");
                acilanHesapBilgisi = input.nextInt();

                if(acilanHesapBilgisi == 1){
                    faizOrani = 20;
                }else if(acilanHesapBilgisi == 2){
                    faizOrani = 10;
                }else{
                    System.out.println("Hesap bilgisi yanlış girildi.\n\n");
                }



                System.out.println("Açmak istediğiniz hesap türünü seçiniz:\n\n" +
                        "11 -> Vadeli Hesap\n" +
                        "12 -> Vadesiz Hesap\n" +
                        "13 -> Yatırım Hesabı");
                acilanHesapCesidi = input.nextInt();

                if (acilanHesapCesidi == 11) {
                    bankaHesap.hesapTuru = "Vadeli Hesap";
                    System.out.println("Yatırıcağınız tutarı yazınız: ");
                    vadeliBakiye = input.nextInt();
                    musteri.hesapEkle(tc, telNo, isim, soyisim, bankaHesap.hesapTuru,faizOrani,vadeliBakiye,acilanHesapSayisi,yatirimTuru,yatirimEkleme,yatirimHesabıEklenecekTutar,yatirimHesabıSilinecekTutar);
                    acilanHesapSayisi++;
                    //Vadeli hesap oluşturuldu.

                    BankaHesap bankaHesap1 = new BankaHesap(iban,vadeliBakiye,acilanHesapBilgisi);

                } else if (acilanHesapCesidi == 12) {
                    bankaHesap.hesapTuru = "Vadesiz Hesap";
                    System.out.println("Yatırıcağınız tutarı yazınız: ");
                    vadesizBakiye = input.nextInt();
                    musteri.hesapEkle(tc, telNo, isim, soyisim, bankaHesap.hesapTuru,faizOrani,vadesizBakiye,acilanHesapSayisi,yatirimTuru,yatirimEkleme,yatirimHesabıEklenecekTutar,yatirimHesabıSilinecekTutar);
                    acilanHesapSayisi++;
                    //Vadesiz hesap oluşturuldu.

                    BankaHesap bankaHesap1 = new BankaHesap(iban,vadesizBakiye,acilanHesapBilgisi);

                } else if (acilanHesapCesidi == 13) {
                    bankaHesap.hesapTuru = "Yatırım Hesabı";
                    System.out.println("Yatırıcağınız türü yazınız: ");
                    yatirimTuru = input.next();
                    System.out.println("Yatırıcağınız tutarı yazınız: ");
                    yatirimBakiye = input.nextInt();
                    musteri.hesapEkle(tc, telNo, isim, soyisim, bankaHesap.hesapTuru,faizOrani,yatirimBakiye,acilanHesapSayisi,yatirimTuru,yatirimEkleme,yatirimHesabıEklenecekTutar,yatirimHesabıSilinecekTutar);
                    acilanHesapSayisi++;
                    //Yatırım hesabı oluşturuldu.

                    BankaHesap bankaHesap1 = new BankaHesap(iban,yatirimBakiye,acilanHesapBilgisi);

                } else {
                    System.out.println("!!!HATALI GİRİŞ!!!");
                }


            } else if (secilenIslem == 2) {

                System.out.println("Adınız: ");
                isim = input.next();

                System.out.println("Soyadınız: ");
                soyisim = input.next();

                System.out.println("TC kimlik numaranız: "+tc+"\n");

                System.out.println("Telefon Numaranız: ");
                telNo = input.nextInt();

                System.out.println(musteri.hesaplar);
                System.out.println("Silmek istediğiniz hesabın indeksini yazınız: ");
                silinecekHesapIndeksii = input.nextInt();



                //if (kisi.getAd()==isim && kisi.getSoyad()==soyisim && kisi.getTcKimlikNo()==tc && kisi.getTelefonNumarasi()==telNo){
                //Müsteri güvenliğini sağlıyor.

                    System.out.println("Silmek istediğiniz hesap türünü seçiniz:\n\n" +
                            "11 -> Vadeli Hesap\n" +
                            "12 -> Vadesiz Hesap\n" +
                            "13 -> Yatırım Hesabı");
                    silinenHesapCesidi = input.nextInt();

                    if(silinenHesapCesidi == 11){
                        if(vadeliBakiye == 0){
                            musteri.hesapSil(tc,telNo,isim,soyisim,silinecekHesapIndeksii);
                        }else{
                            System.out.println("\nLütfen öncelikle bakiyenizi başka bir hesaba aktarınız.\n");
                        }
                    }else if (silinenHesapCesidi == 12){
                        if(vadesizBakiye == 0){
                            musteri.hesapSil(tc,telNo,isim,soyisim,silinecekHesapIndeksii);
                        }else{
                            System.out.println("\nLütfen öncelikle bakiyenizi başka bir hesaba aktarınız.\n");
                        }

                    }else if (silinenHesapCesidi == 13){
                        if(yatirimBakiye == 0){
                            musteri.hesapSil(tc,telNo,isim,soyisim,silinecekHesapIndeksii);
                        }else{
                            System.out.println("\nLütfen öncelikle bakiyenizi başka bir hesaba aktarınız.\n");
                        }

                    }else{
                        System.out.println("!!!HATALI GİRİŞ!!!");
                    }
                //}else {
                    //System.out.println("çalışmadı");
                //}


            } else if (secilenIslem == 3) {

                System.out.println("Adınız: ");
                isim = input.next();
                kisi.setAd(isim);

                System.out.println("Soyadınız: ");
                soyisim = input.next();
                kisi.setSoyad(soyisim);

                System.out.println("TC kimlik numaranız: "+tc+"\n");

                System.out.println("Mail adresiniz: ");
                mail = input.next();
                kisi.setEmail(mail);

                System.out.println("Telefon Numaranız: ");
                telNo = input.nextInt();
                kisi.setTelefonNumarasi(telNo);

                System.out.println("Kart numarınızı giriniz: ");
                kartNumarasi = input.nextInt();

                System.out.println("Kredi kartınızın limitini giriniz: ");
                limit = input.nextInt();


                //KrediKarti krediKarti = new KrediKarti(kartNumarasi,limit,guncelBorc);

                krediKarti.guncelBorc= guncelBorc;
                krediKarti.limit = limit;
                krediKarti.setKartNumarasi(kartNumarasi);

                kullanılabilirLimit = krediKarti.kullanılabilirLimit(kartNumarasi,limit,guncelBorc);

                krediKarti.krediKartiEkle(kartNumarasi,limit,krediKartSayisi,krediKarti,musteri);


                krediKartSayisi++;



            } else if (secilenIslem == 4) {

                System.out.println("Adınız: ");
                isim = input.next();

                System.out.println("Soyadınız: ");
                soyisim = input.next();

                System.out.println("TC kimlik numaranız: "+tc+"\n");

                System.out.println("Telefon Numaranız: ");
                telNo = input.nextInt();

                System.out.println(musteri.krediKartlari);
                System.out.println("Silmek istediğiniz kredi kartının indeksini yazınız: ");
                silinecekKrediKartiIndeksi = input.nextInt();

                krediKarti.krediKartiSil(kartNumarasi,limit,guncelBorc,krediKarti,musteri,silinecekKrediKartiIndeksi);

                System.out.println("\n!!!SEÇİLEN KREDİ KARTINIZ SİLİNDİ!!!");


            } else if (secilenIslem == 5) {

                System.out.println("Adınız: ");
                isim = input.next();
                kisi.setAd(isim);

                System.out.println("Soyadınız: ");
                soyisim = input.next();
                kisi.setSoyad(soyisim);

                System.out.println("TC kimlik numaranız: "+tc+"\n");

                System.out.println("Mail adresiniz: ");
                mail = input.next();
                kisi.setEmail(mail);

                System.out.println("Telefon Numaranız: ");
                telNo = input.nextInt();
                kisi.setTelefonNumarasi(telNo);

                System.out.println("KrediId giriniz: ");
                krediID = input.nextInt();

                System.out.println("Yıllık kazancınızı giriniz: ");
                yillikKazanc = input.nextInt();

                krediMiktari = (yillikKazanc/2);

                System.out.println("\nEn fazla "+krediMiktari+"tl kredi alabilirsiniz.");

                System.out.println("\nAlmak istediğiniz kredi miktarını giriniz: ");
                krediMiktari = input.nextInt();

                Krediler krediler1 = new Krediler(krediID,telNo,yillikKazanc,krediMiktari);


            } else if (secilenIslem == 6) {

                System.out.println("Kredi kartlarınız: "+musteri.krediKartlari);



            } else if (secilenIslem == 7) {

                System.out.println("\nKredi miktarınız: "+krediMiktari);

                System.out.println("\nHesaplarınız: "+musteri.hesaplar);

                int ödemeYapilacakHesapIdeksi=0;
                System.out.println("\nHangi hesabınızdan ödeme yapıcaksınız(indeksini giriniz): ");
                ödemeYapilacakHesapIdeksi = input.nextInt();

                System.out.println("Vadesiz bakiye: "+vadesizBakiye);

                System.out.println("\nKredinizin ne kadarını ödemek istiyorsunuz: ");
                odenecekKrediMiktari = input.nextInt();


                krediMiktari = (krediMiktari - odenecekKrediMiktari);

                System.out.println("\n!!!KREDİ ÖDEMENİZ BAŞARILI!!!\n");

                System.out.println("Ödemeden sonra kalan kredi miktarınız: "+krediMiktari);

                System.out.println("Ödemeden sonra kalan vadesiz hesap bakiyeniz: "+(vadesizBakiye-odenecekKrediMiktari));


            } else if (secilenIslem == 8) {

                System.out.println("Adınız: ");
                isim = input.next();
                kisi.setAd(isim);

                System.out.println("Soyadınız: ");
                soyisim = input.next();
                kisi.setSoyad(soyisim);

                System.out.println("TC kimlik numaranız: "+tc+"\n");

                System.out.println("Mail adresiniz: ");
                mail = input.next();
                kisi.setEmail(mail);

                System.out.println("Telefon Numaranız: ");
                telNo = input.nextInt();
                kisi.setTelefonNumarasi(telNo);


                System.out.println("Yatırım hesabınıza eklemek istediğiniz tutarı yazınız: ");
                yatirimHesabıEklenecekTutar = input.nextInt();

                System.out.println("Mevcut kuru giriniz: ");
                mevcutKur = input.nextInt();

                yatirimBakiye = (yatirimBakiye + yatirimHesabıEklenecekTutar);
                yatirimEkleme=1;

                musteri.hesapEkle(tc, telNo, isim, soyisim, bankaHesap.hesapTuru,faizOrani,yatirimBakiye,acilanHesapSayisi,yatirimTuru,yatirimEkleme,yatirimHesabıEklenecekTutar,yatirimHesabıSilinecekTutar);

                System.out.println("\nYatırım hesabınıza toplam "+yatirimHesabıEklenecekTutar+" "+yatirimTuru+"("+(yatirimHesabıEklenecekTutar*mevcutKur)+" TL) yatırılmıştır.");

                System.out.println("\nYatırım hesabınızın yeni bakiyesi: "+yatirimBakiye+" "+yatirimTuru);



            }else if(secilenIslem == 9){

                System.out.println("Adınız: ");
                isim = input.next();
                kisi.setAd(isim);

                System.out.println("Soyadınız: ");
                soyisim = input.next();
                kisi.setSoyad(soyisim);

                System.out.println("TC kimlik numaranız: "+tc+"\n");

                System.out.println("Mail adresiniz: ");
                mail = input.next();
                kisi.setEmail(mail);

                System.out.println("Telefon Numaranız: ");
                telNo = input.nextInt();
                kisi.setTelefonNumarasi(telNo);

                System.out.println("Yatırım hesabınızdan çekmek istediğiniz tutarı giriniz: ");
                yatirimHesabıSilinecekTutar = input.nextInt();

                System.out.println("Mevcut kuru giriniz: ");
                mevcutKur = input.nextInt();

                yatirimEkleme=2;

                if(yatirimHesabıSilinecekTutar > yatirimBakiye){
                    System.out.println("Hesabınızda bu kadar para bulunmuyor.");
                }else {

                    yatirimBakiye = (yatirimBakiye - yatirimHesabıSilinecekTutar);

                    musteri.hesapEkle(tc, telNo, isim, soyisim, bankaHesap.hesapTuru, faizOrani, yatirimBakiye, acilanHesapSayisi, yatirimTuru, yatirimEkleme, yatirimHesabıEklenecekTutar, yatirimHesabıSilinecekTutar);

                    System.out.println("\nYatırım hesabınızdan toplam " + yatirimHesabıSilinecekTutar + " " + yatirimTuru + "(" + (yatirimHesabıSilinecekTutar * mevcutKur) + " TL) çekilmiştir.");

                    System.out.println("\nYatırım hesabınızın yeni bakiyesi: " + yatirimBakiye + " " + yatirimTuru);

                }
            } else if (secilenIslem == 0) {
                break;
            } else {
                System.out.println("!!!HATALI GİRİŞ!!!");
            }
        }


        
    System.out.println("Hesaplarınız: "+musteri.hesaplar);

    System.out.println("Kredi kartlarınız: "+musteri.krediKartlari);


    }
}
