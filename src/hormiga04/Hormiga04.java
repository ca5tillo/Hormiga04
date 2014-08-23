package hormiga04;
import java.util.ArrayList;
import java.util.Random;
    


public class Hormiga04 {
    Random numeroAleatorio = new Random();
    double Q=10.0,P=0.6;// alfa y beta se colocaron directamente en elpaso 5 son los exponentes de echo beta al ser 1 no se coloco 
    int cantidad_nodos=6;//todos los nodo tienen q tener una relacion 
    int IDnodometa=0;
    int inicio=2;//el punto en el cual iniciara la ultima hormiga 
    // las demas hormgas fueron lanzadas al azar 
    int numerodehormigasenlameta=0;
    ArrayList<Nodo> nodos = new ArrayList<Nodo>();
    ArrayList<Hormiga> hormigas = new ArrayList<Hormiga>();    
    
class CaminosAelegir{
    int IDcamino=0;
    int IDnodoInicio=0;
    int IDnodosiguiente=0;
    double feromonaqueseDejara=0.0;
    int pasos=0;
    double probabilidad=0.0;
    double feromona=0.0;
}
class Nodo {
    int IDnodo=0;   
    ArrayList<CaminosAelegir> caminosAelegir = new ArrayList<CaminosAelegir>();

}  
class Hormiga {
    int IDhormiga;
    boolean estoyenelobjetivo=false;
    int pasos_del_tourH=0;
    ArrayList<CaminosAelegir> listaTabu = new ArrayList<CaminosAelegir>();
}    
class Mapa {
    public Mapa(){
        inicializar();
        Cmapa3();
    }
    
