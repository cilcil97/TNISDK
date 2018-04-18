package TeorijaNaInformacija;

public class  MarkovChain {



    public static void main(String[] args) {

        // the state transition matrix
        double[][] matricaNaTranzicija = { { 0.311, 0.157, 0.202, 0.062, 0.140, 0.047, 0.016},
                { 0.107, 0.267, 0.227, 0.120, 0.207, 0.052, 0.020},
                { 0.035, 0.101, 0.188, 0.191, 0.357, 0.067, 0.061},
                { 0.021, 0.039, 0.112, 0.212, 0.431, 0.124, 0.061},
                { 0.009, 0.024, 0.075, 0.123, 0.473, 0.171, 0.125},
                { 0.000, 0.103, 0.041, 0.088, 0.391, 0.312, 0.155},
                { 0.000, 0.008, 0.036, 0.083, 0.364, 0.235, 0.274},
                { 0.000, 0.250, 0.250, 0.250 ,0.250, 0.000, 0.000}
        };


        double[][] matricaNaTranzicijaPrimer = { {0.51 , 0 , 0.49 }, { 0.18, 0.12,0.7},{0.32,0.41,0.27} };

        int N = 8;                        // number of states // број на
        int sostojbi = N - 1;                // current state
        int cekori = 0;
        int sostojbiI=N;
        // number of transitions
    String output ;
        // run Markov chain
        while (sostojbi > 0) {

            System.out.print("P{"  +  "j= " +sostojbi+  " | " + "i= "+ (sostojbi-1)+  " " +"\n");
            sostojbiI--;
            cekori++;
            double r = Math.random(); // generira random broj megju eden i nula
            double sum = 0.0;

            // determine next state
            for (int j = 0; j < N; j++) {
                sum += matricaNaTranzicija[sostojbi][j];
                if (r <= sum) {
                    sostojbi = j;
                    break;
                }
            }

        }

        System.out.println("The number of steps =  " + cekori);
    }
}
