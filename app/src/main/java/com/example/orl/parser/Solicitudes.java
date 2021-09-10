package com.example.orl.parser;

import java.io.StringReader;

public class Solicitudes {


    public void parse(String texto){
        try{
            StringReader reader = new StringReader(texto);
            CommunicationLexic lexico = new CommunicationLexic(reader);
            CommunicationSyntax syntax = new CommunicationSyntax(lexico);
            //Solo ejecutar porque por los metodos ya se agregan
            syntax.parse();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
