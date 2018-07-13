/*
Navicat MySQL Data Transfer

Source Server         : local-koperasi
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : koperasi

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-10 20:38:56
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `absen_audittrail`
-- ----------------------------
DROP TABLE IF EXISTS `absen_audittrail`;
CREATE TABLE `absen_audittrail` (
  `TABLENAME` varchar(60) DEFAULT NULL,
  `FIELDS` varchar(60) DEFAULT NULL,
  `CONTENTS` varchar(255) DEFAULT NULL,
  `LASTUPDDTM` datetime DEFAULT NULL,
  `LASTUPDBY` varchar(30) DEFAULT NULL,
  `LASTUPDPROCESS` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of absen_audittrail
-- ----------------------------
INSERT INTO absen_audittrail VALUES ('absen_logintrail', 'userCode', 'lambok.sianturi', '2018-06-11 11:38:59', 'lambok.sianturi', 'Login');

-- ----------------------------
-- Table structure for `absen_logintrail`
-- ----------------------------
DROP TABLE IF EXISTS `absen_logintrail`;
CREATE TABLE `absen_logintrail` (
  `USERCODE` varchar(30) DEFAULT NULL,
  `SESSIONID` varchar(60) DEFAULT NULL,
  `LASTUPDDTM` date DEFAULT NULL,
  `LASTUPDPROCESS` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of absen_logintrail
-- ----------------------------

-- ----------------------------
-- Table structure for `log_logintrail`
-- ----------------------------
DROP TABLE IF EXISTS `log_logintrail`;
CREATE TABLE `log_logintrail` (
  `userCode` varchar(30) NOT NULL,
  `sessionId` varchar(60) NOT NULL,
  `lastUpdDtm` time NOT NULL,
  `lastUpdProcess` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- ----------------------------
-- Table structure for `ms_bank`
-- ----------------------------
DROP TABLE IF EXISTS `ms_bank`;
CREATE TABLE `ms_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kode` varchar(8) DEFAULT NULL,
  `nama` varchar(60) DEFAULT NULL,
  `area` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_bank
-- ----------------------------
INSERT INTO ms_bank VALUES ('1', 'BCA', 'Bank Central Asia', null);
INSERT INTO ms_bank VALUES ('2', 'BNI', 'Bank Nasional Indonesia', null);
INSERT INTO ms_bank VALUES ('3', 'MANDIRI', 'Bank Mandiri', null);
INSERT INTO ms_bank VALUES ('4', 'BRI', 'Bank Rakyat Indonesia', null);

-- ----------------------------
-- Table structure for `ms_industri`
-- ----------------------------
DROP TABLE IF EXISTS `ms_industri`;
CREATE TABLE `ms_industri` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_industri
-- ----------------------------
INSERT INTO ms_industri VALUES ('1', 'Garment');
INSERT INTO ms_industri VALUES ('2', 'Minuman');
INSERT INTO ms_industri VALUES ('3', 'Air');
INSERT INTO ms_industri VALUES ('4', 'Komoditi');
INSERT INTO ms_industri VALUES ('5', 'Tekstil');

-- ----------------------------
-- Table structure for `ms_jenis_pinjaman`
-- ----------------------------
DROP TABLE IF EXISTS `ms_jenis_pinjaman`;
CREATE TABLE `ms_jenis_pinjaman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_jenis_pinjaman
-- ----------------------------
INSERT INTO ms_jenis_pinjaman VALUES ('1', 'Bulanan');
INSERT INTO ms_jenis_pinjaman VALUES ('2', 'Mingguan');
INSERT INTO ms_jenis_pinjaman VALUES ('3', 'Harian');

-- ----------------------------
-- Table structure for `ms_jns_anggota`
-- ----------------------------
DROP TABLE IF EXISTS `ms_jns_anggota`;
CREATE TABLE `ms_jns_anggota` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jenis` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_jns_anggota
-- ----------------------------
INSERT INTO ms_jns_anggota VALUES ('1', 'Anggota');
INSERT INTO ms_jns_anggota VALUES ('2', 'Umum');
INSERT INTO ms_jns_anggota VALUES ('3', 'Biasa');

-- ----------------------------
-- Table structure for `ms_jns_kelamin`
-- ----------------------------
DROP TABLE IF EXISTS `ms_jns_kelamin`;
CREATE TABLE `ms_jns_kelamin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_jns_kelamin
-- ----------------------------
INSERT INTO ms_jns_kelamin VALUES ('1', 'Laki-laki');
INSERT INTO ms_jns_kelamin VALUES ('2', 'Wanita');

-- ----------------------------
-- Table structure for `ms_menu`
-- ----------------------------
DROP TABLE IF EXISTS `ms_menu`;
CREATE TABLE `ms_menu` (
  `MENUCODE` varchar(16) NOT NULL,
  `PARENTMENUCODE` varchar(16) DEFAULT NULL,
  `DESCRIPTION` varchar(60) DEFAULT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `MENUTYPE` varchar(12) DEFAULT NULL,
  `SYSTEMMENU` varchar(20) DEFAULT NULL,
  `MENUORDER` varchar(2) DEFAULT NULL,
  `DELETED` int(1) DEFAULT NULL,
  `MENULEVEL` varchar(2) DEFAULT NULL,
  `COMMAND` varchar(255) DEFAULT NULL,
  `CREATEDBY` varchar(30) DEFAULT NULL,
  `LASTUPDDTM` time DEFAULT NULL,
  `LASTUPDBY` varchar(30) DEFAULT NULL,
  `LASTUPDPROCESS` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`MENUCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_menu
-- ----------------------------
INSERT INTO ms_menu VALUES ('KOP', null, 'Koperasi', 'Koperasi', '0', '1', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('MST', 'KOP', 'Master Data', 'Master Data', '1', '1', '1', '0', '1', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('MST_NSB', 'MST', 'Master Nasabah', 'Master Nasabah', '2', '1', '1', '0', '1', 'nasabah.do?dispatch=prepare', null, '00:10:53', 'lambok.sianturi', 'update');
INSERT INTO ms_menu VALUES ('MST_NSB_add', 'MST_NSB', 'Tambah nasabah', 'Tambah nasabah', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_NSB_del', 'MST_NSB', 'Delete nasabah', 'Delete nasabah', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_NSB_search', 'MST_NSB', 'Search nasabah', 'Search nasabah', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_NSB_upd', 'MST_NSB', 'Update nasabah', 'Update nasabah', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_PEG', 'MST', 'Master Pegawai', 'Master Pegawai', '2', '1', '3', '0', '1', 'pegawai.do?dispatch=prepare', null, null, null, null);
INSERT INTO ms_menu VALUES ('MST_PEG_add', 'MST_PEG', null, null, '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_PEG_del', 'MST_PEG', null, null, '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_PEG_search', 'MST_PEG', null, null, '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_PEG_upd', 'MST_PEG', null, null, '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_PRSHN', 'MST', 'Master Perusahaan', 'Master Perusahaan', '2', '1', '2', '0', '1', 'perusahaan.do?dispatch=prepare', null, null, null, null);
INSERT INTO ms_menu VALUES ('MST_PRSHN_add', 'MST_PRSHN', 'Add perusahaan', 'Add perusahaan', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_PRSHN_del', 'MST_PRSHN', 'Delete perusahaan', 'Delete perusahaan', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_PRSHN_search', 'MST_PRSHN', 'View Perusahaan', 'View Perusahaan', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('MST_PRSHN_upd', 'MST_PRSHN', 'Update perusahaan', 'Update perusahaan', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET', 'KOP', 'Setting', 'Setting', '1', '1', '3', '0', '1', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('SET_KANTOR', 'SET', 'Setting Kantor', 'Setting Kantor', '2', '1', '4', '0', '1', 'kantor/pusat.do?dispatch=prepare', 'lambok.sianturi', null, null, null);
INSERT INTO ms_menu VALUES ('SET_KANTOR_upd', 'SET_KANTOR', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('SET_MENU', 'SET', 'Menu', 'Menu', '2', '1', '3', '0', '1', 'usm/MenuAction.do?dispatch=prepare', null, null, null, null);
INSERT INTO ms_menu VALUES ('SET_MENU_add', 'SET_MENU', null, null, '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_MENU_del', 'SET_MENU', null, null, '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_MENU_search', 'SET_MENU', null, null, '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_MENU_upd', 'SET_MENU', null, null, '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_ROLE', 'SET', 'Role', 'Role', '2', '1', '2', '0', '1', 'usm/RoleAction.do?dispatch=prepare', null, null, null, null);
INSERT INTO ms_menu VALUES ('SET_ROLE_add', 'SET_ROLE', null, null, '1', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_ROLE_assign', 'SET_ROLE', null, null, '1', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_ROLE_del', 'SET_ROLE', null, null, '1', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_ROLE_del_as', 'SET_ROLE', null, null, '1', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_ROLE_page', 'SET_ROLE', null, null, '1', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_ROLE_search', 'SET_ROLE', null, null, '1', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_ROLE_upd', 'SET_ROLE', null, null, '1', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_USR', 'SET', 'Master User', 'Master User', '2', '1', '1', '0', '1', 'usm/UserAction.do?dispatch=prepare', null, null, null, null);
INSERT INTO ms_menu VALUES ('SET_USR_search', 'SET_USR', 'User page', 'User page', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('SET_USR_upd', 'SET_USR', 'Update user', 'update user', '0', '0', '0', '0', '0', null, '0', null, null, null);
INSERT INTO ms_menu VALUES ('TRX', 'KOP', 'Transaksi', 'Transaction', '1', '1', '2', '0', '1', '', 'lambok.sianturi', null, null, null);
INSERT INTO ms_menu VALUES ('TRX_PJM', 'TRX', 'Pengajuan Kredit', 'Pengajuan Kredit', '2', '1', '1', '0', '2', 'trx/aju.do?dispatch=prepare', 'lambok.sianturi', null, null, null);
INSERT INTO ms_menu VALUES ('TRX_PJM_add', 'TRX_PJM', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('TRX_PJM_del', 'TRX_PJM', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('TRX_PJM_search', 'TRX_PJM', 'Transaksi Pinjaman Main Page', 'Transaksi Pinjaman Main Page', '0', '0', '0', '0', '0', '', 'lambok.sianturi', '15:38:08', 'lambok.sianturi', 'update');
INSERT INTO ms_menu VALUES ('TRX_PJM_upd', 'TRX_PJM', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('TRX_PJM_view', 'TRX_PJM', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('TRX_REAL', 'TRX', 'Realisasi Kredit', 'Realisasi Kredit', '2', '1', '2', '0', '3', 'trx/real.do?dispatch=prepare', 'lambok.sianturi', null, null, null);
INSERT INTO ms_menu VALUES ('TRX_REAL_add', 'TRX_REAL', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('TRX_REAL_del', 'TRX_REAL', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('TRX_REAL_search', 'TRX_REAL', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('TRX_REAL_upd', 'TRX_REAL', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);
INSERT INTO ms_menu VALUES ('TRX_REAL_view', 'TRX_REAL', null, null, '0', '0', '0', '0', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for `ms_nasabah`
-- ----------------------------
DROP TABLE IF EXISTS `ms_nasabah`;
CREATE TABLE `ms_nasabah` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cabang` int(11) NOT NULL,
  `unit` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jns_kelamin_id` int(11) NOT NULL,
  `sts_sipil_id` int(1) NOT NULL,
  `domisili` text,
  `alamat` text,
  `telepon` varchar(20) NOT NULL,
  `nik` varchar(30) DEFAULT NULL,
  `perusahaan_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `bagian` varchar(30) NOT NULL,
  `no_rek` varchar(30) NOT NULL,
  `pin_atm` varchar(10) DEFAULT NULL,
  `tgl_masuk` date NOT NULL,
  `tgl_payroll` date NOT NULL,
  `sts_krywn_id` int(11) NOT NULL,
  `jns_anggota_id` int(11) NOT NULL,
  `sts_kerja_id` int(11) NOT NULL,
  `no_rek_ref` varchar(30) DEFAULT NULL,
  `nama_ref` varchar(50) DEFAULT NULL,
  `aplikasi` text,
  `keterangan` text,
  `is_agent` int(1) DEFAULT '0',
  `deleted` int(1) DEFAULT '0',
  `LASTUPDDTM` datetime DEFAULT NULL,
  `LASTUPDBY` varchar(30) DEFAULT NULL,
  `LASTUPDPROCESS` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx5` (`no_rek`),
  KEY `idx1` (`nama`),
  KEY `idx2` (`telepon`),
  KEY `idx3` (`perusahaan_id`),
  KEY `idx4` (`bank_id`),
  KEY `idx6` (`jns_anggota_id`),
  KEY `idx7` (`sts_kerja_id`),
  KEY `idx8` (`is_agent`),
  KEY `idx9` (`nik`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_nasabah
-- ----------------------------
INSERT INTO ms_nasabah VALUES ('1', '2', '2', 'Lambok', '1', '2', 'Serpong tangsel apa sih', 'Sukabumi jawa barat yrdyinh rfisy', '08135345343', 'GS123', '2', '1', 'Sewing', '5352', '123123123', '2018-04-30', '2018-03-26', '1', '1', '3', '12323423', 'KOk baru 2', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', 'ini adalah contoh perubahan field', '1', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('2', '2', '2', 'Romero', '2', '1', 'Bandung', 'Jakarta', '23423', 'GS43245', '2', '1', 'Ini bagian perwakilan rakyat', '234234', '888888', '2017-12-31', '2017-12-31', '1', '2', '2', 'Jack De la Rocha', '4234', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', 'Ini contoh perubahan', '0', '0', '2018-06-11 21:43:36', 'lambok.sianturi', 'delete');
INSERT INTO ms_nasabah VALUES ('3', '1', '1', 'Arsene wenger', '1', '1', 'Chase Plaza edy', 'Chase Plaza test', '5435632', 'JK5345', '1', '1', 'Ini bagian perwakilan rakyat', '657867854', '', '2018-04-30', '2018-04-30', '1', '1', '3', '345346', '89768566', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', 'ok', '0', '0', '2018-06-11 11:48:18', 'lambok.sianturi', 'delete');
INSERT INTO ms_nasabah VALUES ('4', '1', '1', 'Takana Juo', '1', '1', 'Chase Plaza', 'tiutyu', '5435632', 'TR342', '1', '1', 'Cutting', 'gdgfg', '', '2018-05-28', '2018-05-28', '1', '1', '1', 'dgsdfg', 'gdfsg', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', 'dfghh', '0', '0', '2018-06-07 15:25:06', 'lambok.sianturi', 'delete');
INSERT INTO ms_nasabah VALUES ('5', '1', '1', 'Ben Klaus', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', 'JS324', '2', '1', '', '56756', null, '2018-04-30', '2018-04-30', '1', '1', '3', '876678', 'gjgjgf', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', 'jtuiu', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('6', '1', '3', 'Robert Plant', '2', '1', 'Chase Plaza', 'Chase Plaza', '5435632', 'LA2134', '1', '1', 'fdghh', '6578', '', '2018-05-28', '2018-05-28', '1', '2', '2', '56865', 'jgjgfj', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', 'jghkjhgk', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('7', '1', '1', 'David Gilmour', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', 'JL324', '3', '1', '', '54', '', '2018-05-28', '2018-05-25', '1', '1', '3', '8586', '333yry', '', 'fdhfdhh', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('8', '1', '1', 'Richard Wright', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', 'YH3242', '1', '1', 'yrtyhrthfd', '3535', '', '2018-05-28', '2018-06-13', '2', '1', '1', '534535', 'fghgdh', '', 'fdhdfh', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('9', '1', '1', 'Lana Calista', '1', '1', 'Cidahu', 'Cicurug', '5353453453', 'SSD222', '4', '1', 'dgfdsfg', '534534', '', '2018-06-20', '2018-06-11', '1', '2', '2', '345345', 'Mama', 'Calista Lana boru bapa', 'dgdgdg', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('10', '1', '1', 'Minke', '1', '1', 'sdfsaf', 'fsadf', '081315791700', 'TR.453', '1', '1', 'Sales', '12345678', '888888', '2018-06-20', '2018-06-11', '1', '1', '1', '87654431', 'Mama', 'sdfa', 'fsdf', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('15', '1', '1', 'George Harrison', '1', '1', 'sdfsadf', 'sdaf', '081315791700', 'sdf', '4', '1', 'Sales', '12345678e', '888888', '2018-06-20', '2018-06-11', '1', '1', '1', 's', 'Mama', 'sadf', 'sdf', '1', '0', null, null, null);

-- ----------------------------
-- Table structure for `ms_nasabah_hist`
-- ----------------------------
DROP TABLE IF EXISTS `ms_nasabah_hist`;
CREATE TABLE `ms_nasabah_hist` (
  `id` int(11) NOT NULL,
  `cabang` int(11) NOT NULL,
  `unit` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jns_kelamin_id` int(11) NOT NULL,
  `sts_sipil_id` int(1) NOT NULL,
  `domisili` text,
  `alamat` text,
  `telepon` varchar(20) NOT NULL,
  `nik` varchar(30) DEFAULT NULL,
  `perusahaan_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `bagian` varchar(30) NOT NULL,
  `no_rek` varchar(30) NOT NULL,
  `pin_atm` varchar(10) DEFAULT NULL,
  `tgl_masuk` date NOT NULL,
  `tgl_payroll` date DEFAULT NULL,
  `sts_krywn_id` int(11) NOT NULL,
  `jns_anggota_id` int(11) NOT NULL,
  `sts_kerja_id` int(11) NOT NULL,
  `no_rek_ref` varchar(30) DEFAULT NULL,
  `nama_ref` varchar(50) DEFAULT NULL,
  `keterangan` text,
  `aplikasi` text,
  `is_agent` int(1) DEFAULT '0',
  `deleted` int(1) DEFAULT '0',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30) DEFAULT NULL,
  KEY `idx1` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_nasabah_hist
-- ----------------------------
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '1', 'Lambok', '1', '1', 'Serpong', 'Sukabumi', '08135345343', '1', '2', '1', 'Sew', '5352', null, '2018-04-30', null, '1', '1', '1', '12323423', 'gdefgdfg', 'gesgsdfg', null, '1', '0', '2018-06-05 17:40:38', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', '1', '1', 'gdfg', '1', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'gdfgdfg', '234234', null, '2017-12-31', null, '1', '1', '1', 'gdfgdf', '4234', 'Ini contoh perubahan', null, '0', '0', '2018-06-05 17:54:20', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', '1', '1', 'gdfg', '1', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', null, '2017-12-31', null, '1', '1', '1', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', null, '0', '0', '2018-06-05 17:56:23', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '1', 'Lambok', '1', '1', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', null, '2018-04-30', null, '1', '1', '1', '12323423', 'gdefgdfg', 'ini adalah contoh perubahan field', null, '1', '0', '2018-06-06 10:51:01', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '1', 'Lambok', '1', '1', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', null, '2018-04-30', null, '1', '1', '1', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', null, '1', '0', '2018-06-06 10:51:19', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', '2', '2', 'gdfg', '2', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', null, '2017-12-31', null, '1', '1', '1', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', null, '0', '0', '2018-06-06 12:43:00', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('3', '1', '1', 'Arsene wenger test edit', '1', '1', 'Chase Plaza edy', 'Chase Plaza test', '5435632', '1', '1', '1', 'Ini bagian perwakilan rakyat', '657867854', null, '2018-04-30', null, '1', '1', '1', '345346', '89768566', 'ok', null, '0', '0', '2018-06-06 15:10:15', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '1', 'tyujtyu', '1', '1', 'Chase Plaza', 'tiutyu', '5435632', '1', '1', '1', 'Cutting', 'gdgfg', null, '2018-05-28', null, '1', '1', '1', 'dgsdfg', 'gdfsg', 'dfghh', null, '0', '0', '2018-06-06 16:41:08', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '1', 'tretet', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '2', '1', '', '56756', null, '2018-04-30', null, '1', '1', '1', '876678', 'gjgjgf', 'jtuiu', null, '0', '0', '2018-06-06 16:46:25', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '1', 'Lambok Sianturin sdhgdfhfh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'fdghh', '6578', null, '2018-05-28', null, '1', '1', '1', '56865', 'jgjgfj', 'jghkjhgk', null, '0', '0', '2018-06-06 16:52:50', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('7', '1', '1', 'Lambok Sianturi dghdhfdh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', '', '54', null, '2018-05-28', null, '1', '1', '1', '8586', '333yry', 'fdhfdhh', null, '0', '0', '2018-06-06 17:10:07', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '1', 'Lambok', '1', '1', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', null, '2018-04-30', '2018-03-26', '1', '1', '1', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', null, '1', '0', '2018-06-06 17:35:43', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('7', '1', '1', 'Lambok Sianturi dghdhfdh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', '', '54', null, '2018-05-28', '2018-05-25', '1', '1', '1', '8586', '333yry', 'fdhfdhh', null, '0', '0', '2018-06-06 17:48:53', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('8', '1', '1', 'Lambok Sianturi', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'yrtyhrthfd', '3535', null, '2018-05-28', '2018-06-13', '1', '1', '1', '534535', 'fghgdh', 'fdhdfh', null, '0', '0', '2018-06-06 17:49:27', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('7', '1', '1', 'Lambok Sianturi dghdhfdh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '3', '1', '', '54', null, '2018-05-28', '2018-05-25', '1', '1', '1', '8586', '333yry', 'fdhfdhh', null, '0', '0', '2018-06-07 14:58:19', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('4', '1', '1', 'tyujtyu', '1', '1', 'Chase Plaza', 'tiutyu', '5435632', '1', '1', '1', 'Cutting', 'gdgfg', null, '2018-05-28', '2018-05-28', '1', '1', '1', 'dgsdfg', 'gdfsg', 'dfghh', null, '0', '1', '2018-06-07 15:25:06', null);
INSERT INTO ms_nasabah_hist VALUES ('2', '2', '1', 'gdfg', '2', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', null, '2017-12-31', '2017-12-31', '1', '1', '1', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', null, '0', '1', '2018-06-07 15:25:38', null);
INSERT INTO ms_nasabah_hist VALUES ('6', '2', '1', 'Lambok Sianturin sdhgdfhfh', '2', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'fdghh', '6578', null, '2018-05-28', '2018-05-28', '1', '1', '1', '56865', 'jgjgfj', 'jghkjhgk', null, '0', '0', '2018-06-08 14:42:20', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '2', 'Lambok', '1', '2', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', null, '2018-04-30', '2018-03-26', '1', '1', '1', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', null, '1', '0', '2018-06-08 14:44:17', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('8', '1', '1', 'Lambok Sianturi', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'yrtyhrthfd', '3535', null, '2018-05-28', '2018-06-13', '2', '1', '1', '534535', 'fghgdh', 'fdhdfh', null, '0', '0', '2018-06-08 14:51:16', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('3', '1', '1', 'Arsene wenger test edit', '1', '1', 'Chase Plaza edy', 'Chase Plaza test', '5435632', '1', '1', '1', 'Ini bagian perwakilan rakyat', '657867854', null, '2018-04-30', '2018-04-30', '1', '1', '1', '345346', '89768566', 'ok', null, '0', '1', '2018-06-11 11:48:18', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('5', '1', '1', 'Ben Klaus', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '2', '1', '', '56756', null, '2018-04-30', '2018-04-30', '1', '1', '1', '876678', 'gjgjgf', 'jtuiu', null, '0', '0', '2018-06-11 11:48:29', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('9', '1', '1', 'Lana', '1', '1', 'dfgdfg', 'dsgfdfg', '5353453453', '1', '1', '1', 'dgfdsfg', '534534', null, '2018-06-20', '2018-06-11', '1', '1', '1', '345345', 'Mama', 'dgdgdg', null, '0', '0', '2018-06-11 11:49:14', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('9', '1', '1', 'Lana', '1', '1', 'Cidahu', 'Cicurug', '5353453453', '1', '1', '1', 'dgfdsfg', '534534', null, '2018-06-20', '2018-06-11', '1', '1', '1', '345345', 'Mama', 'dgdgdg', null, '0', '0', '2018-06-11 11:49:44', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '2', 'Lambok', '1', '2', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', null, '2018-04-30', '2018-03-26', '1', '1', '1', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', 'testing aplikasi', '1', '0', '2018-06-11 14:23:10', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('5', '1', '1', 'Ben Klaus', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '2', '1', '', '56756', null, '2018-04-30', '2018-04-30', '1', '1', '1', '876678', 'gjgjgf', 'jtuiu', 'ini tetsting aupdate aplikasi', '0', '0', '2018-06-11 14:24:47', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', '1', '1', 'gdfg', '2', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', null, '2017-12-31', '2017-12-31', '1', '1', '1', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', null, '0', '1', '2018-06-11 21:43:36', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '1', '1', 'Lambok', '1', '2', 'Serpong tangsel apa sih', 'Sukabumi jawa barat yrdyinh rfisy', '08135345343', '1', '2', '1', 'Sewing', '5352', null, '2018-04-30', '2018-03-26', '1', '1', '3', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', 'testing aplikasi', '1', '0', '2018-06-12 19:20:00', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('9', '1', '1', 'Lana Calista', '1', '1', 'Cidahu', 'Cicurug', '5353453453', '1', '1', '1', 'dgfdsfg', '534534', null, '2018-06-20', '2018-06-11', '1', '2', '2', '345345', 'Mama', 'dgdgdg', 'Calista Lana boru bapa', '0', '0', '2018-06-12 19:20:32', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '2', '2', 'Lambok', '1', '2', 'Serpong tangsel apa sih', 'Sukabumi jawa barat yrdyinh rfisy', '08135345343', '1', '2', '1', 'Sewing', '5352', null, '2018-04-30', '2018-03-26', '1', '1', '3', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', 'testing aplikasi', '1', '0', '2018-06-12 19:20:56', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', '2', '2', 'Lambok', '1', '2', 'Serpong tangsel apa sih', 'Sukabumi jawa barat yrdyinh rfisy', '08135345343', '1', '2', '1', 'Sewing', '5352', '123123123', '2018-04-30', '2018-03-26', '1', '1', '3', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', 'testing aplikasi', '1', '0', '2018-06-12 19:30:05', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', '1', '1', 'gdfg', '2', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', '888888', '2017-12-31', '2017-12-31', '1', '2', '2', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', '', '0', '0', '2018-06-12 19:30:31', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('6', '1', '3', 'Lambok Sianturin sdhgdfhfh', '2', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'fdghh', '6578', '', '2018-05-28', '2018-05-28', '1', '2', '2', '56865', 'jgjgfj', 'jghkjhgk', '', '0', '0', '2018-06-12 19:33:09', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('0', '1', '1', 'Anenlies', '1', '1', 'dfsgdsfg', 'sadfgsdf', '081315791700', 'TR.444', '1', '1', 'Sales', '12345678', '888888', '2018-06-20', '2018-06-11', '1', '1', '1', '87654431', 'Mama', 'dfgds', 'dfgdsg', '0', '0', '2018-06-13 22:12:03', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('0', '1', '1', 'Lana', '1', '1', 'gdfsg', 'fsdfaf', '081315791700', 'dsfgsd', '1', '1', 'Sales', '12345678', 'gdfsg', '2018-06-20', '2018-06-11', '1', '1', '1', 'dfgdsg', 'dfg', 'dfg', 'dfsgdfg', '0', '0', '2018-06-13 22:19:51', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('15', '1', '1', 'Lana', '1', '1', 'sdfsadf', 'sdaf', '081315791700', 'sdf', '1', '1', 'Sales', '12345678e', '888888', '2018-06-20', '2018-06-11', '1', '1', '1', 's', 'Mama', 'sdf', 'sadf', '0', '0', '2018-06-13 22:32:02', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('15', '1', '1', 'Lana tesytsett', '1', '1', 'sdfsadf', 'sdaf', '081315791700', 'sdf', '1', '1', 'Sales', '12345678e', '888888', '2018-06-20', '2018-06-11', '1', '1', '1', 's', 'Mama', 'sdf', 'sadf', '0', '0', '2018-06-13 22:32:21', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('15', '1', '1', 'Lana tesytsett', '1', '1', 'sdfsadf', 'sdaf', '081315791700', 'sdf', '1', '1', 'Sales', '12345678e', '888888', '2018-06-20', '2018-06-11', '1', '1', '1', 's', 'Mama', 'sdf', 'sadf', '1', '0', '2018-06-13 22:32:30', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', '2', '2', 'Romero', '2', '1', 'Bandung', 'Jakarta', '23423', 'GS43245', '2', '1', 'Ini bagian perwakilan rakyat', '234234', '888888', '2017-12-31', '2017-12-31', '1', '2', '2', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', '0', '0', '2018-06-15 18:08:03', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('3', '1', '1', 'Arsene wenger', '1', '1', 'Chase Plaza edy', 'Chase Plaza test', '5435632', 'JK5345', '1', '1', 'Ini bagian perwakilan rakyat', '657867854', '', '2018-04-30', '2018-04-30', '1', '1', '3', '345346', '89768566', 'ok', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', '0', '0', '2018-06-17 12:57:33', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('4', '1', '1', 'Takana Juo', '1', '1', 'Chase Plaza', 'tiutyu', '5435632', 'TR342', '1', '1', 'Cutting', 'gdgfg', '', '2018-05-28', '2018-05-28', '1', '1', '1', 'dgsdfg', 'gdfsg', 'dfghh', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', '0', '0', '2018-06-17 12:57:45', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('6', '1', '3', 'Robert Plant', '2', '1', 'Chase Plaza', 'Chase Plaza', '5435632', 'LA2134', '1', '1', 'fdghh', '6578', '', '2018-05-28', '2018-05-28', '1', '2', '2', '56865', 'jgjgfj', 'jghkjhgk', '1. Ijazah\r\n2. Kartu keluarga\r\n3. Sura Nikah\r\n4. KTP', '0', '0', '2018-06-17 12:57:55', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('7', '1', '1', 'David Gilmour', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', 'JL324', '3', '1', '', '54', '', '2018-05-28', '2018-05-25', '1', '1', '3', '8586', '333yry', 'fdhfdhh', '', '0', '0', '2018-06-17 12:58:05', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('8', '1', '1', 'Richard Wright', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', 'YH3242', '1', '1', 'yrtyhrthfd', '3535', '', '2018-05-28', '2018-06-13', '2', '1', '1', '534535', 'fghgdh', 'fdhdfh', '', '0', '0', '2018-06-17 12:58:20', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('15', '1', '1', 'George Harrison', '1', '1', 'sdfsadf', 'sdaf', '081315791700', 'sdf', '1', '1', 'Sales', '12345678e', '888888', '2018-06-20', '2018-06-11', '1', '1', '1', 's', 'Mama', 'sdf', 'sadf', '1', '0', '2018-06-17 12:58:43', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('9', '1', '1', 'Lana Calista', '1', '1', 'Cidahu', 'Cicurug', '5353453453', 'SSD222', '4', '1', 'dgfdsfg', '534534', '', '2018-06-20', '2018-06-11', '1', '2', '2', '345345', 'Mama', 'dgdgdg', 'Calista Lana boru bapa', '0', '0', '2018-06-17 12:58:53', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('15', '1', '1', 'George Harrison', '1', '1', 'sdfsadf', 'sdaf', '081315791700', 'sdf', '4', '1', 'Sales', '12345678e', '888888', '2018-06-20', '2018-06-11', '1', '1', '1', 's', 'Mama', 'sdf', 'sadf', '1', '0', '2018-06-17 12:59:04', 'lambok.sianturi');

-- ----------------------------
-- Table structure for `ms_pegawai`
-- ----------------------------
DROP TABLE IF EXISTS `ms_pegawai`;
CREATE TABLE `ms_pegawai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cabang` int(11) DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `nama` varchar(60) NOT NULL,
  `alamat` text NOT NULL,
  `domisili` text NOT NULL,
  `telepon` varchar(14) NOT NULL,
  `status_pegawai` int(2) NOT NULL DEFAULT '0',
  `status_sipil` int(2) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `keterangan` text NOT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_pegawai
-- ----------------------------
INSERT INTO ms_pegawai VALUES ('1', '1', '3', 'Asep Sunarya', 'Cicurug', 'Cicurug', '0812342', '1', '4', '2018-03-01', 'ket', '0');
INSERT INTO ms_pegawai VALUES ('2', '1', '1', 'test', 'tset', 'tset', '342', '2', '2', '2017-12-31', 'tewtwt', '0');
INSERT INTO ms_pegawai VALUES ('3', '1', '1', 'tdh', 'fghgfh', 'fdhfd', '3453', '1', '3', '2017-12-31', 'tetdgdsg', '0');
INSERT INTO ms_pegawai VALUES ('4', '1', '1', 'tert', 'tert', 'ert', 'ertr', '1', '3', '2017-12-31', 'ert', '1');
INSERT INTO ms_pegawai VALUES ('5', '1', '1', 'gdfsg', 'dfgds', 'gdgfsdg', 'dfgs', '1', '3', '2017-12-31', 'dfg', '0');
INSERT INTO ms_pegawai VALUES ('6', '1', '1', 'Lambok', 'Tset', 'Test', '023421', '1', '1', '2017-12-31', 'Test', '0');
INSERT INTO ms_pegawai VALUES ('7', '1', '1', 'Javk', 'Laguboti', 'Jakarta', '021-032424', '2', '2', '2017-12-31', 'Testt', '0');
INSERT INTO ms_pegawai VALUES ('8', '1', '1', 'gdsfg', 'dsfgsd', 'dsgsdg', '23423', '1', '1', '2018-04-19', 'gdsfsg', '0');
INSERT INTO ms_pegawai VALUES ('9', '1', '1', 'dfgdsg', 'aaaaaaaaaaaaa', 'dsgdsfg', '345345', '1', '1', '2018-05-29', 'dfsgdsfg', '0');
INSERT INTO ms_pegawai VALUES ('10', '1', '1', 'dfgsdg', 'dfgsdg', 'dsfgdfsg', '081315791700', '1', '1', '2018-04-19', 'gdsfgsdg', '0');

-- ----------------------------
-- Table structure for `ms_pegawai_hist`
-- ----------------------------
DROP TABLE IF EXISTS `ms_pegawai_hist`;
CREATE TABLE `ms_pegawai_hist` (
  `id` int(11) NOT NULL,
  `cabang` int(11) NOT NULL,
  `unit` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `alamat` text NOT NULL,
  `domisili` text NOT NULL,
  `telepon` varchar(14) NOT NULL,
  `status_pegawai` int(2) NOT NULL DEFAULT '0',
  `status_sipil` int(2) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `keterangan` text NOT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  `created_by` varchar(30) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_pegawai_hist
-- ----------------------------
INSERT INTO ms_pegawai_hist VALUES ('1', '1', '1', 'Asep Sunarya', 'Cicurug', 'Cicurug', '0812342', '1', '3', '2018-03-01', 'ket', '0', 'lambok.sianturi', '2018-06-12 21:31:21');
INSERT INTO ms_pegawai_hist VALUES ('10', '1', '1', 'dfgsdg', 'dfgsdg', 'dsfgdfsg', '081315791700', '1', '1', '2018-04-19', 'gdsfgsdg', '0', 'lambok.sianturi', '2018-06-12 21:31:39');
INSERT INTO ms_pegawai_hist VALUES ('1', '2', '2', 'Asep Sunarya', 'Cicurug', 'Cicurug', '0812342', '1', '3', '2018-03-01', 'ket', '0', 'lambok.sianturi', '2018-06-12 21:39:19');
INSERT INTO ms_pegawai_hist VALUES ('1', '1', '3', 'Asep Sunarya', 'Cicurug', 'Cicurug', '0812342', '1', '4', '2018-03-01', 'ket', '0', 'lambok.sianturi', '2018-06-12 21:39:33');

-- ----------------------------
-- Table structure for `ms_perusahaan`
-- ----------------------------
DROP TABLE IF EXISTS `ms_perusahaan`;
CREATE TABLE `ms_perusahaan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) NOT NULL,
  `alamat` text,
  `industri` int(11) NOT NULL,
  `cabang` int(11) NOT NULL,
  `unit` int(11) DEFAULT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx1` (`nama`),
  KEY `idx2` (`cabang`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_perusahaan
-- ----------------------------
INSERT INTO ms_perusahaan VALUES ('1', 'PT Aqua Golden Mississippi ok', 'Jl. Raya Sukabumi No 12. Cicurug test', '4', '1', '6', '0');
INSERT INTO ms_perusahaan VALUES ('2', 'PT. Pocari Sewot', 'Jl Cidahu no 1234', '1', '2', '2', '0');
INSERT INTO ms_perusahaan VALUES ('3', 'PT. Mayora Indah', 'Jl. Parungkuda agak kesini', '4', '1', '1', '0');
INSERT INTO ms_perusahaan VALUES ('4', 'PT Dongfeng Mobilindo', 'Jl. Testing 10', '4', '1', '2', '0');

-- ----------------------------
-- Table structure for `ms_perusahaan_hist`
-- ----------------------------
DROP TABLE IF EXISTS `ms_perusahaan_hist`;
CREATE TABLE `ms_perusahaan_hist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) NOT NULL,
  `alamat` text,
  `industri` int(11) NOT NULL,
  `cabang` int(11) NOT NULL,
  `unit` int(11) NOT NULL,
  `deleted` int(1) DEFAULT '0',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30) DEFAULT NULL,
  KEY `idx1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_perusahaan_hist
-- ----------------------------
INSERT INTO ms_perusahaan_hist VALUES ('2', 'PT. Pocari Sewot', 'Jl Cidahu permai no 1234', '1', '1', '1', '0', null, 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('2', 'PT. Pocari Sewot', 'Jl Cidahu no 1234', '1', '1', '1', '0', '2018-06-08 13:29:57', 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('4', 'PT Dongfeng Mobilindo', 'Jl. Testing 10', '4', '1', '1', '0', '2018-06-08 13:31:42', 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('2', 'PT. Pocari Sewot', 'Jl Cidahu no 1234', '1', '2', '1', '0', '2018-06-11 17:39:49', 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('1', 'PT Aqua Golden Mississippi', 'Jl. Raya Sukabumi No 12. Cicurug', '2', '2', '2', '0', '2018-06-11 18:15:40', 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('1', 'PT Aqua Golden Mississippi', 'Jl. Raya Sukabumi No 12. Cicurug', '2', '1', '6', '0', '2018-06-11 18:18:41', 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('2', 'PT. Pocari Sewot', 'Jl Cidahu no 1234', '1', '2', '2', '0', '2018-06-11 18:19:40', 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('1', 'PT Aqua Golden Mississippi ok', 'Jl. Raya Sukabumi No 12. Cicurug test', '4', '1', '6', '0', '2018-06-11 18:20:09', 'lambok.sianturi');

-- ----------------------------
-- Table structure for `ms_role`
-- ----------------------------
DROP TABLE IF EXISTS `ms_role`;
CREATE TABLE `ms_role` (
  `ROLECODE` varchar(10) NOT NULL,
  `DESCRIPTION` varchar(20) DEFAULT NULL,
  `CREATEDBY` varchar(30) DEFAULT NULL,
  `STATUSID` int(2) DEFAULT NULL,
  PRIMARY KEY (`ROLECODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_role
-- ----------------------------
INSERT INTO ms_role VALUES ('ADMIN', 'Administrator', 'lambok.sianturi', '1');
INSERT INTO ms_role VALUES ('KASIR', 'Kasir', 'lambok.sianturi', '1');
INSERT INTO ms_role VALUES ('MANAGER', 'Manager', 'lambok.sianturi', '1');
INSERT INTO ms_role VALUES ('SPM', 'SPM', 'lambok.sianturi', '0');

-- ----------------------------
-- Table structure for `ms_rolemenu`
-- ----------------------------
DROP TABLE IF EXISTS `ms_rolemenu`;
CREATE TABLE `ms_rolemenu` (
  `ROLECODE` varchar(10) NOT NULL,
  `MENUCODE` varchar(16) NOT NULL,
  `CREATEDBY` varchar(30) DEFAULT NULL,
  `DELETED` int(1) DEFAULT NULL,
  PRIMARY KEY (`ROLECODE`,`MENUCODE`),
  KEY `menu_code_fk` (`MENUCODE`),
  CONSTRAINT `ms_rolemenu_ibfk_1` FOREIGN KEY (`MENUCODE`) REFERENCES `ms_menu` (`MENUCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_rolemenu
-- ----------------------------
INSERT INTO ms_rolemenu VALUES ('KASIR', 'MST', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('KASIR', 'MST_NSB', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('KASIR', 'MST_PRSHN', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('KASIR', 'MST_PRSHN_add', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('KASIR', 'MST_PRSHN_del', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('KASIR', 'MST_PRSHN_upd', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'KOP', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_NSB', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_NSB_add', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_NSB_del', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_NSB_search', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_NSB_upd', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PEG', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PEG_add', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PEG_del', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PEG_search', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PEG_upd', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PRSHN', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PRSHN_add', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PRSHN_del', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PRSHN_search', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'MST_PRSHN_upd', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_KANTOR', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_KANTOR_upd', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_MENU', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_MENU_add', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_MENU_del', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_MENU_search', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_MENU_upd', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_ROLE', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_ROLE_add', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_ROLE_assign', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_ROLE_del', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_ROLE_del_as', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_ROLE_page', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_ROLE_search', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_ROLE_upd', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_USR', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_USR_search', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'SET_USR_upd', 'lambok', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_PJM', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_PJM_add', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_PJM_del', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_PJM_search', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_PJM_upd', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_PJM_view', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_REAL', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_REAL_add', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_REAL_del', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_REAL_search', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_REAL_upd', 'lambok.sianturi', '0');
INSERT INTO ms_rolemenu VALUES ('MANAGER', 'TRX_REAL_view', 'lambok.sianturi', '0');

-- ----------------------------
-- Table structure for `ms_sandi_trx`
-- ----------------------------
DROP TABLE IF EXISTS `ms_sandi_trx`;
CREATE TABLE `ms_sandi_trx` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sandi` varchar(5) NOT NULL,
  `nama` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_sandi_trx
-- ----------------------------
INSERT INTO ms_sandi_trx VALUES ('1', 'ADM', 'Administrasi');
INSERT INTO ms_sandi_trx VALUES ('2', 'PJK', 'Pajak');
INSERT INTO ms_sandi_trx VALUES ('3', 'SAW', 'Saldo Awal');
INSERT INTO ms_sandi_trx VALUES ('4', 'TRF', 'Transfer');
INSERT INTO ms_sandi_trx VALUES ('5', 'SAK', 'Saldo Akhir');

-- ----------------------------
-- Table structure for `ms_sts_kerja`
-- ----------------------------
DROP TABLE IF EXISTS `ms_sts_kerja`;
CREATE TABLE `ms_sts_kerja` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_sts_kerja
-- ----------------------------
INSERT INTO ms_sts_kerja VALUES ('1', 'Aktif');
INSERT INTO ms_sts_kerja VALUES ('2', 'Jeda');
INSERT INTO ms_sts_kerja VALUES ('3', 'Off');
INSERT INTO ms_sts_kerja VALUES ('4', 'Cuti Hamil');
INSERT INTO ms_sts_kerja VALUES ('5', 'Kabur');

-- ----------------------------
-- Table structure for `ms_sts_krywn`
-- ----------------------------
DROP TABLE IF EXISTS `ms_sts_krywn`;
CREATE TABLE `ms_sts_krywn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_sts_krywn
-- ----------------------------
INSERT INTO ms_sts_krywn VALUES ('1', 'Tetap');
INSERT INTO ms_sts_krywn VALUES ('2', 'Kontrak');

-- ----------------------------
-- Table structure for `ms_sts_pegawai`
-- ----------------------------
DROP TABLE IF EXISTS `ms_sts_pegawai`;
CREATE TABLE `ms_sts_pegawai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_sts_pegawai
-- ----------------------------
INSERT INTO ms_sts_pegawai VALUES ('1', 'Magang');
INSERT INTO ms_sts_pegawai VALUES ('2', 'Kontrak');

-- ----------------------------
-- Table structure for `ms_sts_pinjaman`
-- ----------------------------
DROP TABLE IF EXISTS `ms_sts_pinjaman`;
CREATE TABLE `ms_sts_pinjaman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_sts_pinjaman
-- ----------------------------
INSERT INTO ms_sts_pinjaman VALUES ('1', 'Lancar');
INSERT INTO ms_sts_pinjaman VALUES ('2', 'Macet');

-- ----------------------------
-- Table structure for `ms_sts_sipil`
-- ----------------------------
DROP TABLE IF EXISTS `ms_sts_sipil`;
CREATE TABLE `ms_sts_sipil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_sts_sipil
-- ----------------------------
INSERT INTO ms_sts_sipil VALUES ('1', 'Belum Kawin');
INSERT INTO ms_sts_sipil VALUES ('2', 'Kawin');
INSERT INTO ms_sts_sipil VALUES ('3', 'Duda');
INSERT INTO ms_sts_sipil VALUES ('4', 'Janda');

-- ----------------------------
-- Table structure for `ms_suku_bunga`
-- ----------------------------
DROP TABLE IF EXISTS `ms_suku_bunga`;
CREATE TABLE `ms_suku_bunga` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suku` float(5,2) NOT NULL,
  `nama` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_suku_bunga
-- ----------------------------
INSERT INTO ms_suku_bunga VALUES ('1', '1.00', '1%');
INSERT INTO ms_suku_bunga VALUES ('2', '1.50', '1,5%');
INSERT INTO ms_suku_bunga VALUES ('3', '2.00', '2%');
INSERT INTO ms_suku_bunga VALUES ('4', '2.50', '2,5%');
INSERT INTO ms_suku_bunga VALUES ('5', '3.00', '3%');
INSERT INTO ms_suku_bunga VALUES ('6', '3.50', '3,5%');
INSERT INTO ms_suku_bunga VALUES ('7', '4.00', '4%');
INSERT INTO ms_suku_bunga VALUES ('8', '4.50', '4,5%');
INSERT INTO ms_suku_bunga VALUES ('9', '5.00', '5%');

-- ----------------------------
-- Table structure for `ms_tipe_pinjaman`
-- ----------------------------
DROP TABLE IF EXISTS `ms_tipe_pinjaman`;
CREATE TABLE `ms_tipe_pinjaman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_tipe_pinjaman
-- ----------------------------
INSERT INTO ms_tipe_pinjaman VALUES ('1', 'Baru');
INSERT INTO ms_tipe_pinjaman VALUES ('2', 'RO (Repeat Order)');
INSERT INTO ms_tipe_pinjaman VALUES ('3', 'Top Up');
INSERT INTO ms_tipe_pinjaman VALUES ('4', 'Take Over');

-- ----------------------------
-- Table structure for `ms_user`
-- ----------------------------
DROP TABLE IF EXISTS `ms_user`;
CREATE TABLE `ms_user` (
  `USERCODE` varchar(30) NOT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `ROLECODE` varchar(10) DEFAULT NULL,
  `FIRSTNAME` varchar(30) DEFAULT NULL,
  `LASTNAME` varchar(30) DEFAULT NULL,
  `MOBILE` varchar(15) DEFAULT NULL,
  `EMAILADDRESS` varchar(100) DEFAULT NULL,
  `ACTIVESTATUS` int(1) DEFAULT NULL,
  `SESSIONTIMEOUT` int(3) DEFAULT NULL,
  `CREATEDBY` varchar(30) DEFAULT NULL,
  `DELETED` int(1) DEFAULT NULL,
  `LASTLOGON` datetime DEFAULT NULL,
  `LASTUPDDTM` datetime DEFAULT NULL,
  `LASTUPDBY` varchar(30) DEFAULT NULL,
  `LASTUPDPROCESS` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`USERCODE`),
  KEY `pk_rolecode` (`ROLECODE`),
  KEY `idx` (`USERCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_user
-- ----------------------------
INSERT INTO ms_user VALUES ('lambok.sianturi', '900150983cd24fb0d6963f7d28e17f72', 'MANAGER', 'Lambok', 'Sianturi', '0812345678924', null, '1', '30', 'lambok.sianturi', '0', '2018-07-09 21:06:41', null, null, null);

-- ----------------------------
-- Table structure for `num`
-- ----------------------------
DROP TABLE IF EXISTS `num`;
CREATE TABLE `num` (
  `tipe_kredit` varchar(10) NOT NULL,
  `last_num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of num
-- ----------------------------
INSERT INTO num VALUES ('BARU', '23');
INSERT INTO num VALUES ('RO', '1');

-- ----------------------------
-- Table structure for `sy_cabang`
-- ----------------------------
DROP TABLE IF EXISTS `sy_cabang`;
CREATE TABLE `sy_cabang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sy_cabang
-- ----------------------------
INSERT INTO sy_cabang VALUES ('1', 'Cicurug');
INSERT INTO sy_cabang VALUES ('2', 'Cibadak');

-- ----------------------------
-- Table structure for `sy_pusat`
-- ----------------------------
DROP TABLE IF EXISTS `sy_pusat`;
CREATE TABLE `sy_pusat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) NOT NULL,
  `alamat` text NOT NULL,
  `telepon` varchar(60) NOT NULL,
  `fax` varchar(60) NOT NULL,
  `badan_hukum` varchar(60) NOT NULL DEFAULT '0',
  `npwp` varchar(60) NOT NULL,
  `siup` varchar(60) NOT NULL,
  `tdp` varchar(60) NOT NULL,
  `logo_koperasi` varchar(128) NOT NULL,
  `logo_perusahaan` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sy_pusat
-- ----------------------------
INSERT INTO sy_pusat VALUES ('1', 'Koperasi Warga Sejahtera', 'Jl. Raya Cicurug Sukabumi No. 168\r\nDepan PLN Cicurug\r\nSukabumi\r\n', '021-3242432', '021-253234', '12344325', '01.0042.04322', '042342.000234', '053453.000234', 'Logo koperasi', 'Logo kantor');

-- ----------------------------
-- Table structure for `sy_pusat_hist`
-- ----------------------------
DROP TABLE IF EXISTS `sy_pusat_hist`;
CREATE TABLE `sy_pusat_hist` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `alamat` text NOT NULL,
  `telepon` varchar(60) NOT NULL,
  `fax` varchar(60) NOT NULL,
  `badan_hukum` varchar(60) NOT NULL DEFAULT '0',
  `npwp` varchar(60) NOT NULL,
  `siup` varchar(60) NOT NULL,
  `tdp` varchar(60) NOT NULL,
  `logo_koperasi` varchar(128) NOT NULL,
  `logo_perusahaan` varchar(128) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sy_pusat_hist
-- ----------------------------

-- ----------------------------
-- Table structure for `sy_unit`
-- ----------------------------
DROP TABLE IF EXISTS `sy_unit`;
CREATE TABLE `sy_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cabang_id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `alamat` text NOT NULL,
  `telepon` varchar(60) NOT NULL,
  `fax` varchar(60) NOT NULL,
  `kepala_unit` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sy_unit
-- ----------------------------
INSERT INTO sy_unit VALUES ('1', '1', 'Unit Cidahu ', 'Jl. Raya Cicirug', '0261-234244', '0261-234244', 'Rony');
INSERT INTO sy_unit VALUES ('2', '2', 'Unit Cibadak', 'Jl. Sukaraja', '0261-234244', '0261-234244', 'Roy');
INSERT INTO sy_unit VALUES ('3', '1', 'Unit Parungkuda', 'Jl. Raya Parungkuda', '0261-43543', '0261-43543', 'jack');

-- ----------------------------
-- Table structure for `tx_kr_aju`
-- ----------------------------
DROP TABLE IF EXISTS `tx_kr_aju`;
CREATE TABLE `tx_kr_aju` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no_kredit` varchar(14) NOT NULL,
  `tgl_aju` date NOT NULL,
  `nasabah_id` int(11) NOT NULL,
  `tipe_kredit` int(11) NOT NULL,
  `no_aju_ref` int(11) DEFAULT NULL,
  `jenis_pinjam` int(11) NOT NULL,
  `jumlah_aju` double(14,2) NOT NULL,
  `tenor` int(5) NOT NULL,
  `interest_rate` decimal(4,2) NOT NULL,
  `angsuran_aju` double(14,2) DEFAULT NULL,
  `sponsor` int(11) DEFAULT NULL,
  `marketing` int(11) DEFAULT NULL,
  `penjamin` text,
  `agunan` text,
  `jatuh_tempo` date NOT NULL,
  `created_by` varchar(30) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `realisasi` int(1) NOT NULL DEFAULT '0',
  `tgl_real` date DEFAULT NULL,
  `jumlah_real` double(14,2) DEFAULT NULL,
  `biaya_adm` double(14,2) DEFAULT NULL,
  `biaya_lain` double(14,2) DEFAULT NULL,
  `biaya_provisi` double(14,2) DEFAULT NULL,
  `angsuran_real` double(14,2) DEFAULT NULL,
  `diterima` double(14,2) DEFAULT NULL,
  `terbilang` text,
  `keterangan` text,
  `lunas` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx1` (`nasabah_id`),
  KEY `idx2` (`no_kredit`),
  KEY `idx3` (`tgl_aju`),
  KEY `idx4` (`tgl_real`),
  KEY `idx5` (`jenis_pinjam`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tx_kr_aju
-- ----------------------------
INSERT INTO tx_kr_aju VALUES ('1', 'x', '2018-05-09', '1', '1', null, '1', '100000.00', '5', '2.50', '22500.00', '0', '0', 'ok', 'ok', '2018-09-26', 'lambok', '2018-06-13 21:33:52', '1', '2018-06-13', null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('2', 'x', '2018-06-16', '1', '1', null, '1', '10000000.00', '10', '2.00', '1200000.00', '0', '0', 'ini data penjamin', 'ini data agunana', '2019-03-26', 'lambok.sianturi', '2018-06-16 08:50:40', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('3', 'x1', '2018-06-16', '1', '1', null, '1', '10000000.00', '2', '4.50', '5450000.00', '1', '0', 'test', 'test', '2018-07-26', 'lambok.sianturi', '2018-06-16 09:14:32', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('4', '110.', '2018-06-16', '5', '1', null, '1', '3000000.00', '5', '2.50', '675000.00', '0', '0', 'fsfas', 'asdasd', '2018-10-30', 'lambok.sianturi', '2018-06-16 20:10:13', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('5', '110.', '2018-06-16', '9', '1', null, '1', '6000000.00', '8', '2.50', '900000.00', '0', '0', 'gfsdgfdsf', 'sdfsf', '2018-06-11', 'lambok.sianturi', '2018-06-16 20:12:46', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('6', '110.', '2018-06-16', '10', '1', null, '1', '10000000.00', '10', '2.00', '1200000.00', '0', '0', '', '', '2018-06-11', 'lambok.sianturi', '2018-06-16 20:15:49', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('7', '140.', '2018-06-16', '4', '1', null, '1', '10000000.00', '5', '1.50', '2150000.00', '0', '0', '', '', '2018-10-28', 'lambok.sianturi', '2018-06-16 20:20:50', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('8', '110.3', '2018-06-16', '7', '1', null, '1', '3000000.00', '8', '4.50', '510000.00', '0', '0', 'bvvbcvb', 'cvbcxb', '2019-01-25', 'lambok.sianturi', '2018-06-16 20:23:07', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('9', '110.3', '2018-06-16', '3', '1', null, '1', '8000000.00', '12', '1.50', '786666.67', '0', '0', 'teryeryt', 'eryery', '2019-05-30', 'lambok.sianturi', '2018-06-16 20:35:08', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('10', '110.3', '2018-06-16', '1', '1', null, '1', '10000000.00', '2', '1.50', '5150000.00', '0', '0', 'sdfs', 'sdfsf', '2018-07-26', 'lambok.sianturi', '2018-06-16 20:36:04', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('11', '110.6', '2018-06-16', '1', '1', null, '1', '10000000.00', '6', '4.50', '2116666.67', '0', '0', 'fsdf', 'sdfsf', '2018-11-26', 'lambok.sianturi', '2018-06-16 20:41:19', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('12', '110.6', '2018-06-16', '8', '1', null, '1', '3000000.00', '12', '5.00', '400000.00', '0', '0', 'dfssg', 'dsfg', '2018-06-13', 'lambok.sianturi', '2018-06-16 20:41:45', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('13', '110.6', '2018-06-16', '10', '1', null, '1', '10000000.00', '14', '1.50', '864285.71', '1', '1', 'sfdgds', 'dfgdsg', '2018-06-11', 'lambok.sianturi', '2018-06-16 20:49:21', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('14', '110.-1', '2018-06-16', '2', '1', null, '1', '10000000.00', '10', '2.50', '1250000.00', '0', '0', '', '', '2019-04-01', 'lambok.sianturi', '2018-06-16 21:05:13', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('15', '110.0', '2018-06-16', '3', '1', null, '1', '10000000.00', '10', '2.00', '1200000.00', '0', '0', '', '', '2019-03-30', 'lambok.sianturi', '2018-06-16 21:10:25', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('16', '110.10', '2018-06-16', '5', '1', null, '1', '10000000.00', '10', '2.00', '1200000.00', '0', '0', '', '', '2019-03-30', 'lambok.sianturi', '2018-06-16 21:27:52', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('17', '110.10', '2018-06-16', '9', '1', null, '1', '4000000.00', '24', '1.50', '226666.67', '0', '0', 'fsdf', 'sadf', '2018-06-11', 'lambok.sianturi', '2018-06-16 21:28:58', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('20', '110.10', '2018-06-17', '1', '1', null, '1', '10000000.00', '10', '1.50', '1150000.00', '0', '0', 'd', 'd', '2019-03-26', 'lambok.sianturi', '2018-06-17 12:30:42', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('21', '110.12', '2018-06-17', '7', '1', null, '1', '10000000.00', '5', '2.00', '2200000.00', '0', '0', '', '', '2018-10-25', 'lambok.sianturi', '2018-06-17 12:32:04', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('22', '110.13', '2018-06-17', '3', '1', null, '1', '10000000.00', '10', '3.00', '1300000.00', '0', '7', '', '', '2019-03-30', 'lambok.sianturi', '2018-06-17 12:32:22', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('23', '110.14', '2018-06-17', '6', '1', null, '1', '4000000.00', '10', '1.50', '460000.00', '0', '0', 'wef', 'wer', '2019-03-28', 'lambok.sianturi', '2018-06-17 12:33:29', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('24', '110.15', '2018-06-17', '10', '1', null, '1', '3000000.00', '4', '5.00', '900000.00', '0', '0', 'gehg', 'rretyety', '2018-06-11', 'lambok.sianturi', '2018-06-17 12:33:51', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('25', '110.16', '2018-06-17', '4', '1', null, '1', '100000.00', '5', '1.50', '21500.00', '1', '0', '', '', '2018-10-28', 'lambok.sianturi', '2018-06-17 12:37:49', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('26', '110.17', '2018-07-01', '1', '1', null, '1', '600000.00', '10', '2.50', '72000.00', '0', '0', 'sdfsfsdf', 'sdf', '2019-04-26', 'lambok.sianturi', '2018-07-01 10:31:58', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('27', '110.18', '2018-07-01', '2', '1', null, '1', '50000.00', '5', '2.00', '11000.00', '0', '0', '', '', '2018-11-30', 'lambok.sianturi', '2018-07-01 16:37:12', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('28', '110.20', '2018-07-03', '4', '1', null, '1', '10000000.00', '10', '3.00', '1300000.00', '0', '0', 'hfhfdh', 'hfdh', '2019-04-28', 'lambok.sianturi', '2018-07-03 20:57:39', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO tx_kr_aju VALUES ('29', '110.22', '2018-07-03', '2', '1', null, '1', '10000000.00', '7', '2.00', '1628571.00', '0', '0', 'fsdf', 'sdf', '2019-01-31', 'lambok.sianturi', '2018-07-03 21:01:01', '0', null, null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for `tx_kr_aju_sim`
-- ----------------------------
DROP TABLE IF EXISTS `tx_kr_aju_sim`;
CREATE TABLE `tx_kr_aju_sim` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aju_id` int(11) NOT NULL,
  `tgl_cicil` date NOT NULL,
  `no_urut` int(11) NOT NULL,
  `saldo` double(14,2) NOT NULL,
  `pokok` double(14,2) NOT NULL,
  `bunga` double(14,2) NOT NULL,
  `angsuran` double(14,2) NOT NULL,
  `dibayar` double(14,2) DEFAULT NULL,
  `tgl_bayar` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx1` (`aju_id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tx_kr_aju_sim
-- ----------------------------
INSERT INTO tx_kr_aju_sim VALUES ('14', '26', '2018-07-01', '0', '600000.00', '0.00', '0.00', '0.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('15', '26', '2018-07-26', '1', '540000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('16', '26', '2018-08-26', '2', '480000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('17', '26', '2018-09-26', '3', '420000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('18', '26', '2018-10-26', '4', '360000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('19', '26', '2018-11-26', '5', '300000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('20', '26', '2018-12-26', '6', '240000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('21', '26', '2019-01-26', '7', '180000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('22', '26', '2019-02-26', '8', '120000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('23', '26', '2019-03-26', '9', '60000.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('24', '26', '2019-04-26', '10', '0.00', '60000.00', '15000.00', '75000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('25', '27', '2018-07-01', '0', '50000.00', '0.00', '0.00', '0.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('26', '27', '2018-07-30', '1', '40000.00', '10000.00', '1000.00', '11000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('27', '27', '2018-08-30', '2', '30000.00', '10000.00', '1000.00', '11000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('28', '27', '2018-09-30', '3', '20000.00', '10000.00', '1000.00', '11000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('29', '27', '2018-10-30', '4', '10000.00', '10000.00', '1000.00', '11000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('30', '27', '2018-11-30', '5', '0.00', '10000.00', '1000.00', '11000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('31', '28', '2018-07-03', '0', '10000000.00', '0.00', '0.00', '0.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('32', '28', '2018-07-28', '1', '9000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('33', '28', '2018-08-28', '2', '8000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('34', '28', '2018-09-28', '3', '7000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('35', '28', '2018-10-28', '4', '6000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('36', '28', '2018-11-28', '5', '5000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('37', '28', '2018-12-28', '6', '4000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('38', '28', '2019-01-28', '7', '3000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('39', '28', '2019-02-28', '8', '2000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('40', '28', '2019-03-28', '9', '1000000.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('41', '28', '2019-04-28', '10', '0.00', '1000000.00', '300000.00', '1300000.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('149', '29', '2018-07-03', '0', '10000000.00', '0.00', '0.00', '0.00', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('150', '29', '2018-08-01', '1', '8571428.57', '1428571.43', '200000.00', '1628571.43', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('151', '29', '2018-09-01', '2', '7142857.14', '1428571.43', '200000.00', '1628571.43', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('152', '29', '2018-10-01', '3', '5714285.71', '1428571.43', '200000.00', '1628571.43', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('153', '29', '2018-11-01', '4', '4285714.29', '1428571.43', '200000.00', '1628571.43', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('154', '29', '2018-12-01', '5', '2857142.86', '1428571.43', '200000.00', '1628571.43', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('155', '29', '2019-01-01', '6', '1428571.43', '1428571.43', '200000.00', '1628571.43', null, null);
INSERT INTO tx_kr_aju_sim VALUES ('156', '29', '2019-02-01', '7', '0.00', '1428571.43', '200000.00', '1628571.43', null, null);

-- ----------------------------
-- Table structure for `tx_kr_cicil`
-- ----------------------------
DROP TABLE IF EXISTS `tx_kr_cicil`;
CREATE TABLE `tx_kr_cicil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aju_id` int(11) NOT NULL,
  `tgl_cicil` date NOT NULL,
  `saldo_casa` double(14,2) NOT NULL,
  `cicilan` double(14,2) NOT NULL,
  `sisa_bersih` double(14,2) NOT NULL,
  `tunggakan` double(14,2) DEFAULT NULL,
  `tab_wajib` double(14,2) DEFAULT NULL,
  `sisa_gaji` double(14,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id1` (`aju_id`),
  KEY `id2` (`tgl_cicil`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tx_kr_cicil
-- ----------------------------

-- ----------------------------
-- Function structure for `getLastNo`
-- ----------------------------
DROP FUNCTION IF EXISTS `getLastNo`;
DELIMITER ;;
CREATE DEFINER=`customers`@`%` FUNCTION `getLastNo`(`tipeKredit` varchar(14)) RETURNS int(11)
BEGIN
	DECLARE lastNo INT;

	SELECT last_num INTO lastNo
	FROM num
	WHERE tipe_kredit = tipeKredit
	FOR UPDATE;

	CASE 
		WHEN lastNo IS NULL THEN
			INSERT INTO num (tipe_kredit, last_num) VALUES (tipeKredit, 1);
		ELSE
			UPDATE num SET last_num = last_num + 1 WHERE tipe_kredit = tipeKredit;
	END CASE;
	
	RETURN lastNo;

END
;;
DELIMITER ;
