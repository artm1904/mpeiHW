package ru.mpei.LR3.Behaviour;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import ru.mpei.LR3.Model.NodeData;


public class SendResultBehaviour extends Behaviour {

	private NodeData nodeData;
	private int acl;

	public SendResultBehaviour(NodeData nodeData, int acl) {
		this.nodeData = nodeData;
		this.acl = acl;
	}


	//Когда мы доходим до узла-цели, то отправляем найденный путь СТАРТОВОМУ узлу
	@Override
	public void action() {
		ACLMessage msg = new ACLMessage(acl);
		msg.addReceiver(new AID(nodeData.firstNodeName(), false));
		msg.setContent(NodeData.dataToString(nodeData));
		myAgent.send(msg);
		System.err.println("Дошли до узла-цели " + nodeData.getFindNodeName()+ ", отправляем СТАРТОВОМУ УЗЛУ "
				+ nodeData.firstNodeName() + " полученную цепочку узлов:" + msg.getContent());

	}

	@Override
	public boolean done() {
		return true;
	}
}
