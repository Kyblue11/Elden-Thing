package game.displays;

import edu.monash.fit2099.engine.displays.Display;

/**
 * Fancy messages used to print the game title
 * Font obtained from: <a href="https://patorjk.com/software/taag/#p=display&f=Georgia11&t=">link</a>
 * Font: Georgia11
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Aaron Lam Kong Yew
 */
public class FancyMessage {

    /**
     * The title of the game.
     */
    public static String TITLE =
                    "`7MM\"\"\"YMM  `7MMF'      `7MM\"\"\"Yb. `7MM\"\"\"YMM  `7MN.   `7MF'    MMP\"\"MM\"\"YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8\"\"\"bgd  \n" +
                    "  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  \n" +
                    "  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  \n" +
                    "  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           \n" +
                    "  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'\n" +
                    "  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  \n" +
                    ".JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `\"bmmmdPY  \n";

    /**
     * The "You Died" message.
     */
    public static String YOU_DIED =
                    "`YMM'   `MM' .g8\"\"8q. `7MMF'   `7MF'    `7MM\"\"\"Yb. `7MMF'`7MM\"\"\"YMM  `7MM\"\"\"Yb.   \n" +
                    "  VMA   ,V .dP'    `YM. MM       M        MM    `Yb. MM    MM    `7    MM    `Yb. \n" +
                    "   VMA ,V  dM'      `MM MM       M        MM     `Mb MM    MM   d      MM     `Mb \n" +
                    "    VMMP   MM        MM MM       M        MM      MM MM    MMmmMM      MM      MM \n" +
                    "     MM    MM.      ,MP MM       M        MM     ,MP MM    MM   Y  ,   MM     ,MP \n" +
                    "     MM    `Mb.    ,dP' YM.     ,M        MM    ,dP' MM    MM     ,M   MM    ,dP' \n" +
                    "   .JMML.    `\"bmmd\"'    `bmmmmd\"'      .JMMmmmdP' .JMML..JMMmmmmMMM .JMMmmmdP'   \n";

    /**
     * Prints the title of the game.
     * Uses Thread.sleep to print the title line by line.
     */
    public static void printTitle() {
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Prints the "You Died" message.
     * Uses Thread.sleep to print the message line by line.
     */
    public static void printYouDied() {
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
