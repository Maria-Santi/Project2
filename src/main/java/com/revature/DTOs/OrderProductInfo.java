package com.revature.DTOs;

public class OrderProductInfo {
    private int opId;
    private int oId;
    private int pId;
    private int quantity;
    private String name;
    private float price;

    public OrderProductInfo(int opId, int oId, int pId, int quantity, String name, float price) {
        this.opId = opId;
        this.oId = oId;
        this.pId = pId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public OrderProductInfo(){

    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
