package com.benclaus.koperasi.model.trx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Simulasi {
	private Integer id;
	private Integer ajuId;
	private Integer noUrut;
	private Date tglCicilan;
	private Double saldo;
	private Double pokok;
	private Double bunga;
	private Double angsuran;
	private Double dibayar;
	private Date tglBayar;

	public Simulasi() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAjuId() {
		return ajuId;
	}

	public void setAjuId(Integer ajuId) {
		this.ajuId = ajuId;
	}

	public Integer getNoUrut() {
		return noUrut;
	}

	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}

	public Date getTglCicilan() {
		return tglCicilan;
	}

	public void setTglCicilan(Date tglCicilan) {
		this.tglCicilan = tglCicilan;
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
	
	public Double getDibayar() {
		return dibayar;
	}

	public void setDibayar(Double dibayar) {
		this.dibayar = dibayar;
	}

	public Date getTglBayar() {
		return tglBayar;
	}

	public void setTglBayar(Date tglBayar) {
		this.tglBayar = tglBayar;
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
        sim.setNoUrut(0);
        sim.setTglCicilan(calAju.getTime());
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
    		sim.setNoUrut(i);
    		calAju.add(Calendar.MONTH, 1);
    		sim.setTglCicilan(calAju.getTime());
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
