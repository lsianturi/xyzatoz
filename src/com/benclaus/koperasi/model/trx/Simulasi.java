package com.benclaus.koperasi.model.trx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Simulasi {
	
	private Date tanggal;
	private Double saldo;
	private Double pokok;
	private Double bunga;
	private Double angsuran;

	public Simulasi() {
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getPokok() {
		return pokok;
	}

	public void setPokok(Double pokok) {
		this.pokok = pokok;
	}

	public Double getBunga() {
		return bunga;
	}

	public void setBunga(Double bunga) {
		this.bunga = bunga;
	}

	public Double getAngsuran() {
		return angsuran;
	}

	public void setAngsuran(Double angsuran) {
		this.angsuran = angsuran;
	}
	
	public static Double getCicilan(Double pokok, Double ir, Integer tenor) {
		 double rate = ((ir/100) * tenor) * pokok;
	     return (pokok + rate) / tenor;
	}
	
	public static List<Simulasi> getSimulasi(Double pokok, Double ir, Integer tenor, Date tglAju, Date tglPayroll) {
		List<Simulasi> list = new ArrayList<Simulasi>();
		
		Calendar calAju = Calendar.getInstance();
        calAju.setTime(tglAju);
        
        Calendar calPayroll = Calendar.getInstance();
        calPayroll.setTime(tglPayroll);
        
        Integer tglToday = calAju.get(Calendar.DATE);
        Integer tglGajian= calPayroll.get(Calendar.DATE);
        
        System.out.println(tglToday + " & " + tglGajian);
        
        Double pokok_ = pokok/tenor;
        Double angsuran = Simulasi.getCicilan(pokok, ir, tenor);
        Double bunga = angsuran - pokok_ ;
        
        Simulasi sim = null;
        
        sim = new Simulasi();
        sim.setTanggal(calAju.getTime());
        sim.setSaldo(pokok);
        sim.setPokok(0D);
        sim.setBunga(0D);
        sim.setAngsuran(0D);
        list.add(sim);
        
        if ( tglGajian > tglToday) {
        	calAju.add(Calendar.MONTH, -1);
        	calAju.set(Calendar.DATE, tglGajian);
        } else {
        	calAju.set(Calendar.DATE, tglGajian);
        }
        
    	for (int i = 1; i <= tenor; i++) {
    		sim = new Simulasi();
    		calAju.add(Calendar.MONTH, 1);
    		sim.setTanggal(calAju.getTime());
    		pokok = pokok - pokok_;
    		sim.setSaldo(pokok);
    		sim.setPokok(pokok_);
    		sim.setBunga(bunga);
    		sim.setAngsuran(angsuran);
    		list.add(sim);
    	}
        
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Simulasi> test = Simulasi.getSimulasi(new Double("1000000"), new Double("2.5"), 5, new Date(), sdf.parse("11/06/2018"));
	}
}
