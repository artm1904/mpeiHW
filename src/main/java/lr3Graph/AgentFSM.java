package lr3Graph;


import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lr3Graph.Initiator.InitiatorFind;
import lr3Graph.Initiator.SendStartMsgToFindAgent;

public class AgentFSM extends FSMBehaviour {

	public AgentFSM(Agent agent, SerialConfig serialConfig) {

		System.out.println("Агент " + agent.getLocalName() + " зашел FSM и будет настройка его поведений по конфигу");


		registerFirstState(new InitiatorFind(serialConfig), "b1");
		registerState(new SendStartMsgToFindAgent(agent, serialConfig), "b2IfInitiator");


		registerTransition("b1", "b2IfInitiator", 1);


	}
}
