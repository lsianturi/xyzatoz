package com.benclaus.koperasi.utility;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.benclaus.koperasi.dao.app.ConfigService;
import com.benclaus.koperasi.job.ReadExcelJob;
import com.benclaus.koperasi.model.Config;

/**
 * @version 	1.0
 * @author
 */
public class Initializer extends HttpServlet {
	ConfigService cfgService = ConfigService.getInstance();
//	Config cfg = cfgService.getRorConfig();
	private SchedulerFactory sf = null;
    private Scheduler scheduler = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(Initializer.class);

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	}

	/**
	* @see javax.servlet.GenericServlet#void ()
	*/
	@Override
	public void init() throws ServletException {
		super.init();
		
		//String cronExp = "0 */"+cfg.getProcessInterval()+" * * * ?";
		/*Integer sched = 0;
		try {
			sched = Integer.parseInt(getInitParameter("scheduler"));
			if (sched == 1) {

				sf = new StdSchedulerFactory();
		        scheduler = sf.getScheduler();
				
		
				scheduler.standby();
		
				
				JobDetail monthlyJob = newJob(ReadExcelJob.class).withIdentity("ReadExcelJob","Group1").build();
		
				Trigger rorTrigger = newTrigger()
					    .withIdentity("Daily", "group1")
					    .startAt(new Date())
					    .withSchedule(CronScheduleBuilder.cronSchedule(cronExp)) // fire every day at 15:00
					    .build();
				scheduler.scheduleJob(monthlyJob, rorTrigger);
		
				scheduler.start();
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			sched = 0;
		} catch (SchedulerException e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
	}
	
	@Override
	public void destroy() {
		super.destroy();
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			log.debug(e.getMessage());
		}
		
	}

}
