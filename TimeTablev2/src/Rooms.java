public class Rooms {

    static int
            LECTURE = 0,
            TUTORIAL = 1,
            PRACTICAL_CSE = 2,
            PRACTICAL_PHY = 3;

    static String[][] rooms = new String[4][];

    Rooms(){
        run();
    }

    static void run() {
        rooms[LECTURE] = new String[]{"LH1", "LH2", "LH3", "LH4"};
        rooms[TUTORIAL] = new String[]{"TR1", "TR2", "TR3", "TR4", "TR5", "TR6", "TR7", "TR8", "TR9", "TR10"};
        rooms[PRACTICAL_CSE] = new String[]{"PC1", "PC2", "PC3", "PC4", "PC5", "PC6"};
        rooms[PRACTICAL_PHY] = new String[]{"PH1", "PH2"};


//    System.out.println(Arrays.toString(rooms));
//        for (String[] a : rooms){
//            System.out.println(Arrays.toString(a));


    }
}
