package com.benclaus.koperasi.action.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 
 */
public class UploadForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private FormFile file;
	private Integer companyId;
	private Integer year;
	private Integer month;

	public FormFile getFile() {
		return file;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
	   HttpServletRequest request) {

	    ActionErrors errors = new ActionErrors();

	    if( getFile().getFileSize()== 0){
	       errors.add("common.file.err",
	    	new ActionMessage("error.common.file.required"));
	       return errors;
	    }

	    //only allow textfile to upload
	    if(!"text/plain".equals(getFile().getContentType())){
	        errors.add("common.file.err.ext",
	    	 new ActionMessage("error.common.file.textfile.only"));
	        return errors;
	    }

            //file size cant larger than 10kb
	    System.out.println(getFile().getFileSize());
	    if(getFile().getFileSize() > 10240){ //10kb
	       errors.add("common.file.err.size",
		    new ActionMessage("error.common.file.size.limit", 10240));
	       return errors;
	    }

	    return errors;
	}
    
}