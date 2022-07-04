package com.example.fission;

import java.util.ArrayList;
import java.util.List;

public class Agent {


    //本次任务
    public boolean fissionable; //可以发送
    public int fission_task; //现有任务数量 也许task可以单独做一个类
    public int max_help_fission_people; //最多助力几人


    //社交距离/助力意愿
    public HashMap<Agent,List<Integer>> socialAndPsychDistanc;//跟每个人的助力意愿/intimacy 会根据互动历史变化 要不要放进交互历史里面？




    //个人历史数据 （有没有必要）
    public int participation_count = 0; //总共参与次数
    public int success_num = 0; //历史助力成功完成任务次数
    public double success_rate = 0;  //历史平均完成任务rate
    public double avg_sent_count = 0;  //平均发给多少个人
    public int curr_fission_count = 0; //这一轮被（不管什么人）助力成功的次数
    public int total_fission_count = 0; //所有轮被成功助力的次数
    public double avg_fission_count = 0; //所有轮平均被助力次数




    //Class Decision?
    //决定要不要参与
    public boolean  decideToParticipate(EnvironmentStat envStat){
        //个人历史记录可以作为参考
        //跟新世界数据
        //根据历史成功次数 fission success  + participation score 决定要不要参与
        envStat.incrementParticipation();

    }

    //参与了 决定要发给哪些人
    public List<Agent> invitationDecision(){
        List<Agent> invited = new ArrayList<Agent>();
        //考虑a.intimacy < social distance 就发    intimacy 太大某个bar 排除
        return invited;
    }

    //收到了 决定要帮助哪些人
    public List<Agent> decideToHelp(List<Agent> agent){
        //在cur/max社交距离以内
        //在能帮助的人quota以内 max_help_fission_people
        // 通过社交距离排序
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

    发给一个人 partcipationn +1
    回了 他+1我+1 psychDist  90%
    没回  intimacy  110%


}
