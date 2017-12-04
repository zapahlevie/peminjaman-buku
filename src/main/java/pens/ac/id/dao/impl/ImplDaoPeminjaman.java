package pens.ac.id.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import pens.ac.id.AppContainer;
import pens.ac.id.dao.DaoPeminjaman;
import pens.ac.id.model.Buku;
import pens.ac.id.model.Peminjaman;

public class ImplDaoPeminjaman implements DaoPeminjaman {

    private List<Peminjaman> DbPeminjaman;
    private DataSource dataSource;

    public ImplDaoPeminjaman() {
        DbPeminjaman = new ArrayList<Peminjaman>();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void savePeminjaman(Peminjaman peminjaman) {
        String sql = "INSERT INTO peminjaman "
                + "(mahasiswa_id, buku_id, tanggal, status) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        Buku b = AppContainer.getInstance().getServiceBuku().getBukuById(peminjaman.getBukuId());
        if (b.getStokTersedia() > 1) {
            try {
                conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);;
                ps.setLong(1, peminjaman.getMahasiswaId());
                ps.setLong(2, peminjaman.getBukuId());
                ps.setString(3, peminjaman.getTanggal());
                ps.setString(4, peminjaman.getStatus());
                ps.executeUpdate();
                ps.close();
                conn.close();
                b.setStokTersedia(b.getStokTersedia() - 1);
                b.setDalamPeminjaman(b.getDalamPeminjaman() + 1);
                AppContainer.getInstance().getServiceBuku().updateBuku(b);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void updatePeminjaman(Peminjaman peminjaman) {
        String sql = "UPDATE peminjaman SET mahasiswa_id=?, buku_id=?, tanggal=?, status=? WHERE id = ?";
        Connection conn = null;
        Peminjaman p = AppContainer.getInstance().getServicePeminjaman().getPeminjamanById(peminjaman.getId());
        if (p.getStatus().equals("Belum dikembalikan") && peminjaman.getStatus().equals("Sudah dikembalikan")) {
            Buku b = AppContainer.getInstance().getServiceBuku().getBukuById(p.getBukuId());
            b.setStokTersedia(b.getStokTersedia() + 1);
            b.setDalamPeminjaman(b.getDalamPeminjaman() - 1);
            AppContainer.getInstance().getServiceBuku().updateBuku(b);
        }
        else if(p.getStatus().equals("Sudah dikembalikan") && peminjaman.getStatus().equals("Belum dikembalikan")){
            Buku b = AppContainer.getInstance().getServiceBuku().getBukuById(p.getBukuId());
            b.setStokTersedia(b.getStokTersedia() - 1);
            b.setDalamPeminjaman(b.getDalamPeminjaman() + 1);
            AppContainer.getInstance().getServiceBuku().updateBuku(b);
        }
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, peminjaman.getMahasiswaId());
            ps.setLong(2, peminjaman.getBukuId());
            ps.setString(3, peminjaman.getTanggal());
            ps.setString(4, peminjaman.getStatus());
            ps.setLong(5, peminjaman.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePeminjaman(Long peminjamanId) {
        String sql = "DELETE FROM peminjaman WHERE id = ?";
        Connection conn = null;
        Peminjaman p = AppContainer.getInstance().getServicePeminjaman().getPeminjamanById(peminjamanId);
        if (p.getStatus().equals("Belum dikembalikan")) {
            Buku b = AppContainer.getInstance().getServiceBuku().getBukuById(p.getBukuId());
            b.setStokTersedia(b.getStokTersedia() + 1);
            b.setDalamPeminjaman(b.getDalamPeminjaman() - 1);
            AppContainer.getInstance().getServiceBuku().updateBuku(b);
        }
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, peminjamanId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Peminjaman> getAllPeminjaman() {
        String sql = "SELECT * FROM peminjaman";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Peminjaman peminjaman = null;
            ResultSet rs = ps.executeQuery();
            DbPeminjaman.clear();
            while (true) {
                if (rs.next()) {
                    peminjaman = new Peminjaman(
                            rs.getLong("id"),
                            rs.getLong("mahasiswa_id"),
                            rs.getLong("buku_id"),
                            rs.getString("tanggal"),
                            rs.getString("status")
                    );
                    DbPeminjaman.add(peminjaman);
                } else {
                    break;
                }
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return DbPeminjaman;
    }

    @Override
    public Peminjaman getPeminjamanById(Long peminjamanId) {
        String sql = "SELECT * FROM peminjaman WHERE id = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, peminjamanId);
            Peminjaman peminjaman = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                peminjaman = new Peminjaman(
                        rs.getLong("id"),
                        rs.getLong("mahasiswa_id"),
                        rs.getLong("buku_id"),
                        rs.getString("tanggal"),
                        rs.getString("status")
                );
            }
            rs.close();
            ps.close();
            conn.close();
            return peminjaman;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getDbSize() {
        DbPeminjaman = getAllPeminjaman();
        return DbPeminjaman.size();
    }

}
