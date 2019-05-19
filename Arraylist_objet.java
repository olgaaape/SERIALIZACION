package CRUD;


import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Arraylist_objet extends ModeloArrayList
{
    static final String nombrefichero = "productos.csv";

    public Arraylist_objet()
    {
        super();
        cargarDesdeFichero();
        
    }

    public void guardarenfichero()
    {
        File fproductos = new File (nombrefichero);
        try{
            FileOutputStream fos= new FileOutputStream(fproductos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Producto pro:lista){
                oos.writeObject(pro);
            }
            oos.close();
            fos.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private void cargarDesdeFichero() {
        File fproductos =new File (nombrefichero);
        if(!fproductos.exists()){
            return;
        }
        try{
            FileInputStream fin = new FileInputStream(fproductos);
            ObjectInputStream oin = new ObjectInputStream(fin);
            try {
              Producto pro = (Producto) oin.readObject();
              while ( true ){
                lista.add(pro); 
                pro = (Producto) oin.readObject();  
                }
            }
            catch (IOException ex){} 
            oin.close();
            fin.close();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean insertarProducto (Producto p){
        boolean resu = super.insertarProducto(p);
        if (resu){
            guardarenfichero();
        }
        return resu;
    }
    
    public boolean borrarProducto (int codigo){
        boolean resu = super.borrarProducto(codigo);
        if (resu){
            guardarenfichero();
        }
        return resu;
    }
    
    public boolean modificarProducto (Producto nuevo){
        boolean resu = super.modificarProducto(nuevo);
        if (resu){
            guardarenfichero();
        }
        return resu;
    }
}
