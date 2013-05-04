/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package gui.basis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.RuntimeErrorException;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class NetworkAdapter extends Thread{
    
    private boolean active;
    private final Lock _mutex;
    
    private boolean toListen;
    private boolean toConnect;

    private ServerSocket serverHandler;
    private Socket handler;
    
    private PrintWriter handlerWriter;
    private BufferedReader handlerReader;
    
    private String host;
    private int port;
    
    public NetworkAdapter(){
        this.active = false;
        this._mutex = new ReentrantLock(true);
        this.toListen = false;
        this.toConnect = false;
        this.serverHandler = null;
        this.handler = null;
        this.host = "localhost";
        this.port = 5678;
    }
    
    public void setHost(String host){
        this.host = host;
    }
    
    public void setPort(int port){
        this.port = port;
    }
    
    public boolean isActive(){ 
        boolean tmp;
        
        this._mutex.lock();
        tmp = this.active;
        this._mutex.unlock();
        
        return tmp;
    }
    
    public boolean isToListen(){
        return this.toListen;
    }
    
    public boolean isToConnect(){
        return this.toConnect;
    }
    
    /**
     *
     * @param port
     */
    public void listen(int port) throws RuntimeException{
        this.port = port;
        
        this.toListen = true;
    }
    
    private void listen() throws RuntimeException{
        try{
            this.clean();
            
            System.out.println("Processing listen");
            
            this.serverHandler = new ServerSocket();
            this.serverHandler.setReuseAddress(true);
            this.serverHandler.bind(new InetSocketAddress(this.port));
            
            this.handler = serverHandler.accept();
            //this.handler.setReuseAddress(true);
            
            this.serverHandler.close();
            
            this.handlerWriter = new PrintWriter(this.handler.getOutputStream(), true);
            this.handlerReader = new BufferedReader(new InputStreamReader(this.handler.getInputStream()));                
         
            this.active = true;
            this.toListen = false;
        }
        catch(IOException err){
            this.active = false;
            this.toListen = false;
            //throw new RuntimeException("NetworkAdapter - listen");
            throw new RuntimeException(err.getMessage());
        }
    }
    
    public void connect(String host, int port) throws RuntimeException{
        this.host = host;
        this.port = port;
        
        this.toConnect = true;
    }
    
    private void connect() throws RuntimeException{
        try{
            this.clean();
            
            System.out.println("Processing connect");
            
            this.handler = new Socket(this.host, this.port);
            
            this.handlerWriter = new PrintWriter(this.handler.getOutputStream(), true);
            this.handlerReader = new BufferedReader(new InputStreamReader(this.handler.getInputStream())); 
            
            this.active = true;
            this.toConnect = false;
        }
        catch(IOException err){
            this.active = false;
            this.toConnect = false;
            throw new RuntimeException("NetworkAdapter - connect");
        }
    }
    
    @Override
    public void run(){
        while(true){
            try {
                System.out.println("Cycle");
                this._mutex.lock();
                
                if(this.toConnect)
                    this.connect();
                
                if(this.toListen)
                    this.listen();  
                
                

            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
            }
            
            try {  
                
                sleep(1000);
                this._mutex.unlock();
            } catch (InterruptedException ex) {
                Logger.getLogger(NetworkAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void clean(){
        System.out.println("Cleaning");

        try {
            if(this.serverHandler != null)
                this.serverHandler.close();
            
        } catch (IOException ex) {
            Logger.getLogger(NetworkAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(this.handler != null)
                this.handler.close();
            
        } catch (IOException ex) {
            Logger.getLogger(NetworkAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.active = false;
        this.toConnect = false;
        this.toListen = false;
    }
    
    
}
