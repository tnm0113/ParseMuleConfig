package model;

public class MuleApp {
	private MuleFlow flow;
	private MuleHttpListenerConfig listenerConfig;
	
	public MuleApp() {
		
	}
	
	public MuleFlow getFlow() {
		return flow;
	}
	public void setFlow(MuleFlow flow) {
		this.flow = flow;
	}
	public MuleHttpListenerConfig getListenerConfig() {
		return listenerConfig;
	}
	public void setListenerConfig(MuleHttpListenerConfig listenerConfig) {
		this.listenerConfig = listenerConfig;
	}
}
