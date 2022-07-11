package com.example.fission;

import com.example.fission.Scenario.Scenario;
import com.example.fission.Scenario.BaseScenario;
import lombok.Data;
//////not using



@Data
public class Environment {
    public World world;
    public Scenario scenario;


    public Environment() {
        this.world  = new World(200);
        this.scenario = new BaseScenario(this.world,0.5);
//        SwingUtilities.invokeLater((new Runnable() {
//            @Override
//            public void run() {
//                new DrawShapes(world);
//            }
//        }));

    }
    public void makeEnv(){

    }

    public void reset(){
        //reset world
        this.world.resetWorld();
        //reset render TODO
    }



    public void render(World world){

        //create geomery
//        List<Geom> geomList = new ArrayList<Geom>();
//        List<Line> geomLineList= new ArrayList<Line>();
//        for (Agent agent: world.getAgents()){
//            //create circle
//            Geom geom = Rendering.makeCircle(agent.getViewAgent().getSize());
//            geom.setColor(agent.getViewAgent().getColor());
//            geomList.add(geom);
//
//            //create lines
//            for (Agent other: world.getAgents()){
//                List<Double> relation =  agent.getSocialAndPsychDist().get(other);
//                if (relation.get(1) < relation.get(0)){
//                    //愿意助力 划线
//                    geomline = Rendering.makeLine(agent.getViewAgent().getPosition(),
//                            other.getViewAgent().getPosition());
//                    geomLineList.add(geomline);
//                }
//
//            }
//        }

        //add geoms to viewer
//        for viewer:this.viewers{
//            for
//        }

        //render to display

    }
}
