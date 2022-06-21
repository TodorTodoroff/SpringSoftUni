package com.example.battleships.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class FireFxDTO {

    @Positive
    @NotNull
    private Long attackerId;

    @Positive
    @NotNull
    private Long defenderId;

    public FireFxDTO() {
    }

    public Long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
    }

    public Long getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(Long defenderId) {
        this.defenderId = defenderId;
    }

    @Override
    public String toString() {
        return "FireFxDTO{" +
                "attacker=" + attackerId +
                ", defender=" + defenderId +
                '}';
    }
}
