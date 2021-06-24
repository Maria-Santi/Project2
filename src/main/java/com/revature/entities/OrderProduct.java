package com.revature.entities;

public class OrderProduct {
    private int opId;
    private int oId;
    private int pId;
    private int quantity;

    public OrderProduct(){}

    public OrderProduct(int opId, int oId, int pId, int quantity) {
        this.opId = opId;
        this.oId = oId;
        this.pId = pId;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "OrderProduct{" +
                "opId=" + opId +
                ", oId=" + oId +
                ", pId=" + pId +
                ", quantity=" + quantity +
                '}';
    }
}
