import java.io.*;
import java.net.*;
import java.util.*;


public class Assignment8 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //1
        System.out.println("Filename: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        FileCounter counter = new FileCounter();
        FileReader reader;
        try {
            reader = new FileReader(name);
            Scanner fileIn = new Scanner(reader);
            counter.read(fileIn);
            fileIn.close();
            System.out.println("Characters: " + counter.getCharacterCount());
            System.out.println("Words: " + counter.getWordCount());
            System.out.println("Lines : " + counter.getLineCount());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }



        //2
        LyricAnalyzer la = new LyricAnalyzer();
        File file = new File("c:\\lyric1.txt");
        la.read(file);
        la.displayWords();
        la.writeLyrics(new File("c:\\lyric1-1.txt"));
        System.out.println("the total number of unique words:"+la.count());
        System.out.println("the most frequently:"+la.mostFrequentWord());

        //3

        File file1 = new File("c:\\vehicles.txt");
        ArrayList<Vehicle> vehicles = MyJson.readAndGetVehicles(file1);
        String s = MyJson.getJsonString(vehicles);
        System.out.println(s);
        MyJson.writeToFile(s, file1.getParent()+"vehicles-json.txt");
        MyJson.writeToOriginalFile("c:\\vehicles-json.txt", "c:\\vehicles-or.txt");

    }

}

class FileCounter{

    private int characterCount, wordCount, lineCount;
    public int getCharacterCount()
    {
        return characterCount;
    }
    public int getWordCount()
    {
        return wordCount;
    }
    public int getLineCount()
    {
        return lineCount;
    }

    public FileCounter(){
    }

    /**
     Processes an input source and adds its character, word, and line
     counts to the respective variables.
     @param in the scanner to process
     */
    public  void read(Scanner in) throws IOException {
        String str;

        while(in.hasNextLine())
        {
            str = in.nextLine().trim();
            if(str.length()>0)
            {
                lineCount++;
                String[] words = str.split("[^a-zA-Z]");
                wordCount+=words.length;
                characterCount+= str.replaceAll(" ", "").length();
            }
        }
    }
}

