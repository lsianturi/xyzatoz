package com.benclaus.koperasi.utility;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author 
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MessageResources {

	static final int sfiSIZE = 11;
	int iSelIndex = 0;
	int[] aiOrder = new int[sfiSIZE];
	
	Locale[] alSupported = {
		Locale.US,
		Locale.UK,
		Locale.FRANCE,
		Locale.GERMANY,
		new Locale( "id", "ID" )
	};

	ResourceBundle rb;
	String propName = "com/benclaus/koperasi/resources/MenuResources";

	String[] asDNames = new String[alSupported.length];

	private static MessageResources messages = null;
	public static synchronized MessageResources getInstance() {
		if (messages == null)
			messages = new MessageResources();
		return messages;
	}

	public int getLocaleIndex() {
		Locale lDefault = Locale.getDefault();
		
		for(int i = 0; i < alSupported.length; i++ )
		{
			asDNames[i] = alSupported[i].getDisplayName();
			if( iSelIndex == 0 && lDefault.equals( alSupported[i] ) )
				iSelIndex = i;
		} // end for	
		return iSelIndex;
	}

	public int getLocaleIndex(Locale locale) {
		Locale.setDefault(locale);
		Locale lDefault = Locale.getDefault();
		
		for(int i = 0; i < alSupported.length; i++ )
		{
			asDNames[i] = alSupported[i].getDisplayName();
			if( iSelIndex == 0 && lDefault.equals( alSupported[i] ) )
				iSelIndex = i;
		} // end for	
		return iSelIndex;
	}

	public Locale getLocale(Locale locale) {
		Locale.setDefault(locale);
		Locale lDefault = Locale.getDefault();
		return lDefault;
	}

	public String loadFromResourceBundle(String key, int iSelIndex)
	{
		try
		{ // get the PropertyResourceBundle
			rb = ResourceBundle.getBundle(propName, alSupported[iSelIndex] );
			// get data associated with keys
			return rb.getString(key);
		} // end try
		catch( MissingResourceException mre )
		{
			return null;
		}
	} // end loadFromResourceBundle	

	public String loadFromResourceBundle(String key, Locale locale)
	{		
		try
		{ // get the PropertyResourceBundle
			rb = ResourceBundle.getBundle(propName, locale);
			// get data associated with keys
			return rb.getString(key);
		} // end try
		catch( MissingResourceException mre )
		{
			return null;
		}
	} // end loadFromResourceBundle	


}
