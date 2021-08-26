public void report_error(String message, Object info) { 
    if (info instanceof String){
	Symbol s = cur_token;
	errors++;       
        if(message.equalsIgnoreCase(".")){
                message = "Se esperaba alguna forma como "+expectedTokens;
        }
        System.err.println("  "+ errors + "==> " + info + " en "+tokenError+": "+ message + " [linea: "+lineError+" columna: "+columnError+"]");
    }
    else {
    	StringBuffer m = new StringBuffer("Error ");
    	if (info instanceof java_cup.runtime.Symbol) 
     	   m.append( "("+info.toString()+")" );     
    	m.append(" : "+message);   
    	System.err.println(m);
    }
}

public void report_error_final(String message, Object info){
    if (info instanceof String){
	Symbol s = cur_token;
	errors++;       
        
        if(message.equalsIgnoreCase(".")){
                message = "Se esperaba alguna forma como "+expectedTokens;
        }
        System.err.println("  "+ errors + "==> " + info + ": "+ message);
    }
    else {
    	StringBuffer m = new StringBuffer("Error ");
    	if (info instanceof java_cup.runtime.Symbol) 
     	   m.append( "("+info.toString()+")" );     
    	m.append(" : "+message);   
    	System.err.println(m);
    }
}

@Override
	public void syntax_error(Symbol symbol){
	    int line = symbol.left;
	    int column = symbol.right;
	    String token = (String) cur_token.value;
	    //We get the production
            tokenError = token;
            lineError = line;
            columnError = column;
            expectedTokens = "";
            for(int index=0; index<expected_token_ids().size() ; index++){
                //We traduce the token to a form that the user might understand
                expectedTokens += symbl_name_from_id(expected_token_ids().get(index));
				expectedTokens += ", ";
            }
	}

@Override
	public void unrecovered_syntax_error(Symbol symbol){
                report_error_final("Estado Ilegal de Expresion", "Error Sintactico");
	}