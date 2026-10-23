package model;

public class BukuCetak extends KoleksiFisik {
    private int jmlHalaman;
    private boolean isDiperbaiki;

    public BukuCetak(String kode, String judul, int tahunTerbit, int jumlahHalaman, String nomorRak, boolean sedangDiperbaiki) {
        super(kode, judul, tahunTerbit, nomorRak);
        this.jmlHalaman = jumlahHalaman;
        this.isDiperbaiki = sedangDiperbaiki;
    }

    @Override
    public String jenisKoleksi() { return "BukuCetak"; }

    @Override
    public boolean isLayakDipinjam() {
        return !isDiperbaiki;
    }

    @Override
    public String alasanTidakLayak() {
        return "buku fisik ini sedang dalam perbaikan oleh staf perpustakaan";
    }

    @Override
    public String keterangan() {
        String kondisi = isDiperbaiki ? "sedang diperbaiki" : "siap dipinjam";
        return String.format("%d halaman, %s", jmlHalaman, kondisi);
    }

    // WAJIB: Menggabungkan String pakai String.format
    @Override
    public String deskripsi() {
        String kondisi = isDiperbaiki ? "sedang diperbaiki" : "siap dipinjam";
        return String.format("%s | %d halaman | %s", super.deskripsi(), jmlHalaman, kondisi);
    }
}