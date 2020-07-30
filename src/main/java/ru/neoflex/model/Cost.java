package ru.neoflex.model;

public class Cost{
	private String coldWater;
	private String gas;
	private String electricity;
	private String hotWater;

	public void setColdWater(String coldWater){
		this.coldWater = coldWater;
	}

	public String getColdWater(){
		return coldWater;
	}

	public void setGas(String gas){
		this.gas = gas;
	}

	public String getGas(){
		return gas;
	}

	public void setElectricity(String electricity){
		this.electricity = electricity;
	}

	public String getElectricity(){
		return electricity;
	}

	public void setHotWater(String hotWater){
		this.hotWater = hotWater;
	}

	public String getHotWater(){
		return hotWater;
	}

	@Override
 	public String toString(){
		return 
			"Cost{" + 
			"coldWater = '" + coldWater + '\'' + 
			",gas = '" + gas + '\'' + 
			",electricity = '" + electricity + '\'' + 
			",hotWater = '" + hotWater + '\'' + 
			"}";
		}
}
