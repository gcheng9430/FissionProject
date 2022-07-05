package com.example.fission;

import lombok.Data;

@Data
public class EnvironmentStat {
    public int totalParticipation;
    public int totalSuccess;
    public int totalFission;

    public int currentRound;
    public boolean done = false;

    public void incrementCurrentRound(){
        this.currentRound++;
    }


    public void incrementParticipation(){
        this.totalParticipation +=1;
    }

    public void incrementSuccess(int n){

        this.totalSuccess +=n;
    }

    public void incrementFissionCount(int n){
        this.totalFission += n;
    }

}
