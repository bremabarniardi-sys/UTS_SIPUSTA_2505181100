package layanan;

import model.Koleksi;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EksporKatalog {
    
    // WAJIB: Menggunakan BufferedWriter dan Files.newBufferedWriter (Java NIO)
    public void ekspor(List<Koleksi> dataKoleksi) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("katalog.txt"))) {
            
            String header = String.format("%-7s | %-34s | %-13s | %-4s | %-11s | %-27s | %s%n",
                    "KODE", "JUDUL", "JENIS", "THN", "STATUS", "LOKASI ARSIP", "KETERANGAN");
            writer.write(header);
            writer.write("-------------------------------------------------------------------------------------------------------------------------------------\n");

            for (Koleksi item : dataKoleksi) {
                String statusStr = item.isLayakDipinjam() ? "Layak" : "Tidak layak";
                String baris = String.format("%-7s | %-34s | %-13s | %-4d | %-11s | %-27s | %s%n",
                        item.getKode(),
                        item.getJudul(),
                        item.jenisKoleksi(),
                        item.getTahunTerbit(),
                        statusStr,
                        item.lokasiArsip(),
                        item.keterangan());
                writer.write(baris);
            }
            System.out.println("Data katalog berhasil diekspor ke berkas 'katalog.txt' (NIO API).");
            
        } catch (IOException ex) {
            System.out.println(String.format("Gagal mengekspor data katalog: %s", ex.getMessage()));
        }
    }
}