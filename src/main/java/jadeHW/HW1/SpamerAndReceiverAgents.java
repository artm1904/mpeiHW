package jadeHW.HW1;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;

/**
 * Создать двух агентов Spamer и Receiver
 *
 * У агента Receiver должно быть параллельное поведение, внутри которого два подповедения:
 * 1. принимает все сообщения кроме спама и пишет их контент в консоль через sout
 * 2. принимает только спам и выводит его контент через serr
 *
 * !!! При фильтрации учитывать для всех сообщений И перформативу, И протокол.
 */
public class SpamerAndReceiverAgents extends Agent {

	@Override
	protected void setup() {

		//пусть будет два сервиса spam и receive
		String serviceName = String.valueOf(this.getArguments()[0]);
		DfHelper.registerAgent(this, serviceName);

		if (this.getLocalName().equals("Spamer")) {

			this.addBehaviour(new Spam(this, 2_000));
		} else {
			this.addBehaviour(new ReceiverParallel());

		}

	}
}
