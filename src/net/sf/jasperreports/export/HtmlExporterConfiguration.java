/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.export;

import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.annotations.ExporterParameter;
import net.sf.jasperreports.export.annotations.ExporterProperty;


/**
 * Interface containing settings used by the HTML exporters.
 *
 * @see HtmlExporter
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: HtmlExporterConfiguration.java 6993 2014-03-28 10:33:00Z teodord $
 */
public interface HtmlExporterConfiguration extends ExporterConfiguration
{
	/**
	 * Property that provides the default value for the {@link #isFlushOutput()} export configuration setting.
	 * 
	 * <p>
	 * The property can be set at report level or globally.
	 * By default, the HTML exporter performs a flush on the output stream
	 * after export.
	 * </p>
	 */
	public static final String PROPERTY_FLUSH_OUTPUT = JRPropertiesUtil.PROPERTY_PREFIX + "export.html.flush.output";


	/**
	 * Property that provides the default value for the {@link #getHtmlHeader()} export configuration setting.
	 */
	public static final String PROPERTY_HTML_HEADER = JRPropertiesUtil.PROPERTY_PREFIX + "export.html.header";


	/**
	 * Property that provides the default value for the {@link #getHtmlFooter()} export configuration setting.
	 */
	public static final String PROPERTY_HTML_FOOTER = JRPropertiesUtil.PROPERTY_PREFIX + "export.html.footer";


	/**
	 * Property that provides the default value for the {@link #getBetweenPagesHtml()} export configuration setting.
	 */
	public static final String PROPERTY_BETWEEN_PAGES_HTML = JRPropertiesUtil.PROPERTY_PREFIX + "export.html.between.pages";


	/**
	 * Returns a string representing HTML code that will be inserted in front of the generated report. The JasperReports engine places
	 * a piece of HTML code at the top of the page but users can define their own headers and stylesheet links.
	 */
	@SuppressWarnings("deprecation")
	@ExporterParameter(
		type=net.sf.jasperreports.engine.export.JRHtmlExporterParameter.class, 
		name="HTML_HEADER"
		)
	@ExporterProperty(PROPERTY_HTML_HEADER)
	public String getHtmlHeader();
	

	/**
	 * Returns a string representing HTML code that will be inserted after the generated report. By default, JasperReports closes
	 * the usual HTML tags that were opened in {@link #getHtmlHeader()}. If a custom HTML header was provided using {@link #getHtmlHeader()}, 
	 * it is recommended that a value for this setting is provided too, in order to ensure proper construction of HTML page.
	 */
	@SuppressWarnings("deprecation")
	@ExporterParameter(
		type=net.sf.jasperreports.engine.export.JRHtmlExporterParameter.class, 
		name="HTML_FOOTER"
		)
	@ExporterProperty(PROPERTY_HTML_FOOTER)
	public String getHtmlFooter();
	

	/**
	 * Returns a string representing HTML code that will be inserted between pages of the generated report. By default, JasperReports
	 * separates pages by two empty lines, but this behavior can be overridden by this parameter.
	 */
	@SuppressWarnings("deprecation")
	@ExporterParameter(
		type=net.sf.jasperreports.engine.export.JRHtmlExporterParameter.class, 
		name="BETWEEN_PAGES_HTML"
		)
	@ExporterProperty(PROPERTY_BETWEEN_PAGES_HTML)
	public String getBetweenPagesHtml();
	
	
	/**
	 * A flag that determines whether the HTML exporter should flush the
	 * output stream after writing the HTML content to it.
	 * @see #PROPERTY_FLUSH_OUTPUT
	 */
	@SuppressWarnings("deprecation")
	@ExporterParameter(
		type=net.sf.jasperreports.engine.export.JRHtmlExporterParameter.class, 
		name="FLUSH_OUTPUT"
		)
	@ExporterProperty(
		value=PROPERTY_FLUSH_OUTPUT, 
		booleanDefault=true
		)
	public Boolean isFlushOutput();
}