package skeleton;

import exception.MyException;
import java.util.Scanner;

public class Main
{

    static void printOperations()
    {
        System.out.println("\nVálassza ki a kívánt szekvenciát!");
        System.out.println("[1]. Mechanic repairs pump");
        System.out.println("[2]. Mechanic repairs pipe");
        System.out.println("[3]. Saboteur leaks pipe");
        System.out.println("[4]. Player attach pipe");
        System.out.println("[5]. Player attaches pump");
        System.out.println("[6]. Player detach pipe");
        System.out.println("[7]. Player adjust pump Input");
        System.out.println("[8]. Player adjust pump Output");
        System.out.println("[9]. Player moves to pipe successfully");
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
                    skeleton.MechanicRepairsPump();
                    printOperations();
                    break;
                case 2:
                    skeleton.MechanicRepairsPipe();
                    printOperations();
                    break;
                case 3:
                    skeleton.SaboteurLeaksPipe();
                    printOperations();
                    break;
                case 4:
                    skeleton.PlayerAttachPipe();
                    printOperations();
                    break;
                case 5:
                    skeleton.PlayerAttachPump();
                    printOperations();
                    break;
                case 6:
                    skeleton.PlayerDetachPipe();
                    printOperations();
                    break;
                case 7:
                    skeleton.PlayerAdjustPumpInput();
                    printOperations();
                    break;
                case 8:
                    skeleton.PlayerAdjustPumpOutput();
                    printOperations();
                    break;
                case 9:
                    skeleton.PlayerMovesToPipe();
                    printOperations();
                    break;
                case 10:
                    skeleton.PlayerMovesToPipeFail();
                    printOperations();
                    break;
                case 11:
                    skeleton.PlayerMovesToPump();
                    printOperations();
                    break;
                case 12:
                    skeleton.PlayerMovesToCistern();
                    printOperations();
                    break;
                case 13:
                    skeleton.PlayerMovesToMountainSpring();
                    printOperations();
                    break;
                case 14:
                    skeleton.RandomPumpBreaking();
                    printOperations();
                    break;
                case 15:
                    skeleton.CollectingWater();
                    printOperations();
                    break;
            }
        }
    }
}
