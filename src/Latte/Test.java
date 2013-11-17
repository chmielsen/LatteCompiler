package Latte;

import FunctionRecognizer.ProgramFR;
import Latte.Absyn.Program;
import Utils.SemanticAnalysis;
import Utils.State;
import VisibilityChecker.Errors.SemanticError;
import VisibilityChecker.ProgramVC;

import java.io.*;
import java.net.URI;
import java.util.*;

public class Test
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final String GOOD_EXAMPLES_PATH = "examples/good/";
    private static final String BAD_EXAMPLES_PATH = "examples/bad/";

    static FilenameFilter latFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".lat");
        }
    };


    public static void main(String args[]) throws Exception
    {
        try {
            if (args.length > 0) {
                analyzeFile(new File(BAD_EXAMPLES_PATH + args[0] + ".lat"));
            } else {
                File examplesDir= new File(GOOD_EXAMPLES_PATH);
                if (examplesDir.isDirectory()) {
                    File[] examples = examplesDir.listFiles(latFilter);
                    Arrays.sort(examples);
                    for (File example : examples) {
                        analyzeFile(example);
                    }
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Error: File not found: " + args[0]);
            System.exit(1);
        }
        // p = new parser(l);
        /* The default parser is the first-defined entry point. */
        /* You may want to change this. Other options are: */
        /*  */
        /*try {
            Latte.Absyn.Program parse_tree = p.pProgram();
            System.out.println();
            System.out.println("Parse Succesful!");
            System.out.println();
            System.out.println("[Abstract Syntax]");
            System.out.println();
            System.out.println(PrettyPrinter.show(parse_tree));
            System.out.println();
            System.out.println("[Linearized Tree]");
            System.out.println();
            System.out.println(PrettyPrinter.print(parse_tree));

            System.out.println("[Declared Functions]");
            System.out.println(parse_tree.accept(new ProgramFR(), null));
        }
        catch(Throwable e)
        {
            System.out.println("At line " + String.valueOf(l.line_num()) + ", near \"" + l.buff() + "\" :");
            System.out.println("     " + e.getMessage());
            System.exit(1);
        }*/
    }

    static void analyzeFile(File file) throws FileNotFoundException{
        Yylex lexer = new Yylex(new FileInputStream(file));
        try {
            parser parsereiro = new parser(lexer);
            Program program = parsereiro.pProgram();
            State state = new State();
            SemanticAnalysis fnAnalysis = program.accept(new ProgramFR(), state);
            if (fnAnalysis.hasErrors()) {
                System.out.print("[GOOD] " + file.getName() + " : ");
                List<SemanticError> errors = fnAnalysis.getErrors();
                for (SemanticError error : errors) {
                    System.out.println(error.toString());
                }
            }
            System.out.println("[INFO] " + file.getName() + " " + state.getDeclaredFunctions());

            // Start analyzing functions
            SemanticAnalysis programAnalysis = program.accept(new ProgramVC(), state);

            if (programAnalysis.hasErrors()) {
                System.out.print("[FATAL] " + file.getName() + " : ");
                List<SemanticError> errors = programAnalysis.getErrors();
                for (SemanticError error : errors) {
                    System.out.println(error.toString());
                }
            }
        } catch(Throwable e) {
            System.out.println(file.getName() + " : " + e.toString());
            if (e instanceof NullPointerException)
                e.printStackTrace();
            // System.out.print(file.getName() + " : At line " + String.valueOf(lexer.line_num()) + ", near \"" + lexer.buff() + "\" : ");
            // System.out.println("     " + e.getMessage());
        }
        System.out.print("\n");

    }
}
