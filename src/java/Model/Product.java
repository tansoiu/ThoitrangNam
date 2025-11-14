/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author garen
 */
public class Product {
    private int maSanPham;
    private String sku;
    private String tenSanPham;
    private String moTa;
    private double gia;
    private Double giaGiam;
    private int soLuong;
    private String anh;
    private String danhMuc;

    private List<ProductDetail> chiTietSanPham;

    public List<ProductDetail> getChiTietSanPham() {
        return chiTietSanPham;
    }
    public void setChiTietSanPham(List<ProductDetail> chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }

    public Product() {
    }

    public Product(int maSanPham, String sku, String tenSanPham, String moTa, double gia, Double giaGiam, int soLuong, String anh, String danhMuc) {
        this.maSanPham = maSanPham;
        this.sku = sku;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.gia = gia;
        this.giaGiam = giaGiam;
        this.soLuong = soLuong;
        this.anh = anh;
        this.danhMuc = danhMuc;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(Double giaGiam) {
        this.giaGiam = giaGiam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }
    
    
}
