
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Client {
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    //class constructor
    public Client() {
        try {
            System.out.println("Sending request to Server");
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("Connection done");
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();

        } catch (Exception e) {

        }
    }
    public void startReading(){
        //tread read
        Runnable r1=()->
        {
            System.out.println("reader started..");
            while(true)
            {
                try{
                    String msg=br.readLine();
                    if(msg.equals("exit"))
                    {
                        System.out.println("Server terminated the chat");
                        break;
                    }
                    System.out.println("Server: "+msg);

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

        };
        new Thread(r1).start();


    }

    public static void main(String[] args){
        System.out.println("this is Client...");
        new Client();

    }


}


