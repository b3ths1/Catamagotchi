package pckTamagotchi;
import java.util.Random;
import java.util.Scanner;

class Tamagotchi {
    public String name;
    public int age;
    public int health;
    public int hunger;
    public int happiness;
    public boolean isAlive;
    public char[][] matrix = new char[5][5];
    public int pr=0;
    public int pc=0;

    public Tamagotchi(String name) {
        this.name = name;
        this.age = 1;
        this.health = 100;
        this.hunger = 0;
        this.happiness = 100;
        this.isAlive = true;
    }

    public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Health: " + health);
        System.out.println("Hunger: " + hunger);
        System.out.println("Happiness: " + happiness);
    }

    public boolean CheckEnemy(){
        if (matrix[pr][pc]=='E'){
            return false;
        } else if (matrix[pr][pc]=='F'){
            return true;
        }
        return false;
    }
    public boolean playMiniGame() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = '-';
            }
        }
        //Player's position
        matrix[pr][pc] = 'P';


        RandomGenEnemies();
        RandomGenFood();
    

        // Display the matrix
        System.out.println("               )\\._.,--....,'``.      \r\n"
        		+ " .b--.        /;   _.. \\   _\\  (`._ ,.\r\n"
        		+ "`=,-,-'~~~   `----(,_..'--(,_..'`-.;.'\n");
        System.out.println("Player - P\r\n"
        		           + "Enemy - E\r\n"
        		           + "Food - F\r\n");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Play the mini-game
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your move (up, down, left, right): ");
            String move = scanner.nextLine();

            // Update player's position
            int newRow = pr;
            int newCol = pc;
            switch (move) {
                case "up":
                    newRow--;
                    break;
                case "down":
                    newRow++;
                    break;
                case "left":
                    newCol--;
                    break;
                case "right":
                    newCol++;
                    break;
                default:
                    System.out.println("Invalid move. Try again.");
                    continue;
            }

            // Check if the new position is valid
            if (newRow < 0 || newRow >= matrix.length || newCol < 0 || newCol >= matrix[newRow].length) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Check if the player reached the food
            if (matrix[newRow][newCol] == 'F') {
                System.out.println("You reached the food!");
                return true;
            }

            // Check if the player encountered an enemy
            if (matrix[newRow][newCol] == 'E') {
                System.out.println("Oh no! You encountered an enemy!");
                health = Math.max(0, health - 20);
            }
             return false;}
            }
            
            public void RandomGenEnemies() {
                Random random = new Random();
                    int enemyRow = random.nextInt(5);
                    int enemyCol = random.nextInt(5);
                    if (matrix[enemyRow][enemyCol] == '-') {
                        matrix[enemyRow][enemyCol] = 'E';
                    } else {
                        RandomGenEnemies();
                    }
                }

            public void RandomGenFood(){
                Random random = new Random();
                int foodRow = random.nextInt(5);
                int foodCol = random.nextInt(5);
                if (matrix[foodRow][foodCol] == '-') {
                    matrix[foodRow][foodCol] = 'F';
                } else RandomGenFood(); //rekursiq :O
            }

        public void feed() {
            boolean success = CheckEnemy();
            if (success) {
                hunger = Math.max(0, hunger - 20);
                System.out.println("You successfully fed " + name + ".");
            } else {
                System.out.println(name + " was not fed due to the unsuccessful mini-game.");
            }
        }

    public void initializeMatrix(char[][] matrix) {
        Random random = new Random();
        int foodRow = random.nextInt(5);
        int foodCol = random.nextInt(5);
        matrix[foodRow][foodCol] = 'F';

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 'F') {
                    matrix[i][j] = '-';
                }
            }
        }
    }

    public void pet() {
        happiness = Math.min(100, happiness + 10);
        System.out.println("             __..--''``---....___   _..._    __\r\n"
        		+ "    /// //_.-'    .-/\";  `        ``<._  ``.''_ `. / // /\r\n"
        		+ "   ///_.-' _..--.'_    \\                    `( ) ) // //\r\n"
        		+ "   / (_..-' // (< _     ;_..__               ; `' / ///\r\n"
        		+ "    / // // //  `-._,_)' // / ``--...____..-' /// / // \r\n"
        		+ "");
        System.out.println(name + " has been petted.");
        age++;
        hunger += 10;
        
    }

    public void sleep() {
        health = Math.min(100, health + 10);
        
        System.out.println("                      Z\r\n"
        		+"                          Z\r\n"
        		+"                       Z\r\n"
        		+"               /\\____/\\    __\r\n"
        		+ "             .'  \"\"\"\"  `,-'  `--.__\r\n"
        		+ "        __,- :   -  -  ;  \" ::     `-. -.__\r\n"
        		+ "     ,-sssss `._   x _,'\"     ,'~~~::`.sssss-.\r\n"
        		+ "    |ssssss ,' ,_`--'_    __,' ::  `  `.ssssss|\r\n"
        		+ "   |sssssss `-._____~ `,,'_______,---_;; ssssss|\r\n"
        		+ "    |ssssssssss     `--'~{__   ____   ,'ssssss|\r\n"
        		+ "     `-ssssssssssssssssss ~~~~~~~~~~~~ ssss.-'\r\n"
        		+ "          `---.sssssssssssssssssssss.---'           ");
        System.out.println(name + " went to sleep.");
        age++;
        hunger += 10;
        happiness -= 10;
    }

    public void play() {
        hunger = Math.max(0, hunger + 10);
        happiness = Math.min(100, happiness + 10);
        RandomPrinting2();
        System.out.println(name + " played.");
        age++;
    }
    
    public void RandomPrinting2() {
            Random random = new Random();
            int randomNumber = random.nextInt(2);
            if (randomNumber == 0) {
                System.out.println("                     __     __,\r\n"
                		+ "                      \\,`~\"~` /\r\n"
                		+ "      .-=-.           /    . .\\\r\n"
                		+ "     / .-. \\          {  =    Y}=\r\n"
                		+ "    (_/   \\ \\          \\      / \r\n"
                		+ "           \\ \\        _/`'`'`b\r\n"
                		+ "            \\ `.__.-'`        \\-._\r\n"
                		+ "             |            '.__ `'-;_\r\n"
                		+ "             |            _.' `'-.__)\r\n"
                		+ "              \\    ;_..--'/     //  \\\r\n"
                		+ "              |   /  /   |     //    |\r\n"
                		+ "              \\  \\ \\__)   \\   //    /\r\n"
                		+ "               \\__)        './/   .'\r\n"
                		+ "                             `'-'`" );
            } else {
                System.out.println("                   .-o=o-.\r\n"
                		+ "               ,  /=o=o=o=\\ .--.\r\n"
                		+ "              _|\\|=o=O=o=O=|    \\\r\n"
                		+ "          __.'  a`\\=o=o=o=(`\\   /\r\n"
                		+ "          '.   a 4/`|.-\"\"'`\\ \\ ;'`)   .---.\r\n"
                		+ "            \\   .'  /   .--'  |_.'   / .-._)\r\n"
                		+ "             `)  _.'   /     /`-.__.' /\r\n"
                		+ "              `'-.____;     /'-.___.-'\r\n"
                		+ "                       `\"\"\"`");
            }
        }

    public void update() {
        // Check Tamagotchi's health
        if (hunger == 100 || happiness==0 || age==100) {
            isAlive = false;
            System.out.println("   \r\n"
            		+ "                               -|-\r\n"
            		+ "                                |\r\n"
            		+ "                            .-'~~~`-.\r\n"
            		+ "                          .'         `.\r\n"
            		+ "                          |  R  I  P  |\r\n"
            		+ "                          |           |\r\n"
            		+ "                          |           |\r\n"
            		+ "                        \\\\|           |//\r\n"
            		+ "   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\r\n"
            		+ "                       |\\      _,,,---,,_\r\n"
            		+ "                      /,`.-'`'    -.  ;-;;,_\r\n"
            		+ "                     |,4-  ) )-,_..;\\ (  `'-'\r\n"
            		+ "                    '---''(_/--'  `-'\\_)  ");
            System.out.println(name + " has died.");
        }
    }

    public boolean isAlive() {
        return isAlive;
    }
}

class TamagotchiGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" CATAMAGOTCHI       \r\n"
        		+ "                \\`*-.                   \r\n"
        		+ "                 )  _`-.                \r\n"
        		+ "                .  : `. .               \r\n"
        		+ "                : _   '  \\              \r\n"
        		+ "                ; *` _.   `*-._         \r\n"
        		+ "                `-.-'          `-.      \r\n"
        		+ "                  ;       `       `.    \r\n"
        		+ "                  :.       .        \\   \r\n"
        		+ "                  . \\  .   :   .-'   .  \r\n"
        		+ "                  '  `+.;  ;  '      :  \r\n"
        		+ "                  :  '  |    ;       ;-.\r\n"
        		+ "                  ; '   : :`-:     _.`* ;\r\n"
        		+ "               .*' /  .*' ; .*`- +'  `*'\r\n"
        		+ "               `*-*   `*-*  `*-*'       ");
        // Set name for Tamagotchi
        System.out.println("Enter a name for your cat:");
        String name = scanner.nextLine();

        Tamagotchi tamagotchi = new Tamagotchi(name);

        // Game loop
        while (tamagotchi.isAlive()) {
            tamagotchi.displayStats();

            System.out.println("Select an action: (feed, pet, sleep, play)");
            String action = scanner.nextLine();

            switch (action) {
                case "feed":
                    tamagotchi.feed();
                    break;
                case "pet":
                    tamagotchi.pet();
                    break;
                case "sleep":
                    tamagotchi.sleep();
                    break;
                case "play":
                    tamagotchi.play();
                    break;
                default:
                    System.out.println("Invalid action. Try again.");
                    break;
            }

            tamagotchi.update();
            System.out.println();
        }

        System.out.println("GAME OVER!");
    }
}
