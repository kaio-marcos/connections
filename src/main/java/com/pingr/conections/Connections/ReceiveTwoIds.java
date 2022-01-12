package com.pingr.conections.Connections;

public class ReceiveTwoIds {
    private Long idA;
    private Long idB;

    public ReceiveTwoIds(Long idA, Long idB) {
        this.idA = idA;
        this.idB = idB;
    }

    public ReceiveTwoIds() {
    }

    public Long getIdA() {
        return idA;
    }

    public void setIdA(Long idA) {
        this.idA = idA;
    }

    public Long getIdB() {
        return idB;
    }

    public void setIdB(Long idB) {
        this.idB = idB;
    }
}
