/*

Copyright or © or Copr. Ecole des Mines d'Alès (2012) 

This software is a computer program whose purpose is to 
process semantic graphs.

This software is governed by the CeCILL  license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.

 */
 
 
package slib.tools.sml.smbb.cli.conf.xml.utils;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import slib.sml.smbb.core.SmbbCst;
import slib.tools.module.CmdHandler;
import slib.tools.sml.sm.cli.SmCli;
import slib.utils.ex.SGL_Exception;


public class SmbbCmdHandler  extends CmdHandler {

	
	public String  xmlConfFile;


	static Logger logger = LoggerFactory.getLogger(SmbbCmdHandler.class);
	

	public SmbbCmdHandler(String[] args) throws SGL_Exception {
		super(new SmbbCst(), new SmbbCmdHandlerCst(), args);
	}


	public void processArgs(String[] args){
		
		CommandLineParser parser = new BasicParser();

		try {
			CommandLine line = parser.parse( options, args );

			if(line.hasOption("help")){
				ending("",true);
			}
			else{
				
				//-- XML file
				if(line.hasOption("xmlconf")){
					this.xmlConfFile = line.getOptionValue( "xmlconf" );
				}
				else
					ending(SmbbCmdHandlerCst.errorMissingXMLconf,true);
			}

		}
		catch( ParseException exp ) {
			ending( cst.appName+" Parsing failed.  Reason: " + exp.getMessage(),true );
		}
	}



	public static void main(String[] args) {

		try {
			SmbbCmdHandler 	    c = new SmbbCmdHandler(args);
			SmCli ssp = new SmCli();
			ssp.execute(c.xmlConfFile);
			
		} catch (Exception e) {
			logger.info("Ooops : "+e.getMessage());
			logger.info("see log file.");
		}
	}

}
