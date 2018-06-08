package com.benclaus.koperasi.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.benclaus.koperasi.action.trx.utils.ReadExcelUtil;

public class ReadExcelJob implements Job{
	private static Logger log = Logger.getLogger(ReadExcelJob.class);
	public ReadExcelJob(){
		
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ReadExcelUtil util = ReadExcelUtil.getInstance();
		try {
			log.debug("Process excel file(s)...");
			util.process();
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		
		/*DownloadHolidayUtil util =  DownloadHolidayUtil.getInstance();
		//CalculateAbsenUtil calUtil =  CalculateAbsenUtil.getInstance();
		GenerateUtil reportUtil = GenerateUtil.getInstance();*/
		try {
			/*List<Integer> cocode = service.getActiveCompanyCode();
			Calendar cal = GregorianCalendar.getInstance();
			if (cocode != null) {
				for(Integer code:cocode) {
					util.downloadHoliday(code, cal.get(Calendar.YEAR));
					reportUtil.generateReport(code, Calendar.getInstance().get(Calendar.MONTH) +1, Calendar.getInstance().get(Calendar.YEAR));
				}
			}*/
			
		} catch ( Exception e) {
			
		}
	}

}
