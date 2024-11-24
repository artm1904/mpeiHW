package lr3Graph.Initiator;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lr3Graph.SerialConfig;

public class SendStartMsgToFindAgent extends OneShotBehaviour {
	SerialConfig serialConfig;
	Agent a;

	public SendStartMsgToFindAgent(Agent a, SerialConfig serialConfig) {
		this.a=a;
		this.serialConfig = serialConfig;
	}

	@Override
	public void action() {
		System.out.println("Агент "+ a+" ищет агента "+ serialConfig.getFind());

		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);

		//Берем каждого соседа у агента-инициатора и отправляем ему один раз сообщение-запрос на поиск цели
		for (int i = 0; i < serialConfig.getNeighbours().size(); i++){
			AID aid = new AID(serialConfig.getNeighbours().get(i), false);
			msg.addReceiver(aid);
			//
			String content = serialConfig.getFind() + "," + a.getLocalName() +"," + serialConfig.getNeighbours().get(i) + "," + serialConfig.getPathWeight().get(i);
			msg.setContent(content);

			a.send(msg);

		}

	}
}
