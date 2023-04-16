package skeleton;

import exception.MyException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws MyException, InterruptedException {
        DispatcherSkeleton skeleton = new DispatcherSkeleton();
        System.out.println("[Üdvözli önt a Gods of jar csapat skeleton programja!]");
        System.out.println("[Válassza ki a kívánt szekvenciát!]");
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
        int n;
        Scanner scanner = new Scanner(System.in);
        while ((n = scanner.nextInt()) != 0) {
            switch (n) {
                case 1:
                    skeleton.MechanicRepairsPump();
                    break;
                case 2:
                    skeleton.MechanicRepairsPipe();
                    break;
                case 3:
                    skeleton.SaboteurLeaksPipe();
                    break;
                case 4:
                    skeleton.PlayerAttachPipe();
                    break;
                case 5:
                    skeleton.PlayerAttachPump();
                    break;
                case 6:
                    skeleton.PlayerDetachPipe();
                    break;
                case 7:
                    skeleton.PlayerAdjustPumpInput();
                    break;
                case 8:
                    skeleton.PlayerAdjustPumpOutput();
                    break;
                case 9:
                    skeleton.PlayerMovesToPipe();
                    break;
                case 10:
                    skeleton.PlayerMovesToPipeFail();
                    break;
                case 11:
                    skeleton.PlayerMovesToPump();
                    break;
                case 12:
                    skeleton.PlayerMovesToCistern();
                    break;
                case 13:
                    skeleton.PlayerMovesToMountainSpring();
                    break;
                case 14:
                    skeleton.RandomPumpBreaking();
                    break;
                case 15:
                    skeleton.CollectingWater();
                    break;
            }

        }
        scanner.close();
    }
}
