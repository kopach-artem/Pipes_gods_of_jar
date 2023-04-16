package skeleton;

import exception.MyException;
import java.util.Scanner;

public class Main
{

    static void printOperations()
    {
        System.out.println("\nVálassza ki a kívánt szekvenciát!");
        System.out.println("[1]. Mehcanic repairs pump");
        System.out.println("[2]. Mechanic repairs pipe");
        System.out.println("[3]. Saboteur leaks pipe");
        System.out.println("[4]. Player attach pipe");
        System.out.println("[5]. Player attaches pump");
        System.out.println("[6]. Player detach pipe");
        System.out.println("[7]. Player adjust pump Input");
        System.out.println("[8]. Player adjust pump Output");
        System.out.println("[9]. Player moves to pipe succesfully");
        System.out.println("[10]. Player moves to pipe fail");
        System.out.println("[11]. Player moves to pump");
        System.out.println("[12]. Player moves to cistern");
        System.out.println("[13]. Player moves to mountain spring");
        System.out.println("[14]. Random pump breaking");
        System.out.println("[15]. Collecting water in cistern");
        System.out.println("[0]. Kilépés");
    }
    public static void main(String[] args) throws MyException, InterruptedException {
        DispatcherSkeleton skeleton = new DispatcherSkeleton();
        System.out.println("Üdvözli önt a Gods of jar csapat skeleton programja!");
        printOperations();
        int n;
        Scanner scanner = new Scanner(System.in);
        while ((n = scanner.nextInt()) != 0) {
            switch (n) {
                case 1:
                    System.out.flush();
                    skeleton.MechanicRepairsPump();
                    printOperations();
                    break;
                case 2:
                    System.out.flush();
                    skeleton.MechanicRepairsPipe();
                    printOperations();
                    break;
                case 3:
                    System.out.flush();
                    skeleton.SaboteurLeaksPipe();
                    printOperations();
                    break;
                case 4:
                    System.out.flush();
                    skeleton.PlayerAttachPipe();
                    printOperations();
                    break;
                case 5:
                    System.out.flush();
                    skeleton.PlayerAttachPump();
                    printOperations();
                    break;
                case 6:
                    System.out.flush();
                    skeleton.PlayerDetachPipe();
                    printOperations();
                    break;
                case 7:
                    System.out.flush();
                    skeleton.PlayerAdjustPumpInput();
                    printOperations();
                    break;
                case 8:
                    System.out.flush();
                    skeleton.PlayerAdjustPumpOutput();
                    printOperations();
                    break;
                case 9:
                    System.out.flush();
                    skeleton.PlayerMovesToPipe();
                    printOperations();
                    break;
                case 10:
                    System.out.flush();
                    skeleton.PlayerMovesToPipeFail();
                    printOperations();
                    break;
                case 11:
                    System.out.flush();
                    skeleton.PlayerMovesToPump();
                    printOperations();
                    break;
                case 12:
                    System.out.flush();
                    skeleton.PlayerMovesToCistern();
                    printOperations();
                    break;
                case 13:
                    System.out.flush();
                    skeleton.PlayerMovesToMountainSpring();
                    printOperations();
                    break;
                case 14:
                    System.out.flush();
                    skeleton.RandomPumpBreaking();
                    printOperations();
                    break;
                case 15:
                    System.out.flush();
                    skeleton.CollectingWater();
                    printOperations();
                    break;
            }
        }
    }
}
