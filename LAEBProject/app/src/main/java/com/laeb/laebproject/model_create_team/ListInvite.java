
package com.laeb.laebproject.model_create_team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListInvite {

    @SerializedName("player_id")
    @Expose
    private Integer playerId;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

}
