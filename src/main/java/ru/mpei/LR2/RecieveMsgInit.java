package ru.mpei.LR2;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RecieveMsgInit extends Behaviour {

	private double numb1 = 0;
	private double numb2 = 0;
	private double numb3 = 0;
	private double X = 0;
	private double delta = 0;

	private double sum1 = 0;
	private double sum2 = 0;
	private double sum3 = 0;
	private double X1 = 0;
	private double Delta1 = 0;
	private int count = 0;
	private boolean stop = false;
	private double max = 0;

	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
		ACLMessage receive = myAgent.receive(mt);

		if (receive != null) {
			String msg = receive.getContent();
			String[] numb = msg.split(";");
			numb1 = Float.parseFloat(numb[0]);
			numb2 = Float.parseFloat(numb[1]);
			numb3 = Float.parseFloat(numb[2]);
			X = Float.parseFloat(numb[3]);
			delta = Float.parseFloat(numb[4]);

			if (count < 3) {
				count++;
			//	System.out.println(" count = " + count);
				sum1 += numb1;
				sum2 += numb2;
				sum3 += numb3;
				X1 = X;
				Delta1 = delta;

			}

			if (count == 3) {
				System.out.println("_________________________________________________________________________________________________________________________________");
				System.out.println("Общие суммы составят");

				System.out.println("Summa for: (x-delta)=" + sum1 + ", Summa for(x)=" + sum2 + ", Summa for(x+delta)=" + sum3 + " ,X= " + X1 + ", delta= " + Delta1);
				System.out.println("_________________________________________________________________________________________________________________________________");
				if (sum1 > sum2 && sum1 > sum3) {
					X1 = X - delta;
					Delta1 = delta;
					max = sum1;

				} else if (sum3 > sum2 && sum3 > sum1) {
					X1 = X + delta;
					Delta1 = delta;
					max = sum3;
				} else {
					X1 = X;
					Delta1 = delta / 2;
					max = sum2;
				}
				System.out.println("The common summa is evaluated, next X= " + X1 + ", next delta= " + Delta1);


				String initData = X1 + ";" + Delta1;
				count = 0;
				sum1 = 0;
				sum2 = 0;
				sum3 = 0;
			


				if (Delta1 < 0.01) {
					stop = true;
					System.err.println("__________________________________________________________________________________________________________");
					System.err.println("The MAX common summa is evaluated and equal =" + max + "  X= " + X1 + ",  delta= " + Delta1);
					System.err.println("___________________________________________________________________________________________________________");
				}

				ACLMessage sendNewIter = new ACLMessage(ACLMessage.AGREE);
				AID receiver = new AID("first", false);
				sendNewIter.addReceiver(receiver);
				sendNewIter.setContent(initData);
				myAgent.send(sendNewIter);

			}
		} else {
			block();
		}
	}

	@Override
	public boolean done() {
		return stop;
	}
}

