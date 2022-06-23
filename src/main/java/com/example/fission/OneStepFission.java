package com.example.fission;

import java.util.HashMap;
import java.util.List;
import java.util.*;

public class OneStepFission { //base scenario maybe Scenario class?
    World world;
    EnvironmentStat environmentStat;
    BaseScenario scenario;

    public void step(){
        //根据历史收益决定参与意愿 1 - exp(-lambda * x) x:历史成功次数
        //agent.decideToParticipate()

        //为每个人根据已知政策分配任务
        scenario.apply_action_fission_force();
        //决定给哪些邻居发送 返回receiver->sender[]
        HashMap<Agent,List<Agent>> to_be_fissioned = sendingInvites();
        //作为收到任务的人 决定帮忙与否 返回 receiver->助力的人helping[]
        HashMap<Agent,List<Agent>> fissioned = receivingNDecide(to_be_fissioned);
        //动作之后的状态更新
        integrateInfo(to_be_fissioned,fissioned);
    }



    //决定给哪些邻居发送
    public HashMap<Agent,List<Agent>>  sendingInvites(){

        HashMap<Agent, List<Agent>> to_be_fissioned = new HashMap<Agent, List<Agent>>()；

        for (Agent agent: world.agents){
            //if in the  game
            if ( agent.fissionable == true ) {
                //所有要邀请的人
                List<Agent> invited = agent.invitationDecision();
                for (Agent receiver :invited){
                    to_be_fissioned.getOrDefault(receiver).add(agent);
                }
            }
        }
        return to_be_fissioned;
    }

    //收到的人要不要帮忙
    public HashMap<Agent, List<Agent>>  receivingNDecide(HashMap<Agent, List<Agent>> to_be_fissioned){

        HashMap<Agent, List<Agent>> fissioned = new HashMap<Agent, List<Agent>>();
        for (Agent receiver: to_be_fissioned.keySet()){
            //决定要不要帮助 每个收到助力任务的agent都通过社交距离+历史交互/助力意愿选择愿意助力的agent对象
            // 每个人最多可以点max_help_fission_people个人
            List<Agent> helping  = receiver.decideToHelp(to_be_fissioned.get(receiver));

            for (Agent sender: helping ){
                fissioned.getOrDefault(receiver).add(sender);
            }
            ////////////////////////////
            //另一种写法  不需要adjacency list 一边决定一边update所有东西
            for (Agent sender: to_be_fissioned.get(receiver) ){
                if (receiver.decideToHelpAgent(sender)){
                    //发送者者收到助力数量+1
                    //发送者和助力者互动历史更新
                    //关系变亲密
                    //裂变数量+1
                }  else{
                    //发送者者未助力数量+1
                    //发送者和助力者互动历史更新
                    //关系变紧张
                }
            }
            ///////////////////////

        }

        return fissioned;
    }

    //整合结果
    public void integrateInfo(HashMap<Agent, List<Agent>> to_be_fissioned,HashMap<Agent, List<Agent>> fissioned){

        //将用户之间的记录更新 包括任务完成与否
        interactionHistory.update();
        //重新计算社交距离?助理意愿？并保存
        socialDistance.update();
        intimacy.update();
        //这次活动的数据更新
        environmentStat.update();
        //用户数据更新并重置
        Agent.reset();


        //👇

        //将用户之间的记录更新 社交距离更新+助力意愿更新
        for (Agent receiver : fissioned.keySet()){
            List<Agent> all_sender = to_be_fissioned.get(receiver);
            List<Agent> helped_sender = fissioned.get(receiver);
            incrementFissionCount.incrementFission(helped_sender.size()); //裂变数量变化
            receiver.fissionable = true; //下一轮可以参加
            for (Agent sender : all_sender){
                //两人之间success updates
                if (helped_sender.contains(sender)){
                    //发送者和助力者互动历史更新:sender给reciver发送次数+1 成功次数+1
                    sender.interactionHistory.updateSuccess(receiver);
                    //关系变亲密
                    sender.intimacy.get(receiver) += 1; //有待商榷
                    receiver.intimacy.get(sender) += 1;
                    //发起者个人数据更新
                    sender.curr_fission_count += 1;
                    sender.total_fission_count += 1;
                    sender.avg_fission_count ??

                } else{  //两人之间failure updates
                    sender.InteractionHistory.updateFailure(receiver);

                }
            }

        }

        //发起者个人记录更新
        for (Agent agent : world.agents){
            if (agent.fission_task  !=  0 && agent.curr_fission_count >= agent.fission_task){
                agent.success_num += 1;
                agent.success_rate ??
                //世界的记录更新
                environmentStat.incrementSuccess();
            }
        }

        //用户所有当前session的东西重置


    }
}
