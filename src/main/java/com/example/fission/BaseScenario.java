package com.example.fission;

public class BaseScenario extends Scenario{
    private World world;

    //为参与者分派任务
    public static void apply_action_fission_force() {

        for (Agent agent : world.agents) {
            //agent.cur_social_distance = agent.max_social_distance * np.exp(-agent.action.fission / 10)
            agent.max_help_fission_people =  0; //最多助力几人
            if (agent.fissionable) {
                // 平台策略：每个用户的任务
                agent.fission_task  = 0; //according to some formula

            }
        }

    }
}
