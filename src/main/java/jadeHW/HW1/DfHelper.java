package jadeHW.HW1;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.ArrayList;
import java.util.List;

public class DfHelper {
    public static void registerAgent(Agent a, String serviceName) {
        DFAgentDescription agentDescription = new DFAgentDescription();
        agentDescription.setName(a.getAID());

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(serviceName);
        serviceDescription.setName(a.getLocalName());
        agentDescription.addServices(serviceDescription);
        try {
            DFService.register(a, agentDescription);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
    }



    public static List<AID> findAgents(Agent a, String serviceName) {
        DFAgentDescription agentDescription = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(serviceName);
        agentDescription.addServices(serviceDescription);
        try {
            List<AID> findedAgents = new ArrayList<>();
            DFAgentDescription[] searchResult = DFService.search(a, agentDescription);
            for (DFAgentDescription aDescr : searchResult) {
                findedAgents.add(aDescr.getName());
            }
            return findedAgents;
        } catch (FIPAException e) {
            return new ArrayList<>();
        }
    }
}
