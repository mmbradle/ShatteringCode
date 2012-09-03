package shatteringstone.sandbox.cli;

import java.util.List;

import com.lexicalscope.jewel.cli.ArgumentValidationException;
import com.lexicalscope.jewel.cli.Cli;
import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.CommandLineInterface;
import com.lexicalscope.jewel.cli.Option;

public class JewelCli {
	public static void main(String...args) {
		CliOpts options = JewelCli.parseOpts(args);
		JewelCli.interrogateOpts(options);
	}
	
	public static CliOpts parseOpts(String... args) {
		CliOpts options = null;
//		args = new String[] {"--ss", "0", "1"};
//		args = new String[] {"--help"};
		args = new String[] {"--cs", "-r", "0", "1"};
		try {
			options = CliFactory.parseArguments(CliOpts.class, args);
			
		} catch (ArgumentValidationException e) {
			System.out.println(e.getMessage());
		}
		return options;
	}
	
	public static void interrogateOpts(CliOpts options) {
		if (options.isRange()) {
			List<Integer> range = options.getRange();
			System.out.println(range);
		}
		System.out.println(options.toString());
		System.out.println(options.getCrcCompare());
	}
	
	public static void printOptsHelp() {
		Cli<CliOpts> cli = CliFactory.createCli(CliOpts.class);
		System.out.println(cli.getHelpMessage());
	}
	
	@CommandLineInterface(application = "command")
	private static interface CliOpts {
		@Option(longName={"returnCompare", "rc"}, shortName="0")
		boolean getReturnCompare();
		
		@Option(longName={"crcSave", "cs"}, shortName="1")
		boolean getCrcSave();
		
		@Option(longName={"crcCompare", "cc"}, shortName="2")
		boolean getCrcCompare();
		
		@Option(longName={"stateSave", "ss"}, shortName="3")
		boolean getStateSave();
		
		@Option(longName={"stateCompare", "sc"}, shortName="4")
		boolean getStateCompare();
		
		@Option(shortName="r", exactly = 2 )
		List<Integer> getRange();
		boolean isRange();
		
		@Option(helpRequest = true)
		boolean getHelp();
	}
}
