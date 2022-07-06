package com.example.fission;

import Scenario.BaseScenario;
import Scenario.Scenario;

import java.util.HashMap;
import java.util.List;

import static OneStepProcess.ReceivingNDecide.receivingNDecide;
import static OneStepProcess.SendingInvites.sendingInvites;
import static com.example.fission.Agent.updateRelationFailure;
import static com.example.fission.Agent.updateRelationSuccess;

public class OneStepFission {

    public static HashMap<Agent,List<Agent>> step(World world, BaseScenario scenario){
        //由scenario决定是否使用：
        //根据历史收益决定参与意愿 1 - exp(-lambda * x) x:历史成功次数 TODO
        //agent.decideToParticipate()
        //为每个人根据已知政策分配任务 TODO
        //scenario.applyActionFissionForce();

        //新的一轮裂变开始
        world.getEnvironmentStat().incrementCurrentRound();

        //决定给哪些邻居发送 返回receiver->list of sender[]
        HashMap<Agent,List<Agent>> to_be_fissioned = sendingInvites(world,scenario);

        //作为收到任务的人 决定帮忙与否 返回 receiver->list of 助力的人
        HashMap<Agent,List<Agent>> fissioned = receivingNDecide(to_be_fissioned);

        //动作之后的状态更新
        integrateInfo(to_be_fissioned, fissioned, world);
        return fissioned;

    }




    //整合结果

    /**
     *  //将用户之间的记录更新+这次活动的数据更新
     *  //用户个人数据更新(包括任务完成与否)
     *  //为下一轮重置用户信息
     * @param to_be_fissioned
     * @param fissioned
     */
    public static void integrateInfo(HashMap<Agent, List<Agent>> to_be_fissioned,HashMap<Agent, List<Agent>> fissioned, World world){

        //社交距离(socialDIst)更新+助力意愿(pyschDist)更新
        for (Agent receiver : fissioned.keySet()){
            receiver.fissionable = true; //只要收到信息下一轮就可以参加
            List<Agent> all_sender = to_be_fissioned.get(receiver);
            List<Agent> helped_sender = fissioned.get(receiver);
            world.environmentStat.incrementFissionCount(helped_sender.size()); //活动裂变数量变化
            for (Agent sender : all_sender){
                if (helped_sender.contains(sender)){
                    ////两人之间success updates 关系变亲密 sender个人数据增加
                    updateRelationSuccess(sender,receiver);
                } else{  //两人之间failure updates
                    updateRelationFailure(sender,receiver);
                }
            }
        }

        //发起者个人task记录更新并重置
        for (Agent agent : world.agents){
            if (agent.fissionable && agent.getFissionTask()  !=  0 && agent.getCurrFissionCount() >= agent.getFissionTask()){
                agent.setSuccessNum(agent.getSuccessNum()+1);
                //世界的记录更新
                world.environmentStat.incrementSuccess(1);
            }
            agent.resetForNextStep();

        }


    }
}
