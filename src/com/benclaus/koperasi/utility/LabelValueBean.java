package com.benclaus.koperasi.utility;

/**
 * @author 
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LabelValueBean extends org.apache.struts.util.LabelValueBean {
	public LabelValueBean() {
		super(null, null);
	}
	
	public LabelValueBean(String label, String value) {
		super(label, value);
	}	
	
	private String labelValue;
	private String labelSeparator = "-";
		
	/**
	 * Returns the labelValue.
	 * @return String
	 */
	public String getLabelValue() {
		if (!this.getValue().equals("") && !this.getLabel().equals("")) {
			this.labelValue = this.getValue() + labelSeparator + this.getLabel();
		}
		return labelValue;
	}

	/**
	 * Sets the labelValue.
	 * @param labelValue The labelValue to set
	 */
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}

}
