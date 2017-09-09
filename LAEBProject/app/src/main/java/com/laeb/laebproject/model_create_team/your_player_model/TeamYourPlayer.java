
package com.laeb.laebproject.model_create_team.your_player_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamYourPlayer {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("selectedPlayers")
    @Expose
    private List<SelectedPlayer> selectedPlayers = null;
    @SerializedName("invitedPlayers")
    @Expose
    private List<InvitedPlayer> invitedPlayers = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SelectedPlayer> getSelectedPlayers() {
        return selectedPlayers;
    }

    public void setSelectedPlayers(List<SelectedPlayer> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    public List<InvitedPlayer> getInvitedPlayers() {
        return invitedPlayers;
    }

    public void setInvitedPlayers(List<InvitedPlayer> invitedPlayers) {
        this.invitedPlayers = invitedPlayers;
    }

}
