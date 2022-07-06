package com.example.fission;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Data
public class Agent {


    //UI？
    public ViewAgent viewAgent = new ViewAgent();

    //本次任务
    public boolean fissionable = true; //可以发送/参与游戏
    public int fissionTask; //现有任务数量 也许task可以单独做一个类
    public int maxHelpFissionPeople = 3; //最多助力几人
    public int currFissionCount = 0; //这一轮被助力成功的次数


    //跟每个人的社交距离，助力意愿（心理距离）
    public HashMap<Agent,List<Double>> socialAndPsychDist = new HashMap<Agent,List<Double>>();

    //个人历史数据 （有没有必要）
    public double participationScore= 0.1; //总共参与度
    public int participationCount = 0; //总共参与轮次数
    public int successNum = 0; //成功完成任务次数
//    public double successRate = 0;  //历史完成任务rate
    public double totalSentCount = 0;  //总共发给多少个人（除以总参与轮次可以算出平均发给多少人）
    public int totalFissionCount = 0; //所有轮被成功助力的总次数
//    public double avgFissionCount = 0; //所有轮平均被助力次数

    /**
     * helper method :sender对receiver的亲密度重置
     * @param sender
     * @param receiver
     * @param newPsychDist
     */
    public static void setNewPsychDist(Agent sender,Agent receiver,double newPsychDist){
        sender.getSocialAndPsychDist().put(receiver, Arrays.asList(sender.getSocialAndPsychDist().get(receiver).get(0),newPsychDist));
    }

    /**
     *
     * @param agent
     * @param other
     * @return distance between two points
     */
    static double calculateDist(Agent agent, Agent other) {
        double ax = agent.getViewAgent().getPosition()[0];
        double ay = agent.getViewAgent().getPosition()[1];
        double bx = other.getViewAgent().getPosition()[0];
        double by = other.getViewAgent().getPosition()[1];
        return Math.hypot(ax-bx,ay-by);
    }

    /**
     * //    发给一个人 participationScore +1
     * //    回了 sender+1 receiver+1         sender对receiver psychDist 亲密 90%
     * //    没回  sender+1            sender对receiver intimacy 疏远 110%
     * @param sender
     * @param receiver
     */
    public static void updateRelationSuccess(Agent sender, Agent receiver) {
        //成功助力
        //参与度增加
        sender.setParticipationScore(sender.getParticipationScore()+0.1);
        receiver.setParticipationScore(receiver.getParticipationScore()+0.1);
        //sender单方面亲密度增加
        double newPsychDist = sender.getSocialAndPsychDist().get(receiver).get(1)* 1.1;
        Agent.setNewPsychDist(sender,receiver, newPsychDist);
        //sender个人数据更新
        sender.setCurrFissionCount(sender.getCurrFissionCount()+1); //可以拿来判断这一轮成功否
        sender.setTotalFissionCount(sender.getTotalFissionCount()+1);
        sender.setTotalSentCount(sender.getTotalSentCount()+1);
    }


    public static void updateRelationFailure(Agent sender, Agent receiver) {
        //失败助力
        //参与度增加
        sender.setParticipationScore(sender.getParticipationScore()+0.1);
        //sender 单方面亲密度疏远
        double newPsychDist = sender.getSocialAndPsychDist().get(receiver).get(1)* 0.9;
        Agent.setNewPsychDist(sender,receiver, newPsychDist);
        //sender个人数据更新
        sender.setTotalSentCount(sender.getTotalSentCount()+1);

    }


    public void resetForNextStep(){
        this.fissionTask = 0; //现有任务数量
        this.maxHelpFissionPeople  = 3; //最多助力几人
        this.currFissionCount = 0; //这一轮被助力成功的次数
    }


    public ViewAgent getViewAgent() {
        return viewAgent;
    }

    public void setViewAgent(ViewAgent viewAgent) {
        this.viewAgent = viewAgent;
    }

    public boolean isFissionable() {
        return fissionable;
    }

    public void setFissionable(boolean fissionable) {
        this.fissionable = fissionable;
    }

    public int getFissionTask() {
        return fissionTask;
    }

    public void setFissionTask(int fissionTask) {
        this.fissionTask = fissionTask;
    }

    public int getMaxHelpFissionPeople() {
        return maxHelpFissionPeople;
    }

    public void setMaxHelpFissionPeople(int maxHelpFissionPeople) {
        this.maxHelpFissionPeople = maxHelpFissionPeople;
    }

    public int getCurrFissionCount() {
        return currFissionCount;
    }

    public void setCurrFissionCount(int currFissionCount) {
        this.currFissionCount = currFissionCount;
    }

    public HashMap<Agent, List<Double>> getSocialAndPsychDist() {
        return socialAndPsychDist;
    }

    public void setSocialAndPsychDist(HashMap<Agent, List<Double>> socialAndPsychDist) {
        this.socialAndPsychDist = socialAndPsychDist;
    }

    public double getParticipationScore() {
        return participationScore;
    }

    public void setParticipationScore(double participationScore) {
        this.participationScore = participationScore;
    }

    public int getParticipationCount() {
        return participationCount;
    }

    public void setParticipationCount(int participationCount) {
        this.participationCount = participationCount;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public double getTotalSentCount() {
        return totalSentCount;
    }

    public void setTotalSentCount(double totalSentCount) {
        this.totalSentCount = totalSentCount;
    }

    public int getTotalFissionCount() {
        return totalFissionCount;
    }

    public void setTotalFissionCount(int totalFissionCount) {
        this.totalFissionCount = totalFissionCount;
    }
}
