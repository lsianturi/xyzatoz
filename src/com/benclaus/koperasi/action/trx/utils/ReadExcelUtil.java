package com.benclaus.koperasi.action.trx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

import com.benclaus.koperasi.dao.trx.A3Service;
import com.benclaus.koperasi.dao.trx.ConfigService;
import com.benclaus.koperasi.model.BookEnum;
import com.benclaus.koperasi.model.CompanyBook;
import com.benclaus.koperasi.model.Config;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.FormulaArg;
import com.benclaus.koperasi.model.FormulaArgItem;
import com.benclaus.koperasi.model.Mapping;

public class ReadExcelUtil {
	ConfigService cfgService = ConfigService.getInstance();
	A3Service a3Service = A3Service.getInstance();
	private static Logger log = Logger.getLogger(ReadExcelUtil.class);
	
	private final double PER_FULL_AMOUNT = 1000000000.0;
	private final double PER_THOUSAND = 1000000.0;
	private final double PER_MIO = 1000.0;
	
	private final String[] excelFunction = {"ABS","ACCRINT","ACCRINTM","ACOS","ACOSH","ACOT","ACOTH","AGGREGATE","ADDRESS","AMORDEGRC","AMORLINC","AND","TRUE","TRUE","ARABIC","AREAS","ASC","ASIN","ASINH","ATAN","ATAN","ATANH","AVEDEV","AVERAGE","AVERAGEA","AVERAGEIF","AVERAGEIFS","BAHTTEXT","BASE","BESSELI","BESSELJ","BESSELK","BESSELY","BETADIST","BETA.DIST","BETAINV","BETA.INV","BIN","DEC","BIN","HEX","BIN","OCT","BINOMDIST","BINOM.DIST","BINOM.DIST.RANGE","BINOM.INV","BITAND","BITLSHIFT","BITOR","OR","BITRSHIFT","BITXOR","CALL","CEILING","CEILING.MATH","CEILING.PRECISE","CELL","CHAR","CHIDIST","NOTE","CHIINV","NOTE","CHITEST","NOTE","CHISQ.DIST","CHISQ.DIST.RT","CHISQ.INV","CHISQ.INV.RT","CHISQ.TEST","CHOOSE","CLEAN","CODE","COLUMN","COLUMNS","COMBIN","COMBINA","COMPLEX","CONCAT","CONCATENATE","CONFIDENCE","CONFIDENCE.NORM","CONFIDENCE.T","CONVERT","CORREL","COS","COSH","COT","COTH","COUNT","COUNTA","COUNTBLANK","COUNTIF","COUNTIFS","COUPDAYBS","COUPDAYS","COUPDAYSNC","COUPNCD","COUPNUM","COUPPCD","COVAR","COVARIANCE.P","COVARIANCE.S","CRITBINOM","CSC","CSCH","CUBEKPIMEMBER","KPI","KPI","CUBEMEMBER","CUBEMEMBERPROPERTY","CUBERANKEDMEMBER","CUBESET","CUBESETCOUNT","CUBEVALUE","CUMIPMT","CUMPRINC","DATE","DATEDIF","DATEVALUE","DAVERAGE","DAY","DAYS","DAYS","DB","DBCS","DCOUNT","DCOUNTA","DDB","DEC","BIN","DEC","HEX","DEC","OCT","DECIMAL","DEGREES","DELTA","DEVSQ","DGET","DISC","DMAX","DMIN","DOLLAR","DOLLARDE","DOLLARFR","DPRODUCT","DSTDEV","DSTDEVP","DSUM","DURATION","DVAR","DVARP","EDATE","EFFECT","ENCODEURL","URL","EOMONTH","ERF","ERF.PRECISE","ERFC","ERFC.PRECISE","ERF","ERROR.TYPE","EUROCONVERT","EVEN","EXACT","EXP","EXPON.DIST","EXPONDIST","FACT","FACTDOUBLE","FALSE","FALSE","F.DIST","FDIST","F.DIST.RT","FILTERXML","XML","XP","FIND","FINDB","F.INV","F.INV.RT","FINV","FISHER","FISHERINV","FIXED","FLOOR","FLOOR.MATH","FLOOR.PRECISE","FORECAST","FORECAST.LINEAR","FORECAST.ETS","AAA","ETS","FORECAST.ETS.CONFINT","FORECAST.ETS.SEASONALITY","FORECAST.ETS.STAT","FORECAST.LINEAR","FORMULATEXT","FREQUENCY","F.TEST","FTEST","FV","FVSCHEDULE","GAMMA","GAMMA.DIST","GAMMADIST","GAMMA.INV","GAMMAINV","GAMMALN","GAMMALN.PRECISE","GAUSS","GCD","GEOMEAN","GESTEP","GETPIVOTDATA","GROWTH","HARMEAN","HEX","BIN","HEX","DEC","HEX","OCT","HLOOKUP","HOUR","HYPERLINK","HYPGEOM.DIST","HYPGEOMDIST","IF","IFERROR","IFNA","IFS","TRUE","IMABS","IMAGINARY","IMARGUMENT","IMCONJUGATE","IMCOS","IMCOSH","IMCOT","IMCSC","IMCSCH","IMDIV","IMEXP","IMLN","IMLOG","IMLOG","IMPOWER","IMPRODUCT","IMREAL","IMSEC","IMSECH","IMSIN","IMSINH","IMSQRT","IMSUB","IMSUM","IMTAN","INDEX","INDIRECT","INFO","INT","INTERCEPT","INTRATE","IPMT","IRR","ISBLANK","TRUE","ISERR","TRUE","ISERROR","TRUE","ISEVEN","TRUE","ISFORMULA","TRUE","ISLOGICAL","TRUE","ISNA","TRUE","ISNONTEXT","TRUE","ISNUMBER","TRUE","ISODD","TRUE","ISREF","TRUE","ISTEXT","TRUE","ISO.CEILING","ISOWEEKNUM","ISO","ISPMT","JIS","KURT","LARGE","LCM","LEFT","LEFTB","LEN","LENB","LINEST","LN","LOG","LOG","LOGEST","LOGINV","LOGNORM.DIST","LOGNORMDIST","LOGNORM.INV","LOOKUP","LOWER","MATCH","MAX","MAXA","MAXIFS","MDETERM","MDURATION","MEDIAN","MID","MIDB","MIN","MINIFS","MINA","MINUTE","MINVERSE","MIRR","MMULT","MOD","MODE","MODE.MULT","MODE.SNGL","MONTH","MROUND","MULTINOMIAL","MUNIT","NA","NEGBINOM.DIST","NEGBINOMDIST","NETWORKDAYS","NETWORKDAYS.INTL","NOMINAL","NORM.DIST","NORMDIST","NORMINV","NORM.INV","NOTE","NORM.S.DIST","NORMSDIST","NORM.S.INV","NORMSINV","NOT","NOW","NPER","NPV","NUMBERVALUE","OCT","BIN","OCT","DEC","OCT","HEX","ODD","ODDFPRICE","ODDFYIELD","ODDLPRICE","ODDLYIELD","OFFSET","OR","TRUE","TRUE","PDURATION","PEARSON","PERCENTILE.EXC","..","PERCENTILE.INC","PERCENTILE","PERCENTRANK.EXC","..","PERCENTRANK.INC","PERCENTRANK","PERMUT","PERMUTATIONA","PHI","PHONETIC","PI","PMT","POISSON.DIST","POISSON","POWER","PPMT","PRICE","PRICEDISC","PRICEMAT","PROB","PRODUCT","PROPER","PV","QUARTILE","QUARTILE.EXC","..","QUARTILE.INC","QUOTIENT","RADIANS","RAND","RANDBETWEEN","RANK.AVG","RANK.EQ","RANK","RATE","RECEIVED","REGISTER.ID","ID","DLL","REPLACE","REPLACEB","REPT","RIGHT","RIGHTB","ROMAN","ROUND","ROUNDDOWN","ROUNDUP","ROW","ROWS","RRI","RSQ","RTD","COM","SEARCH","SEARCHB","SEC","SECH","SECOND","SERIESSUM","SHEET","SHEETS","SIGN","SIN","SINH","SKEW","SKEW.P","SLN","SLOPE","SMALL","SQL.REQUEST","SQRT","SQRTPI","STANDARDIZE","STDEV","STDEV.P","STDEV.S","STDEVA","STDEVP","STDEVPA","STEYX","SUBSTITUTE","SUBTOTAL","SUM","SUMIF","SUMIFS","SUMPRODUCT","SUMSQ","SUMX","MY","SUMX","PY","SUMXMY","SWITCH","SYD","TAN","TANH","TBILLEQ","TBILLPRICE","TBILLYIELD","T.DIST","T.DIST.","T.DIST.RT","TDIST","TEXT","TEXTJOIN","TIME","TIMEVALUE","T.INV","T.INV.","TINV","TODAY","TRANSPOSE","TREND","TRIM","TRIMMEAN","TRUE","TRUE","TRUNC","T.TEST","TTEST","TYPE","UNICHAR","UNICODE","UPPER","VALUE","VAR","VAR.P","VAR.S","VARA","VARP","VARPA","VDB","VLOOKUP","WEBSERVICE","WEEKDAY","WEEKNUM","WEIBULL","WEIBULL.DIST","WORKDAY","WORKDAY.INTL","XIRR","XNPV","XOR","OR","YEAR","YEARFRAC","YIELD","YIELDDISC","YIELDMAT","Z.TEST","ZTEST"};
	
