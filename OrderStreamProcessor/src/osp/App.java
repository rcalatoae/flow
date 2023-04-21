package osp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import osp.VolumeCalculator.CryptoVolumeStrategy;
import osp.handler.InputHandler;
import osp.handler.OutputHandler;
import osp.handler.StandardInputHandler;
import osp.handler.StandardOutputHandler;
import osp.models.TradeRecorder;
import osp.service.OrderProcessor;

public class App {
    public static void main(String[] args) throws Exception {
        OutputHandler outputHandler = new StandardOutputHandler();
        InputHandler inputHandler = new StandardInputHandler();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TradeRecorder tradeRecorder = new TradeRecorder();
        OrderProcessor orderProcessor = new OrderProcessor(tradeRecorder);
        String line;

        Scanner in = new Scanner(System.in);

        printMenu();

        boolean quit = false;

        while(!quit) {
            int action = in.nextInt();
            in.nextLine();
            switch (action) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter order updates:");
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        if (line.length() == 0) break;
                        sb.append(line + '\n');
                    }
                    if (sb.toString() != "")
                    inputHandler.handleInput(sb.toString(), orderProcessor);
                    printMenu();
                    break;
                case 2:
                    outputHandler.handleTrade(tradeRecorder);
                    printMenu();
                    break;
                case 3:
                    tradeRecorder.getVolume(new CryptoVolumeStrategy());
                    printMenu();
                    break;
                default:
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("Available actions:");
        System.out.println("0 - To quit\n" +
                            "1 - Add order updates\n" +
                            "2 - Generate list of trades\n" +
                            "3 - Volume calculation");
    }
}
