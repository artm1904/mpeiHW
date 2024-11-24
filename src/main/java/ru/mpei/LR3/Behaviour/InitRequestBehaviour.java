package ru.mpei.LR3.Behaviour;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.mpei.LR3.Model.ListData;
import ru.mpei.LR3.Model.NodeData;

import java.util.Map;


public class InitRequestBehaviour extends Behaviour {

	private final ListData data;
	private final Map<String, Integer> neighbours;
	private final String findNodeName;

	private final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.AGREE);

	public InitRequestBehaviour(ListData data, Map<String, Integer> neighbours, String findNodeName) {
		this.data = data;
		this.neighbours = neighbours;
		this.findNodeName = findNodeName;
	}

	// СТАРТОВЫЙ узел отправляет своим соседям сообщение-запрос о начале поиска
	@Override
	public void onStart() {
		for (String node : neighbours.keySet()) {
			ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
			m.addReceiver(new AID(node, false));

			//Создаем объект NodeData который содержит все необходимое:
			// кого ищем, путь от СТАРТОВОЙ точки до СОСЕДА, а так же увеличивает вес данного пути
			NodeData nodeData = new NodeData(myAgent.getLocalName(), findNodeName);
			nodeData.addData(node, neighbours.get(node));
			m.setContent(NodeData.dataToString(nodeData));
			myAgent.send(m);
		}
	}


	//Стартовый узел принимает все пути, дошедшие до узла-цели поиска и кладет их в лист
	@Override
	public void action() {
		ACLMessage msg = myAgent.receive(mt);
		if (msg != null) {
			NodeData nd = NodeData.parseData(msg.getContent());
			if (msg.getPerformative() == ACLMessage.AGREE) {
				data.addDataAgree(nd);
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
