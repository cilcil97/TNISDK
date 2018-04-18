package TeorijaNaInformacija;

import java.util.*;
import java.util.stream.IntStream;

public class BinarySimetricChannel {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        // внесување на веројатноста на грешка
        System.out.println("Внесете ја веројатноста за грешка");
      double P =  scanner.nextDouble();
      if(P>=1){
          System.out.println("Преголема веројатност");
          return;
      }
        System.out.println("Внесете го бројот на елементи од низата");
      int n = scanner.nextInt();
      double[]  niza = new double[n];
        System.out.println("Внесете ги елементите од низата");
      for(int i =0 ;i <niza.length;i++){
            niza[i]=scanner.nextInt();
      }

     OptionalDouble result = Arrays.stream(niza).filter(x -> x!=0 && x!=1).findFirst();

         ///  throw  new NumberFormatException(result + "IMA RAZLICEN ELEMENT OD NULA I EDINICA");
      int k = (int) IntStream.range(0,1).count();
       // System.out.println(k);
       boolean found = Arrays.stream(niza).anyMatch(x-> x>1);
       if(found){
        throw new   IllegalArgumentException("СТЕ ВНЕЛЕ ЕЛЕМЕНТ ВО НИЗАТА ШТО НЕ Е 1 ИЛИ 0 ");

       }


           // бројач
           int counter = 0;
           for (int i = 0; i < niza.length; i++) {
               // АКО РАНДОМ СЕ ПРЕНЕСУВА СИМБОЛ И ВЕРОЈАТНОСТА ТОЈ ДА СЕ  ПРЕНЕСЕ Е ПОГОЛЕМ
               // ОД ВЕРОЈАТНОСТ ЗА ГРЕШКА ГЕНЕРИРАЈ ГО ПРЕНОСОТ НА КОДОТ

               if ((Math.random() * 1) > P) {

                   if (niza[i] == 1) {
                       // ГЕНЕРИРАЈ ПРЕНСУВАЊЕ
                       System.out.println("1 ---> " + niza[i]);
                   } else if (niza[i] == 0) {
                       // ГЕНЕРИРАЈ ПРЕНЕСУВАЊЕ
                       System.out.println("0 --> " + niza[i]);
                   }
               } else {
                   // АКО СЕ СЛУЧИЛА ГРЕШКА БРОЈ ГИ ГРЕШКИТЕ
                   counter++;
                   if (niza[i] == 1) {
                       // ГЕНЕРИРАЈ ПРЕНЕСУВАЊЕ
                       System.out.println("1 --> 0 [ERROR]");
                   } else {
                       // ГЕНЕРИРАЈ ПРЕНЕСУВАЊЕ
                       System.out.println("0 --> 1 [ERROR]");
                   }
               }

           }
           System.out.println(counter);
       }



}
