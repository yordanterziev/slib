package slib.tools.sml.sml_server_deploy.cli;

import slib.tools.module.ModuleCst;
import slib.tools.sml.SmlCliCst;

public class SmlDeployCst extends ModuleCst {


	public static final String   appName     = SmlCliCst.ModuleName_SML_DEPLOY;
	public static final String   version     = "0.0.1";
	public static final String   reference   = null;
	public static final String   description = null;
	public static final String   report_bug  = "harispe.sebastien@gmail.com";
	
	
	public SmlDeployCst() {
		super(appName, version, reference,description, report_bug);
	}
}