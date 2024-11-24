package lr3Graph.Initiator;

import jade.core.behaviours.OneShotBehaviour;
import lr3Graph.SerialConfig;

public class InitiatorFind extends OneShotBehaviour {

	SerialConfig serialConfig;
	boolean isInit;

	public InitiatorFind(SerialConfig serialConfig) {
		this.serialConfig = serialConfig;
	}

	@Override
	public void action() {

		if (serialConfig.getInitiator() == 0) {
			isInit = false;
		} else {
			isInit = true;
		}

	}

	@Override
	public int onEnd() {
		int ret;

		if (isInit == true) {
			ret = 1;
		} else {
			ret = 0;
		}
		return ret;
	}
}
