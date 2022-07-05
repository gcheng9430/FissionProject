package com.example.fission;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.fission.Agent.calculateDist;

@Data
public class World {
    public List<Agent> agents  = new ArrayList<Agent>();
    public int numAgent = 0;
    EnvironmentStat environmentStat;

    public List<Agent> getAgents() {
        return agents;
    }

    public int getNumAgent() {
        return numAgent;
    }

    public EnvironmentStat getEnvironmentStat() {
        return environmentStat;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public void setNumAgent(int numAgent) {
        this.numAgent = numAgent;
    }

    public void setEnvironmentStat(EnvironmentStat environmentStat) {
        this.environmentStat = environmentStat;
    }

    //初始化 在scenario里面 决定world的参数 有多少个agent
    public World (int numAgent){
        //add agents for world
        this.setNumAgent(numAgent);
        for (int i = 0;i < numAgent;i++){
            Agent agent = new Agent();
            this.agents.add(agent);
        }
        //make intitial conditions
        resetWorld();

    }

    //重置world里面所有agent的值
    public void resetWorld(){
        double start = -1;
        double end = 1;
        Random random = new Random();
        //reset or initialize color and position for all agents
        for (Agent agent: this.agents){
            agent.getViewAgent().setColor(new double[]{0.35,0.35,0.85});
            agent.setFissionable(false);
            agent.getViewAgent().setSize(0.5);
            double x = start + random.nextDouble()*(end-start);
            double y = start + random.nextDouble()*(end-start);
            agent.getViewAgent().setPosition(new double[]{x,y});
        }

        this.environmentStat = new EnvironmentStat();
        //为他们所有人分配邻居
        for (Agent agent: this.getAgents()){
            //随机一个邻居范围
            Random rdm  = new Random();
            double maxSocialDist = rdm.nextDouble()*0.2;  //有待考证
            //寻找邻居
            for (Agent other: this.getAgents()){
                double socialDist = calculateDist(agent, other);
                if (socialDist < maxSocialDist && socialDist!=0)
                    agent.getSocialAndPsychDist().put(other, Arrays.asList(socialDist,socialDist));
                    other.getSocialAndPsychDist().put(agent, Arrays.asList(socialDist,socialDist));

            }
        }
    }
}
