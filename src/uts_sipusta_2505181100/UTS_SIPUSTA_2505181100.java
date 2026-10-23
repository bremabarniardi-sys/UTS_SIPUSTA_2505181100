import eksepsi.PustakaException;
import layanan.EksporKatalog;
import layanan.KatalogPerpustakaan;
import layanan.UrutLayakLaluJudul;
import model.BukuCetak;
import model.Majalah;

public class UTS_SIPUSTA_2505181100 {
    public static void main(String[] args) {
        System.out.println("=== SISTEM PERPUSTAKAAN DIGITAL [NIM: 2505181100] ===");
        
        KatalogPerpustakaan katalog = new KatalogPerpustakaan();

        // Pemasukan data uji secara manual satu per satu tanpa array/method terpisah
        katalog.tambah(new BukuCetak("B001", "Pemrograman Java Lanjut", 2022, 450, "A-12", false));
        katalog.tambah(new BukuCetak("B002", "Struktur Data dan Algoritma", 2020, 320, "B-05", true));
        katalog.tambah(new Majalah("M001", "Info Komputer", 2023, 10, "Oktober", "C-01", true));
        katalog.tambah(new Majalah("M002", "Sains & Teknologi", 2021, 5, "Maret", "C-02", false));

        System.out.println("\n--- KATALOG AWAL ---");
        katalog.cetakStatus();

        System.out.println("\n--- SIMULASI PEMINJAMAN ---");
        try {
            katalog.pinjam("B001");
        } catch (PustakaException e) {
            System.out.println(e.getMessage());
        }

        try {
            katalog.pinjam("B001");
        } catch (PustakaException e) {
            System.out.println(e.getMessage());
        }

        try {
            katalog.pinjam("B002");
        } catch (PustakaException e) {
            System.out.println(e.getMessage());
        }

        try {
            katalog.pinjam("M001");
        } catch (PustakaException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- KATALOG TERURUT (KELAYAKAN & JUDUL) ---");
        katalog.urut(new UrutLayakLaluJudul());
        katalog.cetakStatus();

        System.out.println("\n--- EKSPOR FILE KATALOG ---");
        EksporKatalog ekspor = new EksporKatalog();
        ekspor.ekspor(katalog.getDaftarKoleksi());
    }
}