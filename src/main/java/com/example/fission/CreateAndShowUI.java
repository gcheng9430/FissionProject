package com.example.fission;

import com.example.fission.Scenario.BaseScenario;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.List;

import static com.example.fission.OneStepFission.step;


public class CreateAndShowUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private World world;

    public CreateAndShowUI(World world){
        this.world = world;
        setSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Fission");

    }

    static class DrawOneStep extends JPanel{
        World world;
        HashMap<Agent,List<Agent>> fissioned;

        public DrawOneStep(World world,HashMap<Agent,List<Agent>> fissioned ){
            this.world =  world;
            this.fissioned = fissioned;
        }
        @Override
        public void paint(Graphics g){
            super.paint(g);

            Graphics2D g2 = (Graphics2D) g;
            double scalar = 400;
            //画圆
            int parti = 0 ;
            for (Agent agent : world.getAgents()) {
                //create circle
                double x = agent.getViewAgent().getPosition()[0] * scalar+scalar;
                double y = agent.getViewAgent().getPosition()[1] * scalar+scalar;
                Shape circle = new Ellipse2D.Double(x, y, 10, 10);
                //set color geom.setColor(agent.getViewAgent().getColor());
                //geomList.add(geom);
                int newColor = (int) (200 - agent.getParticipationScore()/20);


                g2.setPaint(new Color(255,newColor,newColor));
                g2.fill(circle);
                //g2.draw(circle);
            }//outer loop 结束

            //划线
            for (Agent agent : fissioned.keySet()) {
                for (Agent other : fissioned.get(agent)) {
                    //愿意助力 划线
                    double ax = agent.getViewAgent().getPosition()[0] * scalar+scalar;
                    double ay = agent.getViewAgent().getPosition()[1] * scalar+scalar;
                    double bx = other.getViewAgent().getPosition()[0] * scalar+scalar;
                    double by = other.getViewAgent().getPosition()[1] * scalar+scalar;
                    Shape line = new Line2D.Double(ax, ay, bx, by);
                    g2.setPaint(Color.BLACK);
                    //geomLineList.add(geomline);
                    g2.draw(line);
                } //linne结束
            }//outer loop 结束

            try{
                Thread.sleep(1000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            removeAll();  //TODO
            System.out.println(parti);
        }

    }




    public static void main(String  arg[]){


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
            if (done || episode  >= 500){
                //env.reset();
                episode  = 0;
                return; //可以continue
            }
            DrawOneStep p = new DrawOneStep(world,fissioned);
            f.add(p);
            f.setVisible(true);

            episode ++;

        }

    }
}
