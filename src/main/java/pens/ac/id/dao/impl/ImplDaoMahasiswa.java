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
import pens.ac.id.dao.DaoMahasiswa;
import pens.ac.id.model.Mahasiswa;
import pens.ac.id.model.Peminjaman;

public class ImplDaoMahasiswa implements DaoMahasiswa {

    private List<Mahasiswa> DbMahasiswa;
    private DataSource dataSource;

    public ImplDaoMahasiswa() {
        DbMahasiswa = new ArrayList<Mahasiswa>();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveMahasiswa(Mahasiswa mahasiswa) {
        String sql = "INSERT INTO mahasiswa(nrp, nama) VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, mahasiswa.getNrp());
            ps.setString(2, mahasiswa.getNama());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMahasiswa(Mahasiswa mahasiswa) {
        String sql = "UPDATE mahasiswa SET nrp = ?, nama = ? WHERE id = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, mahasiswa.getNrp());
            ps.setString(2, mahasiswa.getNama());
            ps.setLong(3, mahasiswa.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMahasiswa(Long mahasiswaId) {
        String sql = "DELETE FROM mahasiswa WHERE id = ?";
        Connection conn = null;
        boolean pinjam = false;
        for (Peminjaman p : AppContainer.getInstance().getServicePeminjaman().getAllPeminjaman()) {
            if (p.getMahasiswaId() == mahasiswaId && p.getStatus().equals("Belum dikembalikan")) {
                pinjam = true;
                break;
            }
        }
        if (!pinjam) {
            try {
                conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1, mahasiswaId);
                ps.executeUpdate();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Mahasiswa> getAllMahasiswa() {
        String sql = "SELECT * FROM mahasiswa";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Mahasiswa mahasiswa = null;
            ResultSet rs = ps.executeQuery();
            DbMahasiswa.clear();
            while (true) {
                if (rs.next()) {
                    mahasiswa = new Mahasiswa(
                            rs.getLong("id"),
                            rs.getLong("nrp"),
                            rs.getString("nama")
                    );
                    DbMahasiswa.add(mahasiswa);
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
        return DbMahasiswa;
    }

    @Override
    public Mahasiswa getMahasiswaById(Long mahasiswaId) {
        String sql = "SELECT * FROM mahasiswa WHERE id = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, mahasiswaId);
            Mahasiswa mahasiswa = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mahasiswa = new Mahasiswa(
                        rs.getLong("id"),
                        rs.getLong("nrp"),
                        rs.getString("nama")
                );
            }
            rs.close();
            ps.close();
            conn.close();
            return mahasiswa;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getDbSize() {
        DbMahasiswa = getAllMahasiswa();
        return DbMahasiswa.size();
    }

}
