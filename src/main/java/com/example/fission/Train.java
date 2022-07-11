package com.example.fission;

import com.example.fission.Scenario.BaseScenario;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

import static com.example.fission.OneStepFission.step;

public class Train {

    public static void train(){
        JFrame f = new JFrame();
        f.setSize(1000,1000);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        World world = new World(1000);
        BaseScenario scenario = new BaseScenario(world,0.5);
        int episode = 0;
        System.out.println("start iterations...");
        while (true){
            HashMap<Agent, List<Agent>> fissioned = step(world,scenario);
            boolean done = world.getEnvironmentStat().done;
            if (done || episode  >= 100){
                //env.reset();
                episode  = 0;
                return; //可以continue
            }
            CreateAndShowUI.DrawOneStep p = new CreateAndShowUI.DrawOneStep(world,fissioned);
            f.add(p);
            f.setVisible(true);

            episode ++;

        }

    }
    }





