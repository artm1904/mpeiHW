package jadeHW.HW1;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceiveNotSpam extends Behaviour {
	MessageTemplate mt1;
	MessageTemplate mt2;

	@Override
	public void onStart() {

		mt1 = MessageTemplate.and(
				MessageTemplate.MatchPerformative(ACLMessage.INFORM),
				MessageTemplate.MatchProtocol("politics"));

		mt2 = MessageTemplate.and(
				MessageTemplate.MatchPerformative(ACLMessage.INFORM),
				MessageTemplate.MatchProtocol("weather"));
	}

	@Override
	public void action() {


		ACLMessage msg1 = myAgent.receive(mt1);
		ACLMessage msg2 = myAgent.receive(mt2);

		if (msg1 != null) {
			System.out.println(msg1.getContent());

		} else if (msg2 != null) {
			System.out.println(msg2.getContent());

		} else {
			block();
		}
	}

	@Override
	public boolean done() {
		return false;
	}
}
