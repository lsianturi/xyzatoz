/*
Navicat MySQL Data Transfer

Source Server         : koperasi
Source Server Version : 50722
Source Host           : 10.15.2.115:3306
Source Database       : customers

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-11 08:47:25
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
-- Records of log_logintrail
-- ----------------------------
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '3772245C3CCBA9A912A7D158AB0BC9BF', '08:37:06', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '3772245C3CCBA9A912A7D158AB0BC9BF', '08:39:41', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '65934255EE8E654FB265CBACAC85707F', '11:15:55', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '5F3615AD161DB9D5CAB6A69E22F2F81E', '11:52:25', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '433A2FD0AEB449374DC5150CD5A32755', '13:20:56', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '433A2FD0AEB449374DC5150CD5A32755', '13:22:53', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '433A2FD0AEB449374DC5150CD5A32755', '13:24:10', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '433A2FD0AEB449374DC5150CD5A32755', '13:25:48', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', '433A2FD0AEB449374DC5150CD5A32755', '13:26:54', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', 'D7296A566DD6FFE910E12FE14C16CA04', '13:36:54', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', 'AB779AF46C26CAB12EC93B0FC3CF4F30', '13:49:46', 'Login');
INSERT INTO log_logintrail VALUES ('lambok.sianturi', 'AB779AF46C26CAB12EC93B0FC3CF4F30', '14:30:27', 'Login');

-- ----------------------------
-- Table structure for `ms_area`
-- ----------------------------
DROP TABLE IF EXISTS `ms_area`;
CREATE TABLE `ms_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_area
-- ----------------------------
INSERT INTO ms_area VALUES ('1', 'Cicurug');
INSERT INTO ms_area VALUES ('2', 'Cibadak');
INSERT INTO ms_area VALUES ('3', 'Parungkuda');
INSERT INTO ms_area VALUES ('4', 'Benda');
INSERT INTO ms_area VALUES ('5', 'Bankong Reang');
INSERT INTO ms_area VALUES ('6', 'Parakan Salak');

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
-- Table structure for `ms_jns_anggota`
-- ----------------------------
DROP TABLE IF EXISTS `ms_jns_anggota`;
CREATE TABLE `ms_jns_anggota` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jenis` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_jns_anggota
-- ----------------------------
INSERT INTO ms_jns_anggota VALUES ('1', 'UMUM');
INSERT INTO ms_jns_anggota VALUES ('2', 'BIASA');

-- ----------------------------
-- Table structure for `ms_jns_kelamin`
-- ----------------------------
DROP TABLE IF EXISTS `ms_jns_kelamin`;
CREATE TABLE `ms_jns_kelamin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

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
INSERT INTO ms_menu VALUES ('TRX', 'KOP', 'Transaksi', 'Transaction', '1', '0', '1', '0', '1', '', 'lambok.sianturi', null, null, null);
INSERT INTO ms_menu VALUES ('TRX_PJM', 'TRX', 'Transaksi Pinjaman', 'Pinjaman', '2', '0', '1', '0', '2', 'trx/Transaction.do?dispatch=prepare', 'lambok.sianturi', null, null, null);
INSERT INTO ms_menu VALUES ('TRX_PJM_search', 'TRX_PJM', 'Transaksi Pinjaman Main Page', 'Transaksi Pinjaman Main Page', '2', '0', '1', '0', '3', '', 'lambok.sianturi', '15:38:08', 'lambok.sianturi', 'update');

-- ----------------------------
-- Table structure for `ms_nasabah`
-- ----------------------------
DROP TABLE IF EXISTS `ms_nasabah`;
CREATE TABLE `ms_nasabah` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `jns_kelamin_id` int(11) NOT NULL,
  `sts_sipil_id` int(1) NOT NULL,
  `domisili` text,
  `alamat` text,
  `telepon` varchar(20) NOT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `perusahaan_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `bagian` varchar(30) NOT NULL,
  `no_rek` varchar(30) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `tgl_payroll` date DEFAULT NULL,
  `sts_krywn_id` int(11) NOT NULL,
  `jns_anggota_id` int(11) NOT NULL,
  `sts_anggota_id` int(11) NOT NULL,
  `no_rek_ref` varchar(30) DEFAULT NULL,
  `nama_ref` varchar(50) DEFAULT NULL,
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
  KEY `idx7` (`sts_anggota_id`),
  KEY `idx8` (`is_agent`),
  KEY `idx9` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_nasabah
-- ----------------------------
INSERT INTO ms_nasabah VALUES ('1', 'Lambok', '1', '2', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', '2018-04-30', '2018-03-26', '1', '1', '1', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', '1', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('2', 'gdfg', '2', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', '2017-12-31', '2017-12-31', '1', '1', '1', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', '0', '1', '2018-06-07 15:25:38', 'lambok.sianturi', 'delete');
INSERT INTO ms_nasabah VALUES ('3', 'Arsene wenger test edit', '1', '1', 'Chase Plaza edy', 'Chase Plaza test', '5435632', '1', '1', '1', 'Ini bagian perwakilan rakyat', '657867854', '2018-04-30', '2018-04-30', '1', '1', '1', '345346', '89768566', 'ok', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('4', 'tyujtyu', '1', '1', 'Chase Plaza', 'tiutyu', '5435632', '1', '1', '1', 'Cutting', 'gdgfg', '2018-05-28', '2018-05-28', '1', '1', '1', 'dgsdfg', 'gdfsg', 'dfghh', '0', '1', '2018-06-07 15:25:06', 'lambok.sianturi', 'delete');
INSERT INTO ms_nasabah VALUES ('5', 'tretet', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '2', '1', '', '56756', '2018-04-30', '2018-04-30', '1', '1', '1', '876678', 'gjgjgf', 'jtuiu', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('6', 'Lambok Sianturin sdhgdfhfh', '2', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'fdghh', '6578', '2018-05-28', '2018-05-28', '1', '1', '1', '56865', 'jgjgfj', 'jghkjhgk', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('7', 'Lambok Sianturi dghdhfdh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '3', '1', '', '54', '2018-05-28', '2018-05-25', '1', '1', '1', '8586', '333yry', 'fdhfdhh', '0', '0', null, null, null);
INSERT INTO ms_nasabah VALUES ('8', 'Lambok Sianturi', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'yrtyhrthfd', '3535', '2018-05-28', '2018-06-13', '2', '1', '1', '534535', 'fghgdh', 'fdhdfh', '0', '0', null, null, null);

-- ----------------------------
-- Table structure for `ms_nasabah_hist`
-- ----------------------------
DROP TABLE IF EXISTS `ms_nasabah_hist`;
CREATE TABLE `ms_nasabah_hist` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jns_kelamin_id` int(11) NOT NULL,
  `sts_sipil_id` int(1) NOT NULL,
  `domisili` text,
  `alamat` text,
  `telepon` varchar(20) NOT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `perusahaan_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `bagian` varchar(30) NOT NULL,
  `no_rek` varchar(30) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `tgl_payroll` date DEFAULT NULL,
  `sts_krywn_id` int(11) NOT NULL,
  `jns_anggota_id` int(11) NOT NULL,
  `sts_anggota_id` int(11) NOT NULL,
  `no_rek_ref` varchar(30) DEFAULT NULL,
  `nama_ref` varchar(50) DEFAULT NULL,
  `keterangan` text,
  `is_agent` int(1) DEFAULT '0',
  `deleted` int(1) DEFAULT '0',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30) DEFAULT NULL,
  KEY `idx1` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_nasabah_hist
-- ----------------------------
INSERT INTO ms_nasabah_hist VALUES ('1', 'Lambok', '1', '1', 'Serpong', 'Sukabumi', '08135345343', '1', '2', '1', 'Sew', '5352', '2018-04-30', null, '1', '1', '1', '12323423', 'gdefgdfg', 'gesgsdfg', '1', '0', '2018-06-05 17:40:38', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', 'gdfg', '1', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'gdfgdfg', '234234', '2017-12-31', null, '1', '1', '1', 'gdfgdf', '4234', 'Ini contoh perubahan', '0', '0', '2018-06-05 17:54:20', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', 'gdfg', '1', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', '2017-12-31', null, '1', '1', '1', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', '0', '0', '2018-06-05 17:56:23', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', 'Lambok', '1', '1', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', '2018-04-30', null, '1', '1', '1', '12323423', 'gdefgdfg', 'ini adalah contoh perubahan field', '1', '0', '2018-06-06 10:51:01', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', 'Lambok', '1', '1', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', '2018-04-30', null, '1', '1', '1', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', '1', '0', '2018-06-06 10:51:19', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('2', 'gdfg', '2', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', '2017-12-31', null, '1', '1', '1', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', '0', '0', '2018-06-06 12:43:00', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('3', 'Arsene wenger test edit', '1', '1', 'Chase Plaza edy', 'Chase Plaza test', '5435632', '1', '1', '1', 'Ini bagian perwakilan rakyat', '657867854', '2018-04-30', null, '1', '1', '1', '345346', '89768566', 'ok', '0', '0', '2018-06-06 15:10:15', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', 'tyujtyu', '1', '1', 'Chase Plaza', 'tiutyu', '5435632', '1', '1', '1', 'Cutting', 'gdgfg', '2018-05-28', null, '1', '1', '1', 'dgsdfg', 'gdfsg', 'dfghh', '0', '0', '2018-06-06 16:41:08', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', 'tretet', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '2', '1', '', '56756', '2018-04-30', null, '1', '1', '1', '876678', 'gjgjgf', 'jtuiu', '0', '0', '2018-06-06 16:46:25', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', 'Lambok Sianturin sdhgdfhfh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'fdghh', '6578', '2018-05-28', null, '1', '1', '1', '56865', 'jgjgfj', 'jghkjhgk', '0', '0', '2018-06-06 16:52:50', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('7', 'Lambok Sianturi dghdhfdh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', '', '54', '2018-05-28', null, '1', '1', '1', '8586', '333yry', 'fdhfdhh', '0', '0', '2018-06-06 17:10:07', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', 'Lambok', '1', '1', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', '2018-04-30', '2018-03-26', '1', '1', '1', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', '1', '0', '2018-06-06 17:35:43', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('7', 'Lambok Sianturi dghdhfdh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', '', '54', '2018-05-28', '2018-05-25', '1', '1', '1', '8586', '333yry', 'fdhfdhh', '0', '0', '2018-06-06 17:48:53', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('8', 'Lambok Sianturi', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'yrtyhrthfd', '3535', '2018-05-28', '2018-06-13', '1', '1', '1', '534535', 'fghgdh', 'fdhdfh', '0', '0', '2018-06-06 17:49:27', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('7', 'Lambok Sianturi dghdhfdh', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '3', '1', '', '54', '2018-05-28', '2018-05-25', '1', '1', '1', '8586', '333yry', 'fdhfdhh', '0', '0', '2018-06-07 14:58:19', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('4', 'tyujtyu', '1', '1', 'Chase Plaza', 'tiutyu', '5435632', '1', '1', '1', 'Cutting', 'gdgfg', '2018-05-28', '2018-05-28', '1', '1', '1', 'dgsdfg', 'gdfsg', 'dfghh', '0', '1', '2018-06-07 15:25:06', null);
INSERT INTO ms_nasabah_hist VALUES ('2', 'gdfg', '2', '1', 'Bandung', 'Jakarta', '23423', '1', '2', '1', 'Ini bagian perwakilan rakyat', '234234', '2017-12-31', '2017-12-31', '1', '1', '1', 'Jack De la Rocha', '4234', 'Ini contoh perubahan', '0', '1', '2018-06-07 15:25:38', null);
INSERT INTO ms_nasabah_hist VALUES ('6', 'Lambok Sianturin sdhgdfhfh', '2', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'fdghh', '6578', '2018-05-28', '2018-05-28', '1', '1', '1', '56865', 'jgjgfj', 'jghkjhgk', '0', '0', '2018-06-08 14:42:20', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('1', 'Lambok', '1', '2', 'Serpong tangsel apa sih', 'Sukabumi jawa barat', '08135345343', '1', '2', '1', 'Sewing', '5352', '2018-04-30', '2018-03-26', '1', '1', '1', '12323423', 'KOk baru 2', 'ini adalah contoh perubahan field', '1', '0', '2018-06-08 14:44:17', 'lambok.sianturi');
INSERT INTO ms_nasabah_hist VALUES ('8', 'Lambok Sianturi', '1', '1', 'Chase Plaza', 'Chase Plaza', '5435632', '1', '1', '1', 'yrtyhrthfd', '3535', '2018-05-28', '2018-06-13', '2', '1', '1', '534535', 'fghgdh', 'fdhdfh', '0', '0', '2018-06-08 14:51:16', 'lambok.sianturi');

-- ----------------------------
-- Table structure for `ms_pegawai`
-- ----------------------------
DROP TABLE IF EXISTS `ms_pegawai`;
CREATE TABLE `ms_pegawai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_pegawai
-- ----------------------------
INSERT INTO ms_pegawai VALUES ('1', 'Asep', 'Cicurug', 'Cicurug', '0812342', '1', '0', '2018-03-01', 'ket', '0');
INSERT INTO ms_pegawai VALUES ('2', 'test', 'tset', 'tset', '342', '1', '0', '2017-12-31', 'tewtwt', '0');
INSERT INTO ms_pegawai VALUES ('3', 'tdh', 'fghgfh', 'fdhfd', '3453', '1', '0', '2017-12-31', 'tetdgdsg', '0');
INSERT INTO ms_pegawai VALUES ('4', 'tert', 'tert', 'ert', 'ertr', '1', '0', '2017-12-31', 'ert', '0');
INSERT INTO ms_pegawai VALUES ('5', 'gdfsg', 'dfgds', 'gdgfsdg', 'dfgs', '1', '0', '2017-12-31', 'dfg', '0');
INSERT INTO ms_pegawai VALUES ('6', 'Lambok', 'Tset', 'Test', '023421', '1', '1', '2017-12-31', 'Test', '0');
INSERT INTO ms_pegawai VALUES ('7', 'Javk', 'Laguboti', 'Jakarta', '021-032424', '2', '2', '2017-12-31', 'Testt', '0');

-- ----------------------------
-- Table structure for `ms_perusahaan`
-- ----------------------------
DROP TABLE IF EXISTS `ms_perusahaan`;
CREATE TABLE `ms_perusahaan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) NOT NULL,
  `alamat` text,
  `industri` int(11) NOT NULL,
  `area` int(11) NOT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_perusahaan
-- ----------------------------
INSERT INTO ms_perusahaan VALUES ('1', 'PT Aqua Golden Mississippi', 'Jl. Raya Sukabumi No 12. Cicurug', '2', '1', '1');
INSERT INTO ms_perusahaan VALUES ('2', 'PT. Pocari Sewot', 'Jl Cidahu no 1234', '1', '5', '0');
INSERT INTO ms_perusahaan VALUES ('3', 'PT. Mayora Indah', 'Jl. Parungkuda agak kesini', '4', '4', '0');
INSERT INTO ms_perusahaan VALUES ('4', 'PT Dongfeng Mobilindo', 'Jl. Testing 10', '4', '6', '0');

-- ----------------------------
-- Table structure for `ms_perusahaan_hist`
-- ----------------------------
DROP TABLE IF EXISTS `ms_perusahaan_hist`;
CREATE TABLE `ms_perusahaan_hist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) NOT NULL,
  `alamat` text,
  `industri` int(11) NOT NULL,
  `area` int(11) NOT NULL,
  `deleted` int(1) DEFAULT '0',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30) DEFAULT NULL,
  KEY `idx1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_perusahaan_hist
-- ----------------------------
INSERT INTO ms_perusahaan_hist VALUES ('2', 'PT. Pocari Sewot', 'Jl Cidahu permai no 1234', '1', '5', '0', null, 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('2', 'PT. Pocari Sewot', 'Jl Cidahu no 1234', '1', '5', '0', '2018-06-08 13:29:57', 'lambok.sianturi');
INSERT INTO ms_perusahaan_hist VALUES ('4', 'PT Dongfeng Mobilindo', 'Jl. Testing 10', '4', '6', '0', '2018-06-08 13:31:42', 'lambok.sianturi');

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
-- Table structure for `ms_sts_anggota`
-- ----------------------------
DROP TABLE IF EXISTS `ms_sts_anggota`;
CREATE TABLE `ms_sts_anggota` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_sts_anggota
-- ----------------------------
INSERT INTO ms_sts_anggota VALUES ('1', 'Baru');
INSERT INTO ms_sts_anggota VALUES ('2', 'Lama');

-- ----------------------------
-- Table structure for `ms_sts_krywn`
-- ----------------------------
DROP TABLE IF EXISTS `ms_sts_krywn`;
CREATE TABLE `ms_sts_krywn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ms_sts_krywn
-- ----------------------------
INSERT INTO ms_sts_krywn VALUES ('1', 'Aktif');
INSERT INTO ms_sts_krywn VALUES ('2', 'Jeda');
INSERT INTO ms_sts_krywn VALUES ('3', 'Off');
INSERT INTO ms_sts_krywn VALUES ('4', 'Cuti Hamil');
INSERT INTO ms_sts_krywn VALUES ('5', 'Kabur');

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
INSERT INTO ms_user VALUES ('lambok.sianturi', '900150983cd24fb0d6963f7d28e17f72', 'MANAGER', 'Lambok', 'Sianturi', '0812345678924', null, '1', '30', 'lambok.sianturi', '0', '2018-06-08 14:30:27', null, null, null);
