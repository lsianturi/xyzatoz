/**
 * 
 */
package com.benclaus.koperasi.report;

import java.lang.reflect.Field;
import java.util.Map;

//import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Lambok 
 *
 */
public class Report {
//	private JRDataSource dataSource;
	private Map map;
	private String jasperFilePath;
	/*public JRDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(JRDataSource dataSource) {
		this.dataSource = dataSource;
	}*/
	public String getJasperFilePath() {
		return jasperFilePath;
	}
	public void setJasperFilePath(String jasperFilePath) {
		this.jasperFilePath = jasperFilePath;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("[SOUT REPORT]>>>>> ");        
            try{
                Field[] fields = Report.class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }
}
