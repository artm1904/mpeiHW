package lr3Graph.FindTarget;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lr3Graph.SerialConfig;

public class ReceiveMsgSearchRequest extends Behaviour {
	private MessageTemplate mt;
	private Agent agent;
	private SerialConfig serialConfig;

	public ReceiveMsgSearchRequest(Agent a, SerialConfig serialConfig) {
		this.agent = a;
		this.serialConfig = serialConfig;
	}

	@Override
	public void action() {
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

		ACLMessage receive = agent.receive(mt);
		if (receive != null) {
//			log.trace("Агент {} получил от агента {} сообщение: \n{}", getAgent().getLocalName(), receive.getSender(), receive.getContent());
//			ParserMSGSearch pars = new ParserMSGSearch();
//			pars.parserMSGSearch(receive.getContent(), agent, cfg);
			System.out.println(receive.getContent());
		} else {
			block();
		}
	}

	@Override
	public boolean done() {
		return false;
	}
}
