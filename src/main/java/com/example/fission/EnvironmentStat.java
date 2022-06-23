package com.example.fission;

public class EnvironmentStat {
    private int total_participation;
    private int total_success;
    private int total_fission;


    public void incrementParticipation(){
        this.total_participation +=1;
    }

    public void incrementSuccess(){
        this.total_success +=1;
    }

    public void incrementFissionCount(int n){
        this.total_fission += n;
    }

}
