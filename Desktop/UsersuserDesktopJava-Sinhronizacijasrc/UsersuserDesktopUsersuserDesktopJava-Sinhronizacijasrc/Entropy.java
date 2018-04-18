package TeorijaNaInformacija;

import com.oracle.tools.packager.Log;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.*;


public class Entropy {

    public static void main(String[] args) throws IOException {

        System.out.println("Влез во системот");
        Scanner scanner = new Scanner(in);
        out.println("За да добиете фрекфенција на еден каракткер во дадениот сигнал изберете 1 ");
        out.println("За да добиете фрекфенција на два  каракткери во дадениот сигнал изберете 2 ");

        int izbor = scanner.nextInt();

        if(izbor == 1 ) {
            out.println("Малите и големи букви се разликуваат!! Внимавајте на влезот ");
            out.println("Внесите збор или реченица по ваш избор ");
            char space = ' ';
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String signal = br.readLine();


            signal.replace(space, 'e');

            out.println("Изберете буква во зборот !");
            char x = scanner.next().charAt(0);
            if(x == ' '){
                x = 'e';
                out.println("Успешно");
            }
            double variable = signal.length();

            // za da izberete koja frekfencija

            char finalX = x;
            double s = signal.chars().filter(num -> num == finalX).count();
            double entropija = s/ variable ;
            out.println("Фрекфенцијата на буквата  " + x + "  " + s / variable);
            out.println("Ентропијата на буквата " + ((-1*entropija*Math.log(entropija))));
            //System.out.println(Math.log(help));
//        System.out.println("Frekfencijata na dadenata variable e  "+ s);

            // System.out.println(countChars(k, c));
        }

        if (izbor == 2 ){

            out.println("Малите и големи букви се разликуваат!! Пазете на влезот ");
            out.println("Внесете реченица или збор по ваш избор! ");
            char space = ' ';
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String signal = br.readLine();
            out.println("Изберете две букви во зборот или реченицата !");
            String word = br.readLine();
            signal.replace(space, ' ');
            word.replace(space, 'e');
            if (word.length()!=2 ) {
                out.println("Лошо внесен параметар");
                out.println("Обидете се повтроно");
                return;
            }
            double x_2  = count(signal,word);

            //  double test =  x_2 /signal.length();
//            if(test>=1) {
//                System.out.println("Фрекфенцијата на буквите  " + word + "  " + "1 " );
//            }
            out.println("Фрекфенцијата на буквите  " + word + "  " +x_2/signal.length());

            double EnthropyPair = x_2/signal.length();
            out.println("Ентропијата на парот изнесува  " + word + "  "+ (-1*EnthropyPair*Math.log(EnthropyPair)));

        }



    }

    public static double count(String text, String find) {
        int index = 0, count = 0, length = find.length();
        while( (index = text.indexOf(find, index)) != -1 ) {
            index += length; count++;
        }
        return count;
    }

}



//    public static int countChars(String input,char find){
//        if(input.indexOf(find) != -1){
//            return  countChars(input.substring(0, input.indexOf(find)), find)+
//                    countChars(input.substring(input.indexOf(find)+1),find) + 1;
//        }
//        else {
//            return 0;
//        }
//
//    }
//
//
//}
