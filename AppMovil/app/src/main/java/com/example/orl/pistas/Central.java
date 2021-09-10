package com.example.orl.pistas;

import java.util.ArrayList;

public class Central {
    private static PlayList playList = new PlayList();
    private static ArrayList<PistaReproduccion> pistas = new ArrayList<>();

    public static String getListDescription(String nombreLista){
        ArrayList<ListaReproduccion> listas = playList.getlistas();
        StringBuilder string = new StringBuilder();
        for(int index=0; index<listas.size(); index++){
            if(listas.get(index).getNombre().equals(nombreLista)){
                string.append("LISTA  :").append(nombreLista).append("\n");
                if(listas.get(index).getRandom()){
                    string.append("ALEATORIA  :").append("Si").append("\n");
                }

                if(listas.get(index).getCircular()){
                    string.append("CIRCULAR  :").append("Si").append("\n");
                }
                string.append("Cantidad de pistas: ").append(listas.get(index).getNumberPistas()).append("\n");

                ArrayList<String> pistas = listas.get(index).getPistas();
                ArrayList<Integer> duraciones = listas.get(index).getDuracionPistas();
                if(pistas.size()==0){
                    string.append("\t\t\tSin pistas disponibles\n");
                }else{
                    for(int indexA=0; indexA<pistas.size(); indexA++){
                        string.append("\t\tPista : ").append(pistas.get(indexA)).append(" Duracion :").
                                append(duraciones.get(indexA)/1000).append("s ");
                        string.append(lookPista(pistas.get(indexA))).append("\n");
                    }
                }

            }
        }
        return string.toString();
    }

    public static String lookPista(String nombrePista){
        String string = "No esta en el sistema";
        for(int index=0; index<pistas.size(); index++){
            if(nombrePista.equalsIgnoreCase(pistas.get(index).getName())){
                if(pistas.get(index).getSonidos().size()==0){
                    string = "Si esta en el sistema, pero sin sonidos declarados";
                }else{
                    string = "Si esta en el sistema, canales: "+pistas.get(index).canalesUsados();
                }
            }
        }
        return string;
    }

    //Agrega todas las listas del servidor
    public static void addListas(ArrayList<ListaReproduccion> listas){
        System.out.println("Entrando a addListas");
        playList.agregarListas(listas);
    }

    //Una sola lista con los datos de las pistas
    public static void addListaReproduccion(ListaReproduccion lista){
        //Handle this
        playList.agregarLista(lista);
    }

    public static void addListaPistas(ArrayList<PistaReproduccion> newPistas){
        System.out.println("Entrando a addListaPistas");
        for(int index=0; index<newPistas.size(); index++){
            boolean flag=false;
            for(int index2=0; index2<pistas.size(); index2++){
                if(pistas.get(index2).getName().equals(newPistas.get(index).getName())){
                    pistas.get(index2).setDuracion(newPistas.get(index).getDuracion());
                    flag=true;
                }
            }
            if(!flag){
                pistas.add(newPistas.get(index));
            }
        }
    }

    /**
     * Le colocamos las reproducciones
     * @param pista
     */
    public static void addPistaReproduccion(PistaReproduccion pista){
        boolean flag=false;
        System.out.println("Entrando a addPistaReproduccion");
        for(int index=0; index<pistas.size(); index++){
            if(pistas.get(index).getName().equals(pista.getName())){
                pistas.get(index).setSonidos(pista.getSonidos());
            }
        }

        if(!flag){
            pistas.add(pista);
        }
    }

    public static PlayList getPlayList(){
        return playList;
    }


    public static String getSolicitud(String text) {
        StringBuilder string = new StringBuilder();
        string.append("<solicitud>\n");
        string.append("\t<tipo>Lista</tipo>\n");
        string.append("\t<nombre>\""+text+"\" </nombre>\n");
        string.append("</solicitud>");
        return string.toString();
    }
}
