package TeorijaNaInformacija;

import javax.swing.*;
import java.util.Scanner;

public class slucajnoTalkanje {

    public static void main(String[] args) {
        GraphingData test  = new GraphingData();
        double P; // веројатност на настан А

        Scanner scanner = new Scanner(System.in);
        System.out.println("Внесете веројатност на настан А");
         P = scanner.nextDouble();
         if(P>1) {
             System.out.println("Pregolema verojatnost");
                return;
         }
        double Q = 1-P; // веројатност на настан Б
        System.out.println(P +  " " + Q );
        System.out.println("Внесете колку чекори ке трае талкањето");
        int n = scanner.nextInt();
        double [] particlePositionProbability = new double[n];
        int position = 0;
        for(int i =0 ;i<particlePositionProbability.length;i++){
            particlePositionProbability[i]=1;
            // ги ставаме да имаат вредност единица за да можеме да множиме со нив
        }

        int[] positionOfParticleInAxis = new int[n];
        //декла
        for(int i =0;i<n;i++){
            if(P>=Math.random()*1){   // јава документација вели кога множиш со некоја вредност Math.Random  таа формира интервал од [0 до тој број во овој случај 1]
              particlePositionProbability[i] =  particlePositionProbability[i]*P;
                                //добиваме веројатноста да биде настан а
                position++;
                positionOfParticleInAxis[i]=position;
            }
            else{
                particlePositionProbability[i] = particlePositionProbability[i]*Q;
                position--;
                positionOfParticleInAxis[i]=position;
                // веројатност да биде настан Б
            }
        }
        int s =0;
        double[] probabilityArray = new double[n];
        for(int i =0 ;i<n ;i++){

            System.out.println( "Моментално сме во : " + s+ " секунда   || " + particlePositionProbability[i] + " ----> " + positionOfParticleInAxis[i]);
            s++;
        }

        // користење на готова класа за цртање на оски  од јава
        // креирање на објект од таа класа и ја доделуваме да ја нацрта нашава низа од оваа задача
        GraphingData.data=positionOfParticleInAxis;
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GraphingData());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);

    }
}
