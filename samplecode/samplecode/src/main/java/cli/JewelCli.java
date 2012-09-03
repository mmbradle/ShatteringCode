package cli;

import java.util.List;

import com.lexicalscope.jewel.cli.ArgumentValidationException;
import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.CommandLineInterface;
import com.lexicalscope.jewel.cli.Option;

public class JewelCli {
	public static void main(String... args) {
		args = new String[] {"--help"};
		try {
			CliOpts options = CliFactory.parseArguments(CliOpts.class, args);
			if (options.isCrcSave()) {
				List<Integer> crcSaveArgs = options.getCrcSave();
			}
		} catch (ArgumentValidationException e) {
			System.out.println(e.getMessage());
		}
	}
}

@CommandLineInterface(application = "command")
interface CliOpts {
	@Option(shortName={"1"}, exactly = 2)
	List<Integer> getCrcSave();
	boolean isCrcSave();

	@Option(helpRequest = true)
	boolean getHelp();
}