package duke.ui;

import duke.Tasks.Storage;

import java.util.Scanner;
/**
 * The main driver class of the chatbot, called by notGPT
 */
public class Parser {
    /**
     * Runs the entire program, reading user input and deciding what to do
     *
     * @param scanner system input from command line
     */
    public static void parse(Scanner scanner) {
        Storage storage = new Storage();
        String command;
        String text ="";
        String input;
        do {
            input = scanner.nextLine();
            String[] parts = input.split("\\s+", 2);
            command = parts[0].toLowerCase();
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                text = parts[1];
            }
            switch (command) {
            case "list":
                    if (storage.size()>0) System.out.println(storage);
                    else System.out.println("it's empty... what do u want me to list? :(");
                    break;
                case "bye":
                    System.out.println("It's finally over... *yawn*\nI'm heading to bed\nExiting the program...");
                    break;
                case "mark":
                    int i;
                    try {
                        i = Integer.valueOf(text);
                    } catch (NumberFormatException e) {
                        System.out.println("sorry bud that ain't a number\ni don't know which task u're referring to...");
                        break;
                    }
                    if(0 < i && i <= storage.size()) {
                        storage.mark(i);
                        System.out.println(String.format("marked %s as completed\nuse \"list\" to see changes",i));
                    }else {
                        System.out.println("that number isn't a valid task dude...");
                        System.out.println("it has to be from 1 to " + storage.size());
                    }
                    break;
                case "unmark":
                    try {
                        i = Integer.valueOf(text);
                    } catch (NumberFormatException e) {
                        System.out.println("sorry bud that ain't a number\ni don't know which task u're referring to...");
                        break;
                    }
                    if(0 < i && i <= storage.size()) {
                        storage.unmark(i);
                        System.out.println(String.format("marked %s as uncompleted\nuse \"list\" to see changes",i));
                    }else {
                        System.out.println("that number isn't a valid task dude...");
                        System.out.println("it has to be from 1 to " + storage.size());
                    }
                    break;
                    case "todo":
                    if(!text.isEmpty()){
                        storage.todo(text);
                        System.out.println("Added " + '\"'+ text + "\"" + " as a new task I guess");
                        System.out.println(String.format("you have %s tasks now",storage.size()));
                    } else System.out.println("bruh? type something to add I'm not adding a blank...");
                break;
                case "event":
                    if(!text.isEmpty()){
                        try{
                            if (text.contains("/from") && text.contains("/to")) {
                                storage.event(text);
                                System.out.println("Wow " + '\"' + text + "\"" + " is an event in your life huh?");
                                System.out.println(String.format("you have %s tasks now", storage.size()));
                                break;
                            } System.out.println("The description of an event must include '/from' and '/to' :/");
                        } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                    } else System.out.println("bruh? type something to add I'm not adding a blank...");
                break;
                case "deadline":
                    if(!text.isEmpty()){
                        try{
                            if (text.contains("/by")) {
                                storage.deadline(text);
                                System.out.println("lol " + '\"' + text + "\"" + " is a new deadline, better finish it quick...");
                                System.out.println(String.format("you have %s tasks now", storage.size()));
                                break;
                            }
                            System.out.println("The description of a deadline must include '/by' :/");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

                    } else System.out.println("bruh? type something to add I'm not adding a blank...");
                    break;
                case "delete":
                    try {
                        i = Integer.valueOf(text);
                    } catch (NumberFormatException e) {
                        System.out.println("sorry bud that ain't a number\ni don't know which task u're referring to...");
                        break;
                    }
                    if(0 < i && i <= storage.size()) {
                        storage.delete(i);
                        System.out.println(String.format("deleted %s\nuse \"list\" to see changes",i));
                    }else {
                        System.out.println("that number isn't a valid task dude...");
                        System.out.println("it has to be from 1 to " + storage.size());
                    }
                    break;
                default:
                    System.out.println("what? type an actual command pls...");
            }
            text ="";
            Notgpt.lnDiv();
        } while (!command.equals("bye"));
        scanner.close();
    }
}