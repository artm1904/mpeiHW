package ru.mpei.LR2;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SendMsgAll extends Behaviour {
	@Override
	public void action() {

		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage receive = myAgent.receive(mt);

		MessageTemplate mt2 = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
		ACLMessage receive2 = myAgent.receive(mt2);

		if (receive != null) {
			String initData = receive.getContent();

//			String[] res = initData.split(";");
//			System.out.println(res[0]+res[1]);

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent(initData);
			AID receiver1 = new AID("first", false);
			AID receiver2 = new AID("second", false);
			AID receiver3 = new AID("third", false);

			//Добавляем в лист получателей всех трех агентов
			msg.addReceiver(receiver1);
			msg.addReceiver(receiver2);
			msg.addReceiver(receiver3);
			msg.setContent(initData);
			myAgent.send(msg);
		} else {
			block();
		}

		if (receive2 != null) {
			String initData = receive2.getContent();

			String[] res = initData.split(";");
			System.out.println(res[0]+res[1]);

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent(initData);
			AID receiver1 = new AID("first", false);
			AID receiver2 = new AID("second", false);
			AID receiver3 = new AID("third", false);

			//Добавляем в лист получателей всех трех агентов
			msg.addReceiver(receiver1);
			msg.addReceiver(receiver2);
			msg.addReceiver(receiver3);
			msg.setContent(initData);
			myAgent.send(msg);
		} else {
			block();
		}


	}

	@Override
	public boolean done() {
		return false;
	}
}