	private List<String> functions = null;
	
	private static volatile ReadExcelUtil instance;
	Config cfg = cfgService.getRorConfig();
	
	public ReadExcelUtil() {
		functions = Arrays.asList(excelFunction);
	}

	public static ReadExcelUtil getInstance() {
        final ReadExcelUtil currentInstance;
        if (instance == null) {
            synchronized (ReadExcelUtil.class) {
                if (instance == null) {
                    instance = new ReadExcelUtil();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	public synchronized void process() {
		
		File[] files = null;
		String fileType="";
		
		try {
			File dir = new File(cfg.getExcelSrcDir());
			files = dir.listFiles();
			
			if (files != null) {
				Arrays.sort(files);
				for (File file : files) {
					fileType =  Files.probeContentType(file.toPath());
					
					if (fileType.equals("application/vnd.ms-excel") || fileType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
						readExcel(file);
					}
				}
			}
		} catch (Exception e) {
			log.error(e);
//			e.printStackTrace();
		}
	}
	
	public void readExcel(File file) {
		
		DecimalFormat format = new DecimalFormat("###,###.00");
		File toFile=null;
//		log.debug(file.getName());
//		System.out.println(file.getName());
		
		FileInputStream inputStream = null;
		Workbook wb = null;
		FormulaEvaluator evaluator = null;
		Sheet sheet1 = null;
		Sheet sheet2 = null;
		Sheet sheet3 = null;
//		Sheet sheetTmp = null;
		Cell formulaCell = null;
		
		CompanyBook pnlBook = null;
		CompanyBook bsBook = null;
		CompanyBook cfBook = null;
		try {
			inputStream = new FileInputStream(file);
			wb = WorkbookFactory.create(inputStream);
			evaluator = wb.getCreationHelper().createFormulaEvaluator();
			
			int jmlSheet = wb.getNumberOfSheets();
			Map<Integer, Integer> idx = new HashMap<Integer, Integer>();
	        int no = 0;
	        for(int i= 0; i<jmlSheet; i++ ) {
	        	if (wb.getSheetVisibility(i).equals(SheetVisibility.VISIBLE)) {
	        		idx.put(no, i);
	        		no++;
	        	}
	        }
			
//			sheetTmp = wb.createSheet("Temp");
	        if (idx.size()>0) {
	        	sheet1 = wb.getSheetAt(idx.get(0));
	        }
			if (idx.size()>1) {
				sheet2 = wb.getSheetAt(idx.get(1));
			}
			if (idx.size()>2) {
				sheet3 = wb.getSheetAt(idx.get(2));
			}
		} catch (Exception e) {
			log.debug(e);
//			e.printStackTrace();
		}
		
		// suppose your formula is in B3
		CellReference cellReference = null;
		Row row = null;
		Cell cell = null; 
		Object obj = null;
		String str = null;
		String strFormula = null;
		String[] parts = null;
		
		int pos = file.getName().indexOf("-");
		String prefix = file.getName().substring(0, pos);
		List<Mapping> maps = a3Service.getMapping(prefix);
		FormulaArg args = new FormulaArg();
		FormulaArgItem argItems = new FormulaArgItem();
		Data data = null;
		
		Integer year = Integer.parseInt("20"+ file.getName().substring(pos + 1, pos + 3));
		Integer month = Integer.parseInt(file.getName().substring(pos + 3,pos + 5));
		
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> paramBook = new HashMap<>();
		Integer dataId = 0;
		Double amount=0.0;
		Double prevMonthsAmount=0.0;
		
		if (maps != null) {
			args.setFileName(file.getName());
			args.setCompanyPrefix(prefix);
			args.setYear(year);
			args.setMonth(month);
			args.setProcessTime(new Date());
			
			args = a3Service.insertFormulaArgs(args);
			
			for (Mapping map : maps ) {
				if (map.getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode() && pnlBook == null) {
					paramBook.put("prefix", prefix);
					paramBook.put("bookId", map.getBookItem().getBook().getId());
					pnlBook = a3Service.getBook(paramBook);
				}
				
				if (map.getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode() && bsBook == null) {
					paramBook.put("prefix", prefix);
					paramBook.put("bookId", map.getBookItem().getBook().getId());
					bsBook = a3Service.getBook(paramBook);
				}
				if (map.getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode() && cfBook == null) {
					paramBook.put("prefix", prefix);
					paramBook.put("bookId", map.getBookItem().getBook().getId());
					cfBook = a3Service.getBook(paramBook);
				}
				amount=0.0;
				prevMonthsAmount=0.0;
				data = new Data();
				param.put("company", map.getCompany().getId());
				param.put("year", year);
				param.put("bookItemId", map.getBookItem().getId());
				
				dataId = a3Service.getDataId(param);
				data.setId(dataId);
				data.setCompany(map.getCompany());
				data.setBookItem(map.getBookItem());
				data.setYear(year);
				data.setMonth(month);
				
				argItems.setFormulaArg(args);
				argItems.setBookItem(map.getBookItem());
				argItems.setFormula(map.getFormula());
				
				if (map.getBookItem().getId()==14) {
					System.out.println(map.getFormula());
				}
				
				str = map.getFormula().replaceAll("\\s+", ""); //hilangkan semua space agar hasil split hanya berisi rumus
				strFormula = map.getFormula().replaceAll("\\s+", "");
				parts = str.replaceAll("\"", "").split("((\\*\\.\\+)|(\\+)|(\\:)|([\\,\\<\\>\\+\\-\\*/\\(\\)]+))");
				Double some =0.0;
				if (str.length()>0 && parts.length > 0) {
					formulaCell = null;
					for (int i=0; i<parts.length; i++ ) {
						row = null;
						cell= null;
						
						try {
							some = Double.parseDouble(parts[i]);
						} catch (Exception e){some = null;}
						if (some == null && !parts[i].trim().equals("") && !functions.contains(parts[i].toUpperCase()) && !parts[i].contains("%") ) {
							try {
								cellReference = new CellReference(parts[i]); 
							} catch (Exception e){cellReference = null;}
							if (cellReference!= null ) {
								if (sheet1 != null && map.getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
									row = sheet1.getRow(cellReference.getRow());
								} else if (sheet2 != null && map.getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
									row = sheet2.getRow(cellReference.getRow());
								} else if (sheet3 != null && map.getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
									row = sheet3.getRow(cellReference.getRow());
								}
							}
							
							if (row != null) {
								cell = row.getCell(cellReference.getCol()); 
								if (cell != null) {
									obj = evaluateCellValue(evaluator, cell);
									if (obj instanceof Double)
										strFormula = strFormula.replace(parts[i], format.format((Double)obj));
									else
										strFormula = strFormula.replace(parts[i], obj.toString());
									//str = str.replace(parts[i], obj.toString());
								} else {
									strFormula  = strFormula.replace(parts[i], "null");
									//str = str.replace(parts[i], "0");
								}
							}else {
								strFormula  = strFormula.replace(parts[i], "null");
								//str = "0";
							}
							
						}
					}
					
					
					argItems.setFormulaArgs(strFormula);
//					formulaCell = sheetTmp.createRow(1).createCell(1);
					if (sheet1 != null && map.getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
						formulaCell = sheet1.createRow(1000).createCell(1);
					} else if (sheet2 != null && map.getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
						formulaCell = sheet2.createRow(1000).createCell(1);
					} else if (sheet3 != null && map.getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
						formulaCell = sheet3.createRow(1000).createCell(1);
					} else {
						formulaCell = null;
					}
					if (formulaCell != null ) {
						try {
							formulaCell.setAsActiveCell();
							formulaCell.setCellType(CellType.FORMULA);
							formulaCell.setCellFormula(str);
							amount = (Double) evaluateCellValue(evaluator, formulaCell);
						} catch (Exception e) {
							amount =0d;
						}
					} else {
						amount = 0d;
					}
					System.out.println(amount);
					argItems.setValue(amount);
					a3Service.insertFormulaArgItem(argItems);
					
					//amountnya perlu dicek, 1. apakah monthly apa ytd. 2. Apakah in Bio, in Mio dll
					if (map.getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
						
						if (pnlBook.isFullAmt()) {
							amount = amount / PER_FULL_AMOUNT;
						} else if (pnlBook.isMio()) {
							amount = amount / PER_MIO;
						} else if (pnlBook.isThousand()) {
							amount = amount / PER_THOUSAND;
						}
						
						if (pnlBook.isYearly() && data.getMonth()>1) {
							prevMonthsAmount = a3Service.getPreviousAmt(data);
							amount = amount - prevMonthsAmount;
						}
						
					} else if (map.getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
						if (bsBook.isFullAmt()) {
							amount = amount / PER_FULL_AMOUNT;
						} else if (bsBook.isMio()) {
							amount = amount / PER_MIO;
						} else if (bsBook.isThousand()) {
							amount = amount / PER_THOUSAND;
						}
						
						if (bsBook.isYearly() && data.getMonth()>1) {
							prevMonthsAmount = a3Service.getPreviousAmt(data);
							amount = amount - prevMonthsAmount;
						}
					} else {  //if (map.getBookItem().getBook().getId() == cfBook.getBook().getId()) {
						if (cfBook.isFullAmt()) {
							amount = amount / PER_FULL_AMOUNT;
						} else if (cfBook.isMio()) {
							amount = amount / PER_MIO;
						} else if (cfBook.isThousand()) {
							amount = amount / PER_THOUSAND;
						}
						if (cfBook.isYearly() && data.getMonth()>1) {
							prevMonthsAmount = a3Service.getPreviousAmt(data);
							amount = amount - prevMonthsAmount;
						}
					}
					System.out.println(amount);
					setData(data, month, amount);
					a3Service.updateActual(data);
				} else {
					
				}
			}
		}
		
		try {
			
			wb.close();
			inputStream.close();
			inputStream = new FileInputStream(file);
			GroupPrincipal group=null;
			UserPrincipal owner = null;
			try {
				group = Files.readAttributes(file.toPath(), PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS).group();
				owner = Files.readAttributes(file.toPath(), PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS).owner();
			} catch (Exception u) {}
			
		
			//move the file to 
			toFile = new File(cfg.getExcelBakDir()+file.getName()+".bak");
			try {
				Files.getFileAttributeView(toFile.toPath(), PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS).setGroup(group);
				Files.getFileAttributeView(toFile.toPath(), PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS).setOwner(owner);
			} catch (Exception u) {}
			
			OutputStream outStream = null;
			if (!toFile.exists()) 
				outStream = new FileOutputStream(toFile);
			else
				outStream = new FileOutputStream(toFile, false);
			
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
			
			inputStream.close();
    	    outStream.close();

    	    //delete the original file
    	    file.delete();
		} catch (Exception e){
//			log.error(e);
			e.printStackTrace();
		}
	}
	
	private void setData(Data data, int month, double value) {
		if (month == 1) {
			data.setActual1(value);
		} else if (month == 2) {
			data.setActual2(value);
		} else if (month == 3) {
			data.setActual3(value);
		} else if (month == 4) {
			data.setActual4(value);
		} else if (month == 5) {
			data.setActual5(value);
		} else if (month == 6) {
			data.setActual6(value);
		} else if (month == 7) {
			data.setActual7(value);
		} else if (month == 8) {
			data.setActual8(value);
		} else if (month == 9) {
			data.setActual9(value);
		} else if (month == 10) {
			data.setActual10(value);
		} else if (month == 11) {
			data.setActual11(value);
		} else if (month == 12) {
			data.setActual12(value);
		} 
	}
	
	public Object evaluateCellValue(FormulaEvaluator evaluator, Cell cell) {
		Object obj = null;
		evaluator.setIgnoreMissingWorkbooks(true);
		if (cell!=null) {
		    switch (cell.getCellType()) {
		        case Cell.CELL_TYPE_BOOLEAN:
		            obj = cell.getBooleanCellValue();
		            break;
		        case Cell.CELL_TYPE_NUMERIC:
		        	if (DateUtil.isCellDateFormatted(cell)) {
		        		obj = cell.getDateCellValue();
                    } else {
                    	obj = cell.getNumericCellValue();
                    }
                    break;
		        case Cell.CELL_TYPE_STRING:
		        	obj = cell.getStringCellValue();
		            break;
		        case Cell.CELL_TYPE_BLANK:
		        	obj = "";
		            break;
		        case Cell.CELL_TYPE_ERROR:
		        	obj = cell.getErrorCellValue();
		            break;
		        case Cell.CELL_TYPE_FORMULA: 
		        	try {
		        		Cell c = evaluator.evaluateInCell(cell);
			        	obj = c.getNumericCellValue();
		        	} catch (Exception e) {
//		        		e.printStackTrace();
		        		try {
		        			CellValue c = evaluator.evaluate(cell);
		        			obj = c.getNumberValue();
		        		} catch (Exception e2) {
//		        			e2.printStackTrace();
		        			obj = cell.getNumericCellValue();
		        		}
		        		
		        	}
		            break;
		    }
		}
		return obj;
	}
	
	public static void main(String[] args) {
		ReadExcelUtil util = new ReadExcelUtil();
		util.process();
	}

}
