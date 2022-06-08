import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Client {
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    //declare components
    private final JLabel heading=new JLabel("Client Area");
    private final JTextArea messageArea=new JTextArea();
    private final JTextField messageInput=new JTextField();
    private final Font font=new Font("Roboto",Font.PLAIN,20);

    //class constructor
    public Client() {
        try {
            System.out.println("Sending request to Server");
            socket = new Socket("127.0.0.1", 7778);
            System.out.println("Connection done");
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            CreateGUI();
            handleEvants();
            //startWriting();

        } catch (Exception e) {

        }
    }

    private void handleEvants() {
    }

    private void CreateGUI() {
        //gui
        this.setTitle("Client Messager[END]");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //startreading methodgit status
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
    public void startWriting(){
        //tread write
        Runnable r2= () ->
        {
            System.out.println("writer started");
            while(true)
            {
                try
                {
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }

        };
        new Thread(r2).start();

    }

    public static void main(String[] args){
        System.out.println("this is Client...");
        new Client();

    }


}