class LyricAnalyzer
{
    int index = 1;
    private HashMap<String, ArrayList<Integer>> map;
    LyricAnalyzer()
    {
        map = new HashMap<String,ArrayList<Integer>>();
    }
    public void read(File file)
    {
        FileReader fr=null;
        BufferedReader br=null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null&&(line=line.trim()).length()>0)
            {
                String[] words = line.split("[^a-zA-Z]");
                for(int i=0;i<words.length;i++)
                {
                    if(i==words.length-1)
                        index =-index;
                    add(words[i],index);
                    if(index<0)
                        index = -index;
                    index++;
                }

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                br.close();
                fr.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void add(String lyricWord, int wordPosition)
    {
        if(map.containsKey(lyricWord))
        {
            ArrayList<Integer> al = map.get(lyricWord);
            al.add(wordPosition);
        }
        else
        {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(wordPosition);
            map.put(lyricWord, al);
        }
    }

    public void displayWords()
    {
        if(map==null||map.size()==0)
        {
            System.out.println("");
            System.exit(0);
        }
        ArrayList<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<Map.Entry<String, ArrayList<Integer>>>(map.entrySet());
        //
        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
            public int compare(Map.Entry<String, ArrayList<Integer>> o1, Map.Entry<String, ArrayList<Integer>> o2) {
                //return (o2.getValue() - o1.getValue());
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        String str = String.format("%-12s%s", "Word","Word Position(s)");
        System.out.println(str);
        System.out.println("===========================");

        for(Map.Entry<String,ArrayList<Integer>> e:list)
        {
            String word =String.format("%-12s", e.getKey());
            String indexes =e.getValue().toString().replace("[", "").replace("]", "");
            System.out.println(word+indexes);

        }


    }

    public void writeLyrics(File file)
    {
        String words[] =new String[index];
        for(int i=0;i<words.length;i++)
            words[i] = "";

        for(Map.Entry<String,ArrayList<Integer>> e:map.entrySet())
        {
            for(Integer i:e.getValue())
            {
                int po = Math.abs(i.intValue());
                words[po]=e.getKey();
                if(i.intValue()<0)
                    words[po]+="\r\n";
                else
                    words[po]+=" ";
            }
        }

        FileWriter fw=null;
        try {
            fw = new FileWriter(file);
            for(int i=1;i<words.length;i++)
            {
                fw.write(words[i]);
            }
            fw.flush();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        finally
        {
            try {
                fw.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public int count()
    {
        int num=0;
        for(Map.Entry<String,ArrayList<Integer>> e:map.entrySet())
        {
            if(e.getValue().size()==1)
                num++;
        }

        return num;
    }
    public String mostFrequentWord()
    {
        String maxStr = "";
        int max=0;
        for(Map.Entry<String,ArrayList<Integer>> e:map.entrySet())
        {
            if(e.getValue().size()>max)
            {
                maxStr = e.getKey();
                max = e.getValue().size();
            }
        }

        for(Map.Entry<String,ArrayList<Integer>> e:map.entrySet())
        {
            if(e.getValue().size()==max&&e.getKey().equals(maxStr)==false)
                maxStr+=","+e.getKey();
        }


        return maxStr;
    }
}

class MyJson
{
    public static ArrayList<Vehicle> readAndGetVehicles(File file)
    {
        ArrayList<Vehicle> alVehicle = new ArrayList<Vehicle>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                line = line.trim();
                if(line.length()==0)
                {
                    continue;
                }
                String args[] = line.split("~");
                Vehicle ve = new Vehicle(args);
                alVehicle.add(ve);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return alVehicle;
    }
    public static void writeToFile(String inputToWrite, String filePath)
    {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            fw.write(inputToWrite);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static String getJsonString(ArrayList<Vehicle> vehicles)
    {
        StringBuilder json=new StringBuilder();
        json.append("{\r\n\"gmps-camino\":\r\n[\r\n");
        int index = 1;
        for(Vehicle ve : vehicles)
        {
            json.append("{\r\n");
            json.append("\"id\":"+"\""+ve.id+"\"\r\n");
            json.append("\"category\":"+"\""+ve.category.toString()+"\"\r\n");
            json.append("\"year\":"+"\""+ve.year+"\"\r\n");
            json.append("\"make\":"+"\""+ve.make+"\"\r\n");
            json.append("\"model\":"+"\""+ve.model+"\"\r\n");
            json.append("\"trim\":"+"\""+ve.trim+"\"\r\n");
            json.append("\"type\":"+"\""+ve.type+"\"\r\n");
            json.append("\"price\":"+"\""+ve.price+"\"\r\n");
            json.append("\"photo\":"+"\""+ve.photo.toString()+"\"\r\n");
            json.append("}");
            if(index<vehicles.size())
                json.append(",");
            json.append("\r\n");
            index++;
        }
        json.append("]\r\n}\r\n");
        return json.toString();
    }

    public static void writeToOriginalFile(String jsonFilePah,String originalFilePath)
    {
        FileInputStream fis=null;
        FileWriter fw =null;
        BufferedWriter bw = null;
        try {
            fis = new FileInputStream(jsonFilePah);
            fw =  new FileWriter(originalFilePath);
            int len = fis.available();
            byte[] bytes = new byte[len];
            fis.read(bytes);
            String str = new String(bytes);
            String strs[] = str.split("[\\[\\]{},]");
            for(int i=0;i<strs.length;i++)
            {
                if(strs[i].trim().startsWith("\"id\":"))
                {
                    String lis[] = strs[i].split("\r\n");
                    String line="";
                    int j =0;
                    for(String s : lis)
                    {
                        if(s.trim().length()>0)
                        {
                            String value = s.substring(s.indexOf(":")).replace("\"", "");
                            line+=value+"~";
                            if(j==0)
                            {
                                line+="gmps-camino~";
                            }
                            j++;
                        }

                    }
                    line = line.substring(0, line.lastIndexOf('~'))+"\r\n";
                    fw.write(line);

                }
            }
            fw.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                fw.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


    }
}
class Vehicle{

    String id;
    String webId;
    Category category;
    int year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr){
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Integer.parseInt(arr[3].trim());
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8].trim());
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

enum Category{
    NEW , USED, CERTIFIED;

    public static Category getCategory(String cat){
        switch (cat){
            case "used": return USED;
            case "new": return NEW;
            case "certified": return CERTIFIED;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this){
            case NEW: return "NEW";
            case USED: return "USED";
            case CERTIFIED: return "CERTIFIED";
            default: throw new IllegalArgumentException();
        }
    }
}

