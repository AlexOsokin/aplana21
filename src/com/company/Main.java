package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {


        ArrayList<String> arrayListLine = ReadFile();//считываем строки из файла
        if(arrayListLine.size() == 0){
            System.out.println("Файл пуст!");
            return;
        }
        ArrayList<String> arrayList = getWords(arrayListLine);//находим коллекцию слов
        if(arrayList == null){
            System.out.println("Файл не содержит слов!");
            return;
        }
        Collections.sort(arrayList);
        System.out.println("Сортировка по алфавиту: " + arrayList.toString());
        getMax(arrayList);//вывод слова, встречающияся максимум раз
        }

    private static ArrayList<String> ReadFile(){
        ArrayList<String> arrayListLine = null;
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Введите путь к файлу, например /Users/aleksandrosokin/IdeaProjects/aplana2/Read.txt\nили проосто Read.txt, если файл находится в корне: ");
                String path = in.nextLine();
                File file = new File(path);
                Scanner sc = new Scanner(file);
                arrayListLine = new ArrayList<>();
                while (sc.hasNextLine()) {
                    arrayListLine.add(sc.nextLine());
                }
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Такого файла нет!");
            }
        }
        return arrayListLine;
    }
    private static ArrayList<String> getWords(ArrayList<String> arrayListLine){
        ArrayList<String> arrayList = new ArrayList<>();

        Iterator<String> iterator = arrayListLine.iterator();
        String simbol = null;
        boolean non = false;

        while (iterator.hasNext()){
            String m = iterator.next();
            for(int i = 0; i < m.length(); i++){
                Character c  = m.charAt(i);
                if((c >= 'A' && c <= 'z')||(c >= 'А' && c <= 'я')){
                    non = true;
                    if(simbol != null){
                        simbol = simbol + c.toString();
                    }
                    else{
                        simbol = c.toString();
                    }
                    if(i == m.length() - 1){
                        arrayList.add(simbol.toLowerCase());
                    }
                }
                else if(i != 0 && simbol != null){
                    arrayList.add(simbol.toLowerCase());
                    simbol = null;
                }

            }
            simbol = null;
        }
        if(non == false){
            return null;
        }
        return arrayList;
    }
    private static void getMax(ArrayList<String> arrayList){
        String st = arrayList.get(0);
        int i = 0;
        int max = 0;
        ArrayList<String>  arrayList1 = new ArrayList<>();
        Iterator<String> iterator1 = arrayList.iterator();

        while(true){
            if(iterator1.hasNext()) {
                String st1 = iterator1.next();
                if (st1.equals(st)) {
                    i++;
                } else {
                    System.out.println(st + " встречается " + i + " раза");
                    i = 1;
                    st = st1;
                }
                if(max < i){
                    max = i;
                    arrayList1.clear();
                    arrayList1.add(st);
                    continue;
                }
                if(max == i){
                    arrayList1.add(st);
                }
            }
            else{
                System.out.println(st + " встречается " + i + " раза");
                break;
            }
        }
        System.out.println("C максимальной частотой в " + max + " раза встречаются данные слова "+ arrayList1.toString());
    }
}

