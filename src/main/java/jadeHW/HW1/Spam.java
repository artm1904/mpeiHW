package jadeHW.HW1;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;


/**
 *  * У агента Spamer должно быть поведение Spam, котореое бескнечно шлет сообщения Receiver. Сообщения, которые должны отправляться:
 *  * 1. ACLMessage spam (performative = PROPOSE, content = "do you want a lot of money? Find 1X-bet", protocol = sports_betting)
 *  * 2. ACLMessage news (performative = INFORM, content = "Donald Trump win", protocol = politics)
 *  * 3. ACLMessage skillbox (performative = REQUEST, content = "Do you want to learn AI?", protocol = study)
 *  * 4. ACLMessage news (performative = INFORM, content = "There is rainfall in Moscow today", protocol = weather)
 */
public class Spam extends TickerBehaviour {
	public Spam(Agent a, long period) {
		super(a, period);
	}

	@Override
	protected void onTick() {


		//пусть будет два сервиса spam и receive
		List<AID> agents = DfHelper.findAgents(myAgent, "receive");
		System.out.println(myAgent.getLocalName() + " find " + agents.size() + " agents");

		//1
		ACLMessage spam = new ACLMessage(ACLMessage.PROPOSE);
		spam.setProtocol("sports_betting");
		spam.setContent("do you want a lot of money? Find 1X-bet");
		spam.addReceiver(agents.get(0));
		myAgent.send(spam);

		//2
		ACLMessage news = new ACLMessage(ACLMessage.INFORM);
		news.setProtocol("politics");
		news.setContent("Donald Trump win");
		news.addReceiver(agents.get(0));
		myAgent.send(news);

		//3
		ACLMessage skillbox = new ACLMessage(ACLMessage.REQUEST);

		skillbox.setProtocol("study");
		skillbox.setContent("Do you want to learn AI?");
		skillbox.addReceiver(agents.get(0));
		myAgent.send(skillbox);

		//4
		ACLMessage newsWeather = new ACLMessage(ACLMessage.INFORM);

		newsWeather.setProtocol("weather");
		newsWeather.setContent("There is rainfall in Moscow today");
		newsWeather.addReceiver(agents.get(0));
		myAgent.send(newsWeather);

	}
}
