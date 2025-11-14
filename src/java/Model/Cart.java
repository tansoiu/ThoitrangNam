/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author garen
 */
public class Cart {
    private int maGioHang;
    private int maTaiKhoan;
    private Date ngayTao;

    public Cart() {
    }

    public Cart(int maGioHang, int maTaiKhoan, Date ngayTao) {
        this.maGioHang = maGioHang;
        this.maTaiKhoan = maTaiKhoan;
        this.ngayTao = ngayTao;
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    
}
