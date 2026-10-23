package layanan;

import eksepsi.KoleksiSedangDipinjamException;
import eksepsi.KoleksiTidakLayakException;
import eksepsi.PustakaException;
import model.Koleksi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KatalogPerpustakaan {
    private List<Koleksi> listKoleksi = new ArrayList<>();

    public void tambah(Koleksi itemBaru) {
        listKoleksi.add(itemBaru);
    }

    public List<Koleksi> getDaftarKoleksi() {
        return Collections.unmodifiableList(listKoleksi);
    }

    // WAJIB: Pencarian menggunakan Stream
    public Koleksi cari(String idKoleksi) {
        return listKoleksi.stream()
                .filter(item -> item.getKode().equalsIgnoreCase(idKoleksi))
                .findFirst()
                .orElse(null);
    }

    // WAJIB: Print menggunakan forEach
    public void cetakStatus() {
        System.out.printf("%-7s | %-34s | %-13s | %-4s | %s%n", 
                "KODE", "JUDUL", "JENIS", "THN", "STATUS & DETAIL");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        
        listKoleksi.forEach(item -> {
            String penandaPinjam = item.isSedangDipinjam() ? "  >> SEDANG DIPINJAM" : "";
            System.out.println(String.format("%s%s", item.deskripsi(), penandaPinjam));
        });
        System.out.println(String.format("Total koleksi: %d", listKoleksi.size()));
    }

    public void urut(Comparator<Koleksi> pembanding) {
        listKoleksi.sort(pembanding);
    }

    // WAJIB: Pengecekan error menggunakan if terpisah tanpa else
    public void pinjam(String idKoleksi) throws PustakaException {
        Koleksi target = cari(idKoleksi);

        if (target == null) {
            throw new PustakaException(String.format("[NIM 2505181100] Koleksi kode '%s' tidak ditemukan dalam sistem.", idKoleksi));
        }
        if (target.isSedangDipinjam()) {
            throw new KoleksiSedangDipinjamException(String.format("[NIM 2505181100] Terjadi kendala: '%s' sedang dipinjam oleh pemustaka lain.", target.getJudul()));
        }
        if (!target.isLayakDipinjam()) {
            throw new KoleksiTidakLayakException(String.format("[NIM 2505181100] Terjadi kendala: '%s' belum dapat dipinjam karena %s.", target.getJudul(), target.alasanTidakLayak()));
        }

        target.setSedangDipinjam(true);
        System.out.println(String.format("Peminjaman berhasil! '%s' (%s) resmi tercatat dipinjam.", target.getJudul(), target.getKode()));
    }
}