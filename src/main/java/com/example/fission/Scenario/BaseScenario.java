package com.example.fission.Scenario;

import com.example.fission.World;
import lombok.Data;

@Data
public class BaseScenario implements Scenario {
    private World world;
    private double psychDistBar;

    public BaseScenario(World world,double psychDistBar) {
        this.world = world;
        this.psychDistBar = psychDistBar;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public double getPsychDistBar() {
        return psychDistBar;
    }

    public void setPsychDistBar(double psychDistBar) {
        this.psychDistBar = psychDistBar;
    }

    //为参与者分派任务
//    public static void applyActionFissionForce() {
//
//        for (Agent agent : world.agents) {
//            //agent.cur_social_distance = agent.max_social_distance * np.exp(-agent.action.fission / 10)
//            agent.max_help_fission_people =  0; //最多助力几人
//            if (agent.fissionable) {
//                // 平台策略：每个用户的任务
//                agent.fission_task  = 0; //according to some formula
//
//            }
//        }
//
//    }
}
