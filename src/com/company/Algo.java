package com.company;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Algo {
    City[]cities;
    Node []nodes;
    HashMap<String, Node>hash;
    HashMap<String, City>hashCities;
    PriorityQueue<Node>q;
    String str="";

    public Algo(City[]cities1){
        this.cities=new City[cities1.length];
        this.hash = new HashMap<String, Node>();
        this.hashCities=new HashMap<String, City>();
        this.nodes=new Node[cities1.length];
        this.q = new PriorityQueue<Node>(cities1.length, new Node());

        for(int i=0;i< cities.length;i++){
            nodes[i]=new Node();
            nodes[i].cityNode=cities1[i];
            nodes[i].visited=false;
            nodes[i].f=Double.MAX_VALUE;
            nodes[i].path=null;


            hash.put(cities1[i].cityName, nodes[i]);

        }

    }
    public double SLD(City src, City dest){

            double s=Math.sqrt(Math.pow(src.x-dest.x, 2)+Math.pow(src.y- dest.y, 2));
            //System.out.println(city.cityNode.cityName+" the h = "+city.h);


        return s;
    }
   public void shortestPath(City src, City dest){
        Node srcNode=hash.get(src.cityName);
        //SLD(dest);
       System.out.println("the source = "+srcNode.cityNode.cityName);
        srcNode.f=0;
        q.add(srcNode);
        while(!q.isEmpty()){

            Node srcCity=q.poll();


                if(!srcCity.visited){
                    srcCity.visited=true;

                        if(srcCity.cityNode.cityName.equals(dest.cityName)){

                            break;
                        }
                        //System.out.println("the poped city "+srcCity.cityNode.cityName);

                    for (int i=0;i< srcCity.cityNode.citiesBranches.size();i++) {
                        Node temp=hash.get(srcCity.cityNode.citiesBranches.get(i).cityName);
                        if(!temp.visited){
                            temp.g=(srcCity.cityNode.citiesBranches.get(i).dist+ srcCity.g);
                            temp.h=SLD(temp.cityNode, srcCity.cityNode);

                            if(temp.f>=(temp.g+ temp.h)){

                                temp.f=temp.g+ temp.h;
                                temp.path=srcCity.cityNode;
                                q.add(temp);
                            }
                        }
                    }
                }
                else srcCity=q.poll();

        }


    }
    public String printPathSrc2Dest(City destination){
        if(hash.get(destination.cityName).path!=null){
            printPathSrc2Dest(hash.get(destination.cityName).path);
            str+="⬇\n";
        }
        str+=hash.get(destination.cityName).cityNode.cityName+"\n";
        return str;

    }

}
