package ru.mpei.LR3.Behaviour;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.mpei.LR3.Model.NodeData;


import java.util.Map;


public class RequestBehaviour extends Behaviour {

    private final Map<String, Integer> neighbours;
    public RequestBehaviour(Map<String, Integer> neighbours){
        this.neighbours = neighbours;
    }

    @Override
    public void action() {

        ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (msg != null) {
			System.out.println("Узел "+ this.myAgent.getLocalName()+ " получил информацию от узла "
					+ msg.getSender().getLocalName() + ":" + msg.getContent());

            NodeData nodeData = NodeData.parseData(msg.getContent());

			if (nodeData.getFindNodeName().equals(myAgent.getLocalName())) {
				myAgent.addBehaviour(new SendResultBehaviour(nodeData, ACLMessage.AGREE));
			}



			//Мы получили сообщение, распарсили его в объект NodeData и выполняем проверку,
			// что данное сообщение получил узел-цель пииска.
			// 1) Если текущий узел - это цель, то ему по отправке NodeData СТАРТОВОМУ узлу
            if (nodeData.getFindNodeName().equals(myAgent.getLocalName())) {
                myAgent.addBehaviour(new SendResultBehaviour(nodeData, ACLMessage.AGREE));
            }

			else {
                for (String nodeName : neighbours.keySet()) {
                    NodeData nd = NodeData.parseData(msg.getContent());
					//Тем узлам, через которые поиск пути уже прошел, отправка сообщения не будет
                    if (nd.getNodeNames().contains(nodeName)) {
                        continue;
                    }

                    ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
					m.addReceiver(new AID(nodeName, false));
                    nd.addData(nodeName, neighbours.get(nodeName));
                    m.setContent(NodeData.dataToString(nd));
                    myAgent.send(m);
                }
            }

        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
