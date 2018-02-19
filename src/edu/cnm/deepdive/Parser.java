package edu.cnm.deepdive;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parser {

  private Options options;
  public Parser(){
    options = new Options();
    Option option = Option.builder("l")
                      .longOpt("length")
                      .hasArg(true)
                      .argName("words")
                      .numberOfArgs(1)
                      .optionalArg(false)
                      .required(false)
                      .type(Number.class)
                      .desc("numbers of words in passphrase")
                      .build(); //return the built to option
    options.addOption(option);
    option = Option.builder("?")
                  .longOpt("help")
                  .hasArg(false)
                  .required(false)
                  .desc("display usages")
                  .build();
    options.addOption(option);
    option = Option.builder("c")
                  .longOpt("command")
                  .numberOfArgs(1)
                  .argName("operation")
                  .hasArg(true)
                  .optionalArg(false)
                  .required(false)
                  .type(String.class)
                  .desc("operation to perform")
                  .build();
    options.addOption(option);
    option = Option.builder("f")
        .longOpt("for")
        .numberOfArgs(1)
        .argName("indentifier")
        .hasArg(true)
        .optionalArg(false)
        .required(false)
        .type(String.class)
        .desc("unique indentifier for credential")
        .build();
    options.addOption(option);
    option = Option.builder("u")
        .longOpt("user")
        .numberOfArgs(1)
        .argName("user name")
        .hasArg(true)
        .optionalArg(false)
        .required(false)
        .type(String.class)
        .desc("user name for credential")
        .build();
    options.addOption(option);
    option = Option.builder("p")
        .longOpt("passphrase")
        .numberOfArgs(1)
        .argName("password/phrase")
        .hasArg(true)
        .optionalArg(false)
        .required(false)
        .type(String.class)
        .desc("password/phrase for credential")
        .build();
    options.addOption(option);
    option = Option.builder("g")
        .longOpt("generate")
        .hasArg(false)
        .required(false)
        .desc("generate a random passhrase")
        .build();
    options.addOption(option);
    option = Option.builder("i")
        .longOpt("info")
        .numberOfArgs(1)
        .argName("additional information")
        .hasArg(true)
        .optionalArg(false)
        .required(false)
        .type(String.class)
        .desc("freeform information (if space are include, must surround by double quotes)")
        .build();
    options.addOption(option);
    option = Option.builder("t")
        .longOpt("tags")
        .argName("category tags")
        .hasArgs()
     //   .optionalArg(true)
        .numberOfArgs(Option.UNINITIALIZED)
        .required(false)
        .type(String.class)
        .desc("space-delimited list of categories")
        .build();
    options.addOption(option);
  }
   public Map<String, Object> parse(String[] args) throws ParseException {
     CommandLineParser parser = new DefaultParser();
     CommandLine cmd = parser.parse(options, args);
     if (cmd.hasOption("?")){
       HelpFormatter formatter = new HelpFormatter();
       formatter.printHelp("java edu.cnm.deepdive.Main [options]", options);
       return null;
     }
     HashMap<String, Object> optionsSpecified = new HashMap<>();
     for (Option option : cmd.getOptions()) {
       Object value = cmd.getParsedOptionValue(option.getOpt()); //look for value to agree
       optionsSpecified.put(option.getOpt(), value);
     }
     validate(optionsSpecified);
     return optionsSpecified;
   }

   private void validate(Map<String, Object> options){
    //TODO 
   }

   public static class InvalidOptionCombination extends ParseException {

     public InvalidOptionCombination(String message) {
       super(message);
     }
   }

}
