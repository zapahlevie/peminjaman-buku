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
import pens.ac.id.dao.DaoBuku;
import pens.ac.id.model.Buku;
import pens.ac.id.model.Peminjaman;

public class ImplDaoBuku implements DaoBuku {

    private List<Buku> DbBuku;
    private DataSource dataSource;

    public ImplDaoBuku() {
        DbBuku = new ArrayList<Buku>();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveBuku(Buku buku) {
        boolean sama = false;
        String sql = "INSERT INTO buku "
                + "(author, judul, tahun, stok_tersedia, dalam_peminjaman) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (Buku b : AppContainer.getInstance().getServiceBuku().getAllBuku()) {
                if (b.getJudul().equals(buku.getJudul())) {
                    sama = true;
                    break;
                }
            }
            if (!sama) {
                ps.setString(1, buku.getAuthor());
                ps.setString(2, buku.getJudul());
                ps.setLong(3, buku.getTahun());
                ps.setLong(4, buku.getStokTersedia());
                ps.setLong(5, buku.getDalamPeminjaman());
                ps.executeUpdate();
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBuku(Buku buku) {
        String sql = "UPDATE buku SET author = ?, judul = ?, tahun = ?, stok_tersedia = ?, dalam_peminjaman = ? WHERE id = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, buku.getAuthor());
            ps.setString(2, buku.getJudul());
            ps.setLong(3, buku.getTahun());
            ps.setLong(4, buku.getStokTersedia());
            ps.setLong(5, buku.getDalamPeminjaman());
            ps.setLong(6, buku.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBuku(Long bukuId) {
        String sql = "DELETE FROM buku WHERE id = ?";
        Connection conn = null;
        boolean dipinjam = false;
        for (Peminjaman p : AppContainer.getInstance().getServicePeminjaman().getAllPeminjaman()) {
            if (p.getBukuId() == bukuId && p.getStatus().equals("Belum dikembalikan")) {
                dipinjam = true;
                break;
            }
        }
        if (!dipinjam) {
            try {
                conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1, bukuId);
                ps.executeUpdate();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Buku> getAllBuku() {
        String sql = "SELECT * FROM buku";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Buku buku = null;
            ResultSet rs = ps.executeQuery();
            DbBuku.clear();
            while (true) {
                if (rs.next()) {
                    buku = new Buku(
                            rs.getLong("id"),
                            rs.getString("author"),
                            rs.getString("judul"),
                            rs.getLong("tahun"),
                            rs.getLong("stok_tersedia"),
                            rs.getLong("dalam_peminjaman")
                    );
                    DbBuku.add(buku);
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
        return DbBuku;
    }

    @Override
    public Buku getBukuById(Long bukuId) {
        String sql = "SELECT * FROM buku WHERE id = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, bukuId);
            Buku buku = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                buku = new Buku(
                        rs.getLong("id"),
                        rs.getString("author"),
                        rs.getString("judul"),
                        rs.getLong("tahun"),
                        rs.getLong("stok_tersedia"),
                        rs.getLong("dalam_peminjaman")
                );
            }
            rs.close();
            ps.close();
            conn.close();
            return buku;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getDbSize() {
        DbBuku = getAllBuku();
        return DbBuku.size();
    }

}
