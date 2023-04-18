import java.io.*;

import java.util.ArrayList;



public class Ancestor {



    static String A;

    static String B;

    String m, off;



    private static int length =0;

    static ArrayList<String> AncestorList = new ArrayList<>();

    private static String[] daughter;

    private static String[] mother ;





    private static String find_mother(String firstCommonAncestor)

    {

        for (int i = 0;i < daughter.length;i++)

        {

            if (daughter[i].equals(firstCommonAncestor))

            {

                return mother[i];

            }

        }

        return "";

    }





    private static int is_ancestor(String firstCommonAncestor1, String firstCommonAncestor2)

    {

        int counter = 0;

        while (!firstCommonAncestor2.equals(""))

        {

            if (firstCommonAncestor1.equals(firstCommonAncestor2))

            {

                return counter;

            }

            firstCommonAncestor2 = find_mother(firstCommonAncestor2);

            counter++;

        }

        return -1;

    }



    public static int makeGuess() {







        String firstCommonAncestor = A;

        int b = 0;

        while (!firstCommonAncestor.equals(""))

        {

            if (is_ancestor(firstCommonAncestor, B) != -1)

            {

                break;

            }

            firstCommonAncestor = find_mother(firstCommonAncestor);

            b++;

        }





        if (firstCommonAncestor.equals(""))

        {

            System.out.print("Not related");

        }

        //int x = is_ancestor(A,B);

        else if(is_ancestor(firstCommonAncestor,B) == 1 && b==1) {

            System.out.println("Siblings");

        }

        else if (is_ancestor(firstCommonAncestor,B) > 1 && b>1) {

            System.out.println("cousins");

        }



        else {





            int x = is_ancestor(A,B);

            int y = is_ancestor(B,A);

            if ( x != -1) {

                String s = (A + " is the " );

                for(int i =1; i<x; i++) {

                    if(i== x -1) {

                        s+=" grand ";

                        break;

                    }

                    s+= " great ";

                }

                s+= " mother of " + B;

                System.out.println(s);

                System.exit(0);

            }



            else if ( y != -1) {

                String s = (B + " is the " );

                for(int i =1; i<y; i++) {

                    if(i== y -1) {

                        s+=" grand ";

                        break;

                    }

                    s+= " great ";

                }

                s+= " mother of " + A;

                System.out.println(s);

                System.exit(0);

            }



            //AUNTS



            if(is_ancestor(firstCommonAncestor,B) > 1) {

                String s1 = A + " is the ";



                for(int i =0; i<is_ancestor(firstCommonAncestor,B) -1; i++ ) {

                    if(i == is_ancestor(firstCommonAncestor,B) -2) {

                        s1+=" aunt ";

                        break;

                    }

                    s1+= " great";



                }



                System.out.print(s1 + " of " + B);



            }

            else if (is_ancestor(firstCommonAncestor,A) > 1){



                String firstCommonAncestor1 = B;

                int b1 = 0;

                while (!firstCommonAncestor.equals(""))

                {

                    if (is_ancestor(firstCommonAncestor1, A) != -1)

                    {

                        break;

                    }

                    firstCommonAncestor1 = find_mother(firstCommonAncestor1);

                    b1++;

                }







                String s1 = B + " is the ";



                for(int i =0; i< is_ancestor(firstCommonAncestor1,A)-1; i++ ) {

                    if(i == is_ancestor(firstCommonAncestor1,A)-2) {

                        s1+=" aunt ";

                        break;

                    }

                    s1+= " great";



                }



                System.out.print(s1 + " of " + A);



            }



        }

        return 0;



    }









    public Ancestor(String mother, String offspring) {

        m = mother;

        off = offspring;

    }



    public static void main(String[] args)throws Exception

    {

        // We need to provide file path as the parameter:

        // double backquote is to avoid compiler interpret words

        // like \test as \t (ie. as a escape sequence)

        File file = new File("family.in");



        BufferedReader br = new BufferedReader(new FileReader(file));



        String st;

        while ((st = br.readLine()) != null)

            AncestorList.add(st);





        length = AncestorList.size() -1;



        String s = AncestorList.get(0);

        s = s.substring(s.indexOf(' ')+1);

        A = s.substring(0, s.indexOf(' '));

        B = s.substring(s.indexOf(" ")+1);

        //System.out.println(A + "-" + B + "-");

        //System.out.println(AncestorList.size());



        daughter =  new String[length];

        mother =  new String[length];





        for(int i =1; i<AncestorList.size(); i++) {

            String s1 = AncestorList.get(i);



            String mother1 = s1.substring(0,s1.indexOf(' '));

            String son = s1.substring(s1.indexOf(' ') +1);



            daughter[i-1] = son;

            mother[i-1] = mother1;

        }





        makeGuess();



    }

}