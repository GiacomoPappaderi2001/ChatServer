class ClientHandler implements Runnable{
    Scanner scn = new Scanner(System.in);
    public String name;
    public final BufferedReader dis;
    public final DataOutputStream dos;
    public Socket s;
    public boolean login;
     

    public ClientHandler(Socket s, String name, BufferedReader dis, DataOutputStream dos){
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.login=true;
    }
   
    @Override
    public void run(){
        String ricevuta;
       
        while (true){
            try{
                ricevuta = dis.readLine();
                System.out.println(ricevuta);
                if(ricevuta.equals("Logout")){
                    this.login=false;
                    this.s.close();
                    break;
                }
               
                    StringTokenizer st = new StringTokenizer(ricevuta, "#");
                    String MsgToSend = st.nextToken();
                    String recipient = st.nextToken();
               
                for (ClientHandler mc : Server.lc){
                    if (mc.name.equals(recipient) && mc.login==true){
                        mc.dos.writeUTF(this.name+ " : " +MsgToSend);
                        break;
                    } else if(mc.login==false){
                        System.out.println("Il client destinatario è uscito dalla chat");
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        try{

            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
