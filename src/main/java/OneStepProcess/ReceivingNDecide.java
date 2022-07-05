package OneStepProcess;

import com.example.fission.Agent;

import java.util.*;

public class ReceivingNDecide implements Runnable{

//TODO
    @Override
    public void run() {

    }
    public static HashMap<Agent, List<Agent>> receivingNDecide(HashMap<Agent, List<Agent>> to_be_fissioned){

        HashMap<Agent, List<Agent>> fissioned = new HashMap<Agent, List<Agent>>();

        //遍历每一个接到任务的人
        for (Agent receiver: to_be_fissioned.keySet()){
            //使其做出选择 并计入结果
            List<Agent> helping  = decideToHelp(receiver, to_be_fissioned.get(receiver));
            for (Agent sender: helping ){
                fissioned.putIfAbsent(receiver,new ArrayList<Agent>() );
                fissioned.get(receiver).add(sender);
            }
        }
        return fissioned;
    }

    /**
     *
     * @param receiver
     * @param list of agent to be helped
     * @return list of agent receiver decided to help
     */
    public static List<Agent> decideToHelp(Agent receiver, List<Agent> agent){
        // 通过心理距离/亲密度排序选出要帮助的人
        //必须在最多能帮助的人quota以内 (max_help_fission_people)

        List<Agent> helping = new ArrayList<Agent>();
        if (agent.size()==0){
            return helping;
        }
        HashMap<Agent,List<Double>> relation = receiver.getSocialAndPsychDist();
        if (relation.size()==0) return helping;

        Comparator<Agent> compareByDist = new Comparator<Agent>() {
            @Override
            public int compare(Agent a, Agent b) {
                return relation.get(a).get(1).compareTo(relation.get(b).get(1));

            }
        };
        agent.sort(compareByDist);

        int maxNumHelping = receiver.getMaxHelpFissionPeople();
        //用max heap
//
//        PriorityQueue<Agent> heap =
//                new PriorityQueue<Agent>((n1, n2) -> (int) (relation.get(n1).get(1) - relation.get(n2).get(1)));
//        for (Agent candidate:agent){
//           heap.add(candidate);
//           if (heap.size()>maxNumHelping){
//               heap.poll();
//           }
//        }
//        List<Agent> result = new ArrayList<Agent>();
//        while(!heap.isEmpty()){
//            result.add(heap.poll());
//        }
        //return result;
        return  (agent.size()<=maxNumHelping?agent:agent.subList(0,maxNumHelping));
    }



    //是否帮助一个具体的人
//    public static boolean decideToHelpAgent(Agent receiver,Agent sender){
//        return false;
//    }
}
