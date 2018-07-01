package com.benclaus.koperasi.model.master;

import java.lang.reflect.Field;
import java.util.Date;

import com.benclaus.koperasi.model.kantor.Cabang;
import com.benclaus.koperasi.model.kantor.Unit;
import com.benclaus.koperasi.model.system.Audit;
import com.benclaus.koperasi.model.usm.Menu;

public class Nasabah extends Audit{
	
	private Integer id;
	private String nama;
	private StatusPK jenisKelamin;
	private StatusPK statusSipil;
	private String domisili;
	private String alamat;
	private String telepon;
	private Perusahaan pt;
	private String nik;
	private Bank bank;
	private String bagian;
	private String noRekening;
	private String pinAtm;
	private Date tglMasuk;
	private Date tglPayroll;
	private StatusPK statusKaryawan;
	private StatusPK jenisAnggota;
	private StatusPK statusKerja;
	private String noRekeningRef;
	private String namaRef;
	private String aplikasi;
	private String keterangan;
	private boolean anAgent;
	private Cabang cabang;
	private Unit unit;

	public Nasabah() {
		super();
		super.tableName = "ms_nasabah";
		super.fields = "id";
	}
	
	public Nasabah(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public StatusPK getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(StatusPK jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public StatusPK getStatusSipil() {
		return statusSipil;
	}

	public void setStatusSipil(StatusPK statusSipil) {
		this.statusSipil = statusSipil;
	}

	public String getDomisili() {
		return domisili;
	}

	public void setDomisili(String domisili) {
		this.domisili = domisili;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTelepon() {
		return telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	public Perusahaan getPt() {
		return pt;
	}

	public void setPt(Perusahaan pt) {
		this.pt = pt;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getBagian() {
		return bagian;
	}

	public void setBagian(String bagian) {
		this.bagian = bagian;
	}

	public String getNoRekening() {
		return noRekening;
	}

	public void setNoRekening(String noRekening) {
		this.noRekening = noRekening;
	}

	public Date getTglMasuk() {
		return tglMasuk;
	}

	public void setTglMasuk(Date tglMasuk) {
		this.tglMasuk = tglMasuk;
	}

	public Date getTglPayroll() {
		return tglPayroll;
	}

	public void setTglPayroll(Date tglPayroll) {
		this.tglPayroll = tglPayroll;
	}

	public StatusPK getStatusKaryawan() {
		return statusKaryawan;
	}

	public void setStatusKaryawan(StatusPK statusKaryawan) {
		this.statusKaryawan = statusKaryawan;
	}

	public StatusPK getJenisAnggota() {
		return jenisAnggota;
	}

	public void setJenisAnggota(StatusPK jenisAnggota) {
		this.jenisAnggota = jenisAnggota;
	}
	public String getNoRekeningRef() {
		return noRekeningRef;
	}

	public StatusPK getStatusKerja() {
		return statusKerja;
	}

	public void setStatusKerja(StatusPK statusKerja) {
		this.statusKerja = statusKerja;
	}

	public void setNoRekeningRef(String noRekeningRef) {
		this.noRekeningRef = noRekeningRef;
	}

	public String getNamaRef() {
		return namaRef;
	}

	public void setNamaRef(String namaRef) {
		this.namaRef = namaRef;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	
	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public boolean isAnAgent() {
		return anAgent;
	}

	public void setAnAgent(boolean anAgent) {
		this.anAgent = anAgent;
	}
	public String getPinAtm() {
		return pinAtm;
	}

	public void setPinAtm(String pinAtm) {
		this.pinAtm = pinAtm;
	}

	public String getAplikasi() {
		return aplikasi;
	}

	public void setAplikasi(String aplikasi) {
		this.aplikasi = aplikasi;
	}

	public Cabang getCabang() {
		return cabang;
	}

	public void setCabang(Cabang cabang) {
		this.cabang = cabang;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("[SOUT MS_NASABAH]>>>>> ");        
            try{
                Field[] fields = Menu.class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }	
}
