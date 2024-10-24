package ru.mpei.LR2;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class ReceiveMsg extends Behaviour {

	double x;
	double delta;
	Function calculation = new Function();
	@Override
	public void action() {

		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage receive = myAgent.receive(mt);

		if (receive != null) {
			String initData = receive.getContent();

			String[] res = initData.split(";");
			x = Float.parseFloat(res[0]);
			delta = Float.parseFloat(res[1]);

			System.out.println(myAgent.getLocalName() + " agent gets data from informCount Agent to do count with next value:" +
					"  x = " + res[0] + ", delta = " + res[1]);


			double result1 = calculation.doCalculation(myAgent.getLocalName(), x - delta);
			double result2 = calculation.doCalculation(myAgent.getLocalName(), x);
			double result3 = calculation.doCalculation(myAgent.getLocalName(), x + delta);

			System.out.println(myAgent.getLocalName() + " agent make count: x-delta=" +result1+ ", x=" +result2+ ", x+delta=" +result3);

			ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
			AID receiver = new AID("first", false);
			msg.addReceiver(receiver);
			String data = result1 + ";" + result2 + ";" + result3 + ";" + x + ";" + delta;
			msg.setContent(data);
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
