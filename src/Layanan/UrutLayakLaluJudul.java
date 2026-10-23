package layanan;

import model.Koleksi;
import java.util.Comparator;

public class UrutLayakLaluJudul implements Comparator<Koleksi> {
    
    // WAJIB: Menggunakan Boolean.compare(b.isLayakDipinjam(), a.isLayakDipinjam())
    @Override
    public int compare(Koleksi a, Koleksi b) {
        int hasilStatus = Boolean.compare(b.isLayakDipinjam(), a.isLayakDipinjam());
        if (hasilStatus != 0) {
            return hasilStatus;
        }
        return a.getJudul().compareToIgnoreCase(b.getJudul());
    }
}