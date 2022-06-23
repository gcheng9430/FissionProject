package com.example.fission;

import java.util.ArrayList;

public class InteractionHistory {

    private HashMap<Agent, ArrayList<Integer>> sent_agents;
    //互动/裂变历史 [给每个人发过几次,这个人给自己助力成功了几次,回复率]

    public static void update_success(Agent agent){
        //sender给reciver发送次数+1 成功次数+1 回复率更新
        //更新两人的intimacy -助力意愿

    }
    public static void update_failure(Agent agent){

    }

}