    private void inicializar(){
       for (int x =0; x<cantidad_nodos ;x++){
            Nodo insertCasilla = new Nodo();  
            insertCasilla.IDnodo=x;
            nodos.add(insertCasilla);
        }  
    }
    private void construir_mapa(int IDnodo,int IDsiguiente,int Pasos){
        CaminosAelegir camino =new CaminosAelegir();
        camino.IDnodoInicio=IDnodo;
        camino.IDnodosiguiente=IDsiguiente;
        camino.pasos=Pasos;
//        camino.IDcamino=IDcamin;
        camino.IDcamino=nodos.get(IDnodo).caminosAelegir.size();
        camino.feromonaqueseDejara=Q/Pasos;
        nodos.get(IDnodo).caminosAelegir.add(camino);
    }  
    private void Cmapa(){//3 nodos y el ID meta es 2
        construir_mapa(0,1,20);
        construir_mapa(0,1,10);
        construir_mapa(1,2,30);
        construir_mapa(1,2,20);
    }
    private void Cmapa2(){//6 nodos y el ID meta es 5
        construir_mapa(0,1,30);
        construir_mapa(0,1,20);
        construir_mapa(0,2,10);
        construir_mapa(0,2,40);
        construir_mapa(1,3,10);
        construir_mapa(1,3,20);
        construir_mapa(1,2,10);
        construir_mapa(2,4,30);
        construir_mapa(2,4,35);
        construir_mapa(3,4,20);
        construir_mapa(3,5,30);
        construir_mapa(4,1,20);// se cicla
        construir_mapa(4,5,50);
        construir_mapa(2,3,20);
        construir_mapa(3,2,20);
    }    
    private void Cmapa3(){//7 nodos y el ID meta es 5
        construir_mapa(0,1,30);
        construir_mapa(0,1,20);
        construir_mapa(0,2,10);
        construir_mapa(0,2,40);
        construir_mapa(1,3,10);
        construir_mapa(1,3,20);
        construir_mapa(1,2,10);
        construir_mapa(2,4,30);
        construir_mapa(2,4,35);
        construir_mapa(3,4,20);
        construir_mapa(3,5,30);
        construir_mapa(4,1,20);
        construir_mapa(4,5,50);
        construir_mapa(2,3,20);
        construir_mapa(3,2,20);
        construir_mapa(5,4,250);
        construir_mapa(1,0,20);
    }        
      
}
class ImpMapa{
    int filas =15;
    int columnas=20;
    String a[][]=new String [filas][columnas] ;
    ImpMapa(){
        ini();
        impcaminos();
        impcamino(hormigas.get(hormigas.size()-1));
        imp();
    }
    private void ini(){
        for(int i=0;i<filas;i++)
            for(int j =0;j<columnas;j++)
                a[i][j]=" ";
        a[12][0]="0";
        a[6][6]="1";
        a[12][6]="2";
        a[6][12]="3";
        a[12][12]="4";
        a[9][15]="5";

    }//inizializa y marca los nodos 
    private void impcamino(Hormiga hormiga){
         String b="o";
        for(int i =0;i<hormiga.listaTabu.size();i++){
            int inicio=hormiga.listaTabu.get(i).IDnodoInicio;
            int siguiente=hormiga.listaTabu.get(i).IDnodosiguiente;
            if(inicio==0 && siguiente==1||inicio==1 && siguiente==0){
                a[11][1]=b;a[10][2]=b;a[9][3]=b;a[8][4]=b;a[7][5]=b;
            }
            if(inicio==0 && siguiente==2){
                a[12][1]=b;a[12][2]=b;a[12][3]=b;a[12][4]=b;a[12][5]=b;
            }    
            if(inicio==1 && siguiente==3){
                a[6][7]=b;a[6][8]=b;a[6][9]=b;a[6][10]=b;a[6][11]=b;
            } 
            if(inicio==1 && siguiente==2){
                a[7][6]=b;a[8][6]=b;a[9][6]=b;a[10][6]=b;a[11][6]=b;
            }  
            if(inicio==2 && siguiente==3||inicio==3 && siguiente==2){
                a[11][7]=b;a[10][8]=b;a[9][9]=b;a[8][10]=b;a[7][11]=b;
            } 
            if(inicio==2 && siguiente==4){
                a[12][7]=b;a[12][8]=b;a[12][9]=b;a[12][10]=b;a[12][11]=b;
            }  
            if(inicio==3 && siguiente==5){
                a[7][13]=b;a[8][14]=b;
            } 
            if(inicio==3 && siguiente==4){
                a[7][12]=b;a[8][12]=b;a[9][12]=b;a[10][12]=b;a[11][12]=b;
            } 
            if(inicio==4 && siguiente==5||inicio==5 && siguiente==4){
                a[11][13]=b;a[10][14]=b;
            }      
            if(inicio==4 && siguiente==1){
               a[7][7]=b;a[8][8]=b;a[9][9]=b;a[10][10]=b; a[11][11]=b;
            }             
        }
    }//marca el camino mas corto 
    private void impcaminos(){
        String b="*";
                a[11][1]=b;a[10][2]=b;a[9][3]=b;a[8][4]=b;a[7][5]=b;
                a[12][1]=b;a[12][2]=b;a[12][3]=b;a[12][4]=b;a[12][5]=b;
                a[6][7]=b;a[6][8]=b;a[6][9]=b;a[6][10]=b;a[6][11]=b;
                a[7][6]=b;a[8][6]=b;a[9][6]=b;a[10][6]=b;a[11][6]=b;
                a[11][7]=b;a[10][8]=b;a[9][9]=b;a[8][10]=b;a[7][11]=b;
                a[12][7]=b;a[12][8]=b;a[12][9]=b;a[12][10]=b;a[12][11]=b;
                a[7][13]=b;a[8][14]=b;
                a[7][12]=b;a[8][12]=b;a[9][12]=b;a[10][12]=b;a[11][12]=b;
                a[11][13]=b;a[10][14]=b;
                a[7][7]=b;a[8][8]=b;a[9][9]=b;a[10][10]=b; a[11][11]=b;
        
    }//marca todos los caminos 
    private void imp(){
        for(int i=0;i<filas;i++){
            for(int j =0;j<columnas;j++){
                System.out.print("["+a[i][j]+"]");
            }    
            System.out.println();
        }
    }//imprime el mapa 
}
class Imp{
    public void impNodos(){
        System.out.println("\nDATOS DE LOS NODOS\n");
        int tam=nodos.size();
        for(int i =0; i<tam;i++){
            Nodo nod=nodos.get(i);
            System.out.println("IDnodo= "+nod.IDnodo
                    +" Numero de caminos a elegir "+nod.caminosAelegir.size());
            for(int j=0;j<nod.caminosAelegir.size();j++){
                System.out.println("    IDcamino = "+nod.caminosAelegir.get(j).IDcamino
                        +" IDnodoInicio = "+nod.caminosAelegir.get(j).IDnodoInicio
                        +" IDnodosiguiente = "+nod.caminosAelegir.get(j).IDnodosiguiente
                        +" Pasos = "+nod.caminosAelegir.get(j).pasos
                        +" FeromonaqueseDejara = "+nod.caminosAelegir.get(j).feromonaqueseDejara
                        +" Feromona = "+nod.caminosAelegir.get(j).feromona
                        +" Probabilidad = "+nod.caminosAelegir.get(j).probabilidad
                        );
            }
        }
    }
    public void impHormigas(){
        System.out.println("\nDATOS DE LAS HORMIGAS\n");
        for(int i =0; i<hormigas.size();i++){
            Hormiga hormiga=hormigas.get(i);
            System.out.println("IDhormiga = "+hormiga.IDhormiga
                    +" tamaño lista tabu = "+hormiga.listaTabu.size()
                    +" estoyenelobjetivo "+hormiga.estoyenelobjetivo);
            
            for(int j=0;j<hormiga.listaTabu.size();j++){
                System.out.println("    IDcamino = "+hormiga.listaTabu.get(j).IDcamino
                        +" IDnodoInicio = "+hormiga.listaTabu.get(j).IDnodoInicio
                        +" IDnodosiguiente = "+hormiga.listaTabu.get(j).IDnodosiguiente
                        +" Pasos = "+hormiga.listaTabu.get(j).pasos
                        +" FeromonaqueseDejara = "+hormiga.listaTabu.get(j).feromonaqueseDejara
                        +" Feromona = "+hormiga.listaTabu.get(j).feromona
                        +" Probabilidad = "+hormiga.listaTabu.get(j).probabilidad
                        );
            }
        }
    }
    public void impHormigas_simple(){
        System.out.println("\nDATOS DE LAS HORMIGAS\n");
        for(int i =0; i<hormigas.size();i++){
            Hormiga hormiga=hormigas.get(i);
            System.out.println("IDhormiga = "+hormiga.IDhormiga
                    +" tamaño lista tabu = "+hormiga.listaTabu.size());
            for(int j=0;j<hormiga.listaTabu.size();j++){
                System.out.println(   
                        "    IDnodoInicio = "+hormiga.listaTabu.get(j).IDnodoInicio
                        +" IDnodosiguiente = "+hormiga.listaTabu.get(j).IDnodosiguiente
                        +" Pasos = "+hormiga.listaTabu.get(j).pasos
                        );
            }
            System.out.println("    total_de_pasos = "+hormigas.get(i).pasos_del_tourH);
        }
    }    
    public void impNodos_simple(){
        System.out.println("\nDATOS DE LOS NODOS\n");
        int tam=nodos.size();
        for(int i =0; i<tam;i++){
            Nodo nod=nodos.get(i);
            System.out.println("\nIDnodo= "+nod.IDnodo
                    +" Numero de caminos a elegir "+nod.caminosAelegir.size());
            for(int j=0;j<nod.caminosAelegir.size();j++){
                System.out.println(
                        "        IDnodosiguiente = "+nod.caminosAelegir.get(j).IDnodosiguiente
                        +" Pasos = "+nod.caminosAelegir.get(j).pasos
                        +" Probabilidad = "+nod.caminosAelegir.get(j).probabilidad
                        );
            }
        }
    }    
}
    public void crearHormigas(boolean limpiarhormigero,int numeroDhormigas, int IDnodosemilla){
        if (limpiarhormigero)limpiarHormiguero();
        for (int i =0;i<numeroDhormigas;i++){
            Hormiga hormiga= new Hormiga();
            hormiga.IDhormiga=hormigas.size();
            hormigas.add(hormiga);
        }
        buscar(hormigas.size()-1,IDnodosemilla);
    }
    public void buscar(int IDhormiga,int IDnodo){
        if (numerodehormigasenlameta<hormigas.size()){//verificaque todas las hormigas llegaran a la meta 
            if (hormigas.get(IDhormiga).estoyenelobjetivo==false){//verifica que la hormiga enviada no sea una que ya realizo el recorrido 
            
                if(IDnodo==IDnodometa ){//cuando se llega a la meta 
                    if(hormigas.get(IDhormiga).listaTabu.isEmpty()){//verifica que sea una hormiga que tiene 
                        //un recorrido y NO que solo cayo en el nodo fina como su primer nodo. En este caso se rechaza a la hormiga
                        buscar(numeroAleatorio.nextInt(hormigas.size()),numeroAleatorio.nextInt(nodos.size()));
                    }else{
                        numerodehormigasenlameta+=1;
                        hormigas.get(IDhormiga).estoyenelobjetivo=true;
                        buscar(numeroAleatorio.nextInt(hormigas.size()),numeroAleatorio.nextInt(nodos.size()));
                        // elige una hormiga al aza y un nodo al azar
                    }
                }else{
                    //elegir camino 
                    
                    int numcaminos=nodos.get(IDnodo).caminosAelegir.size();// ES la cantidad de caminos que tiene este nodo
                    CaminosAelegir camino = new CaminosAelegir();
                    double proba=0.0;
                    
                    for(int i=0;i<numcaminos;i++){//para buscar el camino de mayor probabilidad
                        if (proba < nodos.get(IDnodo).caminosAelegir.get(i).probabilidad){
                            proba=nodos.get(IDnodo).caminosAelegir.get(i).probabilidad;
                            camino=nodos.get(IDnodo).caminosAelegir.get(i);
                        }
                    }

                    if (proba==0.0){//si ninguno es el mayor probabilidad elige al azar
                        camino =nodos.get(IDnodo).caminosAelegir.get(numeroAleatorio.nextInt(numcaminos));//elige camino aleatorio
                    }
                    hormigas.get(IDhormiga).listaTabu.add(camino);//agrego este camino a la lista tabu de la hormiga
//                    proba=0.0;
                    
//                    System.out.println("Se elige el camino "+camino.IDcamino+" prababilidad "+camino.probabilidad);
                    buscar(IDhormiga,camino.IDnodosiguiente);
                }
            }else{ //verifica que la hormiga enviada no sea una que ya realizo el recorrido.Si ya se relizo se elige otra hormiga y otro nodo
                buscar(numeroAleatorio.nextInt(hormigas.size()),numeroAleatorio.nextInt(nodos.size()));
            }    
        }else{ // Cuando todas las hormigas an realizado el Tour. Se hace la actualizacion        
            actualizacion();//realiza los pasos 3,4,5 al finalizar la iteracion
        }
    }//al pareces solo funciona si entre los nodos no regresan
    public void paso3(){//actualizacion de la cantidad de feromonas en aristas. Solo por dende paso la hormiga
        // feromona = Feromona anterior + (cantidad de feromona del camino(la del paso 2) * p)
        for(int i=0;i<hormigas.size();i++){
            int pasos_del_tour=0;
            for(int j=0;j<hormigas.get(i).listaTabu.size();j++)pasos_del_tour+=hormigas.get(i).listaTabu.get(j).pasos;          
            hormigas.get(i).pasos_del_tourH=pasos_del_tour;
            for(int j=0;j<hormigas.get(i).listaTabu.size();j++){
                double feromona=hormigas.get(i).listaTabu.get(j).feromona;
                double feromonaqueseDejara=Q/pasos_del_tour;
                double feromonanueva=(feromona+(feromonaqueseDejara*P));
                hormigas.get(i).listaTabu.get(j).feromona=feromonanueva;
            }
        }
    }//actua sobre los datos de las hormigas
    public void paso4(){//EVAPORACION  actua sobre todos los nodos
        // feromona = Feromona anterior * ( 1 - P )
        for(int i=0;i<nodos.size();i++){
            for(int j=0;j<nodos.get(i).caminosAelegir.size();j++){
                double feromona=nodos.get(i).caminosAelegir.get(j).feromona;
                double feromonanueva=(feromona*(1-P));
                nodos.get(i).caminosAelegir.get(j).feromona=feromonanueva;
            }
        }     
    }//EVAPORACION sobre todos los nodos
    public void paso5(){//actua sobre los nodos
        double numerador=0.0;//arriba
        double denominador=0.0;  
        double divicion;
        
        for (int i=0;i<nodos.size();i++){
            for(int j =0; j<nodos.get(i).caminosAelegir.size();j++){
                numerador=0.0;
                denominador=0.0;
                double feromona=nodos.get(i).caminosAelegir.get(j).feromona;
                double feromonaporqcdeja=nodos.get(i).caminosAelegir.get(j).feromonaqueseDejara;
                numerador = elevar(feromona,3)*feromonaporqcdeja;
//                System.out.println("numerador = "+numerador);
                
                for(int k=0;k<nodos.get(i).caminosAelegir.size();k++){
                    double feromona2=nodos.get(i).caminosAelegir.get(k).feromona;
                    double feromonaporqcdeja2=nodos.get(i).caminosAelegir.get(k).feromonaqueseDejara;  
                    denominador += elevar(feromona2,3)*feromonaporqcdeja2;
//                    System.out.println("denominador01 = "+denominador);
                }
                if (denominador!=0.0){
                    divicion=numerador/denominador;
                    nodos.get(i).caminosAelegir.get(j).probabilidad=divicion;
                }else{
//                    System.out.println("  denominador es 0  = ");
                }
            }
        }
        
    }//probabilidad de los caminos
    public void verificarprobabilidad (){
        double verificarP=0.0;
        for(int i =0;i<nodos.size();i++){
            verificarP=0.0;
            for(int j=0;j<nodos.get(i).caminosAelegir.size();j++){
                verificarP += nodos.get(i).caminosAelegir.get(j).probabilidad;
            }
            System.out.println("La suma de la probabilidad de los caminos del nodo  "+nodos.get(i).IDnodo+" es ="+verificarP);
        }
    }//la suma de la probabilidad de cada camino debe se ser 1 
    public void actualizacion(){
        paso3();
        paso4();
        paso5();
    }//ejecuta los paso 3,4,5
    public double elevar(double i,int base){
        double r=1;
        for(int k=0;k<base;k++){
            r*=i;
        }
        return r;
    }
    public void limpiarHormiguero(){
        hormigas.clear();
        numerodehormigasenlameta=0;
    }
    public void _default(){
        Mapa map=new Mapa();
        Imp imp=new Imp();
        for(int i =0;i<500;i++) {
            if(i==0)crearHormigas(false,200,0);
            crearHormigas(true,100+numeroAleatorio.nextInt(200),0);
        }
        crearHormigas(true,1,inicio);
        imp.impHormigas_simple();
        ImpMapa a = new ImpMapa();
//        imp.impNodos_simple();
//        verificarprobabilidad ();
    }
    public static void main(String[] args) {
        Hormiga04 a =new Hormiga04();
        a._default();
    }
}
