package com.example.fission;

import Scenario.BaseScenario;
import Scenario.Scenario;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

import static com.example.fission.OneStepFission.step;

public class Train {

    public static void train(){

        //Environment env = new Environment();
        World world = new World(200);
        BaseScenario scenario = new BaseScenario(world,0.5);
        CreateAndShowUI showUI = new CreateAndShowUI(world);
        int episode = 0;
        System.out.println("start iterations...");
        while (true){
            HashMap<Agent, List<Agent>> fissioned = step(world,scenario);

            boolean done = world.getEnvironmentStat().done;
            if (done || episode  >= 3){
                //env.reset();
                episode  = 0;
                break; //可以continue
            }

            episode ++;
            //env.render(env.world);
            //showUI.drawLine(fissioned);
            //showUI.drawCircle(world);

            SwingUtilities.invokeLater((new Runnable() {
                @Override
                public void run() {
                    showUI.drawLine(fissioned);
                    showUI.drawCircle(world);
                }
            }));
    }
    }


}
