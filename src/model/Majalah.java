package model;

public class Majalah extends KoleksiFisik {
    private int noEdisi;
    private String blnTerbit;
    private boolean statusTerbaru;

    public Majalah(String kode, String judul, int tahunTerbit, int edisi, String bulanTerbit, String nomorRak, boolean edisiTerbaru) {
        super(kode, judul, tahunTerbit, nomorRak);
        this.noEdisi = edisi;
        this.blnTerbit = bulanTerbit;
        this.statusTerbaru = edisiTerbaru;
    }

    @Override
    public String jenisKoleksi() { return "Majalah"; }

    @Override
    public boolean isLayakDipinjam() { return !statusTerbaru; }

    @Override
    public String alasanTidakLayak() {
        return "majalah ini merupakan edisi terbaru (baca di tempat saja)";
    }

    @Override
    public String keterangan() {
        String infoEdisi = statusTerbaru ? "(edisi terbaru)" : "(bukan edisi terbaru)";
        return String.format("Edisi %d, terbit %s %s", noEdisi, blnTerbit, infoEdisi);
    }

    // WAJIB: Menggabungkan String pakai String.format
    @Override
    public String deskripsi() {
        String infoEdisi = statusTerbaru ? "edisi terbaru" : "bukan edisi terbaru";
        return String.format("%s | Edisi %d (%s) | %s", super.deskripsi(), noEdisi, blnTerbit, infoEdisi);
    }
}