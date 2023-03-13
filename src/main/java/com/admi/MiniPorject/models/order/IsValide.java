package com.admi.MiniPorject.models.order;

public class IsValide {
    private boolean isValideOperation;
    private String msg;

    public IsValide(boolean isValideOperation, String msg) {
        this.isValideOperation = isValideOperation;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public IsValide(boolean isValideOperation) {
        this.isValideOperation = isValideOperation;
    }

    public IsValide() {
    }

    public boolean isValideOperation() {
        return isValideOperation;
    }

    public void setValideOperation(boolean valideOperation) {
        isValideOperation = valideOperation;
    }
}
