package com.example.fission;

import Scenario.BaseScenario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.List;

import static com.example.fission.OneStepFission.step;
import static com.example.fission.Train.train;


public class CreateAndShowUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private World world;

    public CreateAndShowUI(World world){
        this.world = world;
        setSize(new Dimension(320,320));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

//        JPanel p = new JPanel(){
//            @Override
//            public void paintComponent(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g;
//                double scalar = 400;
//
////                for (Agent agent : world.getAgents()) {
////                    //create circle
////                    double x = agent.getViewAgent().getPosition()[0] * scalar+scalar;
////                    double y = agent.getViewAgent().getPosition()[1] * scalar+scalar;
////                    Shape circle = new Ellipse2D.Double(x, y, 10, 10);
////                    //set color geom.setColor(agent.getViewAgent().getColor());
////                    //geomList.add(geom);
////                    g2.setPaint(new Color(255,200,200));
////                    g2.fill(circle);
////                    //g2.draw(circle);
////
////                    //create lines
////
////                    for (Agent other : agent.getSocialAndPsychDist().keySet()) {
////                        List<Double> relation = agent.getSocialAndPsychDist().get(other);
////                        if (relation.get(1) <= relation.get(0)) {
////                            //愿意助力 划线
////                            double ax = agent.getViewAgent().getPosition()[0] * scalar+scalar;
////                            double ay = agent.getViewAgent().getPosition()[1] * scalar+scalar;
////                            double bx = other.getViewAgent().getPosition()[0] * scalar+scalar;
////                            double by = other.getViewAgent().getPosition()[1] * scalar+scalar;
////                            Shape line = new Line2D.Double(ax, ay, bx, by);
////                            //geomLineList.add(geomline);
////
////                            g2.draw(line);
////                        }
////                    } //linne结束
////                }//outer loop 结束
////
////
////            }
////        };

        setTitle("Fission");
        //this.getContentPane().add(p);

    }

    public void drawCircle(World world){
        this.world = world;
        setSize(new Dimension(320,320));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        repaint();
        JPanel p = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                double scalar = 400;

                for (Agent agent : world.getAgents()) {
                    //create circle
                    double x = agent.getViewAgent().getPosition()[0] * scalar+scalar;
                    double y = agent.getViewAgent().getPosition()[1] * scalar+scalar;
                    Shape circle = new Ellipse2D.Double(x, y, 10, 10);
                    //set color geom.setColor(agent.getViewAgent().getColor());
                    //geomList.add(geom);
                    int newColor = 200 / agent.getParticipationScore();

                    g2.setPaint(new Color(255,newColor,newColor));
                    g2.fill(circle);
                    //g2.draw(circle);
                }//outer loop 结束
            }
        };
        this.getContentPane().add(p);
    }
    public void drawLine(HashMap<Agent,List<Agent>> fissioned){

        setSize(new Dimension(320,320));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                double scalar = 400;

                for (Agent agent : fissioned.keySet()) {
                    for (Agent other : agent.getSocialAndPsychDist().keySet()) {
                            //愿意助力 划线
                            double ax = agent.getViewAgent().getPosition()[0] * scalar+scalar;
                            double ay = agent.getViewAgent().getPosition()[1] * scalar+scalar;
                            double bx = other.getViewAgent().getPosition()[0] * scalar+scalar;
                            double by = other.getViewAgent().getPosition()[1] * scalar+scalar;
                            Shape line = new Line2D.Double(ax, ay, bx, by);
                            //geomLineList.add(geomline);
                            g2.draw(line);
                    } //linne结束
                }//outer loop 结束
                repaint();
            }
        };
        this.getContentPane().add(p);
        //removeAll();//or remove(JComponent)
        //revalidate();
        repaint();
    }



    public static void main(String  arg[]){
        SwingUtilities.invokeLater((new Runnable() {
            @Override
            public void run() {
                World world = new World(200);
                CreateAndShowUI showUI = new CreateAndShowUI(world);

                BaseScenario scenario = new BaseScenario(world,0.5);
                int episode = 0;
                System.out.println("start iterations...");
                while (true){
                    HashMap<Agent, List<Agent>> fissioned = step(world,scenario);
                    System.out.println(episode);
                    boolean done = world.getEnvironmentStat().done;
                    if (done || episode  >= 100){
                        //env.reset();
                        episode  = 0;
                        break; //可以continue
                    }

                    episode ++;
                    //env.render(env.world);
                    //showUI.drawLine(fissioned);
                    //showUI.drawCircle(world);
                    SwingUtilities.invokeLater(() -> {
                        new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println(2);

                            }
                        }).start();
                    });

                    SwingUtilities.invokeLater((new Runnable() {
                        @Override
                        public void run() {
                            showUI.drawLine(fissioned);
                            showUI.drawCircle(world);
                        }
                    }));

                }
            }
        }));
    }
}
