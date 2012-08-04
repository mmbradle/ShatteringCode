package cli;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

public class Main {
    public static void main(String... args) throws ParseException {
        mainTest("j1");
    }
    
    public static void mainTest(String... args) throws ParseException {
        final Options options = buildOptions();
        final Options subOptions1 = buildSubOptions1();
        final Options subOptions2 = buildSubOptions2();
        CommandLineParser parser = new GnuParser();
        CommandLine cmdline = parser.parse(options, args);

        if (cmdline.hasOption("j1")) {
            HelpFormatter help = new HelpFormatter();
            help.printHelp("cmdname", subOptions1);
            System.exit(-1);
        } else if (cmdline.hasOption("j2")) {
            HelpFormatter help = new HelpFormatter();
            help.printHelp("cmdname", subOptions2);
            System.exit(-1);
        } else {
            HelpFormatter help = new HelpFormatter();
            help.printHelp("cmdname", options);
            System.exit(-1);
        }
    }
    
    private static Options buildOptions() {
        final OptionGroup modeGroup = new OptionGroup();
        modeGroup.addOption(new Option("j1", "Job 1"));
        modeGroup.addOption(new Option("j2", "Job 2"));
        modeGroup.isRequired();

        final Options options = new Options();
        options.addOptionGroup(modeGroup);
        return options;
    }
    
    private static Options buildSubOptions1() {
        final Options options = new Options();
        Option opt1 = new Option("o", true, "Int Option");
        opt1.setArgName("int");
        opt1.isRequired();
        options.addOption(opt1);
        return options;
    }
    
    private static Options buildSubOptions2() {
        final Options options = new Options();
        Option opt1 = new Option("d", true, "Double Option");
        opt1.setArgName("dbl");
        opt1.isRequired();
        options.addOption(opt1);
        return options;
    }
}
