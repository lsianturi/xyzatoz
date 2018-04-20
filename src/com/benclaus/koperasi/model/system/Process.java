package com.benclaus.koperasi.model.system;


public class Process {

	protected long startProcess = 0L;	
	protected long endProcess = 0L;		
	protected Long result;

	public long getStartProcess() { return startProcess; }
	public void setStartProcess(long startProcess) { this.startProcess = startProcess; }

	public long getEndProcess() { return endProcess; }
	public void setEndProcess(long endProcess) { this.endProcess = endProcess; }	

	public String getResult() { 
		return String.valueOf(getEndProcess() -  getStartProcess());
	}
	public void setResult(Long result) { this.result = result; }	
}