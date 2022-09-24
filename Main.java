import java.io.*;

public class Project {
    public static void main(String[] args)
    {
        try(FileReader reader = new FileReader("/Users/Sanya/PP1/src/input.txt"))
        {
            // посимвольно
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        String text = "Eto Progress!"; // строка для записи
        try(FileOutputStream fos=new FileOutputStream("/Users/Sanya/PP1/src/output.txt"))
        {
            // перевод строки в байты
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("The file has been written");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}