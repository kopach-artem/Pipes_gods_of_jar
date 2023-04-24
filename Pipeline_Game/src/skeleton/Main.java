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
        System.out.println("[3]. Player leaks pipe");
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
        System.out.println("[16]. Player moves to a slippery Pipe");
        System.out.println("[17]. Player tries to move from a sticky Pipe");
        System.out.println("[18]. Player fails to leak pipe due to cooldown");
        System.out.println("[0]. Kilépés");
    }
    
    /** 
     * @param args
     * @throws MyException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws MyException, InterruptedException {
        DispatcherSkeleton skeleton = new DispatcherSkeleton();
        System.out.println("Üdvözli önt a Gods of jar csapat skeleton programja!");
        printOperations();
        int n;
        Scanner scanner = new Scanner(System.in);
        while ((n = scanner.nextInt()) != 0) {
            switch (n) {
                case 1:
                    skeleton.MechanicRepairsPump(); // ඞ
                    break;
                case 2:
                    skeleton.MechanicRepairsPipe(); // ඞ
                    break;
                case 3:
                    skeleton.PlayerLeaksPipe(); // ඞ
                    break;
                case 4:
                    skeleton.PlayerAttachPipe(); // ඞ
                    break;
                case 5:
                    skeleton.PlayerAttachPump(); // ඞ
                    break;
                case 6:
                    skeleton.PlayerDetachPipe();
                    break;
                case 7:
                    skeleton.PlayerAdjustPumpInput(); // ඞ
                    break;
                case 8:
                    skeleton.PlayerAdjustPumpOutput(); // ඞ
                    break;
                case 9:
                    skeleton.PlayerMovesToPipe(); // ඞ
                    break;
                case 10:
                    skeleton.PlayerMovesToPipeFail(); // ඞ
                    break;
                case 11:
                    skeleton.PlayerMovesToPump(); // ඞ
                    break;
                case 12:
                    skeleton.PlayerMovesToCistern(); // ඞ
                    break;
                case 13:
                    skeleton.PlayerMovesToMountainSpring(); // ඞ
                    break;
                case 14:
                    skeleton.RandomPumpBreaking(); // ඞ
                    break;
                case 15:
                    skeleton.CollectingWater(); // ඞ
                    break;
                case 16:
                    skeleton.PlayerMovesToSlipperyPipe(); // ඞ
                    break;
                case 17:
                    skeleton.PlayerTriesToMoveFromStickyPipe(); // ඞ
                    break;
                case 18:
                    skeleton.PlayerLeaksPipeFailDueToCooldown(); // ඞ
                    break;
            }
        }
        scanner.close();
    }
}
