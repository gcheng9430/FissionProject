package com.example.fission;

import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

public class Agent {


    //本次任务
    public boolean fissionable; //可以发送
    public boolean be_fissioned;  //可以被发 有什么情况不能被发？
    public int fission_task; //现有任务数量 也许task可以单独做一个类
    public int max_help_fission_people; //最多助力几人


    //社交距离/助力意愿
    public InteractionHistory interactionHistory; //交互历史
    public HashMap<Agent,Integer> intimacy;//跟每个人的助力意愿/intimacy 会根据互动历史变化 要不要放进交互历史里面？
    public int max_social_distances; //最大社交距离（注意不是助力一元
    public int cur_social_distance; //目前社交距离 会根据个人历史数据浮动


    //个人历史数据 （有没有必要）
    public int participation_count = 0; //总共参与次数
    public int success_num = 0; //历史助力成功完成任务次数
    public double success_rate = 0;  //历史平均完成任务rate
    public double avg_sent_count = 0;  //平均发给多少个人
    public int curr_fission_count = 0; //这一轮被（不管什么人）助力成功的次数
    public int total_fission_count = 0; //所有轮被成功助力的次数
    public double avg_fission_count = 0; //所有轮平均被助力次数


    //getters
    //setters

    //Class Decision?
    //决定要不要参与
    public boolean  decideToParticipate(EnvironmentStat envStat){
        //个人历史记录可以作为参考
        //跟新世界数据
        envStat.incrementParticipation();

    }

    //参与了 决定要发给哪些人
    public List<Agent> invitationDecision(){
        List<Agent> invited = new ArrayList<Agent>();
        //考虑a.在社交范围内
        //b.按照之前回复率最高排序
        // c.回复率如果太低一般就不再考虑了
        return invited;
    }

    //收到了 决定要帮助哪些人
    public List<Agent> decideToHelp(List<Agent> agent){
        //在cur/max社交距离以内
        //在能帮助的人quota以内 max_help_fission_people
        // 通过历史交互/助力意愿排序
        //加一定成分的noise
        List<Agent> helping = new List<Agent>();
        return helping;
    }

    //是否帮助一个具体的人
    public boolean decideToHelpAgent(Agent sender){

        return false;
    }

    calculate/update_intimacy(another agent) ：
    //会因为哪些情况变亲近和变疏远 应该跟agent.sent_agents裂变历史里的那个人的回复率有关


}
