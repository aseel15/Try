package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static City[] cities;
    static HashMap<String, City> citiesHash;
    public static void main(String[] args) {
        // write your code here
        File file = new File("towns.csv");
        File file2 = new File ("roads-2.csv");
        int numOfVertex = 0;
        int numOfEdges = 0;
        BufferedReader scans;

            try {
                scans = new BufferedReader(new FileReader(file));
                String str = scans.readLine();
                //String[] strs = str.split("");
                numOfVertex = Integer.parseInt(str);


                cities = new City[numOfVertex];


                for (int counter = 0; counter < numOfVertex; counter++) {
                    String string = scans.readLine();
                    String[] patterns = string.split(",");
                    cities[counter] = new City(patterns[0], Integer.parseInt(patterns[1]), Integer.parseInt(patterns[2]));
                }
                scans.close();

                scans = new BufferedReader(new FileReader(file2));
                numOfEdges = Integer.parseInt(scans.readLine());
                citiesHash = new HashMap<String, City>();
                for (int i = 0; i < numOfVertex; i++) {
                    citiesHash.put(cities[i].cityName, cities[i]);
                }
                for (int m = 0; m < numOfEdges; m++) {
                    String string = scans.readLine();
                    String[] patterns = string.split(",");
                    City j = citiesHash.get(patterns[0]);//read the first city of the line
                    City k = citiesHash.get(patterns[1]);//read the second city of the line


                    City adjS = new City();
                    adjS.cityName = j.cityName;
                    adjS.x = j.x;
                    adjS.y = j.y;

                    adjS.dist = Double.parseDouble(patterns[2]);

                    City adjD = new City();
                    adjD.cityName = k.cityName;
                    adjD.x = k.x;
                    adjD.y = k.y;

                    adjD.dist = Double.parseDouble(patterns[2]);

                    j.addBranch(adjD);//add the destination to the source adjacent
                    k.addBranch(adjS);//add the source to the destination adjacent



                }
                scans.close();
                Scanner s=new Scanner(System.in);
                while (true) {
                    String l = s.next();
                    String ll = s.next();
                    Algo algo = new Algo(cities);
                    //algo.SLD(citiesHash.get("b"));
                    algo.shortestPath(citiesHash.get(l), citiesHash.get(ll));
                    System.out.println(algo.printPathSrc2Dest(citiesHash.get(ll)));
                }
              /* for (int i = 0; i< cities.length; i++){
                    System.out.println("the h of "+ cities[i].+"   "+cities[i].f+"\n");
                    //System.out.println(cities[i].citiesBranches);
                }*/
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



    }
}
