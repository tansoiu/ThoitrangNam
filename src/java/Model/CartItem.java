/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author garen
 */
public class CartItem {
    private int maChiTiet;
    private int maGioHang;
    private int maSanPham;
    private int soLuong;

    private Product sanPham;

    public CartItem() {
    }

    public CartItem(int maChiTiet, int maGioHang, int maSanPham, int soLuong, Product sanPham) {
        this.maChiTiet = maChiTiet;
        this.maGioHang = maGioHang;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Product getSanPham() {
        return sanPham;
    }

    public void setSanPham(Product sanPham) {
        this.sanPham = sanPham;
    }
    
}
