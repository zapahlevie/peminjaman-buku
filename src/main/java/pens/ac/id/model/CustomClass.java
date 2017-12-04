/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pens.ac.id.model;

/**
 *
 * @author zapah
 */
public class CustomClass {

    Long id;
    String nama;
    String judul;
    String tanggal;
    String status;

    public CustomClass() {
    }

    public CustomClass(Long id, String nama, String judul, String tanggal, String status) {
        this.id = id;
        this.nama = nama;
        this.judul = judul;
        this.tanggal = tanggal;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
