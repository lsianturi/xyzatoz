package com.benclaus.koperasi.model.trx;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import com.benclaus.koperasi.model.master.Nasabah;
import com.benclaus.koperasi.model.master.Pegawai;
import com.benclaus.koperasi.model.system.Audit;
import com.benclaus.koperasi.model.usm.Menu;

public class Aju extends Audit{
	
	private Integer id;
	private String noKredit;
	private Date tglAju;
	private Nasabah nasabah;
	private Integer noUrutNsbh;
	private Integer tipeKredit; //BARU, RO,TU, TO
	private Integer jenisPinjam; //Bulanan, Mingguan, Harian
	private Double jumlahAju;
	private Integer tenor;
	private Double interestRate;
	private Double angsuranAju;
	private Nasabah sponsor;
	private Pegawai marketing;
	private Pegawai surveyor;
	private String penjamin;
	private String agunan;
	private Date jatuhTempo;
	private Integer realisasi;
	private List<Simulasi> simulasi;
	private String note;

	public Aju() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTglAju() {
		return tglAju;
	}

	public void setTglAju(Date tglAju) {
		this.tglAju = tglAju;
	}

	public Nasabah getNasabah() {
		return nasabah;
	}

	public void setNasabah(Nasabah nasabah) {
		this.nasabah = nasabah;
	}

	public Integer getNoUrutNsbh() {
		return noUrutNsbh;
	}

	public void setNoUrutNsbh(Integer noUrutNsbh) {
		this.noUrutNsbh = noUrutNsbh;
	}

	public Integer getTipeKredit() {
		return tipeKredit;
	}

	public void setTipeKredit(Integer tipeKredit) {
		this.tipeKredit = tipeKredit;
	}

	public Integer getJenisPinjam() {
		return jenisPinjam;
	}

	public void setJenisPinjam(Integer jenisPinjam) {
		this.jenisPinjam = jenisPinjam;
	}

	public Double getJumlahAju() {
		return jumlahAju;
	}

	public void setJumlahAju(Double jumlahAju) {
		this.jumlahAju = jumlahAju;
	}

	public Integer getTenor() {
		return tenor;
	}

	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getAngsuranAju() {
		return angsuranAju;
	}

	public void setAngsuranAju(Double angsuranAju) {
		this.angsuranAju = angsuranAju;
	}

	public Nasabah getSponsor() {
		return sponsor;
	}

	public void setSponsor(Nasabah sponsor) {
		this.sponsor = sponsor;
	}

	public Pegawai getMarketing() {
		return marketing;
	}

	public void setMarketing(Pegawai marketing) {
		this.marketing = marketing;
	}
	
	public void setNoKredit(String noKredit) {
		this.noKredit = noKredit;
	}

	public String getNoKredit() {
		return noKredit;
	}

	public String getPenjamin() {
		return penjamin;
	}

	public void setPenjamin(String penjamin) {
		this.penjamin = penjamin;
	}

	public String getAgunan() {
		return agunan;
	}

	public void setAgunan(String agunan) {
		this.agunan = agunan;
	}

	public Date getJatuhTempo() {
		return jatuhTempo;
	}

	public void setJatuhTempo(Date jatuhTempo) {
		this.jatuhTempo = jatuhTempo;
	}
	
	public String getTipeKreditName() {
		return TipeKredit.getTipeKredit(tipeKredit);
	}
	public Integer getRealisasi() {
		return realisasi;
	}

	public void setRealisasi(Integer realisasi) {
		this.realisasi = realisasi;
	}

	public List<Simulasi> getSimulasi() {
		return simulasi;
	}

	public void setSimulasi(List<Simulasi> simulasi) {
		this.simulasi = simulasi;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Pegawai getSurveyor() {
		return surveyor;
	}

	public void setSurveyor(Pegawai surveyor) {
		this.surveyor = surveyor;
	}

	public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("[SOUT TX_KR_AJU]>>>>> ");        
            try{
                Field[] fields = Menu.class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }	
}
